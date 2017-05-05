package io.elpoisterio.smartlnmiit.utilities;

import android.content.Context;

/**
 * Created by rishabh on 2/3/17.
 */

public class ApplicationConstant {

    //private static String url = "http://192.168.137.230:8080/";
    private static String url = "http://172.22.28.228:8080/";
    public static String header = "";
    public static String AUTH_TOKEN= "";
    public static String login = url +"login";
    public static String signUp = url+"signup";
    public  static String uploadGrade="";
    public static String cancelClass = "";
    public static String uploadApplication=url +"createapp";
    public static String updateStatus = "";
    public static String getFacultyList="";
    public static String getUserProfile="";
    public static String updateProfile= "";


    public static String postfirebaseKey= "";
    public static String editProfile = url+"student/editProfile";

    public static String getAllTeacher = url+ "teacher/all";
}
