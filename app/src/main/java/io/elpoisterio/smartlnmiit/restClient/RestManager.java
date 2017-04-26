package io.elpoisterio.smartlnmiit.restClient;

import android.content.Context;
import android.os.Handler;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import io.elpoisterio.smartlnmiit.utilities.AppPreferences;
import io.elpoisterio.smartlnmiit.utilities.ApplicationConstant;
import io.elpoisterio.smartlnmiit.utilities.HandlerConstant;

/**
 * Created by rishabh on 17/3/17.
 */

public class RestManager {

    private static RestManager restManager;

    public RestManager (){

    }
    public RestManager getInstance(){
        if(restManager == null)
            restManager = new RestManager();
        return restManager;
    }

    public void login(final Context context, String email, String password, final Handler handler){

        RequestParams params = new RequestParams();
        params.put("email", email);
        params.put("password", password);
        LnmiitRestClient.post(ApplicationConstant.login,params, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                if(response.has("token"))
                    try {
                        String token = response.getString("token");
                        AppPreferences.writeString(context, "token", token);
                        HandlerConstant.sendMessage(handler, HandlerConstant.SUCCESS);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                HandlerConstant.sendMessage(handler, HandlerConstant.FAILURE);
            }
        });

    }

    public void signUp(final  Context context, RequestParams params, final Handler handler){

        LnmiitRestClient.post(ApplicationConstant.signUp, params,new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                if(response.has("token")){
                    String token = null;
                    try {
                        token = response.getString("token");
                        AppPreferences.writeString(context, "token", token);
                        HandlerConstant.sendMessage(handler, HandlerConstant.SUCCESS);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                HandlerConstant.sendMessage(handler, HandlerConstant.FAILURE);
            }
        });

    }

    public void upoloadGrades(final Context context, RequestParams params, final Handler handler) {
        LnmiitRestClient.post(ApplicationConstant.uploadGrade, params, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if(response.has("success") && response.getString("success").equals("true")){
                        HandlerConstant.sendMessage(handler, HandlerConstant.SUCCESS);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                HandlerConstant.sendMessage(handler, HandlerConstant.FAILURE);

            }
        });
    }
    public void sendApplication(final Context context, RequestParams params, final Handler handler) {

        LnmiitRestClient.post(ApplicationConstant.uploadApplication,params, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if(response.has("success") && response.getString("success").equals("true")){
                        HandlerConstant.sendMessage(handler, HandlerConstant.SUCCESS);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                HandlerConstant.sendMessage(handler, HandlerConstant.FAILURE);
            }
        });
    }
}
