package cat.urv.deim.asm.p3.shared;

import androidx.lifecycle.ViewModelProviders;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import cat.urv.deim.asm.p2.common.R;

public class ArticlesFragment extends Fragment {

    private ArticlesViewModel mViewModel;
    private static boolean fav = false;
    private static boolean bookmark = false;

    static ArticlesFragment newInstance() {
        return new ArticlesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View articlesView = inflater.inflate(R.layout.articles_fragment, container, false);
        final ImageView favArticle = articlesView.findViewById(R.id.fav_toggleButton);
        final ImageView bookmarkArticle = articlesView.findViewById(R.id.bookmark_toggleButton);
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
