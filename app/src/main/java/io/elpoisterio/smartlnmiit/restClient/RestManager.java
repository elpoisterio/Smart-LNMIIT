package io.elpoisterio.smartlnmiit.restClient;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cz.msebera.android.httpclient.Header;
import io.elpoisterio.smartlnmiit.models.ModelApplicationSend;
import io.elpoisterio.smartlnmiit.models.ModelTeacher;
import io.elpoisterio.smartlnmiit.utilities.AppPreferences;
import io.elpoisterio.smartlnmiit.utilities.ApplicationConstant;
import io.elpoisterio.smartlnmiit.utilities.HandlerConstant;

import static android.R.attr.key;

/**
 * Created by rishabh on 17/3/17.
 */

public class RestManager {

    private static RestManager restManager;

    public RestManager() {

    }

    public RestManager getInstance() {
        if (restManager == null)
            restManager = new RestManager();
        return restManager;
    }

    public void login(final Context context, RequestParams params, final Handler handler) {


        LnmiitRestClient.post(ApplicationConstant.login, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                if (response.has("_id"))
                    try {
                        String token = response.optString("_id");
                        AppPreferences.writeString(context, "_id", token);
                        AppPreferences.writeBoolean(context, "loggedIn", true);
                        AppPreferences.writeString(context, "role", response.optString("role"));
                        AppPreferences.writeString(context, "email", response.optString("email"));
                        HandlerConstant.sendMessage(handler, HandlerConstant.SUCCESS);
                    } catch (Exception e) {
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

    public void signUp(final Context context, RequestParams params, final Handler handler) {

        LnmiitRestClient.post(ApplicationConstant.signUp, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                if (response.has("_id")) {
                    String token = null;
                    try {
                        token = response.getString("_id");
                        AppPreferences.writeString(context, "_id", token);
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
        LnmiitRestClient.post(ApplicationConstant.uploadGrade, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if (response.has("success") && response.getString("success").equals("true")) {
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

        LnmiitRestClient.post(ApplicationConstant.uploadApplication, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if (statusCode == 200) {
                        HandlerConstant.sendMessage(handler, HandlerConstant.SUCCESS);
                    }
                } catch (Exception e) {
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

    public void sendFirebaseToken(final Context context, final String token) {
        String oldtoken = AppPreferences.readString(context, "firebase_key", null);
        RequestParams params = new RequestParams();
        params.put("firebase_key", token);
        params.put("old_key", oldtoken);

        LnmiitRestClient.post(ApplicationConstant.postfirebaseKey, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                if (response.has("success") && response.optString("success").equals("true")) {
                    Log.i("key", "success");
                    AppPreferences.writeString(context, "firebase_key", token);
                    AppPreferences.writeBoolean(context, "firebase_reg", true);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                AppPreferences.writeBoolean(context, "firebase_reg", false);
                Log.i("firebase_error", responseString);
            }
        });

    }

    public void getProfile(final Context context, String rollNumber, final Handler handler) {

        String url = ApplicationConstant.getUserProfile + "/" + rollNumber;
        LnmiitRestClient.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if (response.has("_id")) {
                        response.getString("email");
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

    public void editProfile(final Context context, RequestParams params, final Handler handler) {

        LnmiitRestClient.get(ApplicationConstant.editProfile, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if (response.has("success") && response.getString("success").equals("true")) {
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

    public void getAllTeacher(final Context context, final Handler handler) {

        LnmiitRestClient.get(ApplicationConstant.getAllTeacher, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    getJsonArray(response);
                    HandlerConstant.sendMessage(handler, HandlerConstant.SUCCESS);
                } catch (Exception e) {
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



    private void getJsonArray( JSONObject response) {

        Iterator keys = response.keys();
        while (keys.hasNext()){
            String currentKey = (String)keys.next();
            try {
                JSONArray teacherArray = response.getJSONArray(currentKey);
                for (int j = 0; j < teacherArray.length(); j++) {
                    JSONObject teacher = teacherArray.getJSONObject(j);
                    String name = teacher.optString("name");
                    String email = teacher.optString("email");
                    String department = teacher.optString("department");
                    String status = teacher.optString("status");
                    String title = teacher.optString("title");
                    String designation = teacher.optString("designation");


                    ModelTeacher modelTeacher = new ModelTeacher();
                    modelTeacher.setEmail(email);
                    modelTeacher.setName(name);
                    modelTeacher.setDepartment(department);
                    modelTeacher.setStatus(status);
                    modelTeacher.setDesignation(title);
                    modelTeacher.setTitle(designation);

                    modelTeacher.save();


                }
            } catch (Exception e) {

                e.printStackTrace();

            }

        }




    }
}
