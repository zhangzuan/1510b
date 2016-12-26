package activity.hhzmy.com.hhzmy.adpter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import activity.hhzmy.com.hhzmy.R;
import activity.hhzmy.com.hhzmy.bean.RsBean;
import activity.hhzmy.com.hhzmy.utils.LeftRecyclerView_ViewHolder;

/**
 * Created by Liuxiaoyu on 2016/11/11.
 */
public class LeftRecyclerView_Adapter extends RecyclerView.Adapter<LeftRecyclerView_ViewHolder> {
    Context context;
    List<RsBean> rsBeen;
    OnRecyclerItemClickListener onRecyclerItemClickListener;

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    public LeftRecyclerView_Adapter(Context context, List<RsBean> rsBeen) {
        this.context = context;
        this.rsBeen = rsBeen;
    }

    @Override
    public LeftRecyclerView_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LeftRecyclerView_ViewHolder holder = new LeftRecyclerView_ViewHolder(LayoutInflater.from(context).inflate(R.layout.recaycalerview_leftitem, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(LeftRecyclerView_ViewHolder holder, final int position) {
        holder.tv_typename.setText(rsBeen.get(position).getDirName());
        if(rsBeen.get(position).isChecked){
            holder.ll.setBackgroundColor(Color.parseColor("#F2F2F2"));
            holder.tv_typename.setTextColor(Color.parseColor("#F29400"));
            holder.view.setVisibility(View.VISIBLE);

        }else{
            holder.tv_typename.setTextColor(Color.BLACK);
            holder.view.setVisibility(View.INVISIBLE);
        }
        if (onRecyclerItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onRecyclerItemClickListener.onItemClick(view, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return rsBeen.size();
    }

    public interface OnRecyclerItemClickListener {
        /**
         * @param view     被点击的ittem
         * @param position 点击索引
         */
        void onItemClick(View view, int position);
    }
}
