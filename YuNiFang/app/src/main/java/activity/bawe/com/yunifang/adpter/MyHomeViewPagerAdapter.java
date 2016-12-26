package activity.bawe.com.yunifang.adpter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import activity.bawe.com.yunifang.R;
import activity.bawe.com.yunifang.bean.HomeBean;
import activity.bawe.com.yunifang.utils.CommonUtils;
import activity.bawe.com.yunifang.utils.ImageLoaderUtils;


public class MyHomeViewPagerAdapter extends PagerAdapter {
    private List<HomeBean.DataBean.ActivityInfoBean.ActivityInfoListBean> activityInfoBeen;
    private Context context;

    public MyHomeViewPagerAdapter(List<HomeBean.DataBean.ActivityInfoBean.ActivityInfoListBean> activityInfoBeen, Context context) {
        this.activityInfoBeen = activityInfoBeen;
        this.context = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view= CommonUtils.inflate(R.layout.home_pager_item1);
        ImageView iv=(ImageView) view.findViewById(R.id.home_pager_iv1);
        ImageLoader.getInstance().displayImage(activityInfoBeen.get(position%activityInfoBeen.size()).getActivityImg(),iv, ImageLoaderUtils.initOptions());

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
