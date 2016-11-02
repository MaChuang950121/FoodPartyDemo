package lanou.foodpartydemo.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


import lanou.foodpartydemo.R;

/**
 * Created by dllo on 16/10/21.
 */
public abstract class BaseFragment extends Fragment {


    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayout() == 0){
            return inflater.inflate(R.layout.null_layout,container,false);
        }
        return inflater.inflate(getLayout(),container,false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        addFragmentArrayList();

        addAdapter();
    }
    

    protected abstract void addAdapter();
    protected abstract void initData();

    protected abstract void addFragmentArrayList();


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        refresh();
        initData();
    }

    protected abstract void refresh();

    protected <T extends View> T bindView(int id){
        return (T) getView().findViewById(id);
    }
    protected <T extends View> T bindViews(View view,int id){
        return (T) view.findViewById(id);
    }
    protected abstract void initView();
    public abstract int getLayout() ;
    protected void bindFragment(ArrayList<Fragment> arrayList, Fragment fragment){
        arrayList.add(fragment);
    }
    protected void setItemOnClick(View.OnClickListener clickListener, View ...views){
        int i = 0;
        for (View view : views){
            if (view == null) {
                Log.d("BaseFragment-click", "i:" + i);
//            Log.e("BaseFragment-click", view.getClass().getSimpleName());
            }else {

                view.setOnClickListener(clickListener);
            }
            i++;
        }
    }

}
