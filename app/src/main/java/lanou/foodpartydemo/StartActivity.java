package lanou.foodpartydemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import lanou.foodpartydemo.base.BaseActivity;

/**
 * Created by dllo on 16/11/15.
 */
public class StartActivity extends BaseActivity{

    private TextView time;
    private ImageView ivStart;
    private CountDownTimer timer;

    @Override
    protected void initData() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.start);
        ivStart.setImageBitmap(bitmap);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this,MainActivity.class);
                startActivity(intent);
                timer.cancel();
                finish();
            }
        });
        timer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {
                time.setText(l / 1000 + "");
            }

            @Override
            public void onFinish() {
                time.setEnabled(true);
                Intent intent = new Intent(StartActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        };
        timer.start();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_start;
    }

    @Override
    protected void initViews() {
        ivStart = bindView(R.id.iv_start);
        time = bindView(R.id.id_time);
    }


}
