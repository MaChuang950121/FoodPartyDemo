package lanou.foodpartydemo.tools;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;


public class GsonRequest<T> extends Request<T> {
    private final Response.Listener<T> mListener;
    private Gson mGson;
    private Class<T> mTClass;

    public GsonRequest(Class<T> tClass
            , String url, Response.Listener<T> mListener,
                       Response.ErrorListener listener) {

        this(Method.GET,tClass, url, mListener, listener);
    }

    public GsonRequest(int method,Class<T> tClass
            , String url, Response.Listener<T> mListener,
                       Response.ErrorListener listener) {
        super(method, url, listener);

        this.mListener = mListener;
        mGson = new Gson();
        this.mTClass = tClass;
    }


    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        T t = mGson.fromJson(parsed, mTClass);
        return Response.success(t, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
}
