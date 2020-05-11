package cat.urv.deim.asm.p2.common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cat.urv.deim.asm.p2.common.ui.login.LoginActivity;

public class ErrorLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_login);

        final Button try_againButton = findViewById(R.id.try_again);

        try_againButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ErrorLoginActivity.this, LoginActivity.class); // redirecting to LoginActivity.
                intent.putExtra("PARAMETER_BEHAVIOUR", "Login1");
                startActivity(intent);
                finish();
            }
        });
    }
}
