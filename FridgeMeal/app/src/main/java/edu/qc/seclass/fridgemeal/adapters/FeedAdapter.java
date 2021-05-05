package edu.qc.seclass.fridgemeal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

import edu.qc.seclass.fridgemeal.models.Favorites;
import edu.qc.seclass.fridgemeal.models.Feed;
import edu.qc.seclass.fridgemeal.R;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    private Context context;
    private List<Feed> feeds;
    private TextView recipeCreator;
    private TextView recipeDescription;
    private TextView recipeName;
    private ImageView recipeImage;
    //private User user;


    public FeedAdapter(Context context, List<Feed> feeds){
        this.context = context;
        this.feeds = feeds;
      //  this.user = user;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_feed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Feed feed = feeds.get(position);
        holder.bind(feed);
    }

    @Override
    public int getItemCount() {
        //if(feeds == null) return 0;
        return feeds.size();
    }

    public void clear() {
        feeds.clear();
        notifyDataSetChanged();
    }


// Add a list of items -- change to type used

    public void addAll(List<Feed> post) {
        feeds.addAll(post);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeCreator = itemView.findViewById(R.id.feedUser);
            recipeImage = itemView.findViewById(R.id.userImage);
            recipeDescription = itemView.findViewById(R.id.userStatus);
            //recipeName = itemView.findViewById(R.id.recipeName);

        }

        public void bind(Feed post) {
            recipeDescription.setText(post.getDescription());
            recipeCreator.setText(post.getUser().getUsername());
            //recipeName.setText(post.getRecipe());
            ParseFile recipeImageFile = post.getRecipeImage();
            if(recipeImageFile != null) {
                Glide.with(context).load(post.getRecipeImage().getUrl()).into(recipeImage);
            }
        }
    }
}