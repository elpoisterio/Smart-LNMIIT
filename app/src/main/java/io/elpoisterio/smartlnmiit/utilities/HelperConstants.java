package io.elpoisterio.smartlnmiit.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by rishabh on 17/3/17.
 */

public class HelperConstants {


    public HelperConstants() {

    }

    public static boolean isEmailValid(CharSequence email) {

        //String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        String expression = "^[\\w\\.-]+@lnmiit.ac.in";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        System.out.println(matcher);
        return matcher.matches();
    }

}
