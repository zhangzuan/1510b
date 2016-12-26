package activity.hhzmy.com.hhzmy.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import activity.hhzmy.com.hhzmy.R;
import activity.hhzmy.com.hhzmy.bean.ShouYeBean;

/**
 * Created by Administrator on 2016/11/14.
 */

public class SouYeGVAdpter extends BaseAdapter {
    private Context context;
    private List<ShouYeBean.DataBean.TagBean> list1;
    private TextView te;
    private ImageView img;

    public SouYeGVAdpter(Context context, List<ShouYeBean.DataBean.TagBean> list1) {
        this.context = context;
        this.list1 = list1;
    }

    @Override
    public int getCount() {
        return list1.size();
    }

    @Override
    public Object getItem(int i) {
        return list1.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = View.inflate(context, R.layout.gv1list, null);
        te = (TextView) view.findViewById(R.id.te);
        te.setText(list1.get(i).getElementName());
        img = (ImageView) view.findViewById(R.id.img);
        ImageLoader.getInstance().displayImage("http://image1.suning.cn/" + list1.get(i).getPicUrl(), img);
        return view;
    }

}