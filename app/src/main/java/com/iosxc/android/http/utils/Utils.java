package com.iosxc.android.http.utils;

/**
 * Created by crazz on 5/2/15.
 */
public class Utils {

    public static boolean isEmpty(String str){
        return str==null||str.length()==0;
    }

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
}
