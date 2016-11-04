package lanou.foodpartydemo.library;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;


import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;

import lanou.foodpartydemo.R;
import lanou.foodpartydemo.base.BaseFragment;
import lanou.foodpartydemo.bean.LibraryBean;
import lanou.foodpartydemo.tools.GsonRequest;
import lanou.foodpartydemo.tools.OnItemClickListener;
import lanou.foodpartydemo.tools.UrlValues;
import lanou.foodpartydemo.tools.VolleySingle;



/**
 * Created by dllo on 16/11/1.
 */
public class LibraryFragment extends BaseFragment  {
    Context context;
    private GridView gridViewCg;
    private GridView gridViewBr;
    private GridView gridViewRt;
    private ArrayList<LibraryBean.GroupBean.CategoriesBean> categories;
    private ArrayList<LibraryBean.GroupBean.CategoriesBean> brand;
    private ArrayList<LibraryBean.GroupBean.CategoriesBean> restaurant;
    private LibraryAdapter adapter;
    private LibraryAdapter adapter1;
    private LibraryAdapter adapter2;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    protected void addAdapter() {


    }

    @Override
    protected void initData() {


            GsonRequest<LibraryBean> gsonRequest = new GsonRequest<LibraryBean>(LibraryBean.class,
                    UrlValues.FOOD_LIBRARY, new Response.Listener<LibraryBean>() {



                @Override
                public void onResponse(LibraryBean response) {

                    categories = (ArrayList<LibraryBean.GroupBean.CategoriesBean>) response.getGroup().get(0).getCategories();
                    brand = (ArrayList<LibraryBean.GroupBean.CategoriesBean>) response.getGroup().get(1).getCategories();
                    restaurant = (ArrayList<LibraryBean.GroupBean.CategoriesBean>) response.getGroup().get(2).getCategories();
                    adapter = new LibraryAdapter(context);
                    adapter.setArrayList(categories);
                    gridViewCg.setAdapter(adapter);
                    adapter1 = new LibraryAdapter(context);
                    adapter1.setArrayList(brand);
                    gridViewBr.setAdapter(adapter1);
                    adapter2 = new LibraryAdapter(context);
                    adapter2.setArrayList(restaurant);
                    gridViewRt.setAdapter(adapter2);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            VolleySingle.getVolleySingle().addRequest(gsonRequest);

        gridViewBr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(),LibraryNextActivity.class);
                intent.putExtra("name",brand.get(i).getName());
                startActivity(intent);
            }
        });
        gridViewCg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(),LibraryNextActivity.class);
                intent.putExtra("name",categories.get(i).getName());
                startActivity(intent);
            }
        });
        gridViewRt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(),LibraryNextActivity.class);
                intent.putExtra("name",restaurant.get(i).getName());
                startActivity(intent);
            }
        });

        }



    @Override
    protected void addFragmentArrayList() {

    }




    @Override
    protected void initView() {
        gridViewCg = bindView(R.id.foodencylope_class);
        gridViewBr = bindView(R.id.foodencylope_brand);
        gridViewRt = bindView(R.id.foodencylope_restaurant);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_library;

    }


}
