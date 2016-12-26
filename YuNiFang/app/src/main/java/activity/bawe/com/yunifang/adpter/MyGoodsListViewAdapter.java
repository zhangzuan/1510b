package activity.bawe.com.yunifang.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import activity.bawe.com.yunifang.R;
import activity.bawe.com.yunifang.bean.GoodsDescBean;


public class MyGoodsListViewAdapter extends BaseAdapter {

    private Context context;
    private List<GoodsDescBean.DataBean.ActivityBean> activityBeen;

    public MyGoodsListViewAdapter(Context context, List<GoodsDescBean.DataBean.ActivityBean> activityBeen) {
        this.context = context;
        this.activityBeen = activityBeen;
    }

    @Override
    public int getCount() {
        return activityBeen.size();
    }

    @Override
    public Object getItem(int i) {
        return activityBeen.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=View.inflate(context, R.layout.setting_item,null);
        TextView tv=(TextView) view.findViewById(R.id.set_tv);
        tv.setText(activityBeen.get(i).getTitle());
        return view;
    }
}
