package com.hhzmy.myxiangmu.farment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.hhzmy.myxiangmu.R;
import com.hhzmy.myxiangmu.adpter.MySouYePagerAdapter;
import com.hhzmy.myxiangmu.adpter.SouYeGVAdpter;
import com.hhzmy.myxiangmu.bean.ShouYeBean;
import com.hhzmy.myxiangmu.utils.StreamTools;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ShouYeFarment extends Fragment {
    private View view;
    private ViewPager viewPager;
    private ShouYeBean bean;
    private List<ShouYeBean.DataBean.TagBean> list;
    private List<ImageView> imgs;
    private List<View> dotlist;
    private int oldp=0;
    private int  page=0;
    private Handler handler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            viewPager.setCurrentItem(page);
        }
    };
    private GridView gd1;
    private List<ShouYeBean.DataBean.TagBean> list1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            view=inflater.inflate(R.layout.tab_one,container,false);
        initview();
        initdata();
        return view ;
    }

    private void initdata() {
        new AsyncTask<String,Void,String>()
        {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                if (s==null)
                {
                    return;
                }
                Gson gson=new Gson();
                bean = gson.fromJson(s, ShouYeBean.class);
                list = bean.getData().get(0).getTag();
                imgs = new ArrayList<ImageView>();
                for (int i=0;i<list.size();i++)
                {
                    ImageView  img=new ImageView(getActivity());
                    ImageLoader.getInstance().displayImage("http://image1.suning.cn/"+list.get(i).getPicUrl(),img);
                    imgs.add(img);
                }
                viewPager.setAdapter(new MySouYePagerAdapter(imgs));
                list1 = bean.getData().get(1).getTag();
                gd1.setAdapter(new SouYeGVAdpter(getActivity(),list1));


            }

            @Override
            protected String doInBackground(String... strings) {
                try {
                    String path=strings[0];
                    URL url=new URL(path);
                    HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    int  code=connection.getResponseCode();
                    if (code==200)
                    {
                        InputStream is=connection.getInputStream();
                        String  json= StreamTools.Read(is);
                        return json;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute("http://mock.eoapi.cn/success/jSWAxchCQfuhI6SDlIgBKYbawjM3QIga");
        dotlist = new ArrayList<>();
        dotlist.add(view.findViewById(R.id.dot1));
        dotlist.add(view.findViewById(R.id.dot2));
        dotlist.add(view.findViewById(R.id.dot3));
        dotlist.add(view.findViewById(R.id.dot4));

    }

    private void initview() {
        viewPager =(ViewPager)view.findViewById(R.id.vp);
        gd1 =(GridView)view.findViewById(R.id.gd);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                page=position;
                dotlist.get(position).setBackgroundResource(R.drawable.focus);
                dotlist.get(oldp).setBackgroundResource(R.drawable.normal);
                oldp=position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        ScheduledExecutorService service= Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(new Task(),2,2, TimeUnit.SECONDS);

    }
    class Task  implements Runnable{

        @Override
        public void run() {
            page=(page+1)%list.size();
            handler.sendEmptyMessage(0);
        }
    }
}