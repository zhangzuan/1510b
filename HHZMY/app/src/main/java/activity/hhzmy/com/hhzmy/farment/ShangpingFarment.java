package activity.hhzmy.com.hhzmy.farment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import activity.hhzmy.com.hhzmy.R;

/**
 * Created by Administrator on 2016/11/17.
 */

public class ShangpingFarment extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     view=inflater.inflate(R.layout.shangping_farm,container,false);

        return view;
    }
}
