package cat.urv.deim.asm.p3.shared;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cat.urv.deim.asm.libraries.commanagerdc.models.Article;
import cat.urv.deim.asm.p2.common.R;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {
    private List<Article> list;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ArticleViewHolder extends RecyclerView.ViewHolder {
        // each data item has a title, a description, a author (all three TextView's) and a image (ImageView) in this case
        TextView title, description, author;
        ImageView image, favArticle, bookmarkArticle;
        boolean fav = false;
        boolean bookmark = false;
        ArticleViewHolder(CardView articleCard) {
            super(articleCard);
            title = articleCard.findViewById(R.id.titleArticle);
            description = articleCard.findViewById(R.id.descriptionArticle);
            author = articleCard.findViewById(R.id.authorArticle);
            image = articleCard.findViewById(R.id.imageArticle);

            favArticle = articleCard.findViewById(R.id.fav_toggleButton);
            bookmarkArticle = articleCard.findViewById(R.id.bookmark_toggleButton);
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
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    ArticleAdapter(List<Article> listArticle) {
        list = listArticle;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ArticleAdapter.ArticleViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        CardView c = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_card, parent, false);
        return new ArticleViewHolder(c);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.title.setText(list.get(position).getTitle());
        holder.description.setText(list.get(position).getAbstractText());
        holder.author.setText(list.get(position).getAuthor());
        Picasso.get().load(list.get(position).getImageURL()).into(holder.image);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return list.size();
    }
}