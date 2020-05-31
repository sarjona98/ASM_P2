package cat.urv.deim.asm.p2.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import cat.urv.deim.asm.p2.common.ui.login.LoginActivity;

public class SplashActivity extends Activity {

    public static final String IS_LOGGED = "isLogged";
    public static final String IS_ANONYMOUS = "isAnonymous";
    public static final String IS_FIRST_RUN = "isFirstRun";
    public static final String LOGIN_INITIAL = "login_initial";
    public static final String EXTRA_NAME = "PARAMETER_BEHAVIOUR";
    public static final String PREFERENCE_NAME = "PREFERENCES";
    public static final int DELAY_MS = 2000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        final boolean isFirstRun = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
                .getBoolean(IS_FIRST_RUN, true);

        final boolean isLogged = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
                .getBoolean(IS_LOGGED, false);

        final boolean isAnonymous = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
                .getBoolean(IS_ANONYMOUS, false);

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
                        intent.putExtra(EXTRA_NAME, LOGIN_INITIAL);
                    }
                }
                startActivity(intent);
                finish();
            }
        }, DELAY_MS);
    }
}