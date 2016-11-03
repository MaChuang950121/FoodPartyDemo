package lanou.foodpartydemo.homepage;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import lanou.foodpartydemo.R;
import lanou.foodpartydemo.base.BaseFragment;
import lanou.foodpartydemo.bean.MyBean;
import lanou.foodpartydemo.tools.GsonRequest;
import lanou.foodpartydemo.tools.UrlValues;
import lanou.foodpartydemo.tools.VolleySingle;

/**
 * Created by dllo on 16/11/1.
 */
public class KnowledgeFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private int page = 2;
    @Override
    protected void addAdapter() {

    }

    @Override
    protected void initData() {
        adapter = new MyAdapter(getContext());
        getGson(UrlValues.KNOWLEDGE,true);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.addOnScrollListener(new EndLessOnScrollListener(manager) {
            @Override
            protected void onLoadMore(int curentPage) {
                getGson("http://food.boohee.com/fb/v1/feeds/category_feed?page="+page+"&category=3&per=10",false);
                page++;
            }
        });
    }


    public void getGson(String url, final boolean isRefresh){
        GsonRequest<MyBean> gsonRequest = new GsonRequest<MyBean>(MyBean.class
                , url, new Response.Listener<MyBean>() {
            @Override
            public void onResponse(MyBean response) {
                adapter.setArrayList(response.getFeeds(), isRefresh);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingle.getVolleySingle().addRequest(gsonRequest);
    }

    @Override
    protected void addFragmentArrayList() {

    }

    @Override
    protected void initView() {
        recyclerView = bindView(R.id.rv_knowledge);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_knowledge;
    }
}
