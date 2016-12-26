package activity.hhzmy.com.hhzmy.adpter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import activity.hhzmy.com.hhzmy.R;
import activity.hhzmy.com.hhzmy.WebViewActivity;
import activity.hhzmy.com.hhzmy.bean.ShouYeBean;

/**
 * Created by Administrator on 2016/11/9.
 */

public class MySouYePagerAdapter extends PagerAdapter {
    String jia="http://image1.suning.cn";
   // private List<ImageView> imglist;
    private List<ShouYeBean.DataBean.TagBean> imglist;
  private Context context;

    public MySouYePagerAdapter(List<ShouYeBean.DataBean.TagBean> imglist, Context context) {
        this.imglist = imglist;
        this.context = context;
    }


    @Override
    public int getCount() {
        return imglist.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
       // container.removeView(imglist.get(position) );
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view=View.inflate(context, R.layout.pager_viewpager1,null);
        ImageView im= (ImageView) view.findViewById(R.id.vp_p1_im);
        ImageLoader.getInstance().displayImage(jia+imglist.get(position).getPicUrl(),im);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context,WebViewActivity.class).putExtra("result",imglist.get(position).getLinkUrl()));
            }
        });
        container.addView(view);
//        ImageView imageView=imglist.get(position);
//
//        ViewParent parent= imageView.getParent();
//        if (parent!=null)
//        {
//            ViewGroup group= (ViewGroup) parent;
//            group.removeView(imageView);
//        }
//        container.addView(imageView);
        return view;
    }
}
