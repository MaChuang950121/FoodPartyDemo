package lanou.foodpartydemo.homepage;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import lanou.foodpartydemo.R;
import lanou.foodpartydemo.base.BaseFragment;
import lanou.foodpartydemo.bean.TestBean;
import lanou.foodpartydemo.tools.GsonRequest;
import lanou.foodpartydemo.tools.OnRecyclerItemClickListener;
import lanou.foodpartydemo.tools.UrlValues;
import lanou.foodpartydemo.tools.VolleySingle;

/**
 * Created by dllo on 16/11/1.
 */
public class TestFragment extends BaseFragment implements OnRecyclerItemClickListener{
    private Context context;
    private TestAdapter adapter;
    private int page = 2;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    private RecyclerView recyclerView;

    @Override
    protected void addAdapter() {

    }

    @Override
    protected void initData() {
        adapter = new TestAdapter(context);
        getGson(UrlValues.TEST,true);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);
        recyclerView.addOnScrollListener(new EndLessOnScrollListener(manager) {
            @Override
            protected void onLoadMore(int curentPage) {
                getGson("http://food.boohee.com/fb/v1/feeds/category_feed?page="+ page +"&category=2&per=10",false);
                page++;
            }
        });

        adapter.setOnRecyclerItemClickListener(this);

    }

    public void getGson(String url, final boolean isRefresh){
        GsonRequest<TestBean> gsonRequest = new GsonRequest<TestBean>(TestBean.class,url ,
                new Response.Listener<TestBean>() {
                    @Override
                    public void onResponse(TestBean response) {
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
        recyclerView = bindView(R.id.rv_test);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_test;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getContext(),WebActivity.class);
        intent.putExtra("url",adapter.arrayList.get(position).getLink());
        startActivity(intent);
    }
}
