package edu.qc.seclass.fridgemeal.models;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import edu.qc.seclass.fridgemeal.R;

public class RecipeDetailActivity extends AppCompatActivity {

    ImageView ivRecipeImageDetail;
    TextView tvRecipeNameDetail;
    TextView tvCookingTimeDetail;
    TextView tvRecipeDescriptionDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        ivRecipeImageDetail = findViewById(R.id.ivRecipeImageDetail);
        tvRecipeNameDetail = findViewById(R.id.tvRecipeNameDetail);
        tvCookingTimeDetail = findViewById(R.id.tvCookingTimeDetail);
        tvRecipeDescriptionDetail = findViewById(R.id.tvRecipeDescriptionDetail);

        String recipeName = getIntent().getStringExtra("recipeName");
        String recipeDescription = getIntent().getStringExtra("recipeDescription");
        String cookingTime = getIntent().getStringExtra("cookingTime");

        tvRecipeNameDetail.setText(recipeName);
        tvCookingTimeDetail.setText(cookingTime);
        tvRecipeDescriptionDetail.setText(recipeDescription);
    }
}