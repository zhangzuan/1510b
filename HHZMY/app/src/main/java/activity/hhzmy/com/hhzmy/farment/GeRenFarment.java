package activity.hhzmy.com.hhzmy.farment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import activity.hhzmy.com.hhzmy.R;


/**
 * Created by Administrator on 2016/11/8.
 */

public class GeRenFarment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fore, container, false);
        return view;
    }
}
