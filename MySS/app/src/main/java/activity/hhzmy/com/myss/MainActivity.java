package activity.hhzmy.com.myss;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import activity.hhzmy.com.myss.activity.hhzmy.com.myss.bean.NewBean;
import activity.hhzmy.com.myss.activity.hhzmy.com.myss.bean.NewBean.DataBean;
import activity.hhzmy.com.myss.activity.hhzmy.com.myss.http.OkHttp;
import activity.hhzmy.com.myss.activity.hhzmy.com.myss.view.PullBaseView;
import activity.hhzmy.com.myss.activity.hhzmy.com.myss.view.PullRecyclerView;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity implements PullBaseView.OnHeaderRefreshListener,
        PullBaseView.OnFooterRefreshListener{

private String Url= "http://m.yunifang.com/yunifang/mobile/goods/getall?random=39986&encode=2092d7eb33e8ea0a7a2113f2d9886c90&category_id=17";
private PullRecyclerView mRecyclerView;
    private List<String> mDatas;
    private HomeAdapter mAdapter;



 private List<DataBean> json;
    ImageLoader loader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();


         loader=ImageLoader.getInstance();
        loader.init(ImageLoaderConfiguration.createDefault(MainActivity.this));
        mRecyclerView = (PullRecyclerView) findViewById(R.id.id_recyclerview);
        mRecyclerView.setOnHeaderRefreshListener(this);
        mRecyclerView.setOnFooterRefreshListener(this);//


        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //  mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //  mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));


    }

    protected void initData()
    {


        OkHttp.getAsync(Url, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Gson gson=new Gson();
                NewBean date = gson.fromJson(result, NewBean.class);
                json = date.getData();
                mAdapter = new HomeAdapter();
                mRecyclerView.setAdapter(mAdapter);

//                Message msg=new Message();
//                msg.obj=json;
//                msg.what=1;
//                myHandler.sendMessage(msg);
//                new Thread(){
//                    @Override
//                    public void run() {
//                        super.run();
//
//                    }
//                }.start();



                Toast.makeText(MainActivity.this,result,Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onFooterRefresh(PullBaseView view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // mDatas.add("TEXT鏇村");
                mAdapter.notifyDataSetChanged();
                mRecyclerView.onFooterRefreshComplete();
            }
        }, 2000);


    }

    @Override
    public void onHeaderRefresh(PullBaseView view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //  mDatas.add(0, "TEXT鏂板");
                mAdapter.notifyDataSetChanged();
                mRecyclerView.onHeaderRefreshComplete();
            }
        }, 3000);

    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>
    {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    MainActivity.this).inflate(R.layout.item_home, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position)
        {
            holder.tv.setText(json.get(position).getGoods_name());
            loader.displayImage(json.get(position).getGoods_img(),holder.im);
        }

        @Override
        public int getItemCount()
        {
            return json.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {

            TextView tv;
            ImageView im;

            public MyViewHolder(View view)
            {
                super(view);
                tv = (TextView) view.findViewById(R.id.id_num);
                im=(ImageView) view.findViewById(R.id.id_im);
            }
        }
    }
}
