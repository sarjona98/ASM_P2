package cat.urv.deim.asm.p2.common.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import cat.urv.deim.asm.p2.common.ErrorLoginActivity;
import cat.urv.deim.asm.p2.common.MainActivity;
import cat.urv.deim.asm.p2.common.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final Button anonymousButton = findViewById(R.id.anonymous);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usernameEditText.getText().toString().equals("sandra.adams@email.com") && passwordEditText.getText().toString().equals("123456")) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class); // redirecting to MainActivity.
                    startActivity(intent);
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
}
