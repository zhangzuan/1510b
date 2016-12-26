package activity.bawe.com.yunifang.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import activity.bawe.com.yunifang.AllGoodsActivity;
import activity.bawe.com.yunifang.GoodsPageActivity;
import activity.bawe.com.yunifang.R;
import activity.bawe.com.yunifang.base.BaseData;
import activity.bawe.com.yunifang.base.BaseFragment;
import activity.bawe.com.yunifang.bean.FenleiBean;
import activity.bawe.com.yunifang.utils.CommonUtils;
import activity.bawe.com.yunifang.utils.ImageLoaderUtils;
import activity.bawe.com.yunifang.utils.URLUtils;
import activity.bawe.com.yunifang.view.MyGridView;
import activity.bawe.com.yunifang.view.ShowingPage;


/**
* autour:张钻
* date: 2016/11/29 15:15
* update: 2016/11/29
*/
public class CategoryFragment extends BaseFragment {
    private String s;
    public String data;
    private MyHomeData myHomeData;
    private RecyclerView class_shuxin;
    private FenleiBean fenLiBean;
    private MyGridView mygv;
    private ImageView feiye_img1;
    private View view;
    private ImageView feiye_img2;
    private ImageView feiye_img3;
    private ImageView feiye_img4;
    private ImageView feiye_img5;
    private ImageView feiye_img6;
    private ImageView feiye_img7;
    private ImageView feiye_img8;
    private ImageView feiye_img9;
    private ImageView feiye_img10;
    private ImageView feiye_img11;
    private TextView fuzhi_tv1;
    private TextView fuzhi_tv2;
    private TextView fuzhi_tv3;
    private TextView fuzhi_tv4;
    private TextView fuzhi_tv5;
    private TextView fuzhi_tv6;
    private GridView fenlei_gv;
    private TextView chankan;
    private ScrollView class_sv;
    private MyGridViewAdapter_FenLei myadapter;

    @Override
    protected void onLoad() {
//        RequestParams re=new RequestParams(URLUtils.homeUrl+"?"+ URLUtils.homeArgs);
//        //去加载
//        x.http().get(re, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String s) {
//                //先赋值---
//                CategoryFragment.this.s=s;
//                CategoryFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
//                LogUtils.e("AAAAA","success-------cart-"+s);
//            }
//
//            @Override
//            public void onError(Throwable throwable, boolean b) {
//
//            }
//            @Override
//            public void onCancelled(CancelledException e) {
//            }
//            @Override
//            public void onFinished() {
//            }
//        });
        myHomeData = new CategoryFragment.MyHomeData();
        myHomeData.getData(URLUtils.categoryUrl, URLUtils.categoryArgs, 0, BaseData.NORMALTIME);
    }

    @Override
    public View createSuccessView() {
        View view = initView();
//        LogUtils.e("AAAAA","create-------cart-"+s);
//        TextView textView =new TextView(getContext());
//        textView.setText(s);
        //分类页面img点击监听
        FeiLeiImgOnClik();
        //分类页面肤质
        initFuZhi();
        //分类页面GridView
        initFenlei_GridView();
     //   initMyGrDate();
        return view;
    }

    private void initFenlei_GridView() {
        final List<FenleiBean.DataBean.GoodsBriefBean> goodsBriefBeen = fenLiBean.getData().getGoodsBrief();
        myadapter = new MyGridViewAdapter_FenLei(getContext(), goodsBriefBeen);
        fenlei_gv.setAdapter(myadapter);
        //点击监听 跳转到商品详情页面
        fenlei_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(), GoodsPageActivity.class);
                //将id  name传到详情页
                intent.putExtra("id",goodsBriefBeen.get(i).getId());
                intent.putExtra("name",goodsBriefBeen.get(i).getGoods_name());
                startActivity(intent);
            }
        });

    }

    private void initFuZhi() {
        List<FenleiBean.DataBean.CategoryBean.ChildrenBean> categoryBeen = fenLiBean.getData().getCategory().get(2).getChildren();
        fuzhi_tv1.setText("# " + categoryBeen.get(0).getCat_name() + " #");
        fuzhi_tv2.setText("# " + categoryBeen.get(1).getCat_name() + " #");
        fuzhi_tv3.setText("# " + categoryBeen.get(2).getCat_name() + " #");
        fuzhi_tv4.setText("# " + categoryBeen.get(3).getCat_name() + " #");
        fuzhi_tv5.setText("# " + categoryBeen.get(4).getCat_name() + " #");
        fuzhi_tv6.setText("# " + categoryBeen.get(5).getCat_name() + " #");
    }

    private void FeiLeiImgOnClik() {
        feiye_img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getContext(), "跳转", Toast.LENGTH_SHORT).show();
            }
        });
        //查看更多
        chankan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), AllGoodsActivity.class);
                startActivity(intent);
            }
        });
    }

