package lanou.foodpartydemo.library;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
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
import lanou.foodpartydemo.bean.LibraryNextBean;
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
    private ArrayList<Integer> id;
    private String value;
    private LibraryNextAdapter libraryNextAdapter;
    private int subValue;
    private int page = 2;
    private String group;
    private ImageView back;

    @Override
    protected void initData() {
        String name = getIntent().getStringExtra("name");
        kind = getIntent().getStringArrayListExtra("kind");
        Log.d("LibraryNextActivity", "kind:" + kind);
        id = getIntent().getIntegerArrayListExtra("id");
        value = getIntent().getStringExtra("value");
        group = getIntent().getStringExtra("group");
        titleKind.setText(name);
        Log.d("LibraryNextActivity", value);
        libraryNextAdapter = new LibraryNextAdapter(this);
        GsonRequest<LibraryNextBean> gsonRequestAll = new GsonRequest<LibraryNextBean>(LibraryNextBean.class,
                "http://food.boohee.com/fb/v1/foods?kind="+group+"&value=" + value + "&order_by=1&page=1&order_asc=0",
                new Response.Listener<LibraryNextBean>() {
                    @Override
                    public void onResponse(LibraryNextBean response) {
                        libraryNextAdapter.setList(response.getFoods(),true);
                        listView.setAdapter(libraryNextAdapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingle.getVolleySingle().addRequest(gsonRequestAll);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

                GsonRequest<LibraryNextBean> gsonRequestAll = new GsonRequest<LibraryNextBean>(LibraryNextBean.class,
                        "http://food.boohee.com/fb/v1/foods?kind="+group+"&value=" + value + "&order_by=1&page="+page+"&order_asc=0",
                        new Response.Listener<LibraryNextBean>() {
                            @Override
                            public void onResponse(LibraryNextBean response) {
                                libraryNextAdapter.setList(response.getFoods(),false);

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                VolleySingle.getVolleySingle().addRequest(gsonRequestAll);


                GsonRequest<LibraryNextBean> gsonRequest =
                                new GsonRequest<LibraryNextBean>(LibraryNextBean.class,
                                        "http://food.boohee.com/fb/v1/foods?kind="+group+"&value="
                                                + value + "(&sub_value=" + subValue + ")&order_by=1&page="
                                                +page+"&order_asc=0",
                                        new Response.Listener<LibraryNextBean>() {
                                            @Override
                                            public void onResponse(LibraryNextBean response) {
                                                libraryNextAdapter.setList(response.getFoods(),false);
                                            }
                                        }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                });
                        VolleySingle.getVolleySingle().addRequest(gsonRequest);
                        page++;

            }
            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });
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
        back = (ImageView) findViewById(R.id.library_next_back);
        nutritionOrder.setOnClickListener(this);
        all.setOnClickListener(this);
        lvAll.setOnItemClickListener(this);
        back.setOnClickListener(this);

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
            case R.id.library_next_back:
                onBackPressed();
                break;

        }
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d("LibraryNextActivity", "点击");
        if (popupWindow.isShowing()){
            popupWindow.dismiss();
        }
        subValue = id.get(i);
        GsonRequest<LibraryNextBean> gsonRequest = new GsonRequest<LibraryNextBean>(LibraryNextBean.class
                , "http://food.boohee.com/fb/v1/foods?kind="+group+"&value="+value+"(&sub_value="
                + subValue + ")&order_by=1&page=1&order_asc=0", new Response.Listener<LibraryNextBean>() {
            @Override
            public void onResponse(LibraryNextBean response) {
                libraryNextAdapter.setList(response.getFoods());
                listView.setAdapter(libraryNextAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingle.getVolleySingle().addRequest(gsonRequest);


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
                , ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindowOrder.setContentView(order);
        orderPopAdapter = new OrderPopAdapter(this);
        getGson(UrlValues.ORDER);
        orderPopAdapter.setOnRecyclerItemClickListener(this);
        popupWindowOrder.showAsDropDown(nutritionOrder);
    }

    public void getGson(String url){
        GsonRequest<NutritionOrderBean> gsonRequest = new GsonRequest<NutritionOrderBean>(NutritionOrderBean.class
                , url, new Response.Listener<NutritionOrderBean>() {
            @Override
            public void onResponse(NutritionOrderBean response) {
                orderPopAdapter.setArrayList((ArrayList<NutritionOrderBean.TypesBean>) response.getTypes());
                recyclerView.setAdapter(orderPopAdapter);
                GridLayoutManager manager = new GridLayoutManager(LibraryNextActivity.this,3);
                recyclerView.setLayoutManager(manager);
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

        if (popupWindowOrder.isShowing()){
            popupWindowOrder.dismiss();
        }
    }
}
