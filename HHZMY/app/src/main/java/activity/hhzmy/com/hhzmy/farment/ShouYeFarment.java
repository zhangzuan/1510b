package activity.hhzmy.com.hhzmy.farment;


import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
 import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


import activity.hhzmy.com.hhzmy.ErWeiMaActivity;
import activity.hhzmy.com.hhzmy.R;
import activity.hhzmy.com.hhzmy.WebViewActivity;
import activity.hhzmy.com.hhzmy.XiangQingActivity;
import activity.hhzmy.com.hhzmy.adpter.MySouYePagerAdapter;
import activity.hhzmy.com.hhzmy.adpter.SouYeGVAdpter;
import activity.hhzmy.com.hhzmy.bean.ShouYeBean;
import activity.hhzmy.com.hhzmy.http.OkHttp;
import activity.hhzmy.com.hhzmy.utils.ImageLoaderUtils;
import activity.hhzmy.com.hhzmy.utils.StreamTools;
import okhttp3.Request;


public class ShouYeFarment extends Fragment implements View.OnClickListener{
 private DisplayImageOptions options = ImageLoaderUtils.initOptions();

    private View view;
    private ViewPager viewPager;
    private ShouYeBean bean;
    public ShouYeBean dadt;
    private List<ShouYeBean.DataBean.TagBean> list;
    private List<ImageView> imgs;
    private List<View> dotlist;
    private int oldp=0;
    private int  page=0;
     private   ImageView erwei,im1, im2, im3, im4, im5, im6,im7, im8, im9, im10, im11, im12,im13,im14,im15,im16,im17, im18, im19, im20, im21,im22, im23, im24, im25, im26, im27,im28,im29,im31,im30;
     private Handler handler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            viewPager.setCurrentItem(page);
        }
    };
    private GridView gd1,gd2,gd3;
    private List<ShouYeBean.DataBean.TagBean> list1;
    private ImageLoader loader=ImageLoader.getInstance();
    private String jia="http://image1.suning.cn/";
    private TextView tv1;
    private LinearLayout ll1,ll2,ll3,ll4;
    public List<ShouYeBean.DataBean.TagBean> json,json1,json2,json3,json4,json5,json6,json7,json8,json9;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.tab_one,container,false);
        loader=ImageLoader.getInstance();
        loader.init(ImageLoaderConfiguration.createDefault(getActivity()));

        initview();
        initdata();
        return view ;
    }

    private void initdata() {
    //                  "http://m.suning.com/?adTypeCode=1013&adId=102410006_0000000000/uimg/cms/img/147896334218385544.png"

        OkHttp.getAsync("http://mock.eoapi.cn/success/jSWAxchCQfuhI6SDlIgBKYbawjM3QIga"

                , new OkHttp.DataCallBack() {
                    public ShouYeBean dadt;


            @Override
            public void requestFailure(Request request, IOException e) {

            }
    @Override
            public void requestSuccess(String result) throws Exception {
             Gson gson=new Gson();

        dadt= gson.fromJson(result,ShouYeBean.class);
        json=dadt.getData().get(2).getTag();

       // json1=dadt.getData().get(3).getTag();
       // json2=dadt.getData().get(4).getTag();
        json1=dadt.getData().get(5).getTag();
        json2=dadt.getData().get(6).getTag();
        json3=dadt.getData().get(7).getTag();
        json4=dadt.getData().get(10).getTag();
        json5=dadt.getData().get(11).getTag();
         json6=dadt.getData().get(13).getTag();
        json7=dadt.getData().get(14).getTag();

        gd2.setAdapter(new SouYeGVAdpter(getActivity(),json1));

       gd3.setAdapter(new SouYeGVAdpter(getActivity(),json2));


        loader.displayImage(jia+json.get(0).getPicUrl(),im1);
        loader.displayImage(jia+json.get(1).getPicUrl(),im2);
        loader.displayImage(jia+json.get(2).getPicUrl(),im3);
        loader.displayImage(jia+json.get(3).getPicUrl(),im4);
        loader.displayImage(jia+json.get(4).getPicUrl(),im5);
        loader.displayImage(jia+json.get(5).getPicUrl(),im6);
        loader.displayImage(jia+json1.get(0).getPicUrl(),im7);
        loader.displayImage(jia+json1.get(1).getPicUrl(),im8);
        loader.displayImage(jia+json2.get(0).getPicUrl(),im9);
        loader.displayImage(jia+json2.get(1).getPicUrl(),im10);
        loader.displayImage(jia+json3.get(0).getPicUrl(),im11);
        loader.displayImage(jia+json3.get(1).getPicUrl(),im12);
        loader.displayImage(jia+json4.get(0).getPicUrl(),im13);
        loader.displayImage(jia+json4.get(1).getPicUrl(),im14);
        loader.displayImage(jia+json5.get(0).getPicUrl(),im15);
        loader.displayImage(jia+json5.get(1).getPicUrl(),im16);
        loader.displayImage(jia+json5.get(2).getPicUrl(),im17);
        loader.displayImage(jia+json5.get(3).getPicUrl(),im18);

        loader.displayImage(jia+json6.get(0).getPicUrl(),im19);
        loader.displayImage(jia+json7.get(0).getPicUrl(),im20);
        loader.getInstance().displayImage("http://image1.suning.cn/"+ dadt.getData().get(13).getTag().get(0).getPicUrl(),im19, options);
        //主题特卖大图片
        loader.displayImage("http://image1.suning.cn/" +dadt.getData().get(14).getTag().get(0).getPicUrl(), im20, options);
        for (int i = 0; i < bean.getData().get(15).getTag().size(); i++) {

            View inflate1 = View.inflate(getActivity(), R.layout.horizontalcrolliewitem, null);
            ImageView mImagerView = (ImageView) inflate1.findViewById(R.id.horizontalscrollview);
            ImageLoader.getInstance().displayImage("http://image1.suning.cn/" + dadt.getData().get(15).getTag().get(i).getPicUrl(), mImagerView, options);
            ll1.addView(inflate1);
        }
        loader.displayImage("http://image1.suning.cn/" + dadt.getData().get(16).getTag().get(0).getPicUrl(), im16, options);
        for (int i = 0; i < bean.getData().get(17).getTag().size(); i++) {

            View inflate1 = View.inflate(getActivity(), R.layout.horizontalcrolliewitem, null);
           ImageView mImagerView = (ImageView) inflate1.findViewById(R.id.horizontalscrollview);
            ImageLoader.getInstance().displayImage("http://image1.suning.cn/" + dadt.getData().get(17).getTag().get(i).getPicUrl(), mImagerView, options);
            ll2.addView(inflate1);
        }
//        ImageLoader.getInstance().displayImage("http://image1.suning.cn/" + zhuYe.data.get(18).tag.get(0).picUrl, zhuTiTeMai_daTu3, options);
//        for (int i = 0; i < zhuYe.data.get(19).tag.size(); i++) {
//
//            View inflate1 = View.inflate(getActivity(), R.layout.horizontalcrolliewitem, null);
//            ImageView mImagerView = (ImageView) inflate1.findViewById(R.id.horizontalscrollview);
//            ImageLoader.getInstance().displayImage("http://image1.suning.cn/" + zhuYe.data.get(19).tag.get(i).picUrl, mImagerView, options);
//            zhuTiTeMai_huaDong3.addView(inflate1);
//        }
//        ImageLoader.getInstance().displayImage("http://image1.suning.cn/" + zhuYe.data.get(20).tag.get(0).picUrl, zhuTiTeMai_daTu4, options);
//        for (int i = 0; i < zhuYe.data.get(21).tag.size(); i++) {
//
//            View inflate1 = View.inflate(getActivity(), R.layout.horizontalcrolliewitem, null);
//            ImageView mImagerView = (ImageView) inflate1.findViewById(R.id.horizontalscrollview);
//            ImageLoader.getInstance().displayImage("http://image1.suning.cn/" + zhuYe.data.get(21).tag.get(i).picUrl, mImagerView, options);
//            zhuTiTeMai_huaDong4.addView(inflate1);
//        }
//
//    }











                }
     });


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
                viewPager.setAdapter(new MySouYePagerAdapter(list, getActivity()));
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
        gd2 =(GridView)view.findViewById(R.id.gd1);
        gd3 =(GridView)view.findViewById(R.id.gd2);
        viewPager.setOnClickListener(this);

        gd1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startActivity(new Intent(getActivity(),WebViewActivity.class).putExtra("result",
                        list1.get(position).getLinkUrl()));
            }
        });
       // tv1=(TextView) view.findViewById(R.id.shouye_tv1);
        erwei=(ImageView) view.findViewById(R.id.erwei);
        erwei.setOnClickListener(this);
        im1=(ImageView) view.findViewById(R.id.shouye_im1);
        im2=(ImageView) view.findViewById(R.id.shouye_im2);
        im3=(ImageView) view.findViewById(R.id.shouye_im3);
        im4=(ImageView) view.findViewById(R.id.shouye_im4);
        im5=(ImageView) view.findViewById(R.id.shouye_im5);








        im6=(ImageView) view.findViewById(R.id.shouye_im6);
        im7=(ImageView) view.findViewById(R.id.shouye_im7);
        im8=(ImageView) view.findViewById(R.id.shouye_im8);
        im9=(ImageView) view.findViewById(R.id.shouye_im9);
        im10=(ImageView) view.findViewById(R.id.shouye_im10);
        im11=(ImageView) view.findViewById(R.id.shouye_im11);
        im12=(ImageView) view.findViewById(R.id.shouye_im12);
        im13=(ImageView) view.findViewById(R.id.shouye_im1_1);
        im14=(ImageView) view.findViewById(R.id.shouye_im1_2);
        im15=(ImageView) view.findViewById(R.id.shouye_im2_1);
        im16=(ImageView) view.findViewById(R.id.shouye_im2_2);
        im17=(ImageView) view.findViewById(R.id.shouye_im2_3);
        im18=(ImageView) view.findViewById(R.id.shouye_im2_4);
        im19=(ImageView) view.findViewById(R.id.shouye_im13);
        im20=(ImageView) view.findViewById(R.id.shouye_im14);
        im1.setOnClickListener(this);
        im2.setOnClickListener(this);
        im3.setOnClickListener(this);
        im4.setOnClickListener(this);
        im5.setOnClickListener(this);
        im6.setOnClickListener(this);
        im7.setOnClickListener(this);
        im9.setOnClickListener(this);
        im10.setOnClickListener(this);
        im11.setOnClickListener(this);
        im12.setOnClickListener(this);
        im13.setOnClickListener(this);
        im14.setOnClickListener(this);
        im15.setOnClickListener(this);
        im16.setOnClickListener(this);
        im17.setOnClickListener(this);
        im18.setOnClickListener(this);
        im19.setOnClickListener(this);
        im20.setOnClickListener(this);








