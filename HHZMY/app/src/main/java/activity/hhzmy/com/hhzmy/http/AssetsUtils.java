package activity.hhzmy.com.hhzmy.http;

import android.Manifest;
import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2016/11/18.
 */

public class AssetsUtils {
    public static String getFromAssets(Context context, String fileName){
        try {
            InputStreamReader inputReader =
                    new InputStreamReader(
                            context.getResources().getAssets().open(fileName) );
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line="";
            StringBuilder result=new StringBuilder();
            while((line = bufReader.readLine()) != null)
                result.append(line) ;
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
 //权限类
    public static final String PERMISSION_RECORD_AUDIO = Manifest.permission.RECORD_AUDIO;
    public static final String PERMISSION_GET_ACCOUNTS = Manifest.permission.GET_ACCOUNTS;
    public static final String PERMISSION_READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE;
    public static final String PERMISSION_CALL_PHONE = Manifest.permission.CALL_PHONE;
    public static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;
    public static final String PERMISSION_ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    public static final String PERMISSION_ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    public static final String PERMISSION_READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    public static final String PERMISSION_WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;

}
