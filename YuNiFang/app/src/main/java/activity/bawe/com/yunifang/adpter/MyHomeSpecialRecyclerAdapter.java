package activity.bawe.com.yunifang.adpter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import activity.bawe.com.yunifang.R;
import activity.bawe.com.yunifang.bean.HomeBean;
import activity.bawe.com.yunifang.utils.ImageLoaderUtils;

/**
* autour:张钻
* date: 2016/12/3 11:56
* update: 2016/12/3
*/

public class MyHomeSpecialRecyclerAdapter extends RecyclerView.Adapter<MyHomeSpecialRecyclerAdapter.MyViewHolder1> {

    private final LayoutInflater mlayout1;
    private List<HomeBean.DataBean.SubjectsBean> subjectsBeen;
    private List<HomeBean.DataBean.SubjectsBean.GoodsListBean> goodsListBeen;
    private Context context;
    private MyViewHolder1 holder1;

    public MyHomeSpecialRecyclerAdapter(List<HomeBean.DataBean.SubjectsBean> subjectsBeen, Context context) {
        this.subjectsBeen = subjectsBeen;
        this.context = context;
        mlayout1=LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {

        holder1 = new MyViewHolder1(mlayout1.inflate(R.layout.home_special_recycler_item,parent,false));
        return holder1;
    }

    @Override
    public void onBindViewHolder(MyViewHolder1 holder, int position) {
        ImageLoader.getInstance().displayImage(subjectsBeen.get(position).getImage(),holder1.imageView, ImageLoaderUtils.initOptions());

        goodsListBeen=subjectsBeen.get(position).getGoodsList();
        LinearLayoutManager layoutManager=new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder1.recyclerView.setLayoutManager(layoutManager);
        holder1.recyclerView.setAdapter(new Recycler_RecyclerAdapter(goodsListBeen,context));
    }

    @Override
    public int getItemCount() {
        return subjectsBeen.size();
    }

    public class MyViewHolder1 extends RecyclerView.ViewHolder{

        private final ImageView imageView;
        private final RecyclerView recyclerView;

        public MyViewHolder1(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.home_special_recycler_img);

            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            recyclerView = (RecyclerView) itemView.findViewById(R.id.home_special_recycler_horizontal);

        }
    }
}
