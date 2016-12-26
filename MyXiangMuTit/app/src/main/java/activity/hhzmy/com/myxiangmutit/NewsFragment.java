package activity.hhzmy.com.myxiangmutit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NewsFragment extends Fragment {

	private TextView tv_content;
	
	private NewsFragment(){}

	public static NewsFragment getNewsFragment(String title){
		NewsFragment nf = new NewsFragment();
		Bundle args = new Bundle();
		args.putString("title", title);
		nf.setArguments(args );
		return nf;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.news_content_fragment, container, false);
		tv_content = (TextView)v.findViewById(R.id.tv_content);
		
		return v;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		Bundle arguments = getArguments();
		
		if(arguments != null){
			String title = arguments.getString("title");
			tv_content.setText(title);
		}
	}
	
}
