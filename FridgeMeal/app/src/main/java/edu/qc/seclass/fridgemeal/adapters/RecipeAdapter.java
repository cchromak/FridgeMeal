package edu.qc.seclass.fridgemeal.adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.qc.seclass.fridgemeal.R;
import edu.qc.seclass.fridgemeal.models.Recipe;

public class RecipeAdapter extends  RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    Context context;
    List<Recipe> recipes;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View recipeView = LayoutInflater.from(context).inflate(R.layout.item_recipe, parent, false);
        return new ViewHolder(recipeView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe recipe    = recipes.get(position);
        holder.bind(recipe);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvRecipeName;
        TextView tvCookingTime;
        TextView tvDescription;
        ImageView ivRecipeImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvRecipeName = itemView.findViewById(R.id.tvRecipeName);
            tvCookingTime = itemView.findViewById(R.id.tvCookingTime);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ivRecipeImage = itemView.findViewById(R.id.ivRecipeImage);
        }

        public void bind(Recipe recipe) {
            tvRecipeName.setText(recipe.getRecipeName());
            tvCookingTime.setText(recipe.getCookingTime());
            tvDescription.setText(recipe.getDescription());
        }
    }
}
