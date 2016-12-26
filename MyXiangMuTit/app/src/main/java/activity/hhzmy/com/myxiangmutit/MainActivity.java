package activity.hhzmy.com.myxiangmutit;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ViewPager vp;

    private String[] newstitiles={"今日要闻","娱乐","体育","八卦","军事","科技","动漫","IT","游戏","竞技","旅游","彩票"};

    private LinearLayout ll_title;

    private List<TextView> tvlist;//存放所有的TextView

    private HorizontalScrollView hsv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        //初始化新闻标题
        initTitle();
    }

    private void initTitle() {
        tvlist = new ArrayList<TextView>();
        for(int i=0;i<newstitiles.length;i++){
            //创建TextView对象
            TextView tv = new TextView(this);
            tv.setTextSize(22);
            tv.setText(newstitiles[i]);
            tv.setId(1000+i);

            if(i==0){//第一个设置为红色
                tv.setTextColor(Color.RED);
            }else{
                tv.setTextColor(Color.BLACK);
            }

            //设置TextView的属性
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            //设置textView之间的间距
            params.setMargins(0, 0, 20, 0);

            ll_title.addView(tv, params);

            //把创建好的TextView添加到集合中
            tvlist.add(tv);

            tv.setOnClickListener(this);

        }

    }

    private void initView() {
        //找对象
        vp = (ViewPager)findViewById(R.id.vp);
        //装title的LinearLayout
        ll_title = (LinearLayout)findViewById(R.id.ll_title);

        hsv = (HorizontalScrollView)findViewById(R.id.hsv);

        //viewPager适配Fragment
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return newstitiles.length;
            }

            @Override
            public Fragment getItem(int position) {


                return NewsFragment.getNewsFragment(newstitiles[position]);
            }
        });

        //viewpager监听
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                //改变TextView的字体颜色
                for(int i=0;i<tvlist.size();i++){
                    TextView tv = tvlist.get(i);
                    if(i==position){
                        tv.setTextColor(Color.RED);
                    }else{
                        tv.setTextColor(Color.BLACK);
                    }
                }

                TextView tv2 = tvlist.get(position);
                //获取TextView的宽度
                int width = tv2.getWidth();

                //滚动到指定位置：水平位置：索引值*（TextView宽度+margin）
                hsv.scrollTo(position*(width+20), 0);
                TextView textView=(TextView) findViewById(R.id.main_tv);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();//1000+索引值,1001
        //设置ViewPager当前显示哪一页
        vp.setCurrentItem(id-1000);
    }
}
