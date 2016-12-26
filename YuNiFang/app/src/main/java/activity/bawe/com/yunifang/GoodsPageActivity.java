package activity.bawe.com.yunifang;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import activity.bawe.com.yunifang.adpter.MyGoodsListViewAdapter;
import activity.bawe.com.yunifang.bean.GoodsDescBean;
import activity.bawe.com.yunifang.utils.CommonUtils;
import activity.bawe.com.yunifang.utils.ImageLoaderUtils;
import activity.bawe.com.yunifang.utils.LogUtils;
import activity.bawe.com.yunifang.utils.OkHttp;
import activity.bawe.com.yunifang.utils.URLUtils;
import activity.bawe.com.yunifang.view.MyListView;
import okhttp3.Request;

public class GoodsPageActivity extends AppCompatActivity {
    private ImageView imag_fanhui;
    private ViewPager vp;
    private ImageView share;
    private ImageView goods_shopping_cart;
    private String data;
    private GoodsDescBean bean;
    private String id;
    private TextView goodspage_goods_name;
    private List<GoodsDescBean.DataBean.GoodsBean.GalleryBean> galleryBeen = new ArrayList<>();
    private List<GoodsDescBean.DataBean.ActivityBean> activityBeen;
    private List<GoodsDescBean.DataBean.GoodsBean> goodsBeen;
    private LinearLayout goods_linear;
    private MyListView goods_lv;
    private TextView tv_shop_price;
    private TextView tv_market_price;
    private TextView collect_count;
    private TextView sales_volume;
    private ViewPager goods_desc_vp;
    private RadioGroup goods_rg;
    private Button buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_page);
        //找到控件
        initView();
        //获取数据
        initShuju();
        //请求数据
        initData();

        //跳转到地图
        initDitu();

        //立即购买
        initBuy();
    }

    private void initBuy() {
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=getIntent();
                Intent intent=new Intent(GoodsPageActivity.this, PayActivity.class);
                String name=in.getStringExtra("name");
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
    }

    private void initDitu() {
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(GoodsPageActivity.this,MapActivity.class);


                startActivity(intent);
            }
        });
    }

    private void initData() {
        LogUtils.e("Tag--", URLUtils.goodsxp + "id=" + id);
        OkHttp.getAsync(URLUtils.goodsxp + "id=" + id, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Gson gson = new Gson();
                bean = gson.fromJson(result, GoodsDescBean.class);
                //viewpager
                initViewPager();
                //listview
                initListView();
                //价格，数量
                initTextView();
                //
                //initgoodview();
            }
        });
    }

    private void initTextView() {
        //设置价格 收藏数量 购买量
        tv_shop_price.setText("¥"+bean.getData().getGoods().getShop_price());
        tv_market_price.setText("¥"+bean.getData().getGoods().getMarket_price());
        tv_market_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//删除线
        collect_count.setText(bean.getData().getGoods().getCollect_count()+"");
        //转化成double类型保留两位小数
        double price=(double)bean.getData().getGoods().getSales_volume()/10000;
        DecimalFormat df   = new DecimalFormat("######0.00");
        sales_volume.setText(df.format(price)+"万");
    }

    private void initListView() {
        activityBeen=bean.getData().getActivity();
        goods_lv.setAdapter(new MyGoodsListViewAdapter(GoodsPageActivity.this,activityBeen));
        //点击监听
        goods_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(GoodsPageActivity.this,WebViewActivity.class);
                intent.putExtra("result",activityBeen.get(i).getDescription());
                startActivity(intent);
            }
        });
    }

    private void initViewPager() {
        galleryBeen = bean.getData().getGoods().getGallery();
        //LogUtils.e("sdad=====",galleryBeen.toString());
        //添加小圆点
        initYuanDian();
        vp.setAdapter(new MyViewPagerAdapter(GoodsPageActivity.this, galleryBeen));
        //viewpager小圆点联动
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int arg0 = position % galleryBeen.size();
                //遍历所有的点对应的ImageView ，判断点的索引是否跟ViewPager当前的索引一致
                for (int i = 0; i < goods_linear.getChildCount(); i++) {

                    ImageView imageView = (ImageView) goods_linear.getChildAt(i);
                    if (i == arg0) {
                        imageView.setSelected(true);
                    } else {
                        imageView.setSelected(false);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initYuanDian() {
        for (int i = 0; i < galleryBeen.size(); i++) {
            //动态实例化ImageView对象，添加到LinearLayout里面
            ImageView imageView = new ImageView(GoodsPageActivity.this);
            //手动代码设置间距
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(5, 0, 5, 0);
            //将当前属性设置给ImageView
            imageView.setLayoutParams(params);
            //给ImageView设置显示资源
            imageView.setBackgroundResource(R.drawable.item_selector);
            //将ImageView添加到LinearLayout里面
            goods_linear.addView(imageView);
            //设置默认选中第一个
            if (i == 0) {
                imageView.setSelected(true);
            }
        }
    }

    private void initShuju() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        //名称
        goodspage_goods_name.setText(getIntent().getStringExtra("name"));
    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.goods_page_view);
        share = (ImageView) findViewById(R.id.share);//分享
        goods_shopping_cart = (ImageView) findViewById(R.id.goods_shopping_cart);//购物车
        imag_fanhui = (ImageView) findViewById(R.id.imag_fanhui);
        goodspage_goods_name = (TextView) findViewById(R.id.goodspage_goods_name);//名称
        goods_linear = (LinearLayout) findViewById(R.id.goods_linear);
        goods_lv = (MyListView) findViewById(R.id.goods_lv);
        tv_shop_price = (TextView) findViewById(R.id.tv_shop_price);//现价
        tv_market_price = (TextView) findViewById(R.id.tv_market_price);//原价
        collect_count = (TextView) findViewById(R.id.collect_count);//收藏数量
        sales_volume = (TextView) findViewById(R.id.sales_volume);//销量
        //goods_desc_vp=(ViewPager) findViewById(R.id.goods_desc_vp);
        goods_rg = (RadioGroup) findViewById(R.id.goods_rg);
        buy = (Button) findViewById(R.id.buy);


        //返回
        imag_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.leftin, R.anim.leftout);
            }
        });
    }

    private class MyViewPagerAdapter extends PagerAdapter {
        private Context context;
        private List<GoodsDescBean.DataBean.GoodsBean.GalleryBean> galleryBeen;

        public MyViewPagerAdapter(Context context, List<GoodsDescBean.DataBean.GoodsBean.GalleryBean> galleryBeen) {
            this.context = context;
            this.galleryBeen = galleryBeen;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = CommonUtils.inflate(R.layout.home_pager_item1);
            ImageView iv = (ImageView) view.findViewById(R.id.home_pager_iv1);
            ImageLoader.getInstance().displayImage(galleryBeen.get(position).getNormal_url(), iv, ImageLoaderUtils.initOptions());
            LogUtils.e("TAG----", galleryBeen.get(position).getNormal_url());
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return galleryBeen.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }


}
