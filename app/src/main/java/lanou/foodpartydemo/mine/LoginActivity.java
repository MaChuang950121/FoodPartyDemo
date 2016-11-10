package lanou.foodpartydemo.mine;

import android.view.View;
import android.widget.ImageView;

import lanou.foodpartydemo.R;
import lanou.foodpartydemo.base.BaseActivity;

/**
 * Created by dllo on 16/11/10.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back;

    @Override
    protected void initData() {
        setOnClick(this,back);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        back = bindView(R.id.login_back);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_back:
                onBackPressed();
                break;
        }
    }
}
