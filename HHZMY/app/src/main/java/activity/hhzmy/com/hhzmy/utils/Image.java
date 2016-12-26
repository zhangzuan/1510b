package activity.hhzmy.com.hhzmy.utils;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Administrator on 2016/11/14.
 */

public class Image extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
       ImageLoaderConfiguration configuration=   ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(configuration);
    }
}
