package cat.urv.deim.asm.p3.shared;

import androidx.lifecycle.ViewModelProviders;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import cat.urv.deim.asm.p2.common.R;

public class ArticlesFragment extends Fragment {

    private ArticlesViewModel mViewModel;

    public static ArticlesFragment newInstance() {
        return new ArticlesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View articlesView = inflater.inflate(R.layout.articles_fragment, container, false);
        final ToggleButton favToggle = articlesView.findViewById(R.id.fav_toggleButton);
        final ToggleButton bookmarkToggle = articlesView.findViewById(R.id.bookmark_toggleButton);
        favToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    favToggle.setBackgroundResource(R.drawable.outline_favorite_border_24);
                } else {
                    favToggle.setBackgroundResource(R.drawable.baseline_favorite_24);
                }
            }
        });
        bookmarkToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    bookmarkToggle.setBackgroundResource(R.drawable.outline_bookmark_border_24);
                } else {
                     bookmarkToggle.setBackgroundResource(R.drawable.outline_bookmark_24);
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
