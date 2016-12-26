package activity.hhzmy.com.myaa.farment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.IOException;
import java.util.List;

import activity.hhzmy.com.myaa.R;
import activity.hhzmy.com.myaa.bean.Bean;
import activity.hhzmy.com.myaa.http.OkHttp;
import okhttp3.Request;

public class Fragment2 extends Fragment {

	RecyclerView mRecyclerView;
	HomeAdapter mAdapter;
	private List<String> mDatas;
	TextView te;
	String path = "http://m.yunifang.com/yunifang/mobile/goods/getall?random=39986&encode=2092d7eb33e8ea0a7a2113f2d9886c90&category_id=17";
	private List<Bean.DataBean> data;
	ImageLoader loader;
	SwipeRefreshLayout swipeRefreshLayout;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=View.inflate(getActivity(), R.layout.fragment2, null);
		loader=ImageLoader.getInstance();
		loader.init(ImageLoaderConfiguration.createDefault(getActivity()));
		mRecyclerView = (RecyclerView)view.findViewById(R.id.id_recyclerview);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(),
				new RecyclerItemClickListener.OnItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				Toast.makeText(getActivity(),"ZHESH",Toast.LENGTH_SHORT).show();
			}
		}


		));
	 swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
		swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				swipeRefreshLayout.setRefreshing(false);

				mAdapter.notifyDataSetChanged();
			}
		});
		in();
		return view;
	}
	public static class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener{
		private OnItemClickListener mListener;

		public interface OnItemClickListener {
			public void onItemClick(View view, int position);
		}
		GestureDetector mGestureDetector;

		public RecyclerItemClickListener(Context context, OnItemClickListener listener) {
			mListener = listener;
			mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
				@Override public boolean onSingleTapUp(MotionEvent e) {
					return true;
				}
			});
		}
		@Override
		public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
			View childView = rv.findChildViewUnder(e.getX(), e.getY());
			if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
				mListener.onItemClick(childView, rv.getChildPosition(childView));
				return true;
			}
			return false;
		}

		@Override
		public void onTouchEvent(RecyclerView rv, MotionEvent e) {

		}

		@Override
		public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

		}
	}

	private void in() {
		OkHttp.getAsync(path, new OkHttp.DataCallBack() {
			@Override
			public void requestFailure(Request request, IOException e) {

			}

			@Override
			public void requestSuccess(String result) throws Exception {
				Gson mGson = new Gson();
				Bean bean = mGson.fromJson(result, Bean.class);
				data =  bean.getData();
				mRecyclerView.setAdapter(mAdapter = new HomeAdapter());
			}
		});
	}

	class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {


		@Override
		public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
					getActivity()).inflate(R.layout.item, parent,
					false));
			return holder;
		}

		@Override
		public void onBindViewHolder(MyViewHolder holder, int position) {

			holder.te.setText(data.get(position).getGoods_name());
			loader.displayImage(data.get(position).getGoods_img(),holder.im);
		}

		@Override
		public int getItemCount() {
			return data.size();
		}

		public class MyViewHolder extends RecyclerView.ViewHolder {
			TextView te;
			ImageView im;

			public MyViewHolder(View itemView) {
				super(itemView);
				te = (TextView) itemView.findViewById(R.id.textView);
				im = (ImageView) itemView.findViewById(R.id.im);
			}
		}
	}

}
