package lanou.foodpartydemo.library;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.ScrollView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lanou.foodpartydemo.R;
import lanou.foodpartydemo.base.BaseActivity;
import lanou.foodpartydemo.bean.EveryOneSearchBean;
import lanou.foodpartydemo.bean.SearchBean;
import lanou.foodpartydemo.homepage.EndLessOnScrollListener;
import lanou.foodpartydemo.tools.GsonRequest;
import lanou.foodpartydemo.tools.OnRecyclerItemClickListener;
import lanou.foodpartydemo.tools.UrlValues;
import lanou.foodpartydemo.tools.VolleySingle;
import lanou.foodpartydemo.tools.sqltools.DBTool;
import lanou.foodpartydemo.tools.sqltools.HistorySqlData;

/**
 * Created by dllo on 16/11/9.
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener, OnRecyclerItemClickListener {

    private ImageView back;
    private EditText edt;
    private String searchContent;
    private SearchAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearManager;
    private ImageView search;
    private ScrollView scrollView;
    private int page = 2;
    private ImageView delete;
    private RecyclerView everyOneSearch;
    private EveryOneSearchAdapter everyOneSearchAdapter;
    private RecyclerView rvSearch;
    private List<String> list;
    private RecentAdapter recentAdapter;
    private ImageView deleteAll;
    private LinearLayoutManager rcmanager;
    public static final int RESULT_CODE = 100;
    @Override
    protected void initData() {
        setOnClick(this,back,search,delete,deleteAll);
        adapter = new SearchAdapter();
        recentAdapter = new RecentAdapter();
        rcmanager = new LinearLayoutManager(this);
        DBTool dbTool = new DBTool();
        dbTool.queryAllData(HistorySqlData.class, new DBTool.OnQueryListener<HistorySqlData>() {
            @Override
            public void onQuery(ArrayList<HistorySqlData> historySqlDatas) {

            }
        });
        linearManager = new LinearLayoutManager(this);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        everyOneSearchAdapter = new EveryOneSearchAdapter();
        everyOneSearchAdapter.setOnRecyclerItemClickListener(this);
        adapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent();
                intent.putExtra("code",adapter.list.get(position).getCode());
                Log.d("SearchActivity", adapter.list.get(position).getCode());
                intent.putExtra("name",adapter.list.get(position).getName());
                intent.putExtra("image",adapter.list.get(position).getThumb_image_url());
                setResult(RESULT_CODE,intent);
                finish();

            }

            @Override
            public void onItemClick(int position, String order) {

            }
        });
        recentAdapter.setOnRecyclerItemClickListener(this);
        GsonRequest<EveryOneSearchBean> gsonRequest = new GsonRequest<EveryOneSearchBean>(EveryOneSearchBean.class,
                UrlValues.EVERYONE_SEARCH, new Response.Listener<EveryOneSearchBean>() {
            @Override
            public void onResponse(EveryOneSearchBean response) {
                everyOneSearchAdapter.setList(response.getKeywords());
                everyOneSearch.setAdapter(everyOneSearchAdapter);
                everyOneSearch.setLayoutManager(gridLayoutManager);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingle.getVolleySingle().addRequest(gsonRequest);

    }
    @Override
    protected int getLayout() {
        return R.layout.activity_search;
    }
    @Override
    protected void initViews() {
        back = bindView(R.id.search_back);
        edt = bindView(R.id.edt_search);
        recyclerView = bindView(R.id.rv_nutrition_order);
        search = bindView(R.id.im_search);
        scrollView = bindView(R.id.sc_scrollView);
        delete = bindView(R.id.im_delete);
        everyOneSearch = bindView(R.id.rv_everyone_search);
        rvSearch = bindView(R.id.rv_search);
        list = new ArrayList<>();
        deleteAll = bindView(R.id.delete_all);
    }
    public static String toUtf8(String str) {
        String result = null;
        try {
           result = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.search_back:
                onBackPressed();
                break;
            case R.id.im_search:

                if (edt.getText().toString().equals("")||edt.getText().toString().equals(null)){
                    Toast.makeText(this, "搜索内容不能为空", Toast.LENGTH_SHORT).show();
                    scrollView.setVisibility(View.VISIBLE);
                    break;
                }else {
                    searchContent = toUtf8(edt.getText().toString());
                    list.add(edt.getText().toString());
                    for (int i = 0; i < list.size(); i++)
                    {
                        for (int j = list.size() - 1 ; j > i; j--)
                        {

                            if (list.get(i).equals(list.get(j)))
                            {
                                list.remove(j);
                            }

                        }
                    }
                    for (String string:list
                         ) {
                        DBTool dbTool = new DBTool();
                        HistorySqlData date = new HistorySqlData();
                        date.setHistoryStr(string);
                        dbTool.insert(string);
                    }

                    Log.d("SearchActivity", "list:" + list);

                    GsonRequest<SearchBean> gsonRequest = new GsonRequest<SearchBean>(SearchBean.class,
                            "http://food.boohee.com/fb/v1/search?page=1&order_asc=desc&q=" + searchContent, new Response.Listener<SearchBean>() {
                        @Override
                        public void onResponse(SearchBean response) {
                            Log.d("SearchActivity", "http://food.boohee.com/fb/v1/search?page=1&order_asc=desc&q=" + searchContent);
                            adapter.setList(response.getItems(), true);

                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(linearManager);

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    VolleySingle.getVolleySingle().addRequest(gsonRequest);
                    recyclerView.addOnScrollListener(new EndLessOnScrollListener(linearManager) {
                        @Override
                        protected void onLoadMore(int curentPage) {
                            GsonRequest<SearchBean> gsonRequest = new GsonRequest<SearchBean>(SearchBean.class,
                                    "http://food.boohee.com/fb/v1/search?page=" + page + "&order_asc=desc&q=%E7%BA%A2%E8%96%AF", new Response.Listener<SearchBean>() {
                                @Override
                                public void onResponse(SearchBean response) {
                                    adapter.setList(response.getItems(), false);
                                    page++;
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            });
                            VolleySingle.getVolleySingle().addRequest(gsonRequest);
                        }
                    });
                }
                recyclerView.setVisibility(View.VISIBLE);
                scrollView.setVisibility(View.GONE);
                break;
            case R.id.im_delete:
                edt.setText("");
                for (int i = 0; i < list.size(); i++)  //外循环是循环的次数
                {
                    for (int j = list.size() - 1 ; j > i; j--)  //内循环是 外循环一次比较的次数
                    {

                        if (list.get(i).equals(list.get(j)))
                        {
                            list.remove(j);
                        }

                    }
                }

                recentAdapter.setList(list);
                rvSearch.setAdapter(recentAdapter);
                rvSearch.setLayoutManager(rcmanager);
                scrollView.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                break;
            case R.id.delete_all:
                list.clear();
                for (String string:list
                     ) {
                    DBTool dbTool = new DBTool();
                    dbTool.deleteHistoryByCondition(HistorySqlData.class,string);
                }

                recentAdapter.setList(list);
                rvSearch.setAdapter(recentAdapter);
                rvSearch.setLayoutManager(rcmanager);
                break;
        }

    }

    @Override
    public void onItemClick(int position) {
        if (everyOneSearchAdapter.list.get(position).equals(null)
                ||everyOneSearchAdapter.list.get(position).equals("")){
            String recentName = recentAdapter.list.get(position);
            edt.setText(recentName);
        }else {
            String name = everyOneSearchAdapter.list.get(position);
            edt.setText(name);
        }

    }

    @Override
    public void onItemClick(int position, String order) {

    }
}
