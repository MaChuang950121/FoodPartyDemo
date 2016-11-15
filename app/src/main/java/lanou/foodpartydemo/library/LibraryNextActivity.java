package lanou.foodpartydemo.library;

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

import org.w3c.dom.Text;

import java.util.ArrayList;

import lanou.foodpartydemo.R;
import lanou.foodpartydemo.base.BaseActivity;
import lanou.foodpartydemo.bean.LibraryNextBean;
import lanou.foodpartydemo.bean.NutritionOrderBean;
import lanou.foodpartydemo.tools.GsonRequest;
import lanou.foodpartydemo.tools.OnRecyclerItemClickListener;
import lanou.foodpartydemo.tools.UrlValues;
import lanou.foodpartydemo.tools.VolleySingle;


public class LibraryNextActivity extends BaseActivity implements  View.OnClickListener, AdapterView.OnItemClickListener, OnRecyclerItemClickListener {

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
   private String ntOder;
    private TextView tvALL;


    @Override
    protected void initData() {
        String name = getIntent().getStringExtra("name");
        kind = getIntent().getStringArrayListExtra("kind");
        id = getIntent().getIntegerArrayListExtra("id");
        value = getIntent().getStringExtra("value");
        group = getIntent().getStringExtra("group");
        titleKind.setText(name);
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


                GsonRequest<LibraryNextBean> gsonRequestOrder = new GsonRequest<LibraryNextBean>(LibraryNextBean.class,
                        "http://food.boohee.com/fb/v1/foods?kind=" + group + "&value=" + value +
                                "&order_by=" + ntOder + "&page="+page+"&order_asc=0",
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
                VolleySingle.getVolleySingle().addRequest(gsonRequestOrder);




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
//        titleKind = (TextView) findViewById(R.id.title_kind);
        titleKind = bindView(R.id.title_kind);
//        listView = (ListView) findViewById(R.id.lv_library_next);
        listView = bindView(R.id.lv_library_next);
//        all = (ImageView) findViewById(R.id.iv_all);
        all = bindView(R.id.iv_all);
        view = LayoutInflater.from(this).inflate(R.layout.pop,null);
        order = LayoutInflater.from(this).inflate(R.layout.order_pop,null);
        lvAll = (ListView) view.findViewById(R.id.lv_pop);
        recyclerView = (RecyclerView) order.findViewById(R.id.rv_order);
//        nutritionOrder = (TextView) findViewById(R.id.nutrition_order);
        nutritionOrder = bindView(R.id.nutrition_order);
//        back = (ImageView) findViewById(R.id.library_next_back);
        back = bindView(R.id.library_next_back);
        tvALL = bindView(R.id.tv_all);
//        nutritionOrder.setOnClickListener(this);
//        all.setOnClickListener(this);
//        back.setOnClickListener(this);
        lvAll.setOnItemClickListener(this);

        setOnClick(this,nutritionOrder,all,back);


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
                break;
            case R.id.library_next_back:
                onBackPressed();
                break;

        }
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        if (popupWindow.isShowing()){
            popupWindow.dismiss();
        }
        subValue = id.get(i);

        String str = (String) adapterView.getItemAtPosition(i);
       tvALL.setText(str);
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
                , ViewGroup.LayoutParams.WRAP_CONTENT);
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

    @Override
    public void onItemClick(int position, String order) {
       ntOder = order;
        nutritionOrder.setText(orderPopAdapter.arrayList.get(position).getName());
        GsonRequest<LibraryNextBean> gsonRequest = new GsonRequest<LibraryNextBean>(LibraryNextBean.class
                , "http://food.boohee.com/fb/v1/foods?kind="+group+"&value="+value+"&order_by="+order+"&page=1&order_asc=0",
                new Response.Listener<LibraryNextBean>() {
            @Override
            public void onResponse(LibraryNextBean response) {
                libraryNextAdapter.setList(response.getFoods());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingle.getVolleySingle().addRequest(gsonRequest);
    }



}
