package edu.qc.seclass.fridgemeal.models;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.LinkedList;

import edu.qc.seclass.fridgemeal.R;

public class RecipeDetailActivity extends AppCompatActivity {

    ImageView ivRecipeImageDetail;
    TextView tvRecipeNameDetail;
    TextView tvRecipeDescriptionDetail;
    Button btnRecipeDirections;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        ivRecipeImageDetail = findViewById(R.id.ivRecipeImageDetail);
        tvRecipeNameDetail = findViewById(R.id.tvRecipeNameDetail);
        tvRecipeDescriptionDetail = findViewById(R.id.tvRecipeDescriptionDetail);
        btnRecipeDirections = findViewById(R.id.btnRecipeDirections);
        tvRecipeDescriptionDetail.setMovementMethod(new ScrollingMovementMethod());

        StringBuilder sb = new StringBuilder();
        String recipeName = getIntent().getStringExtra("recipeName");
        String ingredientArray = getIntent().getStringExtra("ingredientArray");
        String imagePath = getIntent().getStringExtra("image");
        String directionsPath = getIntent().getStringExtra("directionsPath");

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

        Glide.with(RecipeDetailActivity.this).load(imagePath).into(ivRecipeImageDetail);
        tvRecipeNameDetail.setText(recipeName);

        btnRecipeDirections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(directionsPath); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}