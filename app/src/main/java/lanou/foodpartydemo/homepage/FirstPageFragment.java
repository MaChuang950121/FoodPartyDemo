package lanou.foodpartydemo.homepage;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import lanou.foodpartydemo.R;
import lanou.foodpartydemo.base.BaseFragment;
import lanou.foodpartydemo.bean.FirstPageBean;
import lanou.foodpartydemo.tools.GsonRequest;
import lanou.foodpartydemo.tools.UrlValues;
import lanou.foodpartydemo.tools.VolleySingle;

/**
 * Created by dllo on 16/11/1.
 */
public class FirstPageFragment extends BaseFragment {
    private Context context;
    private RecyclerView recyclerView;
    private int page = 2;
    private SwipeRefreshLayout refreshLayout;
    private FirstPageAdapter adapter;

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
        adapter = new FirstPageAdapter(context);
        getGson(UrlValues.FIRST_PAGE,true);
        recyclerView.setAdapter(adapter);
        StaggeredGridLayoutManager manager = new
                StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.addOnScrollListener(new EndLessOnScrollListener(manager) {
            @Override
            protected void onLoadMore(int curentPage) {
                getGson("http://food.boohee.com/fb/v1/feeds/category_feed?page="+page+"&category=1&per=10",false);
                page++;
            }
        });



//        adapter = new FirstPageAdapter(context);
//        arrayList = new ArrayList<>();
//        final GsonRequest<FirstPageBean> gsonRequest = new GsonRequest<FirstPageBean>(FirstPageBean.class,
//                UrlValues.FIRST_PAGE, new Response.Listener<FirstPageBean>() {
//            @Override
//            public void onResponse(FirstPageBean response) {
//                arrayList = (ArrayList<FirstPageBean.FeedsBean>) response.getFeeds();
//                adapter.setArrayList(arrayList);
//                recyclerView.setAdapter(adapter);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        VolleySingle.getVolleySingle().addRequest(gsonRequest);
//         final StaggeredGridLayoutManager manager =  new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(manager);
//        recyclerView.addOnScrollListener(new EndLessOnScrollListener(manager) {
//            @Override
//            protected void onLoadMore(int curentPage) {
//                Log.d("FirstPageFragment", "11111");
//                GsonRequest<FirstPageBean> gsonRequest1 =
//                        new GsonRequest<FirstPageBean>(FirstPageBean.class,
//                                "http://food.boohee.com/fb/v1/feeds/category_feed?page=" + page + "&category=1&per=10",
//                                new Response.Listener<FirstPageBean>() {
//                                    @Override
//                                    public void onResponse(FirstPageBean response) {
//                                        arrayList = (ArrayList<FirstPageBean.FeedsBean>) response.getFeeds();
//                                        adapter.setArrayList(arrayList);
//                                        recyclerView.setAdapter(adapter);
////                                        adapter.notifyDataSetChanged();
//                                        recyclerView.setLayoutManager(manager);
//
//
//                                    }
//                                }, new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//
//                            }
//                        });
//                VolleySingle.getVolleySingle().addRequest(gsonRequest1);
//                page = page + 1;
//                Log.d("FirstPageFragment", "page:" + page);
//            }
//        });


    }

    @Override
    protected void addFragmentArrayList() {

    }
    public void getGson(String url, final boolean isRefresh) {
        GsonRequest<FirstPageBean> gsonRequest = new GsonRequest<FirstPageBean>(FirstPageBean.class,
                url, new Response.Listener<FirstPageBean>() {
            @Override
            public void onResponse(FirstPageBean response) {
//
                adapter.setArrayList(response.getFeeds(),isRefresh);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingle.getVolleySingle().addRequest(gsonRequest);
    }

//    protected void refresh() {

//        recyclerView.addOnScrollListener(new EndLessOnScrollListener(manager) {
//            FirstPageAdapter adapter1 = new FirstPageAdapter(context);
//            @Override
//            protected void onLoadMore(int curentPage) {
//                Log.d("FirstPageFragment", "fdsjlfkj");
//                GsonRequest<FirstPageBean> gsonRequest1 = new GsonRequest<FirstPageBean>(FirstPageBean.class,
//                        "http://food.boohee.com/fb/v1/feeds/category_feed?page=" +page+ "&category=1&per=10",
//                        new Response.Listener<FirstPageBean>() {
//                            @Override
//                            public void onResponse(FirstPageBean response) {
//                                ArrayList<FirstPageBean.FeedsBean> arrayList1 =
//                                        (ArrayList<FirstPageBean.FeedsBean>) response.getFeeds();
//                                Log.d("FirstPageFragment", "arrayList1:" + arrayList1);
//
//                                adapter1.setArrayList(arrayList1);
//                                recyclerView.setAdapter(adapter1);
//                               // StaggeredGridLayoutManager manager2 =
//                                      //  new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
//                                recyclerView.setLayoutManager(manager);
//
//                            }
//                        }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                });
//                VolleySingle.getVolleySingle().addRequest(gsonRequest1);
//                page++;
//                adapter1.notifyDataSetChanged();
//            }
//        });
//    }

    @Override
    protected void initView() {
        recyclerView = bindView(R.id.rv_first_page);
        refreshLayout = (SwipeRefreshLayout) recyclerView.findViewById(R.id.refreshLayout_first_page);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_firstpage;
    }
}
