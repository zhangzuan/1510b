package activity.hhzmy.com.hhzmy.farment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
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

import activity.hhzmy.com.hhzmy.R;
import activity.hhzmy.com.hhzmy.bean.CarData;
import activity.hhzmy.com.hhzmy.bean.ShopCarDataBean;
import activity.hhzmy.com.hhzmy.http.OkHttp;
import activity.hhzmy.com.hhzmy.pay.PayDemoActivity;
import okhttp3.Request;


/**
 * Created by Administrator on 2016/11/8.
 */

public class GouWuFarment extends Fragment implements View.OnClickListener{
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
    public GouWuFarment(){}
    public static GouWuFarment getGouWuFarment(){
        GouWuFarment fragment=new GouWuFarment();

        return fragment;

    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.tab_three, container, false);
        infoview();
        //得到购物车数据
        getCarData();
        checkall.setOnClickListener(this);
        shop_editbutton.setOnClickListener(this);
        shop_bayall.setOnClickListener(this);

        return view;
    }

    private void infoview() {
        shop_lv_view = (ListView) view.findViewById(R.id.shop_lv_view);
        checkall = (CheckBox) view.findViewById(R.id.checkall);
        shop_editbutton = (TextView) view.findViewById(R.id.shop_editbutton);
        rela_allprice = (RelativeLayout) view.findViewById(R.id.rela_allprice);
        shop_bayall = (Button) view.findViewById(R.id.shop_bayall);
        allprice = (TextView) view.findViewById(R.id.allprice);
    }

    //请求数据
    public void getCarData() {
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

    //展示数据
    public void showData(String json) {
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

    @Override
    public void onClick(View view) {
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
                    Intent intent = new Intent(getActivity(), PayDemoActivity.class);
                    startActivity(intent);
                }
                break;
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


    //  wv = (WebView) view.findViewById(R.id.gu_webview);
//        wv.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        String htmlData = "\"<div moduleId='R9000413_2' moduleName='关联推荐'><p><img src2=\"http://image3.suning.cn//uimg/cms/img/147826176955030691.png\" alt=\"\" usemap=\"#Map\" border=\"0\" /> <map name=\"Map\"> <area coords=\"7,9,258,106\" shape=\"rect\" href=\"http://quan.suning.com/lqzx_recommend.do?activityId=201611040000995005&activitySecretKey=MEbbXk0c8XqddzTYKQbAJy3e\" target=\"_blank\" /> <area coords=\"274,6,523,104\" shape=\"rect\" href=\"http://quan.suning.com/lqzx_recommend.do?activityId=201611040000995031&activitySecretKey=P8ktM6fkGs8u31ZrHx9MXwVN\" target=\"_blank\" /> <area coords=\"534,6,770,100\" shape=\"rect\" href=\"http://quan.suning.com/lqzx_recommend.do?activityId=201611040000995044&activitySecretKey=ucGPhCAOtCTKTbHJSVQ7iYIE\" target=\"_blank\" /> </map></p> <!-- 母婴用品互联3.10 --> <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#fff799\"> <tbody> <tr bgcolor=\"#fff799\"> <td align=\"center\"><a href=\"http://cuxiao.suning.com/myzcqjh1101.html\"><img src2=\"http://image3.suning.cn//uimg/cms/img/147841973948824711.jpg\" alt=\"\" /></a></td> </tr> <tr bgcolor=\"#fff799\"> <td align=\"center\"> </td> </tr> <tr> <td bgcolor=\"#fff799\"> <table border=\"0\" align=\"center\" bgcolor=\"#fffcdc\"> <tbody> <tr> <td > <table border=\"0\" > <tbody> <tr> <td><a href=\"http://product.suning.com/0000000000/945004789.html\" target=\"_blank\"><img src2=\"http://image2.suning.cn/uimg/b2c/newcatentries/0000000000-000000000945004789_1_800x800.jpg\" alt=\"\" border=\"0\" /></a></td> </tr> <tr> <td> <table border=\"0\" > <tbody> <tr> <td ><span style=\"font-size: 12px; line-height: 20px;\">雀巢 超级能恩3段800g*2桶 </span></td> <td ><a href=\"http://product.suning.com/0000000000/945004789.html\" target=\"_blank\"><img src2=\"http://image.suning.cn/uimg/BTC/PDI/132578754_20151110164130.jpg\" alt=\"\" border=\"0\" /></a></td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> <td > <table border=\"0\" > <tbody> <tr> <td><a href=\"http://product.suning.com/0000000000/102410015.html\" target=\"_blank\"><img src2=\"http://image5.suning.cn/uimg/b2c/newcatentries/0000000000-000000000102410015_1_800x800.jpg\" alt=\"\" border=\"0\" /></a></td> </tr> <tr> <td> <table border=\"0\" > <tbody> <tr> <td ><span style=\"font-size: 12px; line-height: 20px;\">花王 妙而舒特大号XL38片 学步裤 </span></td> <td ><a href=\"http://product.suning.com/0000000000/102410015.html\" target=\"_blank\"><img src2=\"http://image.suning.cn/uimg/BTC/PDI/132578754_20151110164138.jpg\" alt=\"\" border=\"0\" /></a></td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> <td > <table border=\"0\" > <tbody> <tr> <td><a href=\"http://product.suning.com/0000000000/122736070.html\" target=\"_blank\"><img src2=\"http://image1.suning.cn/uimg/b2c/newcatentries/0000000000-000000000122736070_1_800x800.jpg\" alt=\"\" border=\"0\" /></a></td> </tr> <tr> <td> <table border=\"0\" > <tbody> <tr> <td ><span style=\"font-size: 12px; line-height: 20px;\">贝贝沃尔 芝士鳕鱼肠84g</span></td> <td ><a href=\"http://product.suning.com/0000000000/122736070.html\" target=\"_blank\"><img src2=\"http://image.suning.cn/uimg/BTC/PDI/132578754_20151110164144.jpg\" alt=\"\" border=\"0\" /></a></td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> <td > <table border=\"0\" > <tbody> <tr> <td><a href=\"http://product.suning.com/0000000000/102586393.html\" target=\"_blank\"><img src2=\"http://image4.suning.cn/uimg/b2c/newcatentries/0000000000-000000000102586393_1_800x800.jpg\" alt=\"\" border=\"0\" /></a></td> </tr> <tr> <td> <table border=\"0\" > <tbody> <tr> <td ><span style=\"font-size: 12px; line-height: 20px;\">帮宝适 超薄干爽婴儿纸尿裤加大号XL128片</span></td> <td ><a href=\"http://product.suning.com/0000000000/102586393.html\" target=\"_blank\"><img src2=\"http://image.suning.cn/uimg/BTC/PDI/132578754_20151110164150.jpg\" alt=\"\" border=\"0\" /></a></td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> </tr> <tr> <td bgcolor=\"#fff799\"> <table border=\"0\" align=\"center\" bgcolor=\"#fffcdc\"> <tbody> <tr> <td > <table border=\"0\" > <tbody> <tr> <td><a href=\"http://product.suning.com/0000000000/104392679.html\" target=\"_blank\"><img src2=\"http://image2.suning.cn/uimg/b2c/newcatentries/0000000000-000000000104392679_1_800x800.jpg\" alt=\"\" border=\"0\" /></a></td> </tr> <tr> <td> <table border=\"0\" > <tbody> <tr> <td ><span style=\"font-size: 12px; line-height: 20px;\">宝得适 儿童安全座椅超级百变王 白金版</span></td> <td ><a href=\"http://product.suning.com/0000000000/104392679.html\" target=\"_blank\"><img src2=\"http://image.suning.cn/uimg/BTC/PDI/132578754_20151110164150.jpg\" alt=\"\" border=\"0\" /></a></td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> <td > <table border=\"0\" > <tbody> <tr> <td><a href=\"http://product.suning.com/0000000000/107211174.html\" target=\"_blank\"><img src2=\"http://image3.suning.cn/uimg/b2c/newcatentries/0000000000-000000000107211174_1_800x800.jpg\" alt=\"\" border=\"0\" /></a></td> </tr> <tr> <td> <table border=\"0\" > <tbody> <tr> <td ><span style=\"font-size: 12px; line-height: 20px;\">AUBY 澳贝 运动系列 乖乖小鸭 益智玩具</span></td> <td ><a href=\"http://product.suning.com/0000000000/107211174.html\" target=\"_blank\"><img src2=\"http://image.suning.cn/uimg/BTC/PDI/132578754_20151110164206.jpg\" alt=\"\" border=\"0\" /></a></td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> <td > <table border=\"0\" > <tbody> <tr> <td><a href=\"http://product.suning.com/0000000000/127713074.html\" target=\"_blank\"><img src2=\"http://image1.suning.cn/uimg/b2c/newcatentries/0000000000-000000000127713074_1_800x800.jpg\" alt=\"\" border=\"0\" /></a></td> </tr> <tr> <td> <table border=\"0\" > <tbody> <tr> <td ><span style=\"font-size: 12px; line-height: 20px;\">良良 麻棉祛味隔尿垫（超大号）绿色</span></td> <td ><a href=\"http://product.suning.com/0000000000/127713074.html\" target=\"_blank\"><img src2=\"http://image.suning.cn/uimg/BTC/PDI/132578754_20151110164212.jpg\" alt=\"\" border=\"0\" /></a></td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> <td > <table border=\"0\" > <tbody> <tr> <td><a href=\"http://product.suning.com/0000000000/125942525.html\" target=\"_blank\"><img src2=\"http://image3.suning.cn/uimg/b2c/newcatentries/0000000000-000000000125942526_1_800x800.jpg\" alt=\"\" border=\"0\" /></a></td> </tr> <tr> <td> <table border=\"0\" > <tbody> <tr> <td ><span style=\"font-size: 12px; line-height: 20px;\">好孩子Goodbaby进口床实木无漆多功能婴儿床 </span></td> <td ><a href=\"http://product.suning.com/0000000000/105546656.html\" target=\"_blank\"><img src2=\"http://image.suning.cn/uimg/BTC/PDI/132578754_20151110164150.jpg\" alt=\"\" border=\"0\" /></a></td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> </tr> <tr> <td bgcolor=\"#fff799\"> </td> </tr> </tbody> </table></div><div moduleId='R9000413_3' moduleName='商品详情'><p><span style=\"font-size: 14px;\"><strong> 产品特点:</strong></span></p> <p><span style=\"color: #000000; font-size: 14px;\">1.全面棉柔外层,全新加倍柔软,更透气,就连腰部胶带粘贴区也采用透气绵软材质,宝宝每寸肌肤都能开心呼吸,屁屁不生闷气,整<br />体全彩图案设计,就像宝宝可爱的小裤裤,丰富的图案为宝宝提供更具启发性的视觉刺激.<br />2.全方位吸水体,前后穿都可以,双层超吸收,尿量更多也不怕,瞬间吸水超薄层,吸收更迅速,表层干爽不回渗.<br />3.跨下立体环绕剪裁,双向穿都轻松,怎么穿都服帖.<br />4.独特的侧边伸缩腰围,不论前穿后穿,任何姿势都服帖合身,不松脱,不下滑.<br />5.巧撕圆弧魔术贴,魔术贴变聪明了,好撕好粘更顺手,圆弧造型,不会刮伤小宝宝细微肌肤.<br />6.天然呵护层,表层的每根纤维都添加天然植物精华,经临床实验证实对预防及抑制尿布症的发生具有成效.</span></p> <p><span style=\"font-size: 14px;\"><span style=\"font-family: arial, helvetica, sans-serif;\"><span style=\"color: #000;\"><img src2=\"http://image.suning.cn/uimg/sop/commodity/101709020266969895015290_x.jpg\" alt=\"\" /><img src2=\"http://image.suning.cn/uimg/sop/commodity/100070592712410814056500_x.jpg\" alt=\"\" /><img src2=\"http://image.suning.cn/uimg/sop/commodity/212614477377587906459020_x.jpg\" alt=\"\" /><img src2=\"http://image.suning.cn/uimg/sop/commodity/134331860838867935418580_x.jpg\" alt=\"\" /></span></span></span><strong><span style=\"font-size: 26px;\"><strong><strong><span style=\"font-size: 16px;\"><span style=\"font-family: arial, helvetica, sans-serif;\"><strong><span style=\"font-size: 14px;\"><strong><img src2=\"http://image.suning.cn/uimg/BTC/PDI/102295665_20140928164732.jpg\" alt=\"\" /></strong></span></strong></span></span></strong> </strong></span></strong></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; text-indent: 0cm; mso-layout-grid-align: none; mso-char-indent-count: 0;\"> <span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><strong>花王妙而舒常见问题：</strong></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><strong>1、若不小心将妙而舒纸尿裤和衣物一起洗涤了，如何去除衣物和洗衣机里的附着物？<span lang=\"EN-US\" style=\"display: none; mso-hide: all;\">    </span></strong></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">万一不小心将纸尿裤放入洗衣机里洗，会有胶冻状的物质以及纸屑附着在衣服及洗衣机内。胶冻状物是吸收<span lang=\"EN-US\"><br /> </span>体使用的高分子吸收物，在吸收了尿液或洗衣机里的水之后膨胀起来的物质。纸状物则是纸尿裤的材料，也<span lang=\"EN-US\"><br /> </span>就是纸浆及无纺布。<span lang=\"EN-US\">  </span></span></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><span lang=\"EN-US\" style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">[</span><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">附着在衣服上的有效清除方法<span lang=\"EN-US\">]</span></span></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">在脱水后，干燥前用刷子等刷掉。也可以使用黏着胶带等去除。</span></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">干燥后，还有残渣粘在衣服上的话，请再次用刷子刷。</span></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">然后，若有时间，可以在脱水后或干燥之后，再次水洗。</span></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><span lang=\"EN-US\" style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">[</span><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">附着在洗衣机内时的清除方法<span lang=\"EN-US\">]</span></span></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">先把脏物网中的脏物清除，使用纸巾把洗衣机内部擦拭干净。然后，再次放水冲洗干净。<span lang=\"EN-US\"> </span></span></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><span lang=\"EN-US\" style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">[</span><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">对身体的影响<span lang=\"EN-US\">]</span></span></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">残留在衣服上的高分子吸收体、纸浆和无纺布，直接接触肌肤也不会有任何伤害。</span></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"b??','sans-serif'; color: red;\"><span lang=\"EN-US\" style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">[</span><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">附着在洗衣机内时的清除方法<span lang=\"EN-US\">]</span></span></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">先把脏物网中的脏物清除，使用纸巾把洗衣机内部擦拭干净。然后，再次放水冲洗干净。<span lang=\"EN-US\"> </span></span></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><span lang=\"EN-US\" style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">[</span><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">对身体的影响<span lang=\"EN-US\">]</span></span></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">残留在衣服上的高分子吸收体、纸浆和无纺布，直接接触肌肤也不会有任何伤害。</span></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"be软雅黑','sans-serif'; color: red;\"><strong>2、什么时候该换大一号的妙而舒纸尿裤？</strong></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><span lang=\"EN-US\" style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">1)</span><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">、大腿及腰部觉得太紧、有勒痕时</span></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><span lang=\"EN-US\" style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">2)</span><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">、腰贴位置已经贴在最外侧时</span></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><span lang=\"EN-US\" style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">3)</span><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">、纸尿裤裤裆变短或腰围在肚脐以下时</span></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><span lang=\"EN-US\" style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">4)</span><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">、尿尿便便的量增多，容易外漏时</span></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><span lang=\"EN-US\" style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">5)</span><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">、宝宝体重超过尺寸表中的体重标示时</span></span></span></span></span></span></p> <p class=\"MsoListParagraph\" style=\"background: white; layout-grid-mode: char; margin: 0cm 0cm 0pt; mso-layout-grid-align: none;\"><span style=\"color: #f00;\"><span style=\"font-size: 16px;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif';\"><span style=\"color: #000000;\"><span style=\"font-size: 12pt; font-family: '微软雅黑','sans-serif'; color: red;\"><span lang=\"EN-US\" style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">    </span><span style=\"font-family: '微软雅黑','sans-serif'; color: black; mso-bidi-font-family: arial;\">出现上述情况时，请选用大一号的纸尿裤。</span></span></span></span></span></span></p></div><div moduleId='R9000413_4' moduleName='商品参数'><p>?</p></div><div moduleId='R9000413_5' moduleName='商品展示'><p>?</p></div>\"";
//        htmlData = htmlData.replaceAll("&", "");
//        htmlData = htmlData.replaceAll("quot;", "\"");
//        htmlData = htmlData.replaceAll("lt;", "<");
//        htmlData = htmlData.replaceAll("gt;", ">");
//
//        wv.loadDataWithBaseURL(null, htmlData, "text/html", "utf-8", null);

}
