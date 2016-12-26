package activity.bawe.com.yunifang.adpter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import activity.bawe.com.yunifang.R;
import activity.bawe.com.yunifang.bean.HomeBean;
import activity.bawe.com.yunifang.utils.ImageLoaderUtils;

/**
 * autour: 王佳敏
 * date: 2016/12/2 19:23
 * update: 2016/12/2
 */

public class MyHomeGridAdapter extends BaseAdapter {

    private List<HomeBean.DataBean.DefaultGoodsListBean> defaultGoodsListBeen;
    private Context context;

    public MyHomeGridAdapter(List<HomeBean.DataBean.DefaultGoodsListBean> defaultGoodsListBeen, Context context) {
        this.defaultGoodsListBeen = defaultGoodsListBeen;
        this.context = context;
    }

    @Override
    public int getCount() {
        return defaultGoodsListBeen.size();
    }

    @Override
    public Object getItem(int i) {
        return defaultGoodsListBeen.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView==null){
            convertView=View.inflate(context, R.layout.home_page_grid_item,null);
            holder=new ViewHolder();
            holder.iv=(ImageView) convertView.findViewById(R.id.home_page_gridview_goods_img);
            holder.home_page_gridview_efficacy=(TextView) convertView.findViewById(R.id.home_page_gridview_efficacy);
            holder.home_page_gridview_goods_name=(TextView) convertView.findViewById(R.id.home_page_gridview_goods_name);
            holder.home_page_gridview_shop_price=(TextView) convertView.findViewById(R.id.home_page_gridview_shop_price);
            holder.home_page_gridview_market_price=(TextView) convertView.findViewById(R.id.home_page_gridview_market_price);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder) convertView.getTag();
        }
        holder.home_page_gridview_efficacy.setText(defaultGoodsListBeen.get(i).getEfficacy());
        holder.home_page_gridview_goods_name.setText(defaultGoodsListBeen.get(i).getGoods_name());
        holder.home_page_gridview_shop_price.setText("¥"+defaultGoodsListBeen.get(i).getShop_price());
        holder.home_page_gridview_market_price.setText("¥"+defaultGoodsListBeen.get(i).getMarket_price());
        holder.home_page_gridview_market_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        ImageLoader.getInstance().displayImage(defaultGoodsListBeen.get(i).getGoods_img(),holder.iv, ImageLoaderUtils.initOptions());
        return convertView;
    }
}
class ViewHolder{
    TextView home_page_gridview_efficacy;
    TextView home_page_gridview_goods_name;
    TextView home_page_gridview_shop_price;
    TextView home_page_gridview_market_price;
    ImageView iv;
}
