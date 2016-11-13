package lanou.foodpartydemo.library;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import lanou.foodpartydemo.R;
import lanou.foodpartydemo.bean.CompareBean;
import lanou.foodpartydemo.tools.CommonVH;

/**
 * Created by dllo on 16/11/13.
 */
public class CompareAdapter extends RecyclerView.Adapter<CommonVH>{
    ArrayList<CompareBean.NutritionBean> arrayList;

    public void setArrayList(ArrayList<CompareBean.NutritionBean> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public int getItemViewType(int position) {
        return arrayList.get(position).getType();
    }

    @Override
    public CommonVH onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case 1 :
                return CommonVH.getViewHolder(parent, R.layout.item_left_compare);
            case 2 :
                return CommonVH.getViewHolder(parent,R.layout.item_right_compare);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(CommonVH holder, int position) {
        int type = getItemViewType(position);
        switch (type){
            case 1 :
                holder.setText(R.id.leftText,arrayList.get(position).getValue()+arrayList.get(position).getUnit());
                holder.setText(R.id.left_center_text,arrayList.get(position).getName());
                break;
            case 2 :
                holder.setText(R.id.rightText,arrayList.get(position).getValue()+arrayList.get(position).getUnit());
                holder.setText(R.id.right_center_text,arrayList.get(position).getName());
                break;
        }

        TextView left = holder.getView(R.id.left_center_text);
        TextView right = holder.getView(R.id.right_center_text);
        if (left.getText().toString().equals(right.getText().toString())){
            TextView textView = holder.getView(R.id.rightText);
            holder.setText(R.id.for_right,textView.getText().toString());
        }


    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }
}
