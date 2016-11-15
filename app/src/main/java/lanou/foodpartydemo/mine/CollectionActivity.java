package lanou.foodpartydemo.mine;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import lanou.foodpartydemo.R;
import lanou.foodpartydemo.base.BaseActivity;

/**
 * Created by dllo on 16/11/14.
 */
public class CollectionActivity extends BaseActivity implements View.OnClickListener {

    private TabLayout tbCollection;
    private ViewPager vpCollection;
    private ArrayList<Fragment> arrayList;
    private ImageView back;

    @Override
    protected void initData() {
        arrayList = new ArrayList<>();
        arrayList.add(new ArcticalFragment());
        arrayList.add(new CollectionFoodFragment());
        CollectionAdapter adapter = new CollectionAdapter(getSupportFragmentManager());
        adapter.setFragments(arrayList);
        vpCollection.setAdapter(adapter);
        tbCollection.setupWithViewPager(vpCollection);
        setOnClick(this,back);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_collection;
    }

    @Override
    protected void initViews() {
        tbCollection = bindView(R.id.tb_collection);
        vpCollection = bindView(R.id.vp_collection);
        back = bindView(R.id.web_title_back);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.web_title_back:
                onBackPressed();
                break;
        }
    }
}
