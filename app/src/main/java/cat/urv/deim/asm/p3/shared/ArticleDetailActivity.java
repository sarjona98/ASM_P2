package cat.urv.deim.asm.p3.shared;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

import cat.urv.deim.asm.libraries.commanagerdc.models.Article;
import cat.urv.deim.asm.libraries.commanagerdc.providers.DataProvider;
import cat.urv.deim.asm.p2.common.R;

public class ArticleDetailActivity extends AppCompatActivity {

    static int position_parameter;
    TextView title, description, tags;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_detail_activity);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.title_activity_article_detail);

        position_parameter = Objects.requireNonNull(getIntent().getExtras()).getInt("ARTICLE_POSITION");
        DataProvider dataProviderArticles = DataProvider.getInstance(this);
        List<Article> articlesList = dataProviderArticles.getArticles();

        title = findViewById(R.id.titleArticleDetail);
        description = findViewById(R.id.descriptionArticleDetail);
        tags = findViewById(R.id.tagsArticleDetail);
        image = findViewById(R.id.imageArticleDetail);

        title.setText(articlesList.get(position_parameter).getTitle());
        description.setText(articlesList.get(position_parameter).getDescription());
        tags.setText((CharSequence) articlesList.get(position_parameter).getTags());
        Picasso.get().load(articlesList.get(position_parameter).getImageURL()).into(image);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
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
