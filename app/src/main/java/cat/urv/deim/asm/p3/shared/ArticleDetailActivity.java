package cat.urv.deim.asm.p3.shared;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

import cat.urv.deim.asm.libraries.commanagerdc.models.Article;
import cat.urv.deim.asm.libraries.commanagerdc.models.Tag;
import cat.urv.deim.asm.libraries.commanagerdc.providers.DataProvider;
import cat.urv.deim.asm.p2.common.R;

public class ArticleDetailActivity extends AppCompatActivity {

    static int position_parameter;
    StringBuilder tagsText;
    int count_tags;
    TextView title, description, tags;
    ImageView image, favArticle, bookmarkArticle;
    boolean fav=false, bookmark=false;
    public static final String EXTRA_ARTICLE_POSITION = "ARTICLE_POSITION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_detail_activity);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.title_activity_article_detail);

        position_parameter = Objects.requireNonNull(getIntent().getExtras()).getInt(EXTRA_ARTICLE_POSITION);
        DataProvider dataProviderArticles = DataProvider.getInstance(this);
        List<Article> articlesList = dataProviderArticles.getArticles();

        title = findViewById(R.id.titleArticleDetail);
        description = findViewById(R.id.descriptionArticleDetail);
        tags = findViewById(R.id.tagsArticleDetail);
        image = findViewById(R.id.imageArticleDetail);
        favArticle = findViewById(R.id.fav_toggleButton);
        bookmarkArticle = findViewById(R.id.bookmark_toggleButton);

        title.setText(articlesList.get(position_parameter).getTitle());
        description.setText(articlesList.get(position_parameter).getDescription());
        tagsText = new StringBuilder();
        count_tags = 0;
        for (Tag tag: articlesList.get(position_parameter).getTags()){
            if (count_tags > 0) {
                tagsText.append(" ");
            }
            tagsText.append(tag.getName());
            count_tags++;
        }
        tags.setText(tagsText);
        Picasso.get().load(articlesList.get(position_parameter).getImageURL()).into(image);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_article_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) finish();

        return (super.onOptionsItemSelected(item));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
