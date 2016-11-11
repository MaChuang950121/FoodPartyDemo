package lanou.foodpartydemo.library;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import lanou.foodpartydemo.R;
import lanou.foodpartydemo.bean.LibraryNextBean;
import lanou.foodpartydemo.bean.SearchBean;
import lanou.foodpartydemo.tools.CommonVH;
import lanou.foodpartydemo.tools.OnRecyclerItemClickListener;

/**
 * Created by dllo on 16/11/10.
 */
public class SearchAdapter extends RecyclerView.Adapter<CommonVH> {
    List<SearchBean.ItemsBean> list;
    OnRecyclerItemClickListener onRecyclerItemClickListener;

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    public void setList(List<SearchBean.ItemsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public void setList(List<SearchBean.ItemsBean> list, boolean isRefresh) {
        if (isRefresh || this.list == null){
            setList(list);
        }else {
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public CommonVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return CommonVH.getViewHolder(parent, R.layout.item_search);
    }

    @Override
    public void onBindViewHolder(CommonVH holder, final int position) {
        holder.setCircleImage(R.id.image_food,list.get(position).getThumb_image_url());
        holder.setText(R.id.food_name,list.get(position).getName());
        holder.setText(R.id.tv_heat,list.get(position).getCalory());
        holder.setText(R.id.food_weight,list.get(position).getWeight());
        holder.setViewClick(R.id.rl_order, new View.OnClickListener() {
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
