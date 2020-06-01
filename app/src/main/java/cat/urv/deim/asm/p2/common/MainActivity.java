package cat.urv.deim.asm.p2.common;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

import cat.urv.deim.asm.Constant;
import cat.urv.deim.asm.p2.common.ui.home.HomeFragment;
import cat.urv.deim.asm.p2.common.ui.login.LoginActivity;
import cat.urv.deim.asm.p3.shared.ArticlesFragment;
import cat.urv.deim.asm.p3.shared.FAQsActivity;

public class MainActivity extends AppCompatActivity {

    private static androidx.fragment.app.Fragment actualFrag;

    private AppBarConfiguration mAppBarConfiguration;
    private boolean isLogged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isLogged = getSharedPreferences(Constant.PREFERENCE_NAME, MODE_PRIVATE)
                .getBoolean(Constant.IS_LOGGED, false);
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
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home, R.id.nav_news, R.id.nav_articles, R.id.nav_profile)
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
                FragmentManager manager;
                FragmentTransaction transaction;
                switch (id) {
                    case R.id.nav_profile:
                        if (isLogged) {
                            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            intent.putExtra(Constant.EXTRA_NAME, Constant.LOGIN_INSIDER);
                            startActivity(intent);
                        }
                        break;
                    case R.id.nav_faq:
                        Intent intent = new Intent (MainActivity.this, FAQsActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_articles:
                        actualFrag = new ArticlesFragment();
                        manager = getSupportFragmentManager();
                        transaction = manager.beginTransaction();
                        transaction.replace(R.id.nav_host_fragment, actualFrag);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        break;
                    default:
                        // In other case go to default fragment
                        actualFrag = new HomeFragment();
                        manager = getSupportFragmentManager();
                        transaction = manager.beginTransaction();
                        transaction.replace(R.id.nav_host_fragment, actualFrag);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        break;
                }
                Objects.requireNonNull(getSupportActionBar()).setTitle(menuItem.getTitle());
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
            finishAffinity();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
