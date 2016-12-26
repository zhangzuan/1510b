package activity.bawe.com.erweimasaomiao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
       imy();
    }

    private void imy() {
        Thread1 thread1 = new Thread1();
        Thread1 thread2 = new Thread1();
        Thread1 thread3 = new Thread1();
        Thread1 thread4 = new Thread1();

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}

