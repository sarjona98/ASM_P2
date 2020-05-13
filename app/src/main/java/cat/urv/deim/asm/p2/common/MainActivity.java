package cat.urv.deim.asm.p2.common;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import cat.urv.deim.asm.p2.common.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private boolean isLogged;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isLogged = getSharedPreferences("PREFERENCES", MODE_PRIVATE)
                .getBoolean("isLogged", false);
        final NavigationView navigationView;
        if (isLogged) {
            setContentView(R.layout.activity_main);
            navigationView = findViewById(R.id.nav_view);
        } else {
            setContentView(R.layout.activity_main_anonymous);
            navigationView = findViewById(R.id.nav_view_anonymous);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_news, R.id.nav_profile)
                .setDrawerLayout(drawer)
                .build();
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setCheckedItem(R.id.nav_news);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id=menuItem.getItemId();
                if (id==R.id.nav_profile) {
                    //navigationView.setCheckedItem(R.id.nav_news);
                    if (isLogged) {
                        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        intent.putExtra("PARAMETER_BEHAVIOUR", "Login2");
                        startActivity(intent);
                    }
                }

                //This is for maintaining the behavior of the Navigation view
                NavigationUI.onNavDestinationSelected(menuItem, navController);
                //This is for closing the drawer after acting on it
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            if (isLogged) {
                finishAffinity();
                return true;
            } else {
                finish();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class); // redirecting to LoginActivity.
                intent.putExtra("PARAMETER_BEHAVIOUR", "Login1");
                startActivity(intent);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
