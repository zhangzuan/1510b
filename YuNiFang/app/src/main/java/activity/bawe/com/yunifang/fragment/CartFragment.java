package activity.bawe.com.yunifang.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import activity.bawe.com.yunifang.PayActivity;
import activity.bawe.com.yunifang.R;
import activity.bawe.com.yunifang.base.BaseData;
import activity.bawe.com.yunifang.base.BaseFragment;
import activity.bawe.com.yunifang.bean.CarData;
import activity.bawe.com.yunifang.bean.ShopCarDataBean;
import activity.bawe.com.yunifang.utils.CommonUtils;
import activity.bawe.com.yunifang.utils.OkHttp;
import activity.bawe.com.yunifang.utils.URLUtils;
import activity.bawe.com.yunifang.view.ShowingPage;
import okhttp3.Request;

/**
 * autour:张钻
 * date: 2016/11/30 16:47
 * update: 2016/11/30
 */
 
public class CartFragment extends BaseFragment implements View.OnClickListener {
    private String s;
    private String data;
    private MyHomeData myHomeData;
    private ShopCarDataBean homeRoot;
    private WebView wv;
    private View view;
    private ListView shop_lv_view;
    private CheckBox checkall;
    private TextView shop_editbutton;
    private RelativeLayout rela_allprice;
    private Button shop_bayall;
    private String shop_bayall_text;
    private String shop_editbutton_text;
    private TextView allprice;
    private String json;
    private double sum = 0.00;
    private shop_lv_adapter adapter;
    private List<CarData> list;

    public CartFragment() {
    }

    public static CartFragment getGouWuFarment() {
        CartFragment fragment = new CartFragment();

        return fragment;

    }

    @Override
    protected void onLoad() {
        myHomeData = new CartFragment.MyHomeData();
        myHomeData.getData(URLUtils.addgouwuurl, null, 0, BaseData.NORMALTIME);
//        RequestParams re=new RequestParams("http://www.baidu.com");
//        //去加载
//        x.http().get(re, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String s) {
//                //先赋值---
//                CartFragment.this.s=s;
//                CartFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
//                LogUtils.e("AAAAA","success-------cart-"+s);
//            }
//
//            @Override
//            public void onError(Throwable throwable, boolean b) {
//
//            }
//            @Override
//            public void onCancelled(CancelledException e) {
//            }
//            @Override
//            public void onFinished() {
//            }
//        });
    }

    @Override
    public View createSuccessView() {
        View view = initView();
        getCarData();
        checkall.setOnClickListener(this);
        shop_editbutton.setOnClickListener(this);
        shop_bayall.setOnClickListener(this);

//        LogUtils.e("AAAAA","create-------cart-"+s);
//        TextView textView =new TextView(getContext());
//        textView.setText(s);
//        return textView;
        return view;
    }

