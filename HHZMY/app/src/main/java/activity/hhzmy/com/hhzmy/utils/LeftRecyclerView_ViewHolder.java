package activity.hhzmy.com.hhzmy.utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import activity.hhzmy.com.hhzmy.R;


/**
 * Created by Liuxiaoyu on 2016/11/11.
 */
public class LeftRecyclerView_ViewHolder extends RecyclerView.ViewHolder {
    public TextView tv_typename;
    public View view;
    public LinearLayout ll;
    public LeftRecyclerView_ViewHolder(View itemView) {
        super(itemView);
        ll= (LinearLayout) itemView.findViewById(R.id.linerLayout);
        tv_typename= (TextView) itemView.findViewById(R.id.tv_typename);
        view=itemView.findViewById(R.id.yellow);
    }
}
