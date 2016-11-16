package lanou.foodpartydemo.homepage;

import android.content.Context;
import android.content.Intent;
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
import lanou.foodpartydemo.tools.OnRecyclerItemClickListener;
import lanou.foodpartydemo.tools.UrlValues;
import lanou.foodpartydemo.tools.VolleySingle;

/**
 * Created by dllo on 16/11/1.
 */
public class FirstPageFragment extends BaseFragment {
    private Context context;
    private RecyclerView recyclerView;
    private int page = 2;
//    private SwipeRefreshLayout refreshLayout;
    private FirstPageAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

//    @Override
//    protected void addAdapter() {
//
//    }

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
        adapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent =new  Intent(getContext(),FirstPageNextActivity.class);
                intent.putExtra("publisher",adapter.arrayList.get(position).getPublisher_avatar());
                intent.putExtra("name",adapter.arrayList.get(position).getPublisher());
                intent.putExtra("image",adapter.arrayList.get(position).getCard_image());
                intent.putExtra("like",String.valueOf(adapter.arrayList.get(position).getLike_ct()));
                startActivity(intent);
            }

            @Override
            public void onItemClick(int position, String order) {

            }
        });
    }
//
//    @Override
//    protected void addFragmentArrayList() {
//
//    }
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


    @Override
    protected void initView() {
        recyclerView = bindView(R.id.rv_first_page);
//        refreshLayout = (SwipeRefreshLayout) recyclerView.findViewById(R.id.refreshLayout_first_page);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_firstpage;
    }
}
