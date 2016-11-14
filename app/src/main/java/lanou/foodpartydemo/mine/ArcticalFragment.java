package lanou.foodpartydemo.mine;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import lanou.foodpartydemo.R;
import lanou.foodpartydemo.base.BaseFragment;
import lanou.foodpartydemo.bean.CollectionSqlBean;
import lanou.foodpartydemo.tools.sqltools.DBTool;

/**
 * Created by dllo on 16/11/14.
 */
public class ArcticalFragment extends BaseFragment{

    private RecyclerView arctical;

    @Override
    protected void addAdapter() {

    }

    @Override
    protected void initData() {
        DBTool dbTool = new DBTool();
        dbTool.queryAllData(CollectionSqlBean.class, new DBTool.OnQueryListener<CollectionSqlBean>() {
            @Override
            public void onQuery(ArrayList<CollectionSqlBean> collectionSqlBeen) {
                ArcticalAdapter adapter = new ArcticalAdapter();
                adapter.setArrayList(collectionSqlBeen);
                arctical.setAdapter(adapter);
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                arctical.setLayoutManager(manager);
            }
        });


    }

    @Override
    protected void addFragmentArrayList() {

    }

    @Override
    protected void initView() {
        arctical = bindView(R.id.rv_arctical);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_arctical;
    }
}
