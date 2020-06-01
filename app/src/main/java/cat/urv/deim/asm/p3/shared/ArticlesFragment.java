package cat.urv.deim.asm.p3.shared;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cat.urv.deim.asm.libraries.commanagerdc.models.Article;
import cat.urv.deim.asm.libraries.commanagerdc.providers.DataProvider;
import cat.urv.deim.asm.p2.common.R;

public class ArticlesFragment extends Fragment implements ArticleAdapter.OnArticleListener {

    private static final String EXTRA_ARTICLE_POSITION = "ARTICLE_POSITION";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View articlesView = inflater.inflate(R.layout.articles_fragment, container, false);
        super.onCreate(savedInstanceState);
        RecyclerView recyclerView = articlesView.findViewById(R.id.article_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        DataProvider dataProvider = DataProvider.getInstance(getActivity());
        List<Article> articlesList = dataProvider.getArticles();
        RecyclerView.Adapter mAdapter = new ArticleAdapter(articlesList, this);
        recyclerView.setAdapter(mAdapter);

        return articlesView;
    }

    @Override
    public void onArticleClick(int position) {
        Intent intent = new Intent(this.getActivity(), ArticleDetailActivity.class);
        intent.putExtra(EXTRA_ARTICLE_POSITION, position);
        startActivity(intent);
    }
}
