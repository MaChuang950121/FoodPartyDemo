package lanou.foodpartydemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

import cn.bmob.v3.Bmob;

/**
 * Created by dllo on 16/10/21.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initViews();
        initData();
        //第一：默认初始化 建议初始化放到Application里
        Bmob.initialize(this, "6e73c4c8a347ac2f3a1b9716714db329");
    }

    protected abstract void initData();

    protected abstract int getLayout();
    protected abstract void initViews();
    protected <T extends View> T bindView(int id){
        return (T) findViewById(id);
    }
    protected void setOnClick(View.OnClickListener clickListener, View ...views){
        for (View view : views){

            view.setOnClickListener(clickListener);
        }
    }
    protected void bindFragment(ArrayList<Fragment> arrayList, Fragment fragment){
        arrayList.add(fragment);
    }
}
