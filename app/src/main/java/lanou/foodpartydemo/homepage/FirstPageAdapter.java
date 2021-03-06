package lanou.foodpartydemo.homepage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lanou.foodpartydemo.R;
import lanou.foodpartydemo.bean.FirstPageBean;
import lanou.foodpartydemo.tools.CircleImageView;
import lanou.foodpartydemo.tools.OnRecyclerItemClickListener;
import lanou.foodpartydemo.tools.VolleySingle;

/**
 * Created by dllo on 16/11/1.
 */
public class FirstPageAdapter extends RecyclerView.Adapter<FirstPageAdapter.MyViewHolder>{
    private Context context;
    List<FirstPageBean.FeedsBean> arrayList ;
    OnRecyclerItemClickListener onRecyclerItemClickListener;

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    public FirstPageAdapter(Context context) {
        this.context = context;
    }

    /**
     * 添加数据
     * @param arrayList
     * @param isRefresh true 刷新数据 false 加载数据
     */
    public void setArrayList(List<FirstPageBean.FeedsBean> arrayList,boolean isRefresh) {
        if(isRefresh || this.arrayList == null){
            setArrayList(arrayList);
        }else {
            this.arrayList.addAll(arrayList);
            notifyDataSetChanged();
        }

    }
    public void setArrayList(List<FirstPageBean.FeedsBean> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_first_page,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        VolleySingle.getVolleySingle().getImage(arrayList.get(position).getCard_image(),holder.cardImage);
        holder.title.setText(arrayList.get(position).getTitle());
        holder.description.setText(arrayList.get(position).getDescription());
        //VolleySingle.getVolleySingle().getImage(arrayList.get(position).getPublisher_avatar(),holder.publisherIamge);
        holder.publisher.setText(arrayList.get(position).getPublisher());
        holder.like.setText(String.valueOf(arrayList.get(position).getLike_ct()));
        VolleySingle.getVolleySingle().getCircleImg(arrayList.get(position).getPublisher_avatar(),holder.publishImage);
        holder.rlFirstpage.setOnClickListener(new View.OnClickListener() {
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

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private  ImageView cardImage;
        private  TextView title;
        private  TextView description;

        private  TextView publisher;
        private  TextView like;
        private  ImageView publishImage;
        private  RelativeLayout rlFirstpage;

        public MyViewHolder(View itemView) {
            super(itemView);
            cardImage = (ImageView) itemView.findViewById(R.id.homepage_item_cardimage);
            title = (TextView) itemView.findViewById(R.id.homepage_item_title);
            description = (TextView) itemView.findViewById(R.id.homepage_item_description);
            publishImage = (ImageView) itemView.findViewById(R.id.homepage_item_publisherimage);
            publisher = (TextView) itemView.findViewById(R.id.homepage_item_publisher);
            like = (TextView) itemView.findViewById(R.id.homepage_item_like);
            rlFirstpage = (RelativeLayout) itemView.findViewById(R.id.rl_firstpage);

        }
    }
}
