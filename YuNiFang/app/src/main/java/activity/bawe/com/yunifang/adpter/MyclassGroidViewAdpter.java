package activity.bawe.com.yunifang.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import activity.bawe.com.yunifang.R;
import activity.bawe.com.yunifang.bean.FenleiBean;

/**
 * 1、类型：
 * 2、作者：张钻
 * 3、时间：2016-11-23
 */

public class MyclassGroidViewAdpter extends BaseAdapter{
    private   List<FenleiBean.DataBean.GoodsBriefBean> list;
    private Context context;



    public MyclassGroidViewAdpter(java.util.List<FenleiBean.DataBean.GoodsBriefBean> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=View.inflate(context, R.layout.home_mygv_itim,null);
        ImageView imageView=(ImageView) convertView.findViewById(R.id.home_mygc_im);
        TextView textView=(TextView) convertView.findViewById(R.id.home_mygc_tv);
        ImageLoader.getInstance().displayImage(list.get(position).getGoods_img(),imageView);
        //textView.setText(list.get(position).getTitle());
        return convertView;
    }
}
