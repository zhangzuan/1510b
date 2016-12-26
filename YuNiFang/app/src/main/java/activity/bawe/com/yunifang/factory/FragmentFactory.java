package activity.bawe.com.yunifang.factory;

import android.support.v4.app.Fragment;

import java.util.HashMap;

import activity.bawe.com.yunifang.fragment.CartFragment;
import activity.bawe.com.yunifang.fragment.CategoryFragment;
import activity.bawe.com.yunifang.fragment.HomeFragment;
import activity.bawe.com.yunifang.fragment.MineFragment;

/**
* autour:张钻
* date: 2016/11/30 16:03
* update: 2016/11/30
*/
public class FragmentFactory {
    //集合
    public static HashMap<Integer, Fragment> fragmentMap = new HashMap<>();

    public static Fragment getFragment(int position) {
        Fragment fragment = fragmentMap.get(position);
        if (fragment != null) {
            return fragment;
        }
        switch (position) {
            case 0:
                fragment=new HomeFragment();
                break;
            case 1:
                fragment=new CategoryFragment();
                break;
            case 2:
                fragment=new CartFragment();
                break;
            case 3:
                fragment=new MineFragment();
                break;
        }
        //添加到集合中
        fragmentMap.put(position,fragment);
        return  fragment;
    }
}
