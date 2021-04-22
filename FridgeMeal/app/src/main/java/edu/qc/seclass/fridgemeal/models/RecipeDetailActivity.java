package edu.qc.seclass.fridgemeal.models;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.LinkedList;

import edu.qc.seclass.fridgemeal.R;

public class RecipeDetailActivity extends AppCompatActivity {

    ImageView ivRecipeImageDetail;
    TextView tvRecipeNameDetail;
    TextView tvRecipeDescriptionDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        ivRecipeImageDetail = findViewById(R.id.ivRecipeImageDetail);
        tvRecipeNameDetail = findViewById(R.id.tvRecipeNameDetail);
        tvRecipeDescriptionDetail = findViewById(R.id.tvRecipeDescriptionDetail);
        tvRecipeDescriptionDetail.setMovementMethod(new ScrollingMovementMethod());

        StringBuilder sb = new StringBuilder();
        String recipeName = getIntent().getStringExtra("recipeName");
        String ingredientArray = getIntent().getStringExtra("ingredientArray");

        try {
            JSONArray ingredientJSONArray = new JSONArray(ingredientArray);
            for (int i = 0; i < ingredientJSONArray.length() ; i++) {
                String currIngredient = ingredientJSONArray.get(i).toString();
                if (currIngredient.length() != 1) sb.append(currIngredient + "\n\n");
            }
            tvRecipeDescriptionDetail.setText(sb.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }


        tvRecipeNameDetail.setText(recipeName);

    }
}