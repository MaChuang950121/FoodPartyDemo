package lanou.foodpartydemo.mine;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import lanou.foodpartydemo.R;
import lanou.foodpartydemo.bean.CollectionSqlBean;
import lanou.foodpartydemo.tools.CommonVH;
import lanou.foodpartydemo.tools.OnRecyclerItemClickListener;

/**
 * Created by dllo on 16/11/14.
 */
public class ArcticalAdapter extends RecyclerView.Adapter<CommonVH>{
    ArrayList<CollectionSqlBean> arrayList;
    OnRecyclerItemClickListener onRecyclerItemClickListener;

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    public void setArrayList(ArrayList<CollectionSqlBean> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public CommonVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return CommonVH.getViewHolder(parent, R.layout.item_arctical);
    }

    @Override
    public void onBindViewHolder(CommonVH holder, final int position) {
        holder.setText(R.id.tv_arctical_title,arrayList.get(position).getTitle());
        holder.setViewClick(R.id.rl_arctical, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRecyclerItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 :arrayList.size();
    }
}
