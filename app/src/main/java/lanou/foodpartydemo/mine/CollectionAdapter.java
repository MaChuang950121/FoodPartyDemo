package lanou.foodpartydemo.mine;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/14.
 */
public class CollectionAdapter extends FragmentPagerAdapter{
    ArrayList<Fragment> fragments = new ArrayList<>();
    ArrayList<String> titles = new ArrayList<>();
    String[] title = {"文章","食物"};
    public CollectionAdapter(FragmentManager fm) {
        super(fm);
    }
    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
        for (int i = 0; i < title.length; i++) {
            titles.add(title[i]);
        }

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
        return titles.get(position);
    }
}
