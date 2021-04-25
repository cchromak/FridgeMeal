package edu.qc.seclass.fridgemeal.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import edu.qc.seclass.fridgemeal.models.Feed;
import edu.qc.seclass.fridgemeal.R;
import edu.qc.seclass.fridgemeal.adapters.RecipeAdapter;
import edu.qc.seclass.fridgemeal.models.Recipe;
import okhttp3.Headers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    public static final String RECIPE_URL = "https://api.edamam.com/search?app_id=194cadde&app_key=91faa5c0046d5686b9d896f97ba3435c";
    EditText etIngredient;
    Button btnAddIngredient;
    RecyclerView rvHomeRecipes;
    RecipeAdapter recipeAdapter;
    List<Recipe> recipes;
    List<String> ingredients;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etIngredient = view.findViewById(R.id.etIngredient);
        btnAddIngredient = view.findViewById(R.id.btnAddIngredient);
        rvHomeRecipes = view.findViewById(R.id.rvHomeRecipes);

        recipes = new LinkedList<>();
        ingredients = new LinkedList<>();
        // Create Adapter
        recipeAdapter = new RecipeAdapter(getContext(), recipes);
        // Set Adapter
        rvHomeRecipes.setAdapter(recipeAdapter);
        // Set layout manager
        rvHomeRecipes.setLayoutManager(new LinearLayoutManager(getContext()));

        btnAddIngredient.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                recipeAdapter.clearData();
                ingredients.add(etIngredient.getText().toString());
                etIngredient.setText("");
                String ingredientQuery = getStringOfIngredients(ingredients);
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                params.put("q", ingredientQuery);

                client.get(RECIPE_URL, params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, Headers headers, JSON json) {
                        JSONObject jsonObject = json.jsonObject;
                        try {
                            JSONArray hits = jsonObject.getJSONArray("hits");
                            // Clears last inputted Ingredient if it returns an empty json object
                            if (hits.length() == 0) {
                                ingredients.remove(ingredients.size() - 1);
                                Toast.makeText(getContext(), "Sorry we don't cover this ingredient yet.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            recipes.addAll(Recipe.fromJsonArray(hits));
                            recipeAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                        Toast.makeText(getContext(), "NO CHICKEN", Toast.LENGTH_SHORT).show();
                    }
                });
                
            }
        });

    }

    public String getStringOfIngredients (List<String> ingredients) {
        StringBuilder sb = new StringBuilder();
        for (String ingredient: ingredients) {
            sb.append(ingredient + " ");
        }
        return sb.toString().trim();
    }

}