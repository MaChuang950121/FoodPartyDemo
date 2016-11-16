package lanou.foodpartydemo.mine;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import org.w3c.dom.Text;

import lanou.foodpartydemo.R;
import lanou.foodpartydemo.base.BaseActivity;
import lanou.foodpartydemo.tools.VolleySingle;

/**
 * Created by dllo on 16/11/16.
 */
public class ModifyUserActivity extends BaseActivity implements View.OnClickListener {

    private ImageView userIcon;
    private TextView userName;
    private ImageView back;
    private TextView sex;
    private PopupWindow popupWindow;

    @Override
    protected void initData() {
        String name =  getIntent().getStringExtra("name");
        String icon = getIntent().getStringExtra("icon");
        userName.setText(name);
        VolleySingle.getVolleySingle().getCircleImg(icon,userIcon);
        setOnClick(this,back,sex);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_modify_user;
    }

    @Override
    protected void initViews() {
        userIcon = bindView(R.id.im_user_icon);
        userName = bindView(R.id.tv_user_name);
        back = bindView(R.id.modify_user_back);
        sex = bindView(R.id.sex);
        View pop = LayoutInflater.from(this).inflate(R.layout.sex_pop,null);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.modify_user_back:
                onBackPressed();
                break;
            case R.id.sex:

                break;
        }
    }
    private void sexPop(){
        popupWindow = new PopupWindow();
    }
}
