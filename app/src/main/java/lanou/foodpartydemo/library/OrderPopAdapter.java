package lanou.foodpartydemo.library;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import lanou.foodpartydemo.R;
import lanou.foodpartydemo.bean.NutritionOrderBean;
import lanou.foodpartydemo.tools.OnRecyclerItemClickListener;

/**
 * Created by dllo on 16/11/5.
 */
public class OrderPopAdapter extends RecyclerView.Adapter<OrderPopAdapter.ViewHolder>{
    private Context context;
    ArrayList<NutritionOrderBean.TypesBean> arrayList;
    OnRecyclerItemClickListener onRecyclerItemClickListener;


    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    public void setArrayList(ArrayList<NutritionOrderBean.TypesBean> arrayList) {
        this.arrayList = arrayList;
    }

    public OrderPopAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.order.setText(arrayList.get(position).getName());
        Log.d("OrderPopAdapter", "arrayList.get(position):" + arrayList.get(position).getName());
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRecyclerItemClickListener.onItemClick(position);
                onRecyclerItemClickListener.onItemClick(position,arrayList.get(position).getIndex());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView order;
        private LinearLayout ll;

        public ViewHolder(View itemView) {
            super(itemView);
            order = (TextView) itemView.findViewById(R.id.tv_order);
            ll = (LinearLayout) itemView.findViewById(R.id.ll_order);
        }
    }
}
