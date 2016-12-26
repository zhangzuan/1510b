package com.hhzmy.myxiangmu;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hhzmy.myxiangmu.farment.FenLeiFarment;
import com.hhzmy.myxiangmu.farment.GeRenFarment;
import com.hhzmy.myxiangmu.farment.GouWuFarment;
import com.hhzmy.myxiangmu.farment.ShouYeFarment;

import java.util.ArrayList;

public class SecorActivity extends AppCompatActivity {
   // http://mock.eoapi.cn/success/jSWAxchCQfuhI6SDlIgBKYbawjM3QIga
    private ArrayList<TextView> tvlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secor);
        Fragment fragment1 = new ShouYeFarment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.lin_index, fragment1, "shouye").commit();
        initView();
    }

    private void initView() {
        TextView tv1 = (TextView) findViewById(R.id.rb_index_home);
        TextView tv2 = (TextView) findViewById(R.id.rb_index_classify);
        TextView tv3 = (TextView) findViewById(R.id.rb_index_shopping);
        TextView tv4 = (TextView) findViewById(R.id.rb_index_user);

        // 存放至集合中
        tvlist = new ArrayList<TextView>();
        tvlist.add(tv1);
        tvlist.add(tv2);
        tvlist.add(tv3);
        tvlist.add(tv4);

        View.OnClickListener onclick = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.rb_index_home:// 点击选项卡1
                         Fragment fragment1 = new ShouYeFarment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.lin_index, fragment1, "shouye").commit();
                        break;

                    case R.id.rb_index_classify:// 点击选项卡2

                        Fragment fragment2 = new FenLeiFarment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.lin_index, fragment2, "fenlei").commit();
                        break;
                    case R.id.rb_index_shopping:// 点击选项卡3
                         Fragment fragment3 = new GouWuFarment();
                        getSupportFragmentManager().beginTransaction()
                                .replace( R.id.lin_index, fragment3, "gouwu")
                                .commit();
                        break;

                    case R.id.rb_index_user:// 点击选项卡4
                         Fragment fragment4 = new GeRenFarment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.lin_index, fragment4, "geren")
                                .commit();
                        break;
                }
            }
        };

        tv1.setOnClickListener(onclick);
        tv2.setOnClickListener(onclick);
        tv3.setOnClickListener(onclick);
        tv4.setOnClickListener(onclick);

    }

}
