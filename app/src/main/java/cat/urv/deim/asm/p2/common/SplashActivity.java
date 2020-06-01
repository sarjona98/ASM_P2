package cat.urv.deim.asm.p2.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import cat.urv.deim.asm.Constant;
import cat.urv.deim.asm.p2.common.ui.login.LoginActivity;

public class SplashActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        final boolean isFirstRun = getSharedPreferences(Constant.PREFERENCE_NAME, MODE_PRIVATE)
                .getBoolean(Constant.IS_FIRST_RUN, true);

        final boolean isLogged = getSharedPreferences(Constant.PREFERENCE_NAME, MODE_PRIVATE)
                .getBoolean(Constant.IS_LOGGED, false);

        final boolean isAnonymous = getSharedPreferences(Constant.PREFERENCE_NAME, MODE_PRIVATE)
                .getBoolean(Constant.IS_ANONYMOUS, false);

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
                        intent.putExtra(Constant.EXTRA_NAME, Constant.LOGIN_INITIAL);
                    }
                }
                startActivity(intent);
                finish();
            }
        }, Constant.DELAY_MS);
    }
}