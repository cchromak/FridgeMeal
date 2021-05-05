package edu.qc.seclass.fridgemeal.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.MotionEventCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import edu.qc.seclass.fridgemeal.R;
import edu.qc.seclass.fridgemeal.adapters.FeedAdapter;
import edu.qc.seclass.fridgemeal.adapters.UserAdapter;
import edu.qc.seclass.fridgemeal.models.Favorites;
import edu.qc.seclass.fridgemeal.models.Feed;
import edu.qc.seclass.fridgemeal.models.Recipe;
import edu.qc.seclass.fridgemeal.models.User;

public class RecipesFragment extends Fragment{

    private RecyclerView rvRecipes;
    private Button btnYourRecipes;
    private Button btnFriendsRecipes;
    private Button btnFavoriteRecipes;
    private FeedAdapter feedAdapter;
    protected List<Feed> feeds;

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
        btnYourRecipes = view.findViewById(R.id.btnYourRecipes);
        btnFriendsRecipes = view.findViewById(R.id.btnFriendsRecipes);
        btnFavoriteRecipes = view.findViewById(R.id.btnFavoriteRecipes);
        feeds = new LinkedList<>();

        // Create Adapter
        feedAdapter = new FeedAdapter(getContext(), feeds);

        // Set Adapter
        rvRecipes.setAdapter(feedAdapter);

        // Set layout manager
        rvRecipes.setLayoutManager(new LinearLayoutManager(getContext()));
        queryPosts();


        btnYourRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryPosts();
            }
        });

        btnFriendsRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryFriendsPosts();
            }
        });

        btnFavoriteRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();
                queryFavoriteRecipes();
            }
        });
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
                feedAdapter.clear();
                feedAdapter.addAll(feeds);
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
                feedAdapter.clear();
                feedAdapter.addAll(feeds);
            }
        });
    }
    protected void queryFavoriteRecipes(){
        ParseQuery<Favorites> favorites = ParseQuery.getQuery(Favorites.class);
        ParseUser user = ParseUser.getCurrentUser();
        ParseQuery<User> thisUser = ParseQuery.getQuery(User.class);
        thisUser.whereEqualTo(User.key_user, user.getObjectId());

        Log.i(tag, user.getObjectId());
        favorites.whereEqualTo(Favorites.KEY_USER_PK, user);
        ParseQuery<Feed> feedParseQuery = ParseQuery.getQuery(Feed.class);
        feedParseQuery.whereMatchesKeyInQuery(Feed.KEY_OBJECT_ID, Favorites.KEY_RECIPE_FK, favorites);
        feedParseQuery.findInBackground(new FindCallback<Feed>() {
            @Override
            public void done(List<Feed> objects, ParseException e) {
                if (e != null){
                    Log.e(tag, "", e);
                    return;
                }

                Log.i(tag, "test " + Integer.toString(objects.size()));
            }
        });

    }


}
