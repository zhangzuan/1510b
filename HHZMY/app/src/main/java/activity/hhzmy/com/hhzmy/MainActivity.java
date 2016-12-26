package activity.hhzmy.com.hhzmy;

import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private ArrayList<View> viewList= new ArrayList<View>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);
        boolean flag = sp.getBoolean("status", false);
        if(flag){
            final Intent localIntent=new Intent(this,SecorActivity.class);
            Timer timer=new Timer();
            TimerTask tast=new TimerTask()
            {
                @Override
                public void run(){
                    startActivity(localIntent);
                    finish();
                }
            };
            timer.schedule(tast, 3000);
            return;
        }
        initData();
        initView();

    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.main_vp);
        viewPager.setAdapter(new MyAdapter());
    }

    private void initData() {
        viewList = new ArrayList<View>();
        View view1 = View.inflate(this, R.layout.activity_main, null);
        view1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Timer timer=new Timer();
                TimerTask tast=new TimerTask()
                {
                    @Override
                    public void run(){

                    }
                };
                timer.schedule(tast, 3000);
            }
        });
        viewList.add(view1);
        viewList.add(View.inflate(this, R.layout.viewpager1, null));
        viewList.add(View.inflate(this, R.layout.viewpager2, null));
        View view3 = View.inflate(this, R.layout.viewpager3, null);
        viewList.add(view3);
        view3.findViewById(R.id.imageView2).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //第一次显示viewpager，
                //点击之后，保存一个状态

                //这个创建一个文件
                SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);

                //获取到一个编辑器，笔记本
                Editor editor = sp.edit();
                editor.putBoolean("status", true);
                //相当于word文档保存按钮
                editor.commit();
                final Intent localIntent=new Intent(MainActivity.this,SecorActivity.class);
                Timer timer=new Timer();
                TimerTask tast=new TimerTask()
                {
                    @Override
                    public void run(){
                        startActivity(localIntent);
                    }
                };
                timer.schedule(tast, 3000);



            }
        });

    }
    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return viewList.size();
        }


        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }
        //移除某个条目
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // TODO Auto-generated method stub
            //super.destroyItem(container, position, object);

            container.removeView(viewList.get(position));

        }

        //产生一个条目
        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            container.addView(viewList.get(position));

            return viewList.get(position);
        }




    }
}
