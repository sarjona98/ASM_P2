package cat.urv.deim.asm.p3.shared;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import cat.urv.deim.asm.libraries.commanagerdc.models.Faq;
import cat.urv.deim.asm.libraries.commanagerdc.providers.DataProvider;
import cat.urv.deim.asm.p2.common.MainActivity;
import cat.urv.deim.asm.p2.common.R;

public class FAQsActivity extends AppCompatActivity {

    private List<String> listGroup;
    private HashMap<String, List<String>> listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.title_faqs_activity);

        ExpandableListView expandableListView = findViewById(R.id.faqExp);
        showFaq();

        ExpandableListViewAdapter expandableListViewAdapter = new ExpandableListViewAdapter(this, listGroup, listItem);
        expandableListView.setAdapter(expandableListViewAdapter);
    }

    private void showFaq() {
        listGroup = new ArrayList<>();
        listItem = new HashMap<>();

        DataProvider dataProviderFaqs = DataProvider.getInstance(this);
        List<Faq> faqsList = dataProviderFaqs.getFaqs();
        for (int count_faq = 0; count_faq < faqsList.size(); count_faq++) {
            String pregunta = faqsList.get(count_faq).getTitle();
            listGroup.add(pregunta);
            List<String> resp_list = new ArrayList<>();
            String resposta = faqsList.get(count_faq).getBody();
            resp_list.add(resposta);
            listItem.put(listGroup.get(count_faq), resp_list);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem settings = menu.findItem(R.id.action_settings);
        settings.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            Intent intent = new Intent(FAQsActivity.this, MainActivity.class); // redirecting to MainActivity.
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            finish();
            Intent intent = new Intent(FAQsActivity.this, MainActivity.class); // redirecting to MainActivity.
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
