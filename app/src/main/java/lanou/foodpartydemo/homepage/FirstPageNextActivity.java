package lanou.foodpartydemo.homepage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import lanou.foodpartydemo.R;
import lanou.foodpartydemo.base.BaseActivity;
import lanou.foodpartydemo.bean.FirstPageBean;
import lanou.foodpartydemo.tools.GsonRequest;
import lanou.foodpartydemo.tools.UrlValues;
import lanou.foodpartydemo.tools.VolleySingle;

/**
 * Created by dllo on 16/11/8.
 */
public class FirstPageNextActivity extends BaseActivity{

    private ImageView publisherImage;
    private TextView author;
    private ImageView imageContent;
    private TextView agree;
    private ImageView back;

    @Override
    protected void initData() {
        String name = getIntent().getStringExtra("name");
        String publisher = getIntent().getStringExtra("publisher");
        String image = getIntent().getStringExtra("image");
        String like = getIntent().getStringExtra("like");
        VolleySingle.getVolleySingle().getCircleImg(publisher,publisherImage);
        VolleySingle.getVolleySingle().getImage(image,imageContent);
        author.setText(name);
        agree.setText(like);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_firstpage_next;
    }

    @Override
    protected void initViews() {
        publisherImage = bindView(R.id.im_author);
        author = bindView(R.id.tv_author);
        imageContent = bindView(R.id.im_content);
        agree = bindView(R.id.tv_agree);
        back = (ImageView) findViewById(R.id.fistpage_back);
    }
}
