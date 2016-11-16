package lanou.foodpartydemo.mine;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import lanou.foodpartydemo.R;
import lanou.foodpartydemo.base.BaseFragment;
import lanou.foodpartydemo.bean.CollectionSqlBean;
import lanou.foodpartydemo.homepage.WebActivity;
import lanou.foodpartydemo.tools.OnRecyclerItemClickListener;
import lanou.foodpartydemo.tools.sqltools.DBTool;

/**
 * Created by dllo on 16/11/14.
 */
public class ArcticalFragment extends BaseFragment{

    private RecyclerView arctical;
    private ArcticalAdapter adapter;
    private MyDynamicBroadCast cast;
    private DBTool dbTool;


//    @Override
//    protected void addAdapter() {
//
//    }

    @Override
    protected void initData() {
        dbTool = new DBTool();
        adapter = new ArcticalAdapter();
        cast = new MyDynamicBroadCast();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("lanou.foodpartydemo.homepage");
        getContext().registerReceiver(cast,intentFilter);
        dbTool.queryAllData(CollectionSqlBean.class, new DBTool.OnQueryListener<CollectionSqlBean>() {
            @Override
            public void onQuery(ArrayList<CollectionSqlBean> collectionSqlBeen) {

                adapter.setArrayList(collectionSqlBeen);
                arctical.setAdapter(adapter);
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                arctical.setLayoutManager(manager);
            }
        });
        adapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("url",adapter.arrayList.get(position).getLink());
                startActivity(intent);
            }

            @Override
            public void onItemClick(int position, String order) {

            }
        });


    }

////    @Override
//    protected void addFragmentArrayList() {
//
//    }

    @Override
    protected void initView() {
        arctical = bindView(R.id.rv_arctical);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_arctical;
    }


    private  class MyDynamicBroadCast extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            dbTool.queryAllData(CollectionSqlBean.class, new DBTool.OnQueryListener<CollectionSqlBean>() {
                @Override
                public void onQuery(ArrayList<CollectionSqlBean> collectionSqlBeen) {

                    adapter.setArrayList(collectionSqlBeen);
                    arctical.setAdapter(adapter);
                    LinearLayoutManager manager = new LinearLayoutManager(getContext());
                    arctical.setLayoutManager(manager);
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       getContext().unregisterReceiver(cast);
    }
}
