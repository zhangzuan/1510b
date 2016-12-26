package activity.hhzmy.com.myaa.farment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import activity.hhzmy.com.myaa.R;
import activity.hhzmy.com.myaa.adpter.Myadapter;
import activity.hhzmy.com.myaa.bean.Data;

public class Fragment1 extends Fragment {
	
	List<Data> datas=new ArrayList<Data>();
	private ListView list;
	Myadapter adapter;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return View.inflate(getActivity(), R.layout.fragment1, null);
	}
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	//	qing();
		//list=(ListView)getView().findViewById(R.id.xlist);
		adapter=new Myadapter(datas, getActivity());
		list.setAdapter(adapter);
	}
	
//	public  void qing(){
//		new Thread(){
//			public void run() {
//				try {
//					List<Data> dataa=new ArrayList<Data>();
//					HttpClient client=new DefaultHttpClient();
//					HttpGet get=new HttpGet("http://japi.juhe.cn/joke/content/list.from?key=%20874ed931559ba07aade103eee279bb37%20&page=1&pagesize=10&sort=asc&time=1418745237");
//					HttpResponse response=client.execute(get);
//					if (response.getStatusLine().getStatusCode()==200) {
//						HttpEntity entity=response.getEntity();
//						String json= EntityUtils.toString(entity,"gbk");
//						JSONObject object=new JSONObject(json);
//						JSONObject object2=object.getJSONObject("result");
//						JSONArray array=object2.getJSONArray("data");
//						for (int i = 0; i < array.length(); i++) {
//							JSONObject object3=(JSONObject) array.get(i);
//							String content=object3.optString("content");
//							String updatetime=object3.optString("updatetime");
//							Data data=new Data(content, updatetime);
//							dataa.add(data);
//						}
//						datas.clear();
//						datas.addAll(dataa);
//					}
//				} catch (ClientProtocolException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			};
//		}.start();
//	}

}
