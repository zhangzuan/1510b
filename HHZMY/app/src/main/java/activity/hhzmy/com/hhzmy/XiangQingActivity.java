package activity.hhzmy.com.hhzmy;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import activity.hhzmy.com.hhzmy.farment.ShangpingFarment;
import activity.hhzmy.com.hhzmy.farment.details_det_frag;
import activity.hhzmy.com.hhzmy.pay.AuthResult;
import activity.hhzmy.com.hhzmy.pay.PayResult;
import activity.hhzmy.com.hhzmy.util.OrderInfoUtil2_0;

public class XiangQingActivity extends AppCompatActivity implements View.OnClickListener {
    private List<TextView> tv_list;
    private TextView tv_1, tv_2, tv_3,jia,ditu,didian;
    private Intent mIntent;
    private int requestCode;
    private ViewPager details_vp_pager;
    /** 支付宝支付业务：入参app_id */
    public static final String APPID = "2088901305856832";

    /** 支付宝账户登录授权业务：入参pid值 */
    public static final String PID = "8@qdbaiu.com";
    /** 支付宝账户登录授权业务：入参target_id值 */
    public static final String TARGET_ID = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCd6rV3vOE578e6VlGEakZpPdsX2QmGdIfi/yHe cg1CIEWzX9wn2LNFGtu1EzYQyKACG/RKeog0pUJEVGfBG30zFdNY2YocYJNdPtADqhJbS0GJm7f8 1vRiLKtOwKjdiz9oMEwxhc/5fysfMbercidRmlCDPU9BNL1UPb9bAx25JwIDAQAB";

    /** 商户私钥，pkcs8格式 */
    public static final String RSA_PRIVATE = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAM/KCxg/OIj6er2GEig0DOkHqBSzEPHGigMbTXP1k2nrxEHeE59xOOuyovQH/A1LgbuVKyOac3uAN4GXIBEoozRVzDhu5dobeNm48BPcpYSAfvN3K/5GLacvJeENqsiBx8KufM/9inlHaDRQV7WhX1Oe2ckat1EkdHwxxQgc36NhAgMBAAECgYEAwn3sWpq6cUR65LD8h9MIjopTImTlpFjgz72bhsHDZK6A+eJDXcddrwh7DI34t/0IBqu+QEoOc/f0fIEXS9hMwTvFY59XG7M8M6SdeaAbemrGmZ1IdD6YDmpbQFHn2ishaYF0YDZIkBS3WLDFrtk/efaarBCpGAVXeEiVQE4LewECQQD5W1rpkq+dHDRzzdtdi1bJ479wun5CfmVDVb2CJH7Iz0t8zyp/iEVV2QELnxZMphmoSzKaLXutTTj2OImpzCtRAkEA1VMxG6nQq9NkU51H1+I3mlUXRZ0XeFA1GFJ7xWpNRAVhEWlDz2zy9v/gX+RFpNC3f5uznycas70Xp78ew43TEQJAZFFqi9mlqTF1sLk67bFnIyXrGPEOZrXvC13tNfR0xVkQZ4/46wHp0xXQo9pG4GNaoyhNnVV7EkelCPnJ+HPZYQJAQh6T9QgQZoGR8hyovPAf3dUL7oa/VIo/urcuJ8VIB5JHQNdIrk0NjaNHj1E4iNosVgATj3vWWel9IIArb99QkQJAKvfm78lwnImtg5IM604hdn/Wu1XF8tpxsKLWcnfchMr0bM9rCmKmhAY+wdmqSyPZRiNb1QaaaDTqJxLy6AnQ+Q==\n";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(XiangQingActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(XiangQingActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(XiangQingActivity.this,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(XiangQingActivity.this,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang_qing);
         infoview();
        tv_list = new ArrayList<TextView>();
        tv_list.add(tv_1);
        tv_list.add(tv_2);
        tv_list.add(tv_3);
        for (int i = 0; i < tv_list.size(); i++) {
            tv_list.get(i).setOnClickListener(this);
        }
       servpadapter();
     }
//
    private void servpadapter() {
        details_vp_pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                         fragment = new ShangpingFarment();
                        break;
                    case 1:
                        fragment = new details_det_frag();
                        break;
                    case 2:
                      ///  fragment = new details_pl_frag();
                        break;
                    default:
                        break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
        details_vp_pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < tv_list.size(); i++) {
                    if (i == position) {
                        tv_list.get(i).setTextColor(Color.RED);
                    } else {
                        tv_list.get(i).setTextColor(Color.BLACK);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void infoview() {
        tv_1 = (TextView) findViewById(R.id.details_tv1);
        tv_2 = (TextView) findViewById(R.id.details_tv2);
        tv_3 = (TextView) findViewById(R.id.details_tv3);
        details_vp_pager = (ViewPager) findViewById(R.id.details_vp_pager);
       jia=(TextView) findViewById(R.id.immediately_buy);
        ditu=(TextView) findViewById(R.id.fasong_tv);
        didian=(TextView) findViewById(R.id.fasong_tv1);
        ditu.setOnClickListener(this);
        didian.setText("北京");
        didian.setOnClickListener(this);
        jia.setOnClickListener(this);
     }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        case R.id.details_tv1:
                details_vp_pager.setCurrentItem(0);
                break;
            case R.id.details_tv2:
                details_vp_pager.setCurrentItem(1);
                break;
            case R.id.details_tv3:
                details_vp_pager.setCurrentItem(2);
                break;
            case R.id.immediately_buy:
                if (TextUtils.isEmpty(APPID) || TextUtils.isEmpty(RSA_PRIVATE)) {
                    new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialoginterface, int i) {
                                    //
                                    finish();
                                }
                            }).show();
                    return;
                }

                /**
                 * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
                 * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
                 * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
                 *
                 * orderInfo的获取必须来自服务端；
                 */
                Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID);
                String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
                String sign = OrderInfoUtil2_0.getSign(params, RSA_PRIVATE);
                final String orderInfo = orderParam + "&" + sign;

                Runnable payRunnable = new Runnable() {

                    @Override
                    public void run() {
                        PayTask alipay = new PayTask(XiangQingActivity.this);
                        Map<String, String> result = alipay.payV2(orderInfo, true);
                        Log.i("msp", result.toString());

                        Message msg = new Message();
                        msg.what = SDK_PAY_FLAG;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };

                Thread payThread = new Thread(payRunnable);
                payThread.start();

                break;
            case R.id.fasong_tv:
                mIntent = new Intent();
                       mIntent.setClass(XiangQingActivity.this,
                               BaiDuDiTuActivity.class);
                            requestCode = 0;
                        startActivityForResult(mIntent, requestCode);


                //    startActivity(new Intent(XiangQingActivity.this,BaiDuDiTuActivity.class));

            break;
         }

        }
    @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
           String change01 = data.getStringExtra("change01");
           //String change02 = data.getStringExtra("change02");
           // 根据上面发送过去的请求吗来区别
          switch (requestCode) {
                 case 0:
                       didian.setText(change01);
                           break;
          }
          }


    //  initview();

    }
// }

//    private void initview() {
//        Button but1 = (Button) findViewById(R.id.xq_but1);
//        but1.setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.xq_but1:
//                Fragment f = new GouWuFarment();
//                getSupportFragmentManager().beginTransaction().add(R.id.activity_xiang_qing, f, "my").commit();
//                break;
//        }
//
//
//    }
//}
