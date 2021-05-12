package edu.qc.seclass.fridgemeal.models;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import java.util.List;

import java.util.LinkedList;

import edu.qc.seclass.fridgemeal.R;

public class RecipeDetailActivity extends AppCompatActivity {

    ImageView ivRecipeImageDetail;
    TextView tvRecipeNameDetail;
    TextView tvRecipeDescriptionDetail;
    Button btnRecipeDirections;
    int index = 0;
    private final String tag = "RecipeDetailActivity";
    Context context;
    User user;
    JSONArray jsonArray;
    JSONObject jsonObject;
    JSONObject newObject = new JSONObject();
    Button faveBox;

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


        jsonObject = new JSONObject();
        try {
            jsonObject.put("recipeName", getIntent().getStringExtra("recipeName"));
            jsonObject.put("serving", getIntent().getStringExtra("serving"));
            jsonObject.put("calories", getIntent().getStringExtra("calories"));
            jsonObject.put("cookingTime", getIntent().getStringExtra("cookingTime"));
            jsonObject.put("directionsPath", getIntent().getStringExtra("directionsPath"));
            jsonObject.put("image", getIntent().getStringExtra("image"));
            jsonObject.put("ingredientArray", getIntent().getStringExtra("ingredientArray"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        queryUser();

        try {
            JSONArray ingredientJSONArray = new JSONArray(ingredientArray);
            for (int i = 0; i < ingredientJSONArray.length(); i++) {
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


    protected void queryUser() {
        Toast.makeText(getBaseContext(), "user query", Toast.LENGTH_SHORT).show();
        ParseQuery<User> check = ParseQuery.getQuery(User.class);
        check.include(User.key_user);
        check.whereEqualTo(User.key_user, ParseUser.getCurrentUser());
        //query.setLimit(1);
        check.findInBackground(new FindCallback<User>() {
            @Override
            public void done(List<User> users, ParseException e) {
                if (e != null) {
                    Log.e(tag, "not null!", e);
                    return;
                }
                jsonArray = users.get(0).getFavorites();
                Log.i(tag, "List size: " + jsonArray.length());
                faveBox = findViewById(R.id.faveBox);
                for(index = 0; index > jsonArray.length(); index++){
                    try {
                        newObject = jsonArray.getJSONObject(index);
                        if (newObject.get("recipeNAme").toString().equals(jsonObject.get("recipeName").toString())) {
                            faveBox.isPressed();
                        }
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    }
                }
                faveBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (index = 0; index > jsonArray.length(); index++) {
                            try {
                                newObject = jsonArray.getJSONObject(index);
                                if (newObject.get("recipeNAme").toString().equals(jsonObject.get("recipeName").toString())) {
                                    jsonArray.remove(index);
                                }
                                else {
                                    jsonArray.put(jsonObject);
                                }
                            } catch (JSONException jsonException) {
                                jsonException.printStackTrace();
                            }
                        }
                    }
                });
                saveFaves(jsonArray, users);
            }
        });

    }

    public void saveFaves(JSONArray jsonArray, List<User> users){
        User user = new User();
        user.setUser(users.get(0).getUser());
        user.setFavorites(jsonArray);
        user.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null){
                    Toast.makeText(getBaseContext(), "favorites saved", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}