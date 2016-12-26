package activity.hhzmy.com.hhzmy.farment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import activity.hhzmy.com.hhzmy.R;
import activity.hhzmy.com.hhzmy.adpter.LeftRecyclerView_Adapter;
import activity.hhzmy.com.hhzmy.adpter.RightRecyclerView_Adapter;
import activity.hhzmy.com.hhzmy.bean.ChildrenBean;
import activity.hhzmy.com.hhzmy.bean.RedBaby;
import activity.hhzmy.com.hhzmy.bean.RsBean;
import activity.hhzmy.com.hhzmy.utils.DividerItemDecoration;


/**
 * Created by Administrator on 2016/11/8.
 */

public class FenLeiFarment extends Fragment {

    private View view;
    RecyclerView leftRecyclerView;

    RecyclerView rightRecyclerView;
    ;
    List<ChildrenBean> AllList;
    private LeftRecyclerView_Adapter leftadapter;
    private List<RsBean> rs;
    private RightRecyclerView_Adapter rightadapter;
    private int index=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          view = inflater.inflate(R.layout.tab_two, container, false);
        initUI();
        getData();
        return view;

    }
    public void initUI() {
        leftRecyclerView = (RecyclerView) view.findViewById(R.id.leftType);
        rightRecyclerView = (RecyclerView) view.findViewById(R.id.rightType);
        AllList= new ArrayList<ChildrenBean>();
    }
    public void getData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    InputStream inputStream = getActivity().getAssets().open("category.txt");
                    byte[] bytes = new byte[1024];
                    int len;
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    while ((len = inputStream.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, len);
                    }
                    String json = outputStream.toString("utf-8");
                    final RedBaby redBaby = new Gson().fromJson(json, RedBaby.class);
                    rs = redBaby.getRs();
                    leftRecyclerView.post(new Runnable() {
                        @Override
                        public void run() {
                            rs.get(0).isChecked=true;
                            //设置布局管理器
                            leftRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            //设置适配器
                            leftadapter = new LeftRecyclerView_Adapter(getActivity(), rs);
                            leftRecyclerView.setAdapter(leftadapter);
                            //设置分割线
                                 leftRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST));

                            //点击事件
                            leftadapter.setOnRecyclerItemClickListener(new LeftRecyclerView_Adapter.OnRecyclerItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    rs.get(index).isChecked=false;
                                    rs.get(position).isChecked=true;
                                    index=position;
                                    update(position);
                                    Toast.makeText(getActivity(),"item"+position,Toast.LENGTH_SHORT).show();
                                }
                            });

                            //右边
                            //设备管理器
                            GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),3);
                            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                @Override
                                public int getSpanSize(int position) {
                                    return AllList.get(position).isHeader?3:1;
                                }
                            });
                            rightRecyclerView.setLayoutManager(gridLayoutManager);
                            //设置适配器
                            List<ChildrenBean> childrenBeen = rs.get(0).getChildren();
                            for (int i = 0; i < childrenBeen.size(); i++) {
                                childrenBeen.get(i).isHeader=true;
                                AllList.add(childrenBeen.get(i));
                                AllList.addAll(childrenBeen.get(i).getChildren());
                            }
                            rightadapter = new RightRecyclerView_Adapter(AllList,getActivity());
                            rightRecyclerView.setAdapter(rightadapter);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }
    public void update(int position){
        leftadapter.notifyDataSetChanged();
        AllList.clear();
        List<ChildrenBean> childrenBeen =rs.get(position).getChildren();
        for (int i = 0; i < childrenBeen.size(); i++) {
            childrenBeen.get(i).isHeader=true;
            AllList.add(childrenBeen.get(i));
            AllList.addAll(childrenBeen.get(i).getChildren());
        }

        rightadapter.notifyDataSetChanged();
    }


}
