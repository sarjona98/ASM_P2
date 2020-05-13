package cat.urv.deim.asm.p2.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import cat.urv.deim.asm.p2.common.ui.login.LoginActivity;

public class SplashActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        final boolean isFirstRun = getSharedPreferences("PREFERENCES", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        final boolean isLogged = getSharedPreferences("PREFERENCES", MODE_PRIVATE)
                .getBoolean("isLogged", false);

        final boolean isAnonymous = getSharedPreferences("PREFERENCES", MODE_PRIVATE)
                .getBoolean("isAnonymous", false);

        new Handler().postDelayed(new Runnable(){
            public void run(){
                Intent intent;
                if (isFirstRun) {
                    intent = new Intent(SplashActivity.this, TutorialActivity.class);
                }
                else {
                    if (isLogged || isAnonymous) {
                        intent = new Intent(SplashActivity.this, MainActivity.class);
                    }
                    else {
                        intent = new Intent(SplashActivity.this, LoginActivity.class);
                        intent.putExtra("PARAMETER_BEHAVIOUR", "Login1");
                    }
                }
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}