package edu.qc.seclass.fridgemeal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import edu.qc.seclass.fridgemeal.R;
import edu.qc.seclass.fridgemeal.models.Recipe;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    Context context;
    List<Recipe> recipeList;



    public FavoriteAdapter(Context context, List<Recipe> recipeList){
        this.context = context;
        this.recipeList = recipeList;
    }
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recipe, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.bind(recipe);

    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }
    public void clear(){
        recipeList.clear();
        notifyDataSetChanged();
    }
    public void addAll(List<Recipe> recipes){
        recipeList.addAll(recipes);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout containerRecipe;
        TextView tvRecipeName;
        TextView tvCookingTime;
        TextView tvCalories;
        TextView tvServings;
        ImageView ivRecipeImage;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            tvRecipeName = itemView.findViewById(R.id.tvRecipeName);
            tvCookingTime = itemView.findViewById(R.id.tvCookingTime);
            tvCalories = itemView.findViewById(R.id.tvCalories);
            tvServings = itemView.findViewById(R.id.tvServings);
            ivRecipeImage = itemView.findViewById(R.id.ivRecipeImage);


        }

        public void bind(Recipe recipe) {
            String name = recipe.getRecipeName();
            tvRecipeName.setText(name);
            tvCalories.setText(recipe.getCalories());
            tvServings.setText(recipe.getServing());
            Glide.with(context).load(recipe.getImagePath()).into(ivRecipeImage);
            ivRecipeImage.setClipToOutline(true);
            tvCookingTime.setText(recipe.getCookingTime());
        }
    }
}
