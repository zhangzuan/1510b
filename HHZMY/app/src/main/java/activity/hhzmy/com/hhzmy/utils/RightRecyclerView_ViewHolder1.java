package activity.hhzmy.com.hhzmy.utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import activity.hhzmy.com.hhzmy.R;


/**
 * Created by Liuxiaoyu on 2016/11/12.
 */
public class RightRecyclerView_ViewHolder1 extends RecyclerView.ViewHolder {
    public TextView tv_name;
    public RightRecyclerView_ViewHolder1(View itemView) {
        super(itemView);
        tv_name= (TextView) itemView.findViewById(R.id.tv_righttype);
    }
}
