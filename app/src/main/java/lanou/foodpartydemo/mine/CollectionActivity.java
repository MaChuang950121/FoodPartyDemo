package lanou.foodpartydemo.mine;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import lanou.foodpartydemo.R;
import lanou.foodpartydemo.base.BaseActivity;

/**
 * Created by dllo on 16/11/14.
 */
public class CollectionActivity extends BaseActivity {

    private TabLayout tbCollection;
    private ViewPager vpCollection;
    private ArrayList<Fragment> arrayList;

    @Override
    protected void initData() {
        arrayList = new ArrayList<>();
        arrayList.add(new ArcticalFragment());
        arrayList.add(new CollectionFoodFragment());
        CollectionAdapter adapter = new CollectionAdapter(getSupportFragmentManager());
        adapter.setFragments(arrayList);
        vpCollection.setAdapter(adapter);
        tbCollection.setupWithViewPager(vpCollection);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_collection;
    }

    @Override
    protected void initViews() {
        tbCollection = bindView(R.id.tb_collection);
        vpCollection = bindView(R.id.vp_collection);
    }
}
