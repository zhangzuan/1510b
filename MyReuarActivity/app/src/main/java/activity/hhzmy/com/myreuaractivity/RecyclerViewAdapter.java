package activity.hhzmy.com.myreuaractivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

 /**
 * autour:张钻
 * date: 2016/12/26 15:03
 * update: 2016/12/26
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private Context context;
    private String [] titles;
    //建立枚举 2个item 类型
    public enum ITEM_TYPE {
        ITEM1,
        ITEM2
    }

    public RecyclerViewAdapter(Context context,String[] titles){
        this.titles = titles;
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//加载Item View的时候根据不同TYPE加载不同的布局
        if (viewType == ITEM_TYPE.ITEM1.ordinal()) {
            return new Item1ViewHolder(mLayoutInflater.inflate(R.layout.item1, parent, false));
        } else {
            return new Item2ViewHolder(mLayoutInflater.inflate(R.layout.item2, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Item1ViewHolder) {
            ((Item1ViewHolder) holder).mTextView.setText(titles[position]);
        } else if (holder instanceof Item2ViewHolder) {
            ((Item2ViewHolder) holder).mTextView.setText(titles[position]);
        }
    }



    //设置ITEM类型，可以自由发挥，这里设置item position单数显示item1 偶数显示item2
    @Override
    public int getItemViewType(int position) {
        //Enum类提供了一个ordinal()方法，返回枚举类型的序数，这里ITEM_TYPE.ITEM1.ordinal()代表0， ITEM_TYPE.ITEM2.ordinal()代表1
        return position % 2 == 0 ? ITEM_TYPE.ITEM1.ordinal() : ITEM_TYPE.ITEM2.ordinal();
    }

    @Override
    public int getItemCount() {
        return titles == null ? 0 : titles.length;
    }

    //item1 的ViewHolder
    public static class Item1ViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView;
        public Item1ViewHolder(View itemView) {
            super(itemView);
            mTextView=(TextView)itemView.findViewById(R.id.tv_item1_text);
        }
    }
    //item2 的ViewHolder
    public static class Item2ViewHolder extends RecyclerView.ViewHolder{

        TextView mTextView;
        public Item2ViewHolder(View itemView) {
            super(itemView);
            mTextView=(TextView)itemView.findViewById(R.id.tv_item2_text);
        }
    }
}
