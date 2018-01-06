package king.com.to_do_list;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    public static int time_splash=2000;         //this is to set the time limit for the splash screen

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable(){

            @Override
            public void run()
            {
                Intent i= new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);

                finish();
            }
        },time_splash);

    }
}
