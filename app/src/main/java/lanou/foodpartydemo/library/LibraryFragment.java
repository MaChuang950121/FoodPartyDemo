package lanou.foodpartydemo.library;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


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
public class LibraryFragment extends BaseFragment implements View.OnClickListener {

    private GridView gridViewCg;
    private GridView gridViewBr;
    private GridView gridViewRt;
    private ArrayList<LibraryBean.GroupBean.CategoriesBean> categories;
    private ArrayList<LibraryBean.GroupBean.CategoriesBean> brand;
    private ArrayList<LibraryBean.GroupBean.CategoriesBean> restaurant;
    private LibraryAdapter adapter;
    private LibraryAdapter adapter1;
    private LibraryAdapter adapter2;
    private LinearLayout search;
    private ImageView compare;


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

        gridViewBr.setOnItemClickListener(new MyItemClickListener());
        gridViewCg.setOnItemClickListener(new MyItemClickListener());
        gridViewRt.setOnItemClickListener(new MyItemClickListener());
        setItemOnClick(this,search,compare);


        }
    @Override
    protected void addFragmentArrayList() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.foodencylope_search:
                Intent intent = new Intent(getContext(),SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.im_compare:
                Intent intent1 = new Intent(getContext(),CompareActivity.class);
                startActivity(intent1);
                break;
        }
    }

    class MyItemClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            LibraryBean.GroupBean.CategoriesBean bean = (LibraryBean.GroupBean.CategoriesBean) adapterView.getItemAtPosition(i);

            Intent intent = new Intent(getContext(),LibraryNextActivity.class);
            intent.putExtra("name",bean.getName());
            intent.putExtra("value",bean.getId());
            intent.putExtra("group","group");
            ArrayList<String> brBean = new ArrayList<String>();
            ArrayList<Integer> idBrBean = new ArrayList<Integer>();
            for (int j = 0; j < bean.getSub_category_count(); j++) {
                String kind = bean.getSub_categories().get(j).getName();
                Log.d("LibraryFragment", kind);
                int id = bean.getSub_categories().get(j).getId();
                brBean.add(kind);
                idBrBean.add(id);
            }
            intent.putStringArrayListExtra("kind",brBean);
            intent.putIntegerArrayListExtra("id",idBrBean);
            startActivity(intent);
        }
    }



    @Override
    protected void initView() {
        gridViewCg = bindView(R.id.foodencylope_class);
        gridViewBr = bindView(R.id.foodencylope_brand);
        gridViewRt = bindView(R.id.foodencylope_restaurant);
        search = bindView(R.id.foodencylope_search);
        compare = bindView(R.id.im_compare);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_library;

    }


}
