package lanou.foodpartydemo.library;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import lanou.foodpartydemo.R;


/**
 * Created by dllo on 16/11/5.
 */
public class PopAdapter extends BaseAdapter{
    private Context context;
    ArrayList<String> arrayList ;

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    public PopAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        PopViewHolder viewHolder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_lv_pop,null);
            viewHolder = new PopViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder = (PopViewHolder) view.getTag();
        }
        viewHolder.popAll.setText(arrayList.get(i).toString());
        return view;
    }

    private class PopViewHolder {

        private  TextView popAll;

        public PopViewHolder(View view) {
            popAll = (TextView) view.findViewById(R.id.tv_pop_all);
        }
    }
}