//        im19 = (ImageView) view.findViewById(R.id.fragment1_zhuTiTeMai);
//     im20 = (ImageView) view.findViewById(R.id.fragment1_zhuTiTeMai_DaTu_1);
       ll1 = (LinearLayout) view.findViewById(R.id.fragment1_zhuTiTeMai_HuaDong_1);
       im21 = (ImageView) view.findViewById(R.id.fragment1_zhuTiTeMai_DaTu_2);
     ll2 = (LinearLayout) view.findViewById(R.id.fragment1_zhuTiTeMai_HuaDong_2);


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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.erwei:
                startActivity(new Intent(getActivity(), ErWeiMaActivity.class));
                break;
            case  R.id.shouye_im1:
//                 startActivity(new Intent(getActivity(),WebViewActivity.class).putExtra("result",
//                        json.get(0).getLinkUrl()));
                startActivity(new Intent(getActivity(), XiangQingActivity.class));
                break; case  R.id.shouye_im2:
//                startActivity(new Intent(getActivity(),WebViewActivity.class).putExtra("result",
//                        json.get(1).getLinkUrl()));
                startActivity(new Intent(getActivity(), XiangQingActivity.class));

                break;
            case  R.id.shouye_im3:
//                startActivity(new Intent(getActivity(),WebViewActivity.class).putExtra("result",
//                        json.get(2).getLinkUrl()));
                startActivity(new Intent(getActivity(), XiangQingActivity.class));


                break; case  R.id.shouye_im4:
