package edu.qc.seclass.fridgemeal.fragments;

import android.content.Context;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.LinkedList;
import java.util.List;

import edu.qc.seclass.fridgemeal.Feed;
import edu.qc.seclass.fridgemeal.R;
import edu.qc.seclass.fridgemeal.adapters.RecipeAdapter;
import edu.qc.seclass.fridgemeal.models.Recipe;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private ImageView proPic;
    private ImageView badge1;
    private ImageView badge2;
    private ImageView badge3;
    private ImageView badge4;
    private ImageView badge5;
    private ImageView badge6;
    private TextView description;
    private TextView username;
    private Button addBtn;
    private RecyclerView rProfile;
    private List<Recipe> recipes;
    private RecipeAdapter recipeAdapter;
    private static String tag = "ProfileFragment";



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //public ProfileFragment() {
        // Required empty public constructor
   // }

    public ProfileFragment() {
        //Toast.makeText(getContext(), "profile", Toast.LENGTH_SHORT).show();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        Context context = this.getContext();
        Toast.makeText(context, "test profile frag", Toast.LENGTH_SHORT).show();
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
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        proPic = view.findViewById(R.id.profileImage);
        addBtn = view.findViewById(R.id.addButton);
        rProfile = view.findViewById(R.id.rProfile);
        badge1 = view.findViewById(R.id.bImage1);
        badge2 = view.findViewById(R.id.bImage2);
        badge3 = view.findViewById(R.id.bImage3);
        badge4 = view.findViewById(R.id.bImage4);
        badge5 = view.findViewById(R.id.bImage5);
        badge6 = view.findViewById(R.id.bImage6);
        description = view.findViewById(R.id.profileDescription);
        username = view.findViewById(R.id.profileUsername);

        recipes = new LinkedList<>();
        // Create Adapter
        recipeAdapter = new RecipeAdapter(getContext(), recipes);
        // Set Adapter
        rProfile.setAdapter(recipeAdapter);
        // Set layout manager
        rProfile.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    protected void queryPosts() {
        Toast.makeText(getContext(), "post here", Toast.LENGTH_SHORT).show();
        ParseQuery<Feed> query = ParseQuery.getQuery(Feed.class);
        query.include(Feed.key_user);
        query.whereEqualTo(Feed.key_user, ParseUser.getCurrentUser());
        query.setLimit(20);
        query.addDescendingOrder(Feed.key_created);
        query.findInBackground(new FindCallback<Feed>() {
            @Override
            public void done(List<Feed> recipes, ParseException e) {
                if (e != null) {
                    Log.e(tag, "not null!", e);
                    return;
                }
                for (Feed feed : recipes) { //added username here
                    //Log.i(tag, "post: " + recipes.getDescription() + ", by " + feed.getUser().getUsername());
                    Toast.makeText(getContext(), "post here", Toast.LENGTH_SHORT).show();
                }
                recipes.addAll(recipes);
                recipeAdapter.notifyDataSetChanged();
            }
        });
    }
}