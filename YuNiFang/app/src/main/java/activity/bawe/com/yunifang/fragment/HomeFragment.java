package activity.bawe.com.yunifang.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import activity.bawe.com.yunifang.AllGoodsActivity;
import activity.bawe.com.yunifang.GoodsPageActivity;
import activity.bawe.com.yunifang.R;
import activity.bawe.com.yunifang.WebViewActivity;
import activity.bawe.com.yunifang.adpter.GroidViewAdpter;
import activity.bawe.com.yunifang.adpter.MyHomeGridAdapter;
import activity.bawe.com.yunifang.adpter.MyHomeGroidView;
import activity.bawe.com.yunifang.adpter.MyHomeSpecialRecyclerAdapter;
import activity.bawe.com.yunifang.adpter.MyHomeViewPagerAdapter;
import activity.bawe.com.yunifang.adpter.MyHomeWeekHRecyclerViewAdapter;
import activity.bawe.com.yunifang.base.BaseData;
import activity.bawe.com.yunifang.base.BaseFragment;
import activity.bawe.com.yunifang.bean.HomeBean;
import activity.bawe.com.yunifang.utils.CommonUtils;
import activity.bawe.com.yunifang.utils.RecyclerViewClickListener;
import activity.bawe.com.yunifang.utils.URLUtils;
import activity.bawe.com.yunifang.view.MyGridView;
import activity.bawe.com.yunifang.view.MyRoolViewPager;
import activity.bawe.com.yunifang.view.ShowingPage;


public class HomeFragment extends BaseFragment {
    public String data;
     private MyHomeData myHomeData;
    private HomeBean homeRoot;
    /**
     * 轮播图图片集合
     */
    /**
     * 小点图片数组
     */
    int[] dotArray = new int[]{R.mipmap.page_indicator_focused, R.mipmap.page_indicator_unfocused};

    ArrayList<String> imgUrlList = new ArrayList<>();
    ArrayList<ImageView> dotList = new ArrayList<>();
    private LinearLayout ll_dots;
    private MyRoolViewPager myRoolViewPager;
    private String s;
    private GridView mygv;
//    private LinearLayout ll1;
   private MyGridView home_page_gridview;

    private RecyclerView homepage_recycle;
    private MyHomeWeekHRecyclerViewAdapter recycleAdapter;
    private ViewPager home_viewpager;
    private TextView tv_weekHot;
    private RecyclerView home_page_recycler;
    private TextView all_text;
  private   List<HomeBean.DataBean.DefaultGoodsListBean> defaultGoodsListBeen;

    @Override
    protected void onLoad() {

        myHomeData = new MyHomeData();
         myHomeData.getData(URLUtils.homeUrl, URLUtils.homeArgs, 0, BaseData.NORMALTIME);
    }

    @Override
    public View createSuccessView() {
       View view = initView();
        initRoolViewPager();
        initMyGriyView();
       initHorizontalScrollView();
       initYouHui();

        initRiMen();
        //初始化gridview
        init2GridView();
        //全部商品
        initAllGoods();
        return view;
    }

    private void initAllGoods() {
        all_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AllGoodsActivity.class));
            }
        });
    }

    private void init2GridView() {
       defaultGoodsListBeen = homeRoot.getData().getDefaultGoodsList();
        home_page_gridview.setAdapter(new MyHomeGridAdapter(defaultGoodsListBeen,getActivity()));
        //监听
        home_page_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(), GoodsPageActivity.class);
                intent.putExtra("id",defaultGoodsListBeen.get(i).getId());
                intent.putExtra("name",defaultGoodsListBeen.get(i).getGoods_name());
                startActivity(intent);
            }
        });
    }

    /**
     * 初始化 initYouHui
     */
    private void initRiMen() {
        List<HomeBean.DataBean.SubjectsBean> subjectsBeen = homeRoot.getData().getSubjects();
        //设置Recyclerview垂直显示
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        home_page_recycler.setLayoutManager(layoutManager);
        //初始化Recyclerview适配器
        home_page_recycler.setAdapter(new MyHomeSpecialRecyclerAdapter(subjectsBeen,getActivity()));
        //Recyclerview点击监听
        home_page_recycler.addOnItemTouchListener(new RecyclerViewClickListener(getActivity(), new RecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(getActivity(), GoodsPageActivity.class);

                startActivity(intent);
                //  startActivity(new Intent(getActivity(),WebViewActivity.class).putExtra("result",homeRoot.getData().getAd1().get(position%homeRoot.getData().getAd1().size()).getAd_type_dynamic_data()));

//
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }

    /**
     * 初始化 initYouHui
     */
    private void initYouHui() {
        final List<HomeBean.DataBean.ActivityInfoBean.ActivityInfoListBean> activityInfoListBeen = homeRoot.getData().getActivityInfo().getActivityInfoList();
        MyHomeViewPagerAdapter myHomeViewPagerAdapter = new MyHomeViewPagerAdapter(activityInfoListBeen, getActivity());

        home_viewpager.setAdapter(myHomeViewPagerAdapter);
        home_viewpager.setOffscreenPageLimit(3);// 具体缓存页数
        home_viewpager.setPageMargin(20); // setPageMargin表示设置page之间的间距
        home_viewpager.setCurrentItem(2);
        home_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                startActivity(new Intent(getActivity(),WebViewActivity.class).putExtra("result",homeRoot.getData().getAd1().get(position%homeRoot.getData().getAd1().size()).getAd_type_dynamic_data()));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    /**
     * 初始化HorizontalScrollView
     */
    private void initHorizontalScrollView() {
        List<HomeBean.DataBean.BestSellersBean> date = homeRoot.getData().getBestSellers();
        String des = date.get(0).getName();
         tv_weekHot.setText(des);

        final List<HomeBean.DataBean.BestSellersBean.GoodsListBeanX>   bestSellers = date.get(0).getGoodsList();
       GroidViewAdpter adpter=new GroidViewAdpter(getContext(), bestSellers);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        homepage_recycle.setLayoutManager(layoutManager);
        //Recyclerview点击监听
        homepage_recycle.addOnItemTouchListener(new RecyclerViewClickListener(getActivity(), new RecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(getActivity(), GoodsPageActivity.class);

                startActivity(intent);
              //  startActivity(new Intent(getActivity(),WebViewActivity.class).putExtra("result",homeRoot.getData().getAd1().get(position%homeRoot.getData().getAd1().size()).getAd_type_dynamic_data()));

//
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
        //初始化Recyclerview适配器
        recycleAdapter=new MyHomeWeekHRecyclerViewAdapter(bestSellers,getActivity());
        homepage_recycle.setAdapter(recycleAdapter);


    }

    /**
     * 初始化MyGriyView
     */
    private void initMyGriyView() {
        final List<HomeBean.DataBean.Ad5Bean>   ad5=homeRoot.getData().getAd5();

        MyHomeGroidView adpter=new MyHomeGroidView(getContext(), ad5);

        mygv.setAdapter(adpter);
        mygv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(),WebViewActivity.class).putExtra("result",
                        ad5.get(position).getAd_type_dynamic_data()));
            }
        });
    }

    /**
     * 初始化viewPager轮播图
     */
    private void initRoolViewPager() {
        final List<HomeBean.DataBean.Ad1Bean> ad1 = homeRoot.getData().getAd1();
       for (int i = 0; i < ad1.size(); i++) {
             imgUrlList.add(ad1.get(i).getImage());
        }
       initDots(ad1);


        myRoolViewPager.initData(imgUrlList,dotArray, dotList);
        myRoolViewPager.startViewPager();
        myRoolViewPager.setOnPageClickListener(new MyRoolViewPager.OnPageClickListener() {
            @Override
            public void setOnPage(int position) {
//                Intent intent=new Intent();
                startActivity(new Intent(getActivity(),WebViewActivity.class).putExtra("result",homeRoot.getData().getAd1().get(position%homeRoot.getData().getAd1().size()).getAd_type_dynamic_data()));
                Toast.makeText(getActivity(),"我要跳转到详情了",Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 初始化小点
     * @param ad1
     */
    private void initDots(List<HomeBean.DataBean.Ad1Bean> ad1) {
        dotList.clear();
        ll_dots.removeAllViews();
        for (int i = 0; i < ad1.size(); i++) {
            ImageView imageView = new ImageView(getActivity());
            if (i == 0) {
                imageView.setImageResource(dotArray[0]);
            } else {
                imageView.setImageResource(dotArray[1]);
            }
            dotList.add(imageView);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(CommonUtils.dip2px(10),CommonUtils.dip2px(5),CommonUtils.dip2px(10),CommonUtils.dip2px(5));
            ll_dots.addView(imageView, params);
        }
    }
//控件ID
    @NonNull
    private View initView() {
        View view = CommonUtils.inflate(R.layout.fragment_home);
       ll_dots = (LinearLayout) view.findViewById(R.id.ll_dots);
//
        myRoolViewPager = (MyRoolViewPager) view.findViewById(R.id.myRoolViewPager);
        mygv=(GridView)view.findViewById(R.id.home_mygv);
//        //ll1=(LinearLayout)view.findViewById(R.id.home_farment_ll1);
//         gv1=(MyGridView)view.findViewById(R.id.home_gv1);
        tv_weekHot = (TextView) view.findViewById(R.id.tv_weekHot);
        homepage_recycle=(RecyclerView) view.findViewById(R.id.homepage_recycle);
        home_viewpager=(ViewPager) view.findViewById(R.id.home_viewpager);
        home_page_recycler=(RecyclerView) view.findViewById(R.id.home_page_recycler);
        home_page_gridview=(MyGridView) view.findViewById(R.id.home_page_gridview);
        all_text=(TextView) view.findViewById(R.id.all_text);


        return view;
    }
//
    class MyHomeData extends BaseData {


        @Override
        public void setResultData(String data) {
            //先设置数据
            HomeFragment.this.data = data;
            //data有可能为空
            //再展示
            Gson gson = new Gson();
            homeRoot = gson.fromJson(data, HomeBean.class);

            HomeFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
        }

        @Override
        protected void setResulttError(ShowingPage.StateType stateLoadError) {
            //失败
             HomeFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);

        }
    }
}
