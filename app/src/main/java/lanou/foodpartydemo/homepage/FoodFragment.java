package lanou.foodpartydemo.homepage;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import lanou.foodpartydemo.R;
import lanou.foodpartydemo.base.BaseFragment;
import lanou.foodpartydemo.bean.MyBean;
import lanou.foodpartydemo.tools.GsonRequest;
import lanou.foodpartydemo.tools.OnRecyclerItemClickListener;
import lanou.foodpartydemo.tools.UrlValues;
import lanou.foodpartydemo.tools.VolleySingle;

/**
 * Created by dllo on 16/11/1.
 */
public class FoodFragment extends BaseFragment implements OnRecyclerItemClickListener {

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private int page = 2;

    @Override
    protected void addAdapter() {

    }

    @Override
    protected void initData() {
        adapter = new MyAdapter(getContext());
        getGson(UrlValues.FOOD,true);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.addOnScrollListener(new EndLessOnScrollListener(manager) {
            @Override
            protected void onLoadMore(int curentPage) {
                getGson("http://food.boohee.com/fb/v1/feeds/category_feed?page="+page+"&category=4&per=10",false);
                page++;
            }
        });
        adapter.setOnRecyclerItemClickListener(this);

    }


    public void getGson(String url, final boolean isRefresh){
        GsonRequest<MyBean> gsonRequest = new GsonRequest<MyBean>(MyBean.class, url,
                new Response.Listener<MyBean>() {
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
        recyclerView = bindView(R.id.rv_food);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_food;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getContext(),WebActivity.class);
        intent.putExtra("url",adapter.arrayList.get(position).getLink());
        intent.putExtra("title",adapter.arrayList.get(position).getTitle());
        startActivity(intent);
    }

    @Override
    public void onItemClick(int position, String order) {

    }
}
