package edu.qc.seclass.fridgemeal.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import edu.qc.seclass.fridgemeal.R;
import edu.qc.seclass.fridgemeal.adapters.FavoriteAdapter;
import edu.qc.seclass.fridgemeal.adapters.FeedAdapter;
import edu.qc.seclass.fridgemeal.models.Feed;
import edu.qc.seclass.fridgemeal.models.Recipe;
import edu.qc.seclass.fridgemeal.models.User;

public class RecipesFragment extends Fragment{

    private RecyclerView rvRecipes;
    private FavoriteAdapter favoriteAdapter;
    protected List<Recipe> recipeList;

    private static String tag = "RecipesFragment";


    public RecipesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvRecipes = view.findViewById(R.id.rvRecipes);
        recipeList = new LinkedList<>();



        // Create Adapter
        favoriteAdapter = new FavoriteAdapter(getContext(), recipeList);

        // Set Adapter
        rvRecipes.setAdapter(favoriteAdapter);

        // Set layout manager
        rvRecipes.setLayoutManager(new LinearLayoutManager(getContext()));
        queryFavoriteRecipes();


    }





    protected void queryPosts() {
        ParseQuery<Feed> query = ParseQuery.getQuery(Feed.class);
        query.include(Feed.key_user);
        query.setLimit(20);
        query.whereEqualTo(Feed.key_user, ParseUser.getCurrentUser());
        query.addDescendingOrder(Feed.key_created);
        query.findInBackground(new FindCallback<Feed>() {
            @Override
            public void done(List<Feed> feeds, ParseException e) {
                if (e != null) {
                    Log.e(tag, "not null!", e);
                    return;
                }
                favoriteAdapter.clear();
                favoriteAdapter.addAll(recipeList);
            }
        });
    }

    protected void queryFriendsPosts() {
        ParseQuery<Feed> query = ParseQuery.getQuery(Feed.class);
        query.include(Feed.key_user);
        query.setLimit(20);
        query.addDescendingOrder(Feed.key_created);
        query.findInBackground(new FindCallback<Feed>() {
            @Override
            public void done(List<Feed> feeds, ParseException e) {
                if (e != null) {
                    Log.e(tag, "not null!", e);
                    return;
                }
                favoriteAdapter.clear();
//                favoriteAdapter.addAll(feeds);
            }
        });

    }

    protected void queryFavoriteRecipes(){
        ParseQuery<User> userParseQuery = ParseQuery.getQuery(User.class);
        userParseQuery.include(User.key_user);
        ParseUser user = ParseUser.getCurrentUser();
        userParseQuery.whereEqualTo(User.key_user, user);
        userParseQuery.findInBackground(new FindCallback<User>() {
            @Override
            public void done(List<User> objects, ParseException e) {
                if (e != null){
                    Log.e(tag, "Error fetching user", e);
                    return;

                }
                JSONArray jsonArray = objects.get(0).getJSONArray(User.KEY_FAVORITE);
                System.out.println(jsonArray.length());
                try {
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    System.out.println(jsonObject.toString());
                    Recipe recipe = new Recipe();
                    recipe.setRecipeName(jsonObject.getString("recipeName"));
                    recipe.setCalories(jsonObject.getInt("calories"));
                    recipe.setServing(jsonObject.getInt("servings"));
                    recipe.setCookingTime(jsonObject.getInt("cookingTime"));
                    System.out.println(recipe.toString());
                    System.out.println(recipe.getRecipeName());
                    recipeList.add(recipe);
                    favoriteAdapter.addAll(recipeList);
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }

            }


        });

    }


}
