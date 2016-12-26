package activity.bawe.com.ynf;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import activity.bawe.com.ynf.factory.FragmentFactory;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private RadioGroup rg_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.vp_main);
        rg_main = (RadioGroup) findViewById(R.id.rg_main);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return FragmentFactory.getFragment(position);
            }

            @Override
            public int getCount() {
                return 4;
            }
        });
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

//                viewPager.setCurrentItem(i);
                switch (i) {
                    case R.id.rb_home_page:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.rb_category:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.rb_cart:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.rb_mine:
                        viewPager.setCurrentItem(3);
                        break;

                }
            }
        });

    }
}
