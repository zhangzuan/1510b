package com.hhzmy.myxiangmu.farment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhzmy.myxiangmu.R;

/**
 * Created by Administrator on 2016/11/8.
 */

public class GouWuFarment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_three, container, false);
        return view;
    }
}
