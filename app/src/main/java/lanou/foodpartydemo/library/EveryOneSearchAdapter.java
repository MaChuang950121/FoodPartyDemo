package lanou.foodpartydemo.library;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import lanou.foodpartydemo.R;
import lanou.foodpartydemo.bean.EveryOneSearchBean;
import lanou.foodpartydemo.tools.CommonVH;
import lanou.foodpartydemo.tools.OnRecyclerItemClickListener;

/**
 * Created by dllo on 16/11/10.
 */
public class EveryOneSearchAdapter extends RecyclerView.Adapter<CommonVH> {
    List<String> list;
    OnRecyclerItemClickListener onRecyclerItemClickListener;

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public CommonVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return CommonVH.getViewHolder(parent, R.layout.item_everyone_search);
    }

    @Override
    public void onBindViewHolder(CommonVH holder, final int position) {
        holder.setText(R.id.tv_everyone_search,list.get(position));
        holder.setViewClick(R.id.ll_search, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRecyclerItemClickListener.onItemClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
}
