package lanou.foodpartydemo.homepage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import lanou.foodpartydemo.R;
import lanou.foodpartydemo.bean.CollectionSqlBean;
import lanou.foodpartydemo.bean.CompareBean;
import lanou.foodpartydemo.tools.VolleySingle;
import lanou.foodpartydemo.tools.sqltools.DBTool;

/**
 * Created by dllo on 16/11/4.
 */
public class WebActivity extends AppCompatActivity {

    private WebView webView;
    private ImageView back;
    private TextView tvCollection;
    private ImageView ivCollection;
    private String url;
    private String title;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        webView = (WebView) findViewById(R.id.web_view);
        tvCollection = (TextView) findViewById(R.id.strolleatweb_collectText);
        ivCollection = (ImageView) findViewById(R.id.strolleatweb_heart);
        webView.setWebChromeClient(new WebChromeClient());
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.loadUrl(url);
        back = (ImageView) findViewById(R.id.web_title_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        DBTool dbTool = new DBTool();
        dbTool.queryAllData(CollectionSqlBean.class, new DBTool.OnQueryListener<CollectionSqlBean>() {
            @Override
            public void onQuery(ArrayList<CollectionSqlBean> collectionSqlBeen) {
                for (int i = 0; i < collectionSqlBeen.size(); i++) {
                    if (collectionSqlBeen.get(i).getLink().equals(url)){
                        Bitmap bitmap = BitmapFactory.
                                decodeResource(getResources(), R.mipmap.ic_news_keep_heighlight);
                        ivCollection.setImageBitmap(bitmap);
                        tvCollection.setText("已收藏");
                    }
                }
            }
        });
            tvCollection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (tvCollection.getText().equals("收藏")) {
                        DBTool dbTool = new DBTool();
                        CollectionSqlBean bean =  new CollectionSqlBean();
                        bean.setTitle(title);
                        bean.setLink(url);
                        dbTool.insert(bean);
                        Bitmap bitmap = BitmapFactory.
                                decodeResource(getResources(), R.mipmap.ic_news_keep_heighlight);
                        ivCollection.setImageBitmap(bitmap);
                        tvCollection.setText("已收藏");


                    }else if (tvCollection.getText().toString().equals("已收藏")){
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_favorate_unchecked);
                        ivCollection.setImageBitmap(bitmap);
                        tvCollection.setText("收藏");
                        DBTool dbTool = new DBTool();
                        dbTool.deleteWebCollect(url);
                        Intent intent = new Intent("lanou.foodpartydemo.homepage");
                        sendBroadcast(intent);

//                        dbTool.deleteAllData(CollectionSqlBean.class);

                    }

                }
            });


    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }


}
