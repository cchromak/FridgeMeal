package edu.qc.seclass.fridgemeal.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import edu.qc.seclass.fridgemeal.models.Feed;
import edu.qc.seclass.fridgemeal.R;
import edu.qc.seclass.fridgemeal.models.User;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    private final Context context;
    private final List<Feed> feeds;
    TextView recipeName;
    TextView cookingTime;
    TextView calories;
    TextView servings;
    ImageView recipeImage;


    public FeedAdapter(Context context, List<Feed> feeds){
        this.context = context;
        this.feeds = feeds;
      //  this.user = user;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recipe, parent, false);
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
            recipeName = itemView.findViewById(R.id.tvRecipeName);
            cookingTime = itemView.findViewById(R.id.tvCookingTime);
            calories = itemView.findViewById(R.id.tvCalories);
            servings = itemView.findViewById(R.id.tvServings);
            recipeImage = itemView.findViewById(R.id.ivRecipeImage);

        }

        public void bind(Feed post) {
            recipeName.setText(post.getRecipe());
            cookingTime.setText(post.getCookTime());
            calories.setText(post.getCalories());
            servings.setText(post.getServings());
            ParseFile recipeImageFile = post.getRecipeImage();
            if(recipeImageFile != null) {
                Glide.with(context).load(post.getRecipeImage().getUrl()).into(recipeImage);
            }
        }
    }
}