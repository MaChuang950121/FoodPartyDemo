package lanou.foodpartydemo.mine;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import lanou.foodpartydemo.R;
import lanou.foodpartydemo.base.BaseFragment;
import lanou.foodpartydemo.tools.VolleySingle;


/**
 * Created by dllo on 16/11/1.
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {

    private View collection;
    private TextView btnLogIn;
    private TextView maNameTv;
    private TextView myDataBtn;
    private ImageView userIcon;
    private ImageView setting;


    @Override
    protected void addAdapter() {

    }

    @Override
    protected void initData() {
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
        try {

            PlatformDb platformDb = qq.getDb();
           String name = platformDb.getUserName();
           String icon = platformDb.getUserIcon();

            if (!TextUtils.isEmpty(name)) {
                btnLogIn.setVisibility(View.GONE);
                maNameTv.setVisibility(View.VISIBLE);
                myDataBtn.setVisibility(View.VISIBLE);
                maNameTv.setText(name);
                VolleySingle.getVolleySingle().getCircleImg(icon,userIcon);
            }
        } catch (NullPointerException e) {

        }
        setItemOnClick(this,btnLogIn,collection,setting);
    }

    @Override
    protected void addFragmentArrayList() {

    }

    @Override
    protected void initView() {
        collection = bindView(R.id.collection);
        btnLogIn = bindView(R.id.login);
        maNameTv = bindView(R.id.user_name);
        myDataBtn = bindView(R.id.modify_user);
        userIcon = bindView(R.id.user_icon);
        setting = bindView(R.id.iv_setting);

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
                startActivityForResult(intent,1);
                break;
            case R.id.collection:
                Intent intent1 = new Intent(getContext(),CollectionActivity.class);
                startActivity(intent1);
                break;
            case R.id.iv_setting:
                Intent intent2  = new Intent(getContext(),SettingActivity.class);
                startActivityForResult(intent2,1);
                break;

        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        Log.d("MyFragment", "resultCode:" + resultCode);
        if (requestCode == 1 && LoginActivity.RESULT == resultCode) {
            String name = data.getStringExtra("name");
            Log.d("MyFragment123", name);
            String icon = data.getStringExtra("icon");
            Log.d("MyFragment123", icon);
            btnLogIn.setVisibility(View.GONE);
            maNameTv.setVisibility(View.VISIBLE);
            myDataBtn.setVisibility(View.VISIBLE);
            maNameTv.setText(name);
            VolleySingle.getVolleySingle().getCircleImg(icon,userIcon);

        }

    }
}
