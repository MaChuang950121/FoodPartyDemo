package lanou.foodpartydemo.homepage;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;

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
    private ArrayList<FirstPageBean.FeedsBean> arrayList;

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
        GsonRequest<FirstPageBean> gsonRequest = new GsonRequest<FirstPageBean>(FirstPageBean.class,
                UrlValues.FIRST_PAGE, new Response.Listener<FirstPageBean>() {
            @Override
            public void onResponse(FirstPageBean response) {
                arrayList = (ArrayList<FirstPageBean.FeedsBean>) response.getFeeds();
                FirstPageAdapter adapter = new FirstPageAdapter(context);
                adapter.setArrayList(arrayList);
                recyclerView.setAdapter(adapter);
                StaggeredGridLayoutManager manager =
                        new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
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
    protected void addFragmentArrayList() {

    }

    @Override
    protected void initView() {
        recyclerView = bindView(R.id.rv_first_page);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_firstpage;
    }
}
