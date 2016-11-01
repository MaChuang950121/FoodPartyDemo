package lanou.foodpartydemo.homepage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import lanou.foodpartydemo.R;
import lanou.foodpartydemo.bean.FirstPageBean;
import lanou.foodpartydemo.tools.CircleImageView;
import lanou.foodpartydemo.tools.VolleySingle;

/**
 * Created by dllo on 16/11/1.
 */
public class FirstPageAdapter extends RecyclerView.Adapter<FirstPageAdapter.MyViewHolder>{
    private Context context;
    ArrayList<FirstPageBean.FeedsBean> arrayList = new ArrayList<>();

    public FirstPageAdapter(Context context) {
        this.context = context;
    }

    public void setArrayList(ArrayList<FirstPageBean.FeedsBean> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_first_page,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        VolleySingle.getVolleySingle().getImage(arrayList.get(position).getCard_image(),holder.cardImage);
        holder.title.setText(arrayList.get(position).getTitle());
        holder.description.setText(arrayList.get(position).getDescription());
        VolleySingle.getVolleySingle().getImage(arrayList.get(position).getPublisher_avatar(),holder.publisherIamge);
        holder.publisher.setText(arrayList.get(position).getPublisher());
        holder.like.setText(String.valueOf(arrayList.get(position).getLike_ct()));
    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 :arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView cardImage;
        private final TextView title;
        private final TextView description;
        private final CircleImageView publisherIamge;
        private final TextView publisher;
        private final TextView like;

        public MyViewHolder(View itemView) {
            super(itemView);
            cardImage = (ImageView) itemView.findViewById(R.id.homepage_item_cardimage);
            title = (TextView) itemView.findViewById(R.id.homepage_item_title);
            description = (TextView) itemView.findViewById(R.id.homepage_item_description);
            publisherIamge = (CircleImageView) itemView.findViewById(R.id.homepage_item_publisherimage);
            publisher = (TextView) itemView.findViewById(R.id.homepage_item_publisher);
            like = (TextView) itemView.findViewById(R.id.homepage_item_like);

        }
    }
}
