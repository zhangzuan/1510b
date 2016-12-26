package activity.bawe.com.yunifang.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

import org.xutils.x;

import activity.bawe.com.yunifang.utils.ImageLoaderUtils;

/**
 * Created by zhiyuan on 16/9/28.
 */
public class MyApplication extends Application {

    private static Context context;
    private static Handler handler;
    private static int mainThreadId;

    @Override
    public void onCreate() {
        super.onCreate();
        //获取当前应用的上下文
        context = getApplicationContext();
        handler = new Handler();
        //获取主线程的线程号
        mainThreadId = Process.myTid();
        //imageLoader初始化
        ImageLoaderUtils.initConfiguration(this);
        //xutils3初始化配置
        x.Ext.init(this);
        //设置是debug模式
        x.Ext.setDebug(true);
    }

    public static int getMainThreadId(){
        return mainThreadId;
    }

    public static Handler getHandler() {
        return handler;
    }

    /**
     * 获取主线程
     * @return
     */
    public static Thread getMainThread(){
        return Thread.currentThread();
    }

    /**
     * 获取整个应用的上下文
     *
     * @return
     */
    public static Context getContext() {
        return context;
    }

}
