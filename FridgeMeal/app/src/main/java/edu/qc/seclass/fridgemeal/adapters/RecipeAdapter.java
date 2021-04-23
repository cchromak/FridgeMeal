package edu.qc.seclass.fridgemeal.adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import edu.qc.seclass.fridgemeal.MainActivity;
import edu.qc.seclass.fridgemeal.R;
import edu.qc.seclass.fridgemeal.models.Recipe;
import edu.qc.seclass.fridgemeal.models.RecipeDetailActivity;

public class RecipeAdapter extends  RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    Context context;
    List<Recipe> recipes;

    public RecipeAdapter(Context context, List<Recipe> recipes) {
        this.context = context;
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View recipeView = LayoutInflater.from(context).inflate(R.layout.item_recipe, parent, false);
        return new ViewHolder(recipeView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.bind(recipe);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public void clearData() {
        // clear the data
        recipes.clear();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout containerRecipe;
        TextView tvRecipeName;
        TextView tvCookingTime;
        TextView tvCalories;
        TextView tvServings;
        ImageView ivRecipeImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            containerRecipe = itemView.findViewById(R.id.containerRecipe);
            tvRecipeName = itemView.findViewById(R.id.tvRecipeName);
            tvCookingTime = itemView.findViewById(R.id.tvCookingTime);
            tvCalories = itemView.findViewById(R.id.tvCalories);
            tvServings = itemView.findViewById(R.id.tvServings);
            ivRecipeImage = itemView.findViewById(R.id.ivRecipeImage);

        }

        public void bind(Recipe recipe) {

            tvRecipeName.setText(recipe.getRecipeName());
            tvCookingTime.setText(recipe.getCookingTime());
            tvCalories.setText(recipe.getCalories());
            tvServings.setText(recipe.getServing());
            Glide.with(context).load(recipe.getImagePath()).into(ivRecipeImage);

            containerRecipe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, RecipeDetailActivity.class);
                    i.putExtra("recipeName", recipe.getRecipeName());
//                    i.putExtra("serving", recipe.getServing());
//                    i.putExtra("calories", recipe.getCalories());
//                    i.putExtra("cookingTime", recipe.getCookingTime());
                    i.putExtra("directionsPath", recipe.getDirectionsPath());
                    i.putExtra("image", recipe.getImagePath());
                    i.putExtra("ingredientArray", recipe.getIngredientArray().toString());
                    context.startActivity(i);
                }
            });
        }
    }
}
