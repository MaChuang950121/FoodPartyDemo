package lanou.foodpartydemo.tools;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 *
 * Created by dllo on 16/10/31.
 */
public class CommonVH extends RecyclerView.ViewHolder{
    //SparseArray用法和HashMap一样
    //但是key固定是int类型
    //用它来存放所有的View,key就是View的id
    private SparseArray<View> views;
    private View itemView;//行布局

    public CommonVH(View itemView) {
        super(itemView);
        this.itemView = itemView;
        views = new SparseArray<>();
    }
    //通过view的id来获得指定的view
    //如果该view没有赋值,就执行findviewbyid
    //然后把它放到view的集合里
    //使用泛型来取消强转
    public <T extends View> T getView(int id){
        View view = views.get(id);
        if (view == null){
            //证明SparseArray里没有这个view
            view = itemView.findViewById(id);
            views.put(id,view);
        }
        return (T) view;
    }
    //专门给ListView使用的方法
    public static CommonVH getViewHolder(View itemView, ViewGroup parent,int itemId){
        CommonVH viewHolder = null;
        if (itemView == null){
            Context context = parent.getContext();
            itemView = LayoutInflater.from(context).inflate(itemId,parent,false);
            viewHolder = new CommonVH(itemView);
            itemView.setTag(viewHolder);
        }else{
            viewHolder = (CommonVH) itemView.getTag();
        }
        return viewHolder;
    }
    //专门给RecycleView使用的方法
    public static CommonVH getViewHolder(ViewGroup parent,int itemId){
        return getViewHolder(null,parent,itemId);
    }
    //返回行布局

    public View getItemView() {
        return itemView;
    }

    /**************ViewHolder设置数据的方法**************/
    //设置文字
    public CommonVH setText(int id,String text){
        TextView textView = getView(id);
        textView.setText(text);
        return this;
    }
    public CommonVH setImage(int id,int imageId){
        ImageView imageView = getView(id);
        imageView.setImageResource(imageId);
        return this;
    }
    public CommonVH setImage(int id,String url){
        ImageView imageView = getView(id);
        VolleySingle.getVolleySingle().getImage(url,imageView);
        return this;
    }
    public CommonVH setCircleImage(int id,String url){
        ImageView imageView = getView(id);
        VolleySingle.getVolleySingle().getCircleImg(url,imageView);
        return this;
    }
    public CommonVH setViewClick(int id, View.OnClickListener listener){
        getView(id).setOnClickListener(listener);
        return this;
    }
    public CommonVH setItemClick(View.OnClickListener listener){
        itemView.setOnClickListener(listener);
        return this;
    }

}
