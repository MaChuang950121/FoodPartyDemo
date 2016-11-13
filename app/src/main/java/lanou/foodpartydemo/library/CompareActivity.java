package lanou.foodpartydemo.library;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;

import lanou.foodpartydemo.R;
import lanou.foodpartydemo.base.BaseActivity;
import lanou.foodpartydemo.bean.CompareBean;
import lanou.foodpartydemo.tools.GsonRequest;
import lanou.foodpartydemo.tools.VolleySingle;

/**
 * Created by dllo on 16/11/11.
 */
public class CompareActivity extends BaseActivity implements View.OnClickListener {

    private ImageView compareLeft;
    private ImageView compareRight;
    private TextView leftTv;
    private TextView rightTv;
    private String place;
    public static final int RESULT_CODE = 101;
    private ImageView leftImage;
    private ImageView rightImage;
    private ImageButton leftClear;
    private ImageButton rightClear;
    private String leftCode;
    private String rightCode;
    private CompareAdapter compareAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void initData() {
        setOnClick(this,compareLeft,compareRight);
        compareAdapter = new CompareAdapter();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_CODE ==requestCode && SearchActivity.RESULT_CODE == resultCode){
        if (place.equals("left")){
            leftClear.setVisibility(View.VISIBLE);
            leftTv.setText(data.getStringExtra("name"));
            leftCode = data.getStringExtra("code");
            getGson("http://food.boohee.com/fb/v1/foods/"+leftCode+"/brief?app_de" +
                    "vice=Android&app_version=2.6&channel=baidu&user_key=90026eec-a1ef-44ff-87bb" +
                    "-e196d7b2848f&token=WDQy4wnxCkVbEy2zG4cB&phone_model=ZTE+N909&os_version=4.1.2%20HTTP/1.1");
            recyclerView.setAdapter(compareAdapter);
            LinearLayoutManager manager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(manager);
            VolleySingle.getVolleySingle().getImage(data.getStringExtra("image"),leftImage);
        }else {
            rightClear.setVisibility(View.VISIBLE);
            rightTv.setText(data.getStringExtra("name"));
            rightCode = data.getStringExtra("code");
            getGson("http://food.boohee.com/fb/v1/foods/"+rightCode+"/brief?app_device=Android&" +
                    "app_version=2.6&channel=baidu&user_key=90026eec-a1ef-44ff-87bb-e196d7b2848f&" +
                    "token=WDQy4wnxCkVbEy2zG4cB&phone_model=ZTE+N909&os_version=4.1.2%20HTTP/1.1");
            recyclerView.setAdapter(compareAdapter);
            LinearLayoutManager manager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(manager);
            VolleySingle.getVolleySingle().getImage(data.getStringExtra("image"),rightImage);
        }

        }
    }
 public void getGson(String url){
     GsonRequest<CompareBean> gsonRequest = new GsonRequest<CompareBean>(CompareBean.class, url,
             new Response.Listener<CompareBean>() {
         @Override
         public void onResponse(CompareBean response) {
                compareAdapter.setArrayList((ArrayList<CompareBean.NutritionBean>) response.getNutrition());
         }
     }, new Response.ErrorListener() {
         @Override
         public void onErrorResponse(VolleyError error) {

         }
     });
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
        leftImage = bindView(R.id.compare_leftMessage);
        rightImage = bindView(R.id.compare_rightMessage);
        leftClear = bindView(R.id.compare_leftclear);
        rightClear = bindView(R.id.compare_rightclear);
        recyclerView = bindView(R.id.compare_rv);
    }

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
