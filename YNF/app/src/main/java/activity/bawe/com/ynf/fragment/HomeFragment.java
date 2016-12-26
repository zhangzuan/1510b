package activity.bawe.com.ynf.fragment;

import android.view.View;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import activity.bawe.com.ynf.R;
import activity.bawe.com.ynf.bean.ShouYeBean;
import activity.bawe.com.ynf.bese.BaseFragment;
import activity.bawe.com.ynf.utils.LogUtils;
import activity.bawe.com.ynf.view.ShowingPage;

/**
* autour:张钻
* date: 2016/11/28 14:11
* update: 2016/11/28
*/
public class HomeFragment extends BaseFragment {
    private List<ShouYeBean.DataBean.Ad1Bean> json;
    private String s;
    private View view;

    @Override
    protected void onLoad() {
//        OkHttp.getAsync("http://m.yunifang.com/yunifang/mobile/home?random=59676&encode=62d458fefce9c740359873cc19b05188", new OkHttp.DataCallBack() {
//            @Override
//            public void requestFailure(Request request, IOException e) {
//
//            }
//
//            @Override
//            public void requestSuccess(String result) throws Exception {
//                Gson gson=new Gson();
//                ShouYeBean date = gson.fromJson(result, ShouYeBean.class);
//                  json= date.getData().getAd1();
//            }
//        });
        RequestParams re=new RequestParams("http://m.yunifang.com/yunifang/mobile/home?random=59676&encode=62d458fefce9c740359873cc19b05188");
        //去加载
        x.http().get(re, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                //先赋值---
                HomeFragment.this.s=s;
                HomeFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
                LogUtils.e("AAAAA","success-------cart-"+s);
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }
            @Override
            public void onCancelled(CancelledException e) {
            }
            @Override
            public void onFinished() {
            }
        });
     }

    @Override
    public View createSuccessView() {

//        TextView textView =new TextView(getContext());
//        textView.setText(s );
//        return textView;
 view=View.inflate(getContext(), R.layout.shouye_view,null);
return view;
    }
}
