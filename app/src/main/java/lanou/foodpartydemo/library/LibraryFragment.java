package lanou.foodpartydemo.library;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;


import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;

import cn.bmob.v3.b.I;
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
                intent.putExtra("value",brand.get(i).getId());
                intent.putExtra("group","brand");
                ArrayList<String> brBean = new ArrayList<String>();
                ArrayList<Integer> idBrBean = new ArrayList<Integer>();
                for (int j = 0; j < brand.get(i).getSub_category_count(); j++) {
                    String kind = brand.get(i).getSub_categories().get(j).getName();
                    Log.d("LibraryFragment", kind);
                    int id = brand.get(i).getSub_categories().get(j).getId();
                    brBean.add(kind);
                    idBrBean.add(id);
                }
                intent.putStringArrayListExtra("kind",brBean);
                intent.putIntegerArrayListExtra("id",idBrBean);
                startActivity(intent);
            }
        });
        gridViewCg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(),LibraryNextActivity.class);
                intent.putExtra("group","group");
                intent.putExtra("name",categories.get(i).getName());
                intent.putExtra("value",categories.get(i).getId());
                ArrayList<String> cgBean = new ArrayList<String>();
                ArrayList<Integer> idCgBean = new ArrayList<Integer>();
                for (int j = 0; j < categories.get(i).getSub_category_count(); j++) {
                   String kind =  categories.get(i).getSub_categories().get(j).getName();
                    int id = categories.get(i).getSub_categories().get(j).getId();
                    Log.d("LibraryFragment", kind);
                    cgBean.add(kind);
                    idCgBean.add(id);
                }
                intent.putIntegerArrayListExtra("id",idCgBean);
                intent.putStringArrayListExtra("kind",cgBean);
                startActivity(intent);
            }
        });
        gridViewRt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(),LibraryNextActivity.class);
                intent.putExtra("name",restaurant.get(i).getName());
                intent.putExtra("value",restaurant.get(i).getId());
                intent.putExtra("group","restaurant");
                ArrayList<String> rtBean = new ArrayList<String>();
                ArrayList<Integer> idRtBean = new ArrayList<Integer>();
                for (int j = 0; j < restaurant.get(i).getSub_category_count(); j++) {
                    String kind = restaurant.get(i).getSub_categories().get(j).getName();
                    int id = restaurant.get(i).getSub_categories().get(j).getId();
                    Log.d("LibraryFragment", kind);
                    idRtBean.add(id);
                    rtBean.add(kind);
                }
                intent.putStringArrayListExtra("kind",rtBean);
                intent.putIntegerArrayListExtra("id",idRtBean);
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
