package lanou.foodpartydemo.library;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import lanou.foodpartydemo.R;
import lanou.foodpartydemo.bean.LibraryBean;
import lanou.foodpartydemo.tools.OnItemClickListener;
import lanou.foodpartydemo.tools.VolleySingle;

/**
 * Created by dllo on 16/11/1.
 */
public class LibraryAdapter extends BaseAdapter {
    private Context context;
    ArrayList<LibraryBean.GroupBean.CategoriesBean> arrayList;

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public LibraryAdapter(Context context) {
        this.context = context;
    }

    public void setArrayList(ArrayList<LibraryBean.GroupBean.CategoriesBean> arrayList) {
        this.arrayList = arrayList;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_library,viewGroup,false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.libraryTv.setText(arrayList.get(i).getName());
        VolleySingle.getVolleySingle().getImage(arrayList.get(i).getImage_url(),viewHolder.libraryIm);

        return view;
    }

    private class ViewHolder {

        private  ImageView libraryIm;
        private  TextView libraryTv;
        private final LinearLayout ll;

        public ViewHolder(View view) {
            libraryIm = (ImageView) view.findViewById(R.id.im_library);
            libraryTv = (TextView) view.findViewById(R.id.tv_library);
            ll = (LinearLayout) view.findViewById(R.id.ll_library);
        }
    }
}