    private void getCarData() {
        OkHttp.getAsync("http://mock.eoapi.cn/success/megQ2CBFAeFzJzIwTSVdNnpQYZCrsrIq", new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                json = result;
                //Toast.makeText(ShopCarActivity.this, json + "", Toast.LENGTH_SHORT).show();
                showData(json);
            }
        });
    }

    private void showData(String json) {
        //Toast.makeText(ShopCarActivity.this, json + "", Toast.LENGTH_SHORT).show();
        Gson gson = new Gson();
        ShopCarDataBean shopping = gson.fromJson(json, ShopCarDataBean.class);
        //商品型号
        List<ShopCarDataBean.DataBeanX.Data1Bean.DataBean.ItemClusterDisplayVOBean.ColorListBean> cl = shopping.getData().getData1().getData().getItemClusterDisplayVO().getColorList();
        //商品名称
        ShopCarDataBean.DataBeanX.Data1Bean.DataBean.ItemInfoVoBean name = shopping.getData().getData1().getData().getItemInfoVo();

        //价格
        ShopCarDataBean.DataBeanX.PriceBean.SaleInfoBean jia = shopping.getData().getPrice().getSaleInfo().get(0);
        //jia1.setText(jia.getNetPrice());现价
        //jia2.setText(jia.getRefPrice());原价
        list = new ArrayList<>();
        CarData cardata = new CarData();
        cardata.setGoodName(name.getItemName() + "");
        cardata.setNowPrice(Double.parseDouble(jia.getNetPrice()));
        cardata.setOriginalPrice(Double.parseDouble(jia.getRefPrice()));
        cardata.setGoodCL(cl.get(0).getCharacterValueName() + "");
        list.add(cardata);
        //Toast.makeText(ShopCarActivity.this, list.get(0).getNowPrice() + "", Toast.LENGTH_SHORT).show();
        adapter = new shop_lv_adapter(getActivity(), list);
        shop_lv_view.setAdapter(adapter);
    }

    private View initView() {
        View view = CommonUtils.inflate(R.layout.tab_three);
        shop_lv_view = (ListView) view.findViewById(R.id.shop_lv_view);
        checkall = (CheckBox) view.findViewById(R.id.checkall);
        shop_editbutton = (TextView) view.findViewById(R.id.shop_editbutton);
        rela_allprice = (RelativeLayout) view.findViewById(R.id.rela_allprice);
        shop_bayall = (Button) view.findViewById(R.id.shop_bayall);
        allprice = (TextView) view.findViewById(R.id.allprice);
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (view.getId()) {
            case R.id.checkall:
                boolean flag = checkall.isChecked();
                double f1 = 0;
                for (int i = 0; i < adapter.getSelect().size(); i++) {
                    adapter.getSelect().set(i, flag);
                }
                if (flag == true) {
                    for (int i = 0; i < list.size(); i++) {
                        if (i == 0) {
                            sum = 0.00;
                        }
                        sum = sum + (list.get(i).getNowPrice()) * 1;
                        BigDecimal b1 = new BigDecimal(Double.toString(sum));
                        BigDecimal b2 = new BigDecimal(Double.toString(1));
                        f1 = b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    }
                    allprice.setText("总计:￥" + f1);
                } else if (flag == false) {
                    sum = 0.00;
                    allprice.setText("总计:￥" + sum);
                }
                // shop_lv_view.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                break;
            case R.id.shop_editbutton:
                shop_editbutton_text = shop_editbutton.getText().toString();
                shop_bayall_text = shop_bayall.getText().toString();
                if (shop_editbutton_text.equals("编辑")) {
                    shop_editbutton.setText("完成");
                    rela_allprice.setVisibility(View.GONE);
                    if (shop_bayall_text.equals("结算")) {
                        shop_bayall.setText("删除");
                    }
                } else if (shop_editbutton_text.equals("完成")) {
                    shop_editbutton.setText("编辑");
                    rela_allprice.setVisibility(View.VISIBLE);
                    if (shop_bayall_text.equals("删除")) {
                        shop_bayall.setText("结算");
                    }
                }
                break;
            case R.id.shop_bayall:
                shop_bayall_text = shop_bayall.getText().toString();
                if (shop_bayall_text.equals("删除")) {
                    /*for (int i = 0; i < list.size(); i++) {
                        sum = sum - (list.get(i).getNowPrice());
                        shop_lv_view.setAdapter(new shop_lv_adapter(ShopCarActivity.this, null));
                        adapter.notifyDataSetChanged();
                    }
                    allprice.setText("总计:￥" + sum);*/
                    Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT).show();
                } else if (shop_bayall_text.equals("结算")) {
                    Toast.makeText(getActivity(), "准备结算", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), PayActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }


    class MyHomeData extends BaseData {


        @Override
        public void setResultData(String data) {
            //先设置数据
            CartFragment.this.data = data;
            //data有可能为空
            //再展示
            Gson gson = new Gson();
            homeRoot = gson.fromJson(data, ShopCarDataBean.class);

            CartFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
        }

        @Override
        protected void setResulttError(ShowingPage.StateType stateLoadError) {
            //失败
            CartFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);

        }
    }

    // 适配器
    class shop_lv_adapter extends BaseAdapter {
        private Context context;
        private List<CarData> list;
        private LinkedList<Boolean> linkedList = new LinkedList<Boolean>();

        public shop_lv_adapter(Context context, List<CarData> list) {
            super();
            this.context = context;
            this.list = list;
            // 初始化
            for (int i = 0; i < list.size(); i++) {
                getSelect().add(false);
            }
        }

        private List<Boolean> getSelect() {
            return linkedList;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder vh;
            if (convertView == null) {
                convertView = convertView.inflate(context, R.layout.shop_lv_item, null);
                vh = new ViewHolder();
                vh.shop_checkbox = (CheckBox) convertView.findViewById(R.id.shop_checkbox);
                vh.shop_goodimg = (ImageView) convertView.findViewById(R.id.shop_goodimg);
                vh.shop_goodname = (TextView) convertView.findViewById(R.id.shop_goodname);
                vh.shop_price = (TextView) convertView.findViewById(R.id.shop_price);
                vh.shop_count = (TextView) convertView.findViewById(R.id.shop_count);
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            vh.shop_goodname.setText(list.get(position).getGoodName() + "");
            vh.shop_count.setText("数量:1");
            vh.shop_price.setText("现价:￥" + list.get(position).getNowPrice() + "元");

            vh.shop_checkbox.setChecked(linkedList.get(position));
            // 不能用onCheckChangedListner()复用的时候
            vh.shop_checkbox.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    linkedList.set(position, !linkedList.get(position));
                    if (linkedList.contains(false)) {
                        checkall.setChecked(false);
                    } else {
                        checkall.setChecked(true);
                    }
                    if (vh.shop_checkbox.isChecked() == true) {
                        sum = sum + (list.get(position).getNowPrice());
                        //goodid_list.add(list.get(position).getGoodid());
                    } else if (vh.shop_checkbox.isChecked() == false) {
                        sum = sum - (list.get(position).getNowPrice());
                        //list.remove(list.get(position));
                    }
                    //计算
                    //BigDecimal b1 = new BigDecimal(Double.toString(sum));
                    //BigDecimal b2 = new BigDecimal(Double.toString(1));
                    //double f1 = b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    allprice.setText("总计:￥" + sum);
                    // double
                    // sum=(list.get(position).getPrice())*(list.get(position).getCount());
                    // Toast.makeText(context, "点击了"+position+"的checkbox",
                    // 0).show();
                    // 刷新
                    notifyDataSetChanged();
                }
            });
            return convertView;
        }

        class ViewHolder {
            CheckBox shop_checkbox;
            ImageView shop_goodimg;
            TextView shop_goodname, shop_price, shop_count;
        }
    }
}