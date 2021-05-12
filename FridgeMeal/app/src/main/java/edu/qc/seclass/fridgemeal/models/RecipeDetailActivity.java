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
import com.parse.ParseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

import edu.qc.seclass.fridgemeal.R;

public class RecipeDetailActivity extends AppCompatActivity {

    ImageView ivRecipeImageDetail;
    TextView tvRecipeNameDetail;
    TextView tvRecipeDescriptionDetail;
    Button btnRecipeDirections;
    Button faveBox;
    User user;
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);


        ivRecipeImageDetail = findViewById(R.id.ivRecipeImageDetail);
        tvRecipeNameDetail = findViewById(R.id.tvRecipeNameDetail);
        tvRecipeDescriptionDetail = findViewById(R.id.tvRecipeDescriptionDetail);
        btnRecipeDirections = findViewById(R.id.btnRecipeDirections);
        faveBox = findViewById(R.id.faveBox);
        tvRecipeDescriptionDetail.setMovementMethod(new ScrollingMovementMethod());

        StringBuilder sb = new StringBuilder();
        String recipeName = getIntent().getStringExtra("recipeName");
        String ingredientArray = getIntent().getStringExtra("ingredientArray");
        String imagePath = getIntent().getStringExtra("image");
        String directionsPath = getIntent().getStringExtra("directionsPath");

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("recipeName", getIntent().getStringExtra("recipeName"));
            jsonObject.put("serving", Integer.parseInt(getIntent().getStringExtra("serving")));
            jsonObject.put("calories", Integer.parseInt(getIntent().getStringExtra("calories")));
            jsonObject.put("cookingTime", Integer.parseInt(getIntent().getStringExtra("cookingTime")));
            jsonObject.put("directionsPath", getIntent().getStringExtra("directionsPath"));
            jsonObject.put("image", getIntent().getStringExtra("image"));
            jsonObject.put("ingredientArray", getIntent().getStringExtra("ingredientArray"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        


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

//        for(index = 0; jsonArray.; index++)

//        faveBox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(json object does not exist) jsonArray.put(jsonObject);
//                else jsonArray.remove(jsonObject);
//            }
//        });

        btnRecipeDirections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(directionsPath); // missing 'http://' will cause crashed //works fine
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}