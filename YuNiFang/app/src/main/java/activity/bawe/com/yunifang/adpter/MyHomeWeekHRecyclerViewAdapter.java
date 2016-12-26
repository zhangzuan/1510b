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
* date: 2016/12/5 12:07
* update: 2016/12/5
*/

public class MyHomeWeekHRecyclerViewAdapter extends RecyclerView.Adapter<MyHomeWeekHRecyclerViewAdapter.MyViewHolder> {

    private final LayoutInflater mlayout;
    private List<HomeBean.DataBean.BestSellersBean.GoodsListBeanX> goodsListBeanXes;
    private Context context;

    public MyHomeWeekHRecyclerViewAdapter(List<HomeBean.DataBean.BestSellersBean.GoodsListBeanX> goodsListBeanXes, Context context) {
        this.goodsListBeanXes = goodsListBeanXes;
        this.context = context;
        mlayout = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder=new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.homepage_recycle_item,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ImageLoader.getInstance().displayImage(goodsListBeanXes.get(position).getGoods_img(),holder.iv, ImageLoaderUtils.initOptions());
        holder.tv_name.setText(goodsListBeanXes.get(position).getGoods_name());
        holder.shop_price.setText("¥"+ goodsListBeanXes.get(position).getShop_price());
        holder.market_price.setText("¥"+ goodsListBeanXes.get(position).getMarket_price());
        holder.market_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public int getItemCount() {
        return goodsListBeanXes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private final ImageView iv;
        private final TextView tv_name;
        private final TextView shop_price;
        private final TextView market_price;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.home_recycle_iv);
            tv_name = (TextView) itemView.findViewById(R.id.home_recycle_goods_name);
            shop_price = (TextView) itemView.findViewById(R.id.home_recycle_shop_price);
            market_price = (TextView) itemView.findViewById(R.id.home_recycle_market_price);
        }
    }
}

