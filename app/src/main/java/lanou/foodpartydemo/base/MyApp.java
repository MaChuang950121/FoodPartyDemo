package lanou.foodpartydemo.base;

import android.app.Application;
import android.content.Context;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by dllo on 16/10/25.
 */
public class MyApp extends Application{
    private static Context scontext;
    @Override
    public void onCreate() {
        super.onCreate();
        scontext = this;
        ShareSDK.initSDK(this,"190ea16813c60");
    }
    public static Context getContext(){return scontext;}
}
