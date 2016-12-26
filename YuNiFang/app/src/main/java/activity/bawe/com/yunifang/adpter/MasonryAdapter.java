package activity.bawe.com.yunifang.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import activity.bawe.com.yunifang.R;
import activity.bawe.com.yunifang.bean.FenleiBean;
import activity.bawe.com.yunifang.utils.ImageLoaderUtils;

/**
 * 1、类型：
 * 2、作者：张钻
 * 3、时间：2016-11-23
 */

public class MasonryAdapter extends RecyclerView.Adapter<MasonryAdapter.MasonryView> {
 private   List<FenleiBean.DataBean.GoodsBriefBean> list;
  private   Context context;
    private MasonryView masonryView;
    private   LayoutInflater mlayout1;
    private MasonryView viewhould;

    public MasonryAdapter(List<FenleiBean.DataBean.GoodsBriefBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MasonryAdapter.MasonryView onCreateViewHolder(ViewGroup parent, int viewType) {

//        viewhould = new MasonryView(mlayout1.inflate(R.layout.masonry_item, parent,false) );

//        return viewhould;
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.masonry_item,parent, false);
        return new MasonryView(view);
    }

    @Override
    public void onBindViewHolder(MasonryAdapter.MasonryView holder, int position) {
        ImageLoader.getInstance().displayImage(list.get(position).getGoods_img(),holder.imageView, ImageLoaderUtils.initOptions());

        //masonryView.imageView.setImageResource(list.get(position).getId());
     // masonryView.textView.setText(list.get(position).getCat_name());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class MasonryView extends  RecyclerView.ViewHolder{

        ImageView imageView;
          TextView textView;

        public MasonryView(View itemView){

            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.masonry_item_img );
           textView= (TextView) itemView.findViewById(R.id.masonry_item_title);
        }

    }

}
