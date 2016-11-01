package lanou.foodpartydemo.homepage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/1.
 */
public class HomePageAdapter extends FragmentPagerAdapter{
    ArrayList<Fragment> fragments  = new ArrayList<>();
    ArrayList<String> titleList = new ArrayList<>();
    String[] title = {"首页","测评","知识","美食"};

    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
        for (int i = 0; i < title.length; i++) {
            titleList.add(title[i]);
        }

    }

    public HomePageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