//                startActivity(new Intent(getActivity(),WebViewActivity.class).putExtra("result",
//                        json.get(3).getLinkUrl()));
                startActivity(new Intent(getActivity(), XiangQingActivity.class));

                break;
            case  R.id.shouye_im5:
//                startActivity(new Intent(getActivity(),WebViewActivity.class).putExtra("result",
//                        json.get(4).getLinkUrl()));
                startActivity(new Intent(getActivity(), XiangQingActivity.class));

                break; case  R.id.shouye_im6:
//                startActivity(new Intent(getActivity(),WebViewActivity.class).putExtra("result",
//                        json.get(5).getLinkUrl()));
                startActivity(new Intent(getActivity(), XiangQingActivity.class));

                break;
            case  R.id.shouye_im7:
                startActivity(new Intent(getActivity(),WebViewActivity.class).putExtra("result",
                        json1.get(0).getLinkUrl()));
                break;
            case  R.id.shouye_im8:
                startActivity(new Intent(getActivity(),WebViewActivity.class).putExtra("result",
                        json1.get(1).getLinkUrl()));
                break;
            case  R.id.shouye_im9:
                startActivity(new Intent(getActivity(),WebViewActivity.class).putExtra("result",
                        json2.get(0).getLinkUrl()));
                break; case  R.id.shouye_im10:
                startActivity(new Intent(getActivity(),WebViewActivity.class).putExtra("result",
                        json2.get(1).getLinkUrl()));
                break; case  R.id.shouye_im11:
                startActivity(new Intent(getActivity(),WebViewActivity.class).putExtra("result",
                        json3.get(0).getLinkUrl()));
                break; case  R.id.shouye_im12:
                startActivity(new Intent(getActivity(),WebViewActivity.class).putExtra("result",
                        json3.get(1).getLinkUrl()));
                break;
            case  R.id.shouye_im1_1:
                startActivity(new Intent(getActivity(),WebViewActivity.class).putExtra("result",
                        json4.get(0).getLinkUrl()));
                break;
            case  R.id.shouye_im1_2:
            startActivity(new Intent(getActivity(),WebViewActivity.class).putExtra("result",
                    json4.get(1).getLinkUrl()));
            break;
            case  R.id.shouye_im2_1:
            startActivity(new Intent(getActivity(),WebViewActivity.class).putExtra("result",
                    json5.get(0).getLinkUrl()));
            break;
            case  R.id.shouye_im2_2:
            startActivity(new Intent(getActivity(),WebViewActivity.class).putExtra("result",
                    json5.get(1).getLinkUrl()));
            break;
            case  R.id.shouye_im2_3:
            startActivity(new Intent(getActivity(),WebViewActivity.class).putExtra("result",
                    json5.get(2).getLinkUrl()));
            break;
            case  R.id.shouye_im2_4:
            startActivity(new Intent(getActivity(),WebViewActivity.class).putExtra("result",
                    json5.get(3).getLinkUrl()));
            break;
            case  R.id.shouye_im13:
            startActivity(new Intent(getActivity(),WebViewActivity.class).putExtra("result",
                    json6.get(4).getLinkUrl()));
            break;
            case  R.id.shouye_im14:
            startActivity(new Intent(getActivity(),WebViewActivity.class).putExtra("result",
                    json7.get(0).getLinkUrl()));
            break;








        }


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