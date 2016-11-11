package lanou.foodpartydemo.library;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import lanou.foodpartydemo.R;
import lanou.foodpartydemo.base.BaseActivity;

/**
 * Created by dllo on 16/11/11.
 */
public class CompareActivity extends BaseActivity implements View.OnClickListener {

    private ImageView compareLeft;
    private ImageView compareRight;
    private TextView leftTv;
    private TextView rightTv;
    private String place;

    @Override
    protected void initData() {
        setOnClick(this,compareLeft,compareRight);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_CODE == resultCode && SearchActivity.RESULT_CODE == resultCode){
        if (place.equals("left")){
            leftTv.setText(data.getStringExtra("name"));
        }else {
            rightTv.setText(data.getStringExtra("name"));
        }
        }
    }



    @Override
    protected int getLayout() {
        return R.layout.activity_compare;
    }

    @Override
    protected void initViews() {
        compareLeft = bindView(R.id.compare_leftMessage);
        compareRight = bindView(R.id.compare_rightMessage);
        leftTv = bindView(R.id.compare_lefttitle);
        rightTv = bindView(R.id.compare_righttitle);
    }
public static final int RESULT_CODE = 101;
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.compare_leftMessage:
                Intent intent = new Intent(CompareActivity.this,SearchActivity.class);
                startActivityForResult(intent,RESULT_CODE);
                place = "left";
                break;
            case R.id.compare_rightMessage:
                Intent intent1 = new Intent(CompareActivity.this,SearchActivity.class);
                startActivityForResult(intent1,RESULT_CODE);
              place = "right";
                break;
        }
    }
}
