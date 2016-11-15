package lanou.foodpartydemo.library;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import lanou.foodpartydemo.R;
import lanou.foodpartydemo.bean.CompareBean;
import lanou.foodpartydemo.tools.CommonVH;

/**
 * Created by dllo on 16/11/13.
 */
public class CompareAdapter extends RecyclerView.Adapter<CommonVH>{


    @Override
    public CommonVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return CommonVH.getViewHolder(parent, R.layout.item_compare);

    }

    @Override
    public void onBindViewHolder(CommonVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
