package lanou.foodpartydemo.homepage;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import lanou.foodpartydemo.R;
import lanou.foodpartydemo.base.BaseFragment;

/**
 * Created by dllo on 16/11/1.
 */
public class HomePageFragment extends BaseFragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> arrayList;

    @Override
    protected void addAdapter() {
        HomePageAdapter adapter = new HomePageAdapter(getChildFragmentManager());
        adapter.setFragments(arrayList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void addFragmentArrayList() {
        arrayList = new ArrayList<>();
        bindFragment(arrayList,new FirstPageFragment());
        bindFragment(arrayList,new TestFragment());
        bindFragment(arrayList,new KnowledgeFragment());
        bindFragment(arrayList,new FoodFragment());
    }

    @Override
    protected void refresh() {

    }

    @Override
    protected void initView() {
        tabLayout = bindView(R.id.tb_home_page);
        viewPager = bindView(R.id.vp_home_page);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_homepage;
    }
}
