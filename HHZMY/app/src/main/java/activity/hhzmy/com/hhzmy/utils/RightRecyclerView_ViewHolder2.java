package activity.hhzmy.com.hhzmy.utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import activity.hhzmy.com.hhzmy.R;


/**
 * Created by Liuxiaoyu on 2016/11/12.
 */
public class RightRecyclerView_ViewHolder2 extends RecyclerView.ViewHolder {
    public TextView godname;
    public ImageView img;
    public RightRecyclerView_ViewHolder2(View itemView) {
        super(itemView);
        godname= (TextView) itemView.findViewById(R.id.tv_rightgodname);
        img= (ImageView) itemView.findViewById(R.id.rightimg);
    }
}
