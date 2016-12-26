package activity.hhzmy.com.myreuaractivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button list,grid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }



    private void initView() {
        list=(Button)findViewById(R.id.btn_list);
        grid=(Button)findViewById(R.id.btn_grid);

    }

    private void initListener() {
        list.setOnClickListener(this);
        grid.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_list:
                Intent intent=new Intent(MainActivity.this,RecyclerViewActivity.class);
                intent.putExtra("type",1);
                startActivity(intent);
                break;
            case R.id.btn_grid:
                Intent intent2=new Intent(MainActivity.this,RecyclerViewActivity.class);
                intent2.putExtra("type",2);
                startActivity(intent2);
                break;
        }

    }
}
