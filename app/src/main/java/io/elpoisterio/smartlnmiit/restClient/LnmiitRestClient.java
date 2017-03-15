package io.elpoisterio.smartlnmiit.restClient;

import android.content.Context;
import android.os.Looper;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;
import com.loopj.android.http.SyncHttpClient;

import cz.msebera.android.httpclient.Header;
import io.elpoisterio.smartlnmiit.utilities.ApplicationConstant;


/**
 * Created by rishabh on 21/12/15.
 */
public class LnmiitRestClient {



    private static AsyncHttpClient client = new AsyncHttpClient();


    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        if (params == null)
            params = new RequestParams();
        params.put("app", "android");
        getClient().get(url, params, responseHandler);
    }
    public static void getWithoutHeaders(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        if (params == null)
            params = new RequestParams();
        params.put("app", "android");
        getClient().removeHeader(ApplicationConstant.header);
        getClient().removeAllHeaders();
        getClient().get(url, params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        if (params == null)
            params = new RequestParams();
        params.put("app", "android");
        getClient().post(url, params, responseHandler);
    }
    public static void postLogin(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        if (params == null)
            params = new RequestParams();
        params.put("app", "android");
        getClient().removeHeader(ApplicationConstant.header);
        getClient().removeAllHeaders();
        getClient().post(url, params, responseHandler);
    }

    public static void get(Context context, String url, Header[] headers, RequestParams params, ResponseHandlerInterface responseHandler) {
        if (params == null)
            params = new RequestParams();
        params.put("app", "android");
        // getClient().addHeader(ApplicationConstant.header,ApplicationConstant.getHeaderAuth(context));
        getClient().get(context, url, headers, params, responseHandler);
    }

    public static void addheaders(String header, String value) {
        getClient().addHeader(header, value);
    }

    private static AsyncHttpClient getClient() {
        // Return the synchronous HTTP client when the thread is not prepared
        if (Looper.myLooper() == null) {
            client = new SyncHttpClient();
            client.addHeader(ApplicationConstant.header, ApplicationConstant.AUTH_TOKEN);
        }
        return client;
    }



    public static void setTimeOutForImage() {
        getClient().setTimeout(30000);
    }



}
