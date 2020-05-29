package cat.urv.deim.asm.p3.shared;

import androidx.lifecycle.ViewModelProviders;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import cat.urv.deim.asm.libraries.commanagerdc.models.Article;
import cat.urv.deim.asm.libraries.commanagerdc.models.Articles;
import cat.urv.deim.asm.libraries.commanagerdc.providers.DataProvider;
import cat.urv.deim.asm.p2.common.R;

public class ArticlesFragment extends Fragment {

    private ArticlesViewModel mViewModel;
    private static boolean fav = false;
    private static boolean bookmark = false;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Article> articlesList;

    static ArticlesFragment newInstance() {
        return new ArticlesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View articlesView = inflater.inflate(R.layout.articles_fragment, container, false);
        super.onCreate(savedInstanceState);
        recyclerView = (RecyclerView) articlesView.findViewById(R.id.article_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        DataProvider dataProvider = DataProvider.getInstance(getActivity());
        articlesList = dataProvider.getArticles();
        mAdapter = new ArticleAdapter(articlesList);
        recyclerView.setAdapter(mAdapter);

        final View cardView = inflater.inflate(R.layout.article_card, container, false);
        final ImageView favArticle = cardView.findViewById(R.id.fav_toggleButton);
        final ImageView bookmarkArticle = cardView.findViewById(R.id.bookmark_toggleButton);
        favArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fav) {
                    favArticle.setBackgroundResource(R.drawable.outline_favorite_border_24);
                    fav = false;
                } else {
                    favArticle.setBackgroundResource(R.drawable.baseline_favorite_24);
                    fav = true;
                }
            }
        });
        bookmarkArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bookmark) {
                    bookmarkArticle.setBackgroundResource(R.drawable.outline_bookmark_border_24);
                    bookmark = false;
                } else {
                    bookmarkArticle.setBackgroundResource(R.drawable.outline_bookmark_24);
                    bookmark = true;
                }
            }
        });
        return articlesView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ArticlesViewModel.class);
        // TODO: Use the ViewModel
    }

}
