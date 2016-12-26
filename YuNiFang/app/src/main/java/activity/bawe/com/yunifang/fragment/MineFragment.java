package activity.bawe.com.yunifang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import activity.bawe.com.yunifang.LoginActivity;
import activity.bawe.com.yunifang.R;
import activity.bawe.com.yunifang.SettingActivity;
import activity.bawe.com.yunifang.base.BaseFragment;
import activity.bawe.com.yunifang.utils.CommonUtils;


/**
* autour:张钻
* date: 2016/11/30 14:26
* update: 2016/11/30
*/
public class MineFragment extends BaseFragment {
    private ListView lv;
    private RelativeLayout login;
    private ImageButton user_ib;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= CommonUtils.inflate(R.layout.minepage);
        //初始化控件
        lv=(ListView) view.findViewById(R.id.user_lv);
        user_ib=(ImageButton) view.findViewById(R.id.user_ib);
        login=(RelativeLayout) view.findViewById(R.id.user_login);
        //适配器
        lv.setAdapter(new MyMineListAdapter());
        //点击监听
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        user_ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), SettingActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    protected void onLoad() {
    }

    @Override
    public View createSuccessView() {

        return null;
    }


    private class MyMineListAdapter extends BaseAdapter {

        private String [] name={"我的订单","邀请有礼","刷脸测尺寸","我的现金券","我的抽奖单","我收藏的商品","联系客服"};
        private int [] image={R.mipmap.my_order_icon,R.mipmap.my_invite_gift_icon,R.mipmap.my_face_test_icon,R.mipmap.my_coupon_icon,R.mipmap.my_lottery_icon,R.mipmap.my_collection_icon,R.mipmap.personal_center_contact_service_icon};

        @Override
        public int getCount() {
            return image.length;
        }

        @Override
        public Object getItem(int i) {
            return image[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1=View.inflate(getContext(),R.layout.user_list_item,null);
            ImageView iv=(ImageView) view1.findViewById(R.id.user_list_img);
            iv.setImageResource(image[i]);
            TextView tv=(TextView) view1.findViewById(R.id.user_list_name);
            tv.setText(name[i]);
            return view1;
        }
    }
}
