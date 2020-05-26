package cat.urv.deim.asm.p2.common;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

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

//import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cat.urv.deim.asm.libraries.commanagerdc.models.Article;
import cat.urv.deim.asm.libraries.commanagerdc.models.CalendarItem;
import cat.urv.deim.asm.libraries.commanagerdc.models.Event;
import cat.urv.deim.asm.libraries.commanagerdc.models.Faq;
import cat.urv.deim.asm.libraries.commanagerdc.models.New;
import cat.urv.deim.asm.libraries.commanagerdc.models.Tag;
import cat.urv.deim.asm.libraries.commanagerdc.providers.DataProvider;
import cat.urv.deim.asm.p2.common.ui.login.LoginActivity;
import cat.urv.deim.asm.p3.shared.FAQsActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private AppBarConfiguration mAppBarConfiguration;
    private boolean isLogged;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataProvider dataProvider;

        //Override wrapper that's allows to load data from any resource in raw directory of the project
        dataProvider = DataProvider.getInstance(this.getApplicationContext(),R.raw.faqs,R.raw.news,R.raw.articles,R.raw.events,R.raw.calendar);

        List<? extends List> dataLists = new LinkedList<>();
        try {
            Object dataArray[]= {
                    dataProvider.getFaqs(),
                    dataProvider.getNews(),
                    dataProvider.getArticles(),
                    dataProvider.getEvents(),
                    dataProvider.getCalendar()
            };

            for (Object obj:dataArray){
                ArrayList<Object> list = (ArrayList<Object>) obj;

                Log.d(TAG,""+list.get(0).getClass().getSimpleName());

                if (list.get(0).getClass() == Faq.class){
                    showFaq((Faq) list.get(0));
                }else if(list.get(0).getClass() == New.class){
                    showNew((New) list.get(0));
                }else if(list.get(0).getClass() == Article.class){
                    showArticle((Article)list.get(0));
                }else if(list.get(0).getClass() == Event.class){
                    showEvent((Event) list.get(0));
                }else if(list.get(0).getClass() == CalendarItem.class){
                    showCalendar((CalendarItem) list.get(0));
                }else{
                    Log.e(TAG,"Type not supported");
                }
            }
        }
        catch (NullPointerException exception){
            Log.e(TAG,"Error accessing data");
        }

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
                switch (id) {
                    case R.id.nav_profile:
                        if (isLogged) {
                            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            intent.putExtra("PARAMETER_BEHAVIOUR", "Login2");
                            startActivity(intent);
                        }
                        break;
                    case R.id.nav_faq:
                        Intent intent = new Intent (MainActivity.this, FAQsActivity.class);
                        startActivity(intent);
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
            finishAffinity();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //showImage((ImageView)findViewById(R.id.detail_image_view));
    }

    protected void showImage(ImageView view){

        DataProvider dataProvider = DataProvider.getInstance(this);
        int articleIndex = 0;
        String imageURL = dataProvider.getArticles().get(articleIndex).getImageURL();
        //Picasso.get().load(imageURL).placeholder(R.drawable.ic_launcher_background).into(view);


    }

    protected void showFaq(Faq faq){
        Log.d(TAG,"Field Body:"+faq.getBody());
        Log.d(TAG,"Field Title:"+faq.getTitle());
    }

    protected void showArticle(Article article){
        Log.d(TAG,"Field Author:"+article.getAuthor());
        Log.d(TAG,"Field Date:"+article.getDate());
        Log.d(TAG,"Field DateUpdate:"+article.getDateUpdate());
        Log.d(TAG,"Field Title:"+article.getTitle());
        Log.d(TAG,"Field AbstractText:"+article.getAbstractText());
        Log.d(TAG,"Field Text:"+article.getText());
        Log.d(TAG,"Field Description:"+article.getDescription());
        Log.d(TAG,"Field ImageURL:"+article.getImageURL());
        Log.d(TAG,"Field Tags:");
        for (Tag tag:article.getTags()){
            Log.d(TAG,"Field Tags:"+tag.getName());
        }
    }

    protected void showNew(New newItem){
        Log.d(TAG,"Field Title:"+newItem.getTitle());
        Log.d(TAG,"Field Subtitle:"+newItem.getSubtitle());
        Log.d(TAG,"Field Text:"+newItem.getText());
        Log.d(TAG,"Field Date:"+newItem.getDate());
        Log.d(TAG,"Field DateUpdate:"+newItem.getDateUpdate());
        Log.d(TAG,"Field ImageURL:"+newItem.getImageURL());
        Log.d(TAG,"Field Tags:");
        for (Tag tag:newItem.getTags()){
            Log.d(TAG,"Field Tags:"+tag.getName());
        }

    }

    protected void showEvent(Event event){
        Log.d(TAG,"Field Name:"+event.getName());
        Log.d(TAG,"Field Description:"+event.getDescription());
        Log.d(TAG,"Field Type:"+event.getType());
        Log.d(TAG,"Field Tags:"+event.getTags());
        Log.d(TAG,"Field webURL:"+event.getWebURL());
        Log.d(TAG,"Field ImageURL:"+event.getImageURL());
        Log.d(TAG,"Field Tags:");
        for (Tag tag:event.getTags()){
            Log.d(TAG,"Field Tags:"+tag.getName());
        }
    }

    protected void showCalendar(CalendarItem calendar){
        Log.d(TAG,"Field Name:"+calendar.getName());
        Log.d(TAG,"Field Descripció:"+calendar.getDescription());
        Log.d(TAG,"Field Venue:"+calendar.getVenue());
        Log.d(TAG,"Field Date:"+calendar.getDate());
        Log.d(TAG,"Field Hour:"+calendar.getHour());
        Log.d(TAG,"Field ImageURL:"+calendar.getImageURL());
        Log.d(TAG,"Field Tags:");
        for (Tag tag:calendar.getTags()){
            Log.d(TAG,"Field Tags:"+tag.getName());
        }
    }
}
