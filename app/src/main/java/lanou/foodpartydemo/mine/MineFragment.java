package lanou.foodpartydemo.mine;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import lanou.foodpartydemo.R;
import lanou.foodpartydemo.base.BaseFragment;


/**
 * Created by dllo on 16/11/1.
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {

    private TextView login;
    private View collection;


    @Override
    protected void addAdapter() {

    }

    @Override
    protected void initData() {
        setItemOnClick(this,login,collection);
    }

    @Override
    protected void addFragmentArrayList() {

    }

    @Override
    protected void initView() {
        login = bindView(R.id.login);
        collection = bindView(R.id.collection);

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                Intent intent = new Intent(getContext(),LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.collection:
                Intent intent1 = new Intent(getContext(),CollectionActivity.class);
                startActivity(intent1);
                break;

        }
    }
}
