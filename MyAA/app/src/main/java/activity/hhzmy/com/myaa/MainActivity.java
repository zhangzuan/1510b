package activity.hhzmy.com.myaa;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import activity.hhzmy.com.myaa.farment.Fragment1;
import activity.hhzmy.com.myaa.farment.Fragment2;
import activity.hhzmy.com.myaa.farment.Fragment3;
import activity.hhzmy.com.myaa.farment.Fragment4;
import activity.hhzmy.com.myaa.farment.Fragment5;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager vp;
    private TextView t1;
    private TextView t2;
    private TextView t3;
    private TextView t4;
    private TextView t5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp = (ViewPager) findViewById(R.id.vp);
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return 5;
            }

            @Override
            public Fragment getItem(int arg0) {
                // TODO Auto-generated method stub
                Fragment fragment = null;
                switch (arg0) {
                    case 0:
                        fragment = new Fragment1();
                        break;
                    case 1:
                        fragment = new Fragment2();
                        break;
                    case 2:
                        fragment = new Fragment3();
                        break;
                    case 3:
                        fragment = new Fragment4();
                        break;
                    case 4:
                        fragment = new Fragment5();
                        break;

                    default:
                        break;
                }
                return fragment;
            }
        });


        t1 = (TextView) findViewById(R.id.t1);
        t2 = (TextView) findViewById(R.id.t2);
        t3 = (TextView) findViewById(R.id.t3);
        t4 = (TextView) findViewById(R.id.t4);
        t5 = (TextView) findViewById(R.id.t5);
        t1.setOnClickListener(this);
        t2.setOnClickListener(this);
        t3.setOnClickListener(this);
        t4.setOnClickListener(this);
        t5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.t1:
                vp.setCurrentItem(0);
                t1.setTextColor(Color.RED);
                t2.setTextColor(Color.BLACK);
                t3.setTextColor(Color.BLACK);
                t4.setTextColor(Color.BLACK);
                t5.setTextColor(Color.BLACK);
                break;
            case R.id.t2:
                vp.setCurrentItem(1);
                t2.setTextColor(Color.RED);
                t1.setTextColor(Color.BLACK);
                t3.setTextColor(Color.BLACK);
                t4.setTextColor(Color.BLACK);
                t5.setTextColor(Color.BLACK);
                break;
            case R.id.t3:
                vp.setCurrentItem(2);
                t3.setTextColor(Color.RED);
                t2.setTextColor(Color.BLACK);
                t1.setTextColor(Color.BLACK);
                t4.setTextColor(Color.BLACK);
                t5.setTextColor(Color.BLACK);
                break;
            case R.id.t4:
                vp.setCurrentItem(3);
                t4.setTextColor(Color.RED);
                t2.setTextColor(Color.BLACK);
                t3.setTextColor(Color.BLACK);
                t1.setTextColor(Color.BLACK);
                t5.setTextColor(Color.BLACK);
                break;
            case R.id.t5:
                vp.setCurrentItem(4);
                t5.setTextColor(Color.RED);
                t2.setTextColor(Color.BLACK);
                t3.setTextColor(Color.BLACK);
                t4.setTextColor(Color.BLACK);
                t1.setTextColor(Color.BLACK);
                break;


            default:
                break;
        }
    }
}
