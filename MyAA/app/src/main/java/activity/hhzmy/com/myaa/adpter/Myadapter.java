package activity.hhzmy.com.myaa.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import activity.hhzmy.com.myaa.R;
import activity.hhzmy.com.myaa.bean.Data;


public class Myadapter extends BaseAdapter {
	List<Data> datas;
	Context context;
	
	
	public Myadapter(List<Data> datas, Context context) {
		super();
		this.datas = datas;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		view=View.inflate(context, R.layout.list_item, null);
		TextView w1=(TextView) view.findViewById(R.id.w1);
		TextView w2=(TextView) view.findViewById(R.id.w2);
		w1.setText(datas.get(position).getContent());
		w2.setText(datas.get(position).getUpdatetime());
		return view;
	}

}
