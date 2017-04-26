package io.elpoisterio.smartlnmiit.utilities;

import android.os.Handler;
import android.os.Message;

/**
 * Created by rishabh on 21/6/16.
 */
public class HandlerConstant {

    //do not use constant 0, because when sending data msg.what is 0.
    public static final int MOVE_TO_HOME=1;
    public static final int FAILURE = -1;
    public static final int SHOW_NO_CONNECTION = -2;
    public static final int CHECK_VALIDITY=3;
    public static final int SET_RESULT_OK = 4;
    public static final int SET_RESULT_CANCELED = -4;
    public static final int DELETE_CASE = -5;
    public static final int ADD_DOC = 6;
    public static final int PAYMENT_FAILURE = -6;
    public static final int CASE_ALREDAY_EXISTS = 7;
    public static final int SUCCESS = 200;
    public static final int EXPIRED_STATUS_CODE=401;
    public static final int DISPLAY_EMPTY = 8;
    public static final int FORUM_DATA_LOADED = 9;

    private static String errors= "Could not connect";

    public static void sendMessage(Handler handler, int msg){
        if(handler!=null)
        {
            Message message = handler.obtainMessage();
            message.what = msg;
            handler.sendMessage(message);
        }

    }
    public static void sendMessagewithData(Handler handler, Object o){
        Message message = handler.obtainMessage();
        message.obj = o;
        handler.sendMessage(message);
    }

    public static void setErrorMessage(String error){
        errors = error;
    }
    public static String getErrorMessage(){
        return errors;
    }
}