//    private void initMyGrDate() {
//        List<FenleiBean.DataBean.GoodsBriefBean> chilDren = fenLiBean.getData().getGoodsBrief();
//        MyclassGroidViewAdpter adapter=new MyclassGroidViewAdpter(chilDren,getContext());
//        mygv.setAdapter(adapter);
//    }
//
//    private void initShuxin() {
//        List<FenleiBean.DataBean.GoodsBriefBean> chilDren = fenLiBean.getData().getGoodsBrief();
////        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
////        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        GridLayoutManager girdLayoutManager=new GridLayoutManager(getActivity(),2);
//        girdLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
//
//        class_shuxin.setLayoutManager(girdLayoutManager);
//
//      //  class_shuxin.setLayoutManager(layoutManager);
////设置layoutManager
//   //     class_shuxin.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
//
//        //设置adapter
//
//        MasonryAdapter adapter=new MasonryAdapter(chilDren,getContext());
//        class_shuxin.setAdapter(adapter);
//
//    }

    private View initView() {
        View view = CommonUtils.inflate(R.layout.fragment_classify);

        feiye_img1 = (ImageView) view.findViewById(R.id.feiye_img1);
        feiye_img2 = (ImageView) view.findViewById(R.id.feiye_img2);
        feiye_img3 = (ImageView) view.findViewById(R.id.feiye_img3);
        feiye_img4 = (ImageView) view.findViewById(R.id.feiye_img4);
        feiye_img5 = (ImageView) view.findViewById(R.id.feiye_img5);
        feiye_img6 = (ImageView) view.findViewById(R.id.feiye_img6);
        feiye_img7 = (ImageView) view.findViewById(R.id.feiye_img7);
        feiye_img8 = (ImageView) view.findViewById(R.id.feiye_img8);
        feiye_img9 = (ImageView) view.findViewById(R.id.feiye_img9);
        feiye_img10 = (ImageView) view.findViewById(R.id.feiye_img10);
        feiye_img11 = (ImageView) view.findViewById(R.id.feiye_img11);
        //分类页面肤质
        fuzhi_tv1 = (TextView) view.findViewById(R.id.fuzhi_tv1);
        fuzhi_tv2 = (TextView) view.findViewById(R.id.fuzhi_tv2);
        fuzhi_tv3 = (TextView) view.findViewById(R.id.fuzhi_tv3);
        fuzhi_tv4 = (TextView) view.findViewById(R.id.fuzhi_tv4);
        fuzhi_tv5 = (TextView) view.findViewById(R.id.fuzhi_tv5);
        fuzhi_tv6 = (TextView) view.findViewById(R.id.fuzhi_tv6);

        //分类GridView
        fenlei_gv = (GridView) view.findViewById(R.id.feilei_gv);
        chankan=(TextView)view.findViewById(R.id.chakan);

        class_sv=(ScrollView) view.findViewById(R.id.class_sv);
        class_sv.smoothScrollTo(0,0);

  return view;
    }

    class MyHomeData extends BaseData {


        @Override
        public void setResultData(String data) {
            //先设置数据
            CategoryFragment.this.data = data;
            //data有可能为空
            //再展示
            Gson gson = new Gson();
       //     homeRoot = gson.fromJson(data, HomeBean.class);
             fenLiBean=gson.fromJson(data,FenleiBean.class);
            CategoryFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
        }

        @Override
        protected void setResulttError(ShowingPage.StateType stateLoadError) {
            //失败
            CategoryFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);

        }
    }
    private class MyGridViewAdapter_FenLei extends BaseAdapter {

        private Context context;
        private List<FenleiBean.DataBean.GoodsBriefBean> goodsBriefBeen;

        public MyGridViewAdapter_FenLei(Context context, List<FenleiBean.DataBean.GoodsBriefBean> goodsBriefBeen) {
            this.context = context;
            this.goodsBriefBeen = goodsBriefBeen;
        }

        @Override
        public int getCount() {
            return goodsBriefBeen.size();
        }

        @Override
        public Object getItem(int i) {
            return goodsBriefBeen.get(i);
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
            holder.home_page_gridview_efficacy.setText(goodsBriefBeen.get(i).getEfficacy());
            holder.home_page_gridview_goods_name.setText(goodsBriefBeen.get(i).getGoods_name());
            holder.home_page_gridview_shop_price.setText("¥" + goodsBriefBeen.get(i).getShop_price());
            holder.home_page_gridview_market_price.setText("¥" + goodsBriefBeen.get(i).getMarket_price());
            holder.home_page_gridview_market_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            ImageLoader.getInstance().displayImage(goodsBriefBeen.get(i).getGoods_img(), holder.iv, ImageLoaderUtils.initOptions());
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
