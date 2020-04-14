package cat.urv.deim.asm.p2.common.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

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
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                    if (usernameEditText.getText().toString().equals("sandra.adams@email.com") && passwordEditText.getText().toString().equals("123456")) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class); // redirecting to MainActivity.
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(LoginActivity.this, ErrorLoginActivity.class); // redirecting to ErrorLoginActivity.
                        startActivity(intent);
                    }
            }
        });
    }
}
