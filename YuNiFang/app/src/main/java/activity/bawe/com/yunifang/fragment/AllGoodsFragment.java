package activity.bawe.com.yunifang.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import activity.bawe.com.yunifang.GoodsPageActivity;
import activity.bawe.com.yunifang.R;
import activity.bawe.com.yunifang.base.BaseData;
import activity.bawe.com.yunifang.base.BaseFragment;
import activity.bawe.com.yunifang.bean.AllGoodsBean;
import activity.bawe.com.yunifang.utils.ImageLoaderUtils;
import activity.bawe.com.yunifang.utils.URLUtils;
import activity.bawe.com.yunifang.view.ShowingPage;

/**
 * 1、类型：
 * 2、作者：张钻
 * 3、时间：2016-11-23
 */

public class AllGoodsFragment extends BaseFragment {

    private String data;
    private  MyHomeData myclassData;
    private AllGoodsBean goodBean;
    private View view;
    private GridView gv;
    private List<AllGoodsBean.DataBean> dataBeen;
    @Override
    protected void onLoad() {
        myclassData = new AllGoodsFragment.MyHomeData();
        myclassData.getData(URLUtils.allgoods, null, 0, BaseData.NORMALTIME);
    }

    @Override
    public View createSuccessView() {
        //初始化数据
        view = View.inflate(getActivity(), R.layout.allgoods_page, null);
        gv = (GridView) view.findViewById(R.id.all_goods_gv);
        //适配器
        dataBeen = goodBean.getData();
        gv.setAdapter(new MyAllGoodsAdapter(getActivity(), dataBeen));

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(), GoodsPageActivity.class);
                intent.putExtra("id",dataBeen.get(i).getId());
                intent.putExtra("name",dataBeen.get(i).getGoods_name());
                startActivity(intent);
            }
        });

        return view;
    }
    class MyHomeData extends BaseData {


        @Override
        public void setResultData(String data) {
            //先设置数据
            AllGoodsFragment.this.data = data;
            //data有可能为空
            //再展示
            Gson gson = new Gson();
            //     homeRoot = gson.fromJson(data, HomeBean.class);
            goodBean=gson.fromJson(data,AllGoodsBean.class);
            AllGoodsFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
        }

        @Override
        protected void setResulttError(ShowingPage.StateType stateLoadError) {
            //失败
            AllGoodsFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);

        }
    }
    private class MyAllGoodsAdapter extends BaseAdapter {
        private Context context;
        private List<AllGoodsBean.DataBean> dataBeen;

        public MyAllGoodsAdapter(Context context, List<AllGoodsBean.DataBean> dataBeen) {
            this.context = context;
            this.dataBeen = dataBeen;
        }

        @Override
        public int getCount() {
            return dataBeen.size();
        }

        @Override
        public Object getItem(int i) {
            return dataBeen.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {

            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.home_page_grid_item, null);
                holder = new ViewHolder();
                holder.iv = (ImageView) convertView.findViewById(R.id.home_page_gridview_goods_img);
                holder.home_page_gridview_efficacy = (TextView) convertView.findViewById(R.id.home_page_gridview_efficacy);
                holder.home_page_gridview_goods_name = (TextView) convertView.findViewById(R.id.home_page_gridview_goods_name);
                holder.home_page_gridview_shop_price = (TextView) convertView.findViewById(R.id.home_page_gridview_shop_price);
                holder.home_page_gridview_market_price = (TextView) convertView.findViewById(R.id.home_page_gridview_market_price);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.home_page_gridview_efficacy.setText(dataBeen.get(i).getEfficacy());
            holder.home_page_gridview_goods_name.setText(dataBeen.get(i).getGoods_name());
            holder.home_page_gridview_shop_price.setText("¥" + dataBeen.get(i).getShop_price());
            holder.home_page_gridview_market_price.setText("¥" + dataBeen.get(i).getMarket_price());
            holder.home_page_gridview_market_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            ImageLoader.getInstance().displayImage(dataBeen.get(i).getGoods_img(), holder.iv, ImageLoaderUtils.initOptions());
            return convertView;
        }
    }

    class ViewHolder {
        TextView home_page_gridview_efficacy;
        TextView home_page_gridview_goods_name;
        TextView home_page_gridview_shop_price;
        TextView home_page_gridview_market_price;
        ImageView iv;
    }
}
