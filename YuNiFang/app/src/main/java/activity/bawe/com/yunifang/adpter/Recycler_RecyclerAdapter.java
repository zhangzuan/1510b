package activity.bawe.com.yunifang.adpter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import activity.bawe.com.yunifang.R;
import activity.bawe.com.yunifang.bean.HomeBean;
import activity.bawe.com.yunifang.utils.ImageLoaderUtils;

/**
* autour:张钻
* date: 2016/12/3 11:57
* update: 2016/12/3
*/

public class Recycler_RecyclerAdapter extends RecyclerView.Adapter<Recycler_RecyclerAdapter.RecyclerAdapter> {

    private List<HomeBean.DataBean.SubjectsBean.GoodsListBean> subjectsBeen;
    private Context context;

    public Recycler_RecyclerAdapter(List<HomeBean.DataBean.SubjectsBean.GoodsListBean> subjectsBeen, Context context) {
        this.subjectsBeen = subjectsBeen;
        this.context = context;

    }

    @Override
    public RecyclerAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerAdapter holder=new RecyclerAdapter(LayoutInflater.from(context).inflate(R.layout.homepage_recycle_item,parent,false));

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter holder, int position) {
        ImageLoader.getInstance().displayImage(subjectsBeen.get(position).getGoods_img(),holder.iv, ImageLoaderUtils.initOptions());
        holder.tv_name.setText(subjectsBeen.get(position).getGoods_name());
        holder.shop_price.setText("¥"+ subjectsBeen.get(position).getShop_price());
        holder.market_price.setText("¥"+ subjectsBeen.get(position).getMarket_price());
        holder.market_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public int getItemCount() {
        return subjectsBeen.size();
    }

    public class RecyclerAdapter extends RecyclerView.ViewHolder{

        private final ImageView iv;
        private final TextView tv_name;
        private final TextView shop_price;
        private final TextView market_price;
        public RecyclerAdapter(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.home_recycle_iv);
            tv_name = (TextView) itemView.findViewById(R.id.home_recycle_goods_name);
            shop_price = (TextView) itemView.findViewById(R.id.home_recycle_shop_price);
            market_price = (TextView) itemView.findViewById(R.id.home_recycle_market_price);
        }
    }
}
