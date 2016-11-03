package lanou.foodpartydemo.homepage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lanou.foodpartydemo.R;
import lanou.foodpartydemo.bean.TestBean;
import lanou.foodpartydemo.tools.VolleySingle;

/**
 * Created by dllo on 16/11/3.
 */
public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder>{
    Context context;
   List<TestBean.FeedsBean> arrayList;

    public void setArrayList(List<TestBean.FeedsBean> arrayList,boolean isRefresh) {
        if (isRefresh || this.arrayList == null){
            setArrayList(arrayList);
        }else{
            this.arrayList.addAll(arrayList);
            notifyDataSetChanged();
        }
    }

    public void setArrayList(List<TestBean.FeedsBean> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    public TestAdapter(Context context) {
        this.context = context;
    }



    @Override
    public TestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_test,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TestAdapter.ViewHolder holder, int position) {
        VolleySingle.getVolleySingle().getImage(arrayList.get(position).getBackground(),holder.im);
        holder.source.setText(arrayList.get(position).getSource());
        holder.title.setText(arrayList.get(position).getTitle());
        holder.tail.setText(arrayList.get(position).getTail());
    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView im;
        private final TextView source;
        private final TextView title;
        private final TextView tail;

        public ViewHolder(View itemView) {
            super(itemView);
            im = (ImageView) itemView.findViewById(R.id.im_test);
            source = (TextView) itemView.findViewById(R.id.tv_test_source);
            title = (TextView) itemView.findViewById(R.id.tv_test_title);
            tail = (TextView) itemView.findViewById(R.id.tv_test_tail);
        }
    }
}
