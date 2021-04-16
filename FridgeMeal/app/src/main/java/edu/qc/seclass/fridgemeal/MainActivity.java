package edu.qc.seclass.fridgemeal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText etIngredient;
    Button btnAddIngredient;
    RecyclerView rvHomeRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etIngredient = findViewById(R.id.etIngredient);
        btnAddIngredient = findViewById(R.id.btnAddIngredient);
        rvHomeRecipes = findViewById(R.id.rvHomeRecipes);


    }
}