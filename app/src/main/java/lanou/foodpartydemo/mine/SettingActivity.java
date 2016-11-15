package lanou.foodpartydemo.mine;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import lanou.foodpartydemo.R;
import lanou.foodpartydemo.base.BaseActivity;

/**
 * Created by dllo on 16/11/15.
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back;
    private Button leave;
    private PlatformActionListener platformActionListener;

    @Override
    protected void initData() {
        setOnClick(this,back,leave);
        platformActionListener = new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                //输出所有授权信息
                platform.getDb().exportData();
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        };
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initViews() {
        back = bindView(R.id.setting_title_back);
        leave = bindView(R.id.btn_leave);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.setting_title_back:
                onBackPressed();
                break;
            case R.id.btn_leave:
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                if (qq.isAuthValid()) {
                    qq.removeAccount(true);
            }

                qq.setPlatformActionListener(platformActionListener);
//authorize与showUser单独调用一个即可
            qq.authorize();//单独授权，OnComplete返回的hashmap是空的
            qq.showUser(null);//授权并获取用户信息
//isValid和removeAccount不开启线程，会直接返回。
//                qzone.removeAccount(true);
                break;

        }
    }
}
