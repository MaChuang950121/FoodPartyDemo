package lanou.foodpartydemo.mine;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import lanou.foodpartydemo.R;
import lanou.foodpartydemo.base.BaseActivity;

/**
 * Created by dllo on 16/11/10.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back;
    private ImageView qq;
    public static int RESULT = 0;

    @Override
    protected void initData() {
        setOnClick(this,back,qq);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        back = bindView(R.id.login_back);
        qq = bindView(R.id.login_qq);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_back:
                onBackPressed();
                break;
            case R.id.login_qq:
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                qq.authorize();
                qq.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        PlatformDb platformDb = platform.getDb();
                        String name = platformDb.getUserName();
                        Log.d("LoginActivity", name);
                        String icon = platformDb.getUserIcon();
                        Intent intent = new Intent();
                        intent.putExtra("name", name);
                        intent.putExtra("icon", icon);
                        setResult(RESULT, intent);
                        finish();
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {

                    }
                });
                break;
        }
    }
}
