package cat.urv.deim.asm.p3.shared;

import android.content.Context;
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
import cat.urv.deim.asm.libraries.commanagerdc.providers.DataProvider;
import cat.urv.deim.asm.p2.common.R;

import static cat.urv.deim.asm.p3.shared.ArticleAdapter.*;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {
    private Context articleContext;
    private List<Article> list;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ArticleViewHolder extends RecyclerView.ViewHolder {
        // each data item has a title, a description, a author (all three TextView's) and a image (ImageView) in this case
        public TextView title, description, author;
        public ImageView image;
        //public CardView articleCard;
        public ArticleViewHolder(CardView articleCard) {
            super(articleCard);
            title = articleCard.findViewById(R.id.titleArticle);
            description = articleCard.findViewById(R.id.descriptionArticle);
            author = articleCard.findViewById(R.id.authorArticle);
            image = articleCard.findViewById(R.id.imageArticle);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ArticleAdapter(List<Article> listArticle) {
        list = listArticle;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ArticleAdapter.ArticleViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        CardView c = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_card, parent, false);
        ArticleViewHolder vh = new ArticleViewHolder(c);
        return vh;
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
