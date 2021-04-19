package edu.qc.seclass.fridgemeal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.LinkedList;
import java.util.List;

import edu.qc.seclass.fridgemeal.adapters.RecipeAdapter;
import edu.qc.seclass.fridgemeal.models.Recipe;

public class MainActivity extends AppCompatActivity {

    EditText etIngredient;
    Button btnAddIngredient;
    RecyclerView rvHomeRecipes;

    List<Recipe> recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etIngredient = findViewById(R.id.etIngredient);
        btnAddIngredient = findViewById(R.id.btnAddIngredient);
        rvHomeRecipes = findViewById(R.id.rvHomeRecipes);

        recipes = new LinkedList<>();

        Recipe chick = new Recipe("Chicken", "34 mins", "You know it, you love it, it should be every meal. It's chicken");
        Recipe beef = new Recipe("Beef", "100 mins", "You know it, you love it, it should be every meal. It's Beef");
        Recipe pizza = new Recipe("Pizza", "5 mins", "You know it, you love it, it should be every meal. It's Pizza");
        recipes.add(chick);
        recipes.add(beef);
        recipes.add(pizza);

        // Create Adapter
        RecipeAdapter recipeAdapter = new RecipeAdapter(this, recipes);
        // Set Adapter
        rvHomeRecipes.setAdapter(recipeAdapter);
        // Set layout manager
        rvHomeRecipes.setLayoutManager(new LinearLayoutManager(this));





    }
}