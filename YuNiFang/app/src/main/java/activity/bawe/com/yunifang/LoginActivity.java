package activity.bawe.com.yunifang;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import activity.bawe.com.yunifang.utils.ImageLoaderUtils;



public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imag_fanhui;
    private String img = "http://image.hmeili.com/yunifang/images/goods/ad1//1503311533748344878180.png";
    private ImageView login_image;
    private TextView tv_login;
    private TextView tv_sjdl;
    private TextView tv_ynfdl;
    private Button button1;
    private LinearLayout buju_shouji;
    private EditText ed_mima;
    private TextView tv_wjmm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //初始化控件
        initView();
        //实现点击监听
        initHttp();


    }

    /**
     * 初始化控件
     */
    private void initView() {
        imag_fanhui = (ImageView) findViewById(R.id.imag_fanhui);
        login_image=(ImageView) findViewById(R.id.login_image);
        tv_login=(TextView) findViewById(R.id.tv_login);
        tv_sjdl=(TextView) findViewById(R.id.tv_sjdl);
        tv_ynfdl=(TextView) findViewById(R.id.tv_ynfdl);
        button1=(Button) findViewById(R.id.button1);
        buju_shouji=(LinearLayout) findViewById(R.id.buju_shouji);
        ed_mima=(EditText) findViewById(R.id.ed_mima);
        tv_wjmm=(TextView) findViewById(R.id.tv_wjmm);


    }
    /**
     * 实现点击监听
     */
    private void initHttp() {
        ImageLoader.getInstance().displayImage(img,login_image, ImageLoaderUtils.initOptions());
        imag_fanhui.setOnClickListener(this);
        tv_sjdl.setOnClickListener(this);
        tv_ynfdl.setOnClickListener(this);
    }

    /**
     * 点击监听
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_ynfdl:
                tv_wjmm.setHint(View.VISIBLE);
                break;
            case R.id.tv_sjdl:
          //   tv_wjmm.setHint(View.INVISIBLE);
                break;
            case R.id.imag_fanhui:
                finish();
                overridePendingTransition(R.anim.leftin, R.anim.leftout);
                break;

        }
    }
}
