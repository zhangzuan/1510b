package activity.bawe.com.yunifang;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SettingActivity extends AppCompatActivity {
    private ListView lv;
    private List<String> list=new ArrayList<>();
    String [] title={"购物须知","意见反馈","清除缓存","关于我们","拨打电话","检查更新"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        lv=(ListView) findViewById(R.id.setting_lv);
        initData();
        lv.setAdapter(new MySetListAdapter(this, list));
    }

    private void initData() {
        for (int i = 0; i < title.length; i++) {
            list.add(title[i]);

        }

    }

    private class MySetListAdapter extends BaseAdapter {

        private Context context;
        private List<String> list;

        public MySetListAdapter(Context context, List<String> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view=View.inflate(context,R.layout.setting_item,null);
            TextView tv=(TextView) view.findViewById(R.id.set_tv);
            tv.setText(list.get(i));

            return view;
        }
    }
    }

