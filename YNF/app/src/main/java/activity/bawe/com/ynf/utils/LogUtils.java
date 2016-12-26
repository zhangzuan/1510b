package activity.bawe.com.ynf.utils;

import android.util.Log;
/**
* autour:张钻
* date: 2016/11/28 14:02
* update: 2016/11/28
*/
public class LogUtils {
    public static final  boolean isDebug=true;
    public static void i(String TAG,String info){
        if(isDebug){
            Log.i(TAG,info);
        }
    }
    public static void d(String TAG,String info){
        if(isDebug){
            Log.d(TAG,info);
        }
    }
    public static void e(String TAG,String info){
        if(isDebug){
            Log.e(TAG,info);
        }
    }
}
