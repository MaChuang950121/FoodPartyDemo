package lanou.foodpartydemo.homepage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import lanou.foodpartydemo.R;
import lanou.foodpartydemo.bean.MyBean;
import lanou.foodpartydemo.tools.VolleySingle;

/**
 * Created by dllo on 16/11/3.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private Context context;
    List<MyBean.FeedsBean> arrayList;


    public void setArrayList(List<MyBean.FeedsBean> arrayList, boolean isRefresh) {
        if (isRefresh || this.arrayList == null){
            setArrayList(arrayList);
        }else{
            this.arrayList.addAll(arrayList);
            notifyDataSetChanged();
        }
    }
    public void setArrayList(List<MyBean.FeedsBean> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }


    public MyAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (arrayList.get(position).getImages().size() == 1){
            return 1;
        }
        else {
            return 2;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case 1:View view = LayoutInflater.from(context).inflate(R.layout.item_knowledge,parent,false);
                   ViewHolder viewHolder = new ViewHolder(view);
                   return viewHolder;
            case 2:
                View view1 = LayoutInflater.from(context).inflate(R.layout.item_knowledge2,parent,false);
                ViewHolder viewHolder1 = new ViewHolder(view1);
                return viewHolder1;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (arrayList.get(position).getImages().size() == 1){
        holder.title.setText(arrayList.get(position).getTitle());
        holder.source.setText(arrayList.get(position).getSource());
        holder.tail.setText(arrayList.get(position).getTail());
        VolleySingle.getVolleySingle().
                getImage(String.valueOf(arrayList.get(position).getImages().get(0)),holder.iv);
        }else {
            holder.lateralTitle.setText(arrayList.get(position).getTitle());
            holder.lateralSource.setText(arrayList.get(position).getSource());
            holder.lateralTail.setText(arrayList.get(position).getTail());
            VolleySingle.getVolleySingle().getImage(arrayList.get(position).getImages().get(0),holder.imLeft);
            VolleySingle.getVolleySingle().getImage(arrayList.get(position).getImages().get(1),holder.imMid);
            VolleySingle.getVolleySingle().getImage(arrayList.get(position).getImages().get(2),holder.imRight);
        }
    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView source;
        private final TextView tail;
        private final ImageView iv;
        private final TextView lateralTitle;
        private final ImageView imLeft;
        private final ImageView imMid;
        private final ImageView imRight;
        private final TextView lateralSource;
        private final TextView lateralTail;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            source = (TextView) itemView.findViewById(R.id.tv_source);
            tail = (TextView) itemView.findViewById(R.id.tv_tail);
            iv = (ImageView) itemView.findViewById(R.id.im_knowledge);
            lateralTitle = (TextView) itemView.findViewById(R.id.tv_lateral_title);
            imLeft = (ImageView) itemView.findViewById(R.id.im_left);
            imMid = (ImageView) itemView.findViewById(R.id.im_mid);
            imRight = (ImageView) itemView.findViewById(R.id.im_right);
            lateralSource = (TextView) itemView.findViewById(R.id.tv_lateral_source);
            lateralTail = (TextView) itemView.findViewById(R.id.tv_lateral_tail);
        }
    }
}
