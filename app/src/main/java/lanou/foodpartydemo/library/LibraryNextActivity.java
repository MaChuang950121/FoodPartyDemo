package lanou.foodpartydemo.library;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;

import lanou.foodpartydemo.R;
import lanou.foodpartydemo.base.BaseActivity;
import lanou.foodpartydemo.bean.LibraryBean;
import lanou.foodpartydemo.bean.NutritionOrderBean;
import lanou.foodpartydemo.tools.GsonRequest;
import lanou.foodpartydemo.tools.OnRecyclerItemClickListener;
import lanou.foodpartydemo.tools.UrlValues;
import lanou.foodpartydemo.tools.VolleySingle;

/**
 * Created by dllo on 16/11/4.
 */
public class LibraryNextActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener, OnRecyclerItemClickListener {

    private TextView titleKind;
    private ListView listView;
    private ImageView all;
    private PopupWindow popupWindow;
    private ArrayList<String> kind;
    private ListView lvAll;
    private View view;
    private TextView nutritionOrder;
    private View order;
    private RecyclerView recyclerView;
    private OrderPopAdapter orderPopAdapter;
    private PopupWindow popupWindowOrder;


    @Override
    protected void initData() {
        String name = getIntent().getStringExtra("name");
        kind = getIntent().getStringArrayListExtra("kind");
        Log.d("LibraryNextActivity", "kind:" + kind);
        titleKind.setText(name);

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_library_next;
    }

    @Override
    protected void initViews() {
        titleKind = (TextView) findViewById(R.id.title_kind);
        listView = (ListView) findViewById(R.id.lv_library_next);
        all = (ImageView) findViewById(R.id.iv_all);
        view = LayoutInflater.from(this).inflate(R.layout.pop,null);
        order = LayoutInflater.from(this).inflate(R.layout.order_pop,null);
        lvAll = (ListView) view.findViewById(R.id.lv_pop);
        recyclerView = (RecyclerView) order.findViewById(R.id.rv_order);
        nutritionOrder = (TextView) findViewById(R.id.nutrition_order);
        nutritionOrder.setOnClickListener(this);
        all.setOnClickListener(this);
        lvAll.setOnItemClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_all:
                if (popupWindow == null || !popupWindow.isShowing()){
                    initAllPop();
                }
                break;
            case R.id.nutrition_order:
                if (popupWindowOrder == null || !popupWindowOrder.isShowing()){
                    initOrderPop();
                }
                Log.d("LibraryNextActivity", "营养");
                break;

        }
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d("LibraryNextActivity", "点击");
        if (popupWindow.isShowing()){
            popupWindow.dismiss();
        }
    }
    private void initAllPop(){
        popupWindow = new PopupWindow(200,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);
        PopAdapter adapter = new PopAdapter(this);
        adapter.setArrayList(kind);
        lvAll.setAdapter(adapter);
        popupWindow.showAsDropDown(all);
    }

    private void initOrderPop() {
        popupWindowOrder = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT
                ,200);
        popupWindowOrder.setContentView(order);
        orderPopAdapter = new OrderPopAdapter(this);
        getGson(UrlValues.ORDER);
        recyclerView.setAdapter(orderPopAdapter);
        GridLayoutManager manager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(manager);
        orderPopAdapter.setOnRecyclerItemClickListener(this);
        popupWindowOrder.showAsDropDown(nutritionOrder);
    }

    public void getGson(String url){
        GsonRequest<NutritionOrderBean> gsonRequest = new GsonRequest<NutritionOrderBean>(NutritionOrderBean.class
                , url, new Response.Listener<NutritionOrderBean>() {
            @Override
            public void onResponse(NutritionOrderBean response) {
                orderPopAdapter.setArrayList((ArrayList<NutritionOrderBean.TypesBean>) response.getTypes());
                Log.d("LibraryNextActivity", "response.getTypes():" + response.getTypes());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingle.getVolleySingle().addRequest(gsonRequest);
    }

    @Override
    public void onItemClick(int position) {
        Log.d("LibraryNextActivity", "点击");
        if (popupWindowOrder.isShowing()){
            popupWindowOrder.dismiss();
        }
    }
}
