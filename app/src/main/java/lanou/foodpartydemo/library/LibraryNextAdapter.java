package lanou.foodpartydemo.library;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import lanou.foodpartydemo.R;
import lanou.foodpartydemo.bean.LibraryNextBean;
import lanou.foodpartydemo.tools.CircleDrawable;
import lanou.foodpartydemo.tools.VolleySingle;

/**
 * Created by dllo on 16/11/7.
 */
public class LibraryNextAdapter extends BaseAdapter{
    private Context context;
    List<LibraryNextBean.FoodsBean> list;

    public void setList(List<LibraryNextBean.FoodsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public void setList(List<LibraryNextBean.FoodsBean> list,boolean isRefresh) {
        if (isRefresh || this.list == null){
            setList(list);
        }else {
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    public LibraryNextAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
         ViewHolder viewHolder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_library_next,null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.nameNext.setText(list.get(i).getName());
        viewHolder.calory.setText(list.get(i).getCalory());
        viewHolder.weight.setText(list.get(i).getWeight());
        VolleySingle.getVolleySingle().getImage(list.get(i).getThumb_image_url(),viewHolder.libNext);
//        Bitmap nextBitmap = BitmapFactory.decodeResource(context.getResources(),R.id.im_library_next);
//        CircleDrawable circleDrawable = new CircleDrawable(nextBitmap);
//        viewHolder.libNext.setImageDrawable(circleDrawable);
        if (list.get(i).getHealth_light() == 1){
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_food_light_green);
            viewHolder.point.setImageBitmap(bitmap);
        }else if (list.get(i).getHealth_light() == 2){
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_food_light_yellow);
            viewHolder.point.setImageBitmap(bitmap);
        }else {
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_food_light_red);
            viewHolder.point.setImageBitmap(bitmap);
        }
        return view;
    }
    public class ViewHolder{

        private  ImageView libNext;
        private  TextView nameNext;
        private  TextView calory;
        private  ImageView point;
        private final TextView weight;

        public ViewHolder(View view) {
            libNext = (ImageView) view.findViewById(R.id.im_library_next);
            nameNext = (TextView) view.findViewById(R.id.tv_name);
            calory = (TextView) view.findViewById(R.id.tv_calory);
            point = (ImageView) view.findViewById(R.id.im_point);
            weight = (TextView) view.findViewById(R.id.tv_weight);

        }
    }
}
