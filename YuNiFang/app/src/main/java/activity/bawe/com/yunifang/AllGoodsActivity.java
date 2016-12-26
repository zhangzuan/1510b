package activity.bawe.com.yunifang;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import activity.bawe.com.yunifang.fragment.AllGoodsFragment;

public class AllGoodsActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener{
    private ImageView imag_fanhui;
    private ViewPager vp;
    private RadioGroup rg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_goods);
        //找到控件
        initView();
        //点击事件
        initOnclick();
    }

    private void initOnclick() {
        rg.setOnCheckedChangeListener(this);
        imag_fanhui.setOnClickListener(this);
    }

    private void initView() {
        rg=(RadioGroup) findViewById(R.id.allgoods_rg);

        imag_fanhui = (ImageView) findViewById(R.id.imag_fanhui);
        vp=(ViewPager) findViewById(R.id.all_goods_vp);

        vp.setAdapter(new MyAllGoodsAdapter(getSupportFragmentManager()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imag_fanhui:
                finish();
                overridePendingTransition(R.anim.leftin, R.anim.leftout);
                break;
        }


    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.allgoods_rb1:
                vp.setCurrentItem(0);
                break;
            case R.id.allgoods_rb2:
                vp.setCurrentItem(1);
                break;
            case R.id.allgoods_rb3:
                vp.setCurrentItem(2);
                break;
        }
    }
    private class MyAllGoodsAdapter extends FragmentPagerAdapter {
        Fragment fragment=null;
        public MyAllGoodsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){

                default:
                    fragment=new AllGoodsFragment();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
