package lanou.foodpartydemo.tools;

import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import lanou.foodpartydemo.R;
import lanou.foodpartydemo.base.MyApp;


/**
 * Created by dllo on 16/10/25.
 */
public class VolleySingle {
    private static VolleySingle volleySingle;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    private VolleySingle() {
        requestQueue = Volley.newRequestQueue(MyApp.getContext());
        imageLoader = new ImageLoader(requestQueue, new MemoryCache());
    }

    public static VolleySingle getVolleySingle() {
        if (volleySingle == null) {
            synchronized (VolleySingle.class) {
                if (volleySingle == null) {
                    volleySingle = new VolleySingle();
                }

            }
        }
        return volleySingle;
    }

    public void getImage(String url, ImageView imageView) {
        ImageLoader.ImageListener imageListener =
                ImageLoader.getImageListener(imageView, R.mipmap.ic_launcher, R.mipmap.ic_launcher);

        imageLoader.get(url, imageListener);
    }

    public <T> void addRequest(Request<T> request) {
        requestQueue.add(request);
    }
}
