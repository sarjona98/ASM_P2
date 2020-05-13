package cat.urv.deim.asm.p2.common.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import cat.urv.deim.asm.p2.common.ErrorLoginActivity;
import cat.urv.deim.asm.p2.common.MainActivity;
import cat.urv.deim.asm.p2.common.ProfileActivity;
import cat.urv.deim.asm.p2.common.R;

public class LoginActivity extends AppCompatActivity {

    String parameter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final Button anonymousButton = findViewById(R.id.anonymous);

        try {
            parameter = getIntent().getStringExtra("PARAMETER_BEHAVIOUR");
        } catch (Exception e) {
            parameter = "NOT_SUPPORTED";
        }

        assert parameter != null;
        if (parameter.equals("Login1")) {
            anonymousButton.setText(R.string.action_anonymously);
            Objects.requireNonNull(getSupportActionBar()).hide();
        } else if (parameter.equals("Login2")) {
            anonymousButton.setText(R.string.action_anonymously2);
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usernameEditText.getText().toString().equals("sandra.adams@email.com") && passwordEditText.getText().toString().equals("123456")) {
                    SharedPreferences preferences =
                            getSharedPreferences("PREFERENCES", MODE_PRIVATE);

                    preferences.edit()
                            .putBoolean("isLogged", true).apply();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class); // redirecting to MainActivity.
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(LoginActivity.this, ErrorLoginActivity.class); // redirecting to ErrorLoginActivity.
                    startActivity(intent);
                }
            }
        });

        anonymousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class); // redirecting to MainActivity.
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (parameter.equals("Login2")) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
            MenuItem search = menu.findItem(R.id.action_search);
            search.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            if (parameter.equals("Login1")) {
                finish();
                return true;
            } else if (parameter.equals("Login2")) {
                finish();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class); // redirecting to MainActivity.
                startActivity(intent);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            if (parameter.equals("Login1")) {
                finishAffinity();
                return true;
            } else if (parameter.equals("Login2")) {
                finish();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class); // redirecting to MainActivity.
                startActivity(intent);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
