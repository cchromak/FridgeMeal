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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.LinkedList;
import java.util.List;

import edu.qc.seclass.fridgemeal.adapters.UserAdapter;
import edu.qc.seclass.fridgemeal.models.Feed;
import edu.qc.seclass.fridgemeal.R;
import edu.qc.seclass.fridgemeal.adapters.FeedAdapter;
import edu.qc.seclass.fridgemeal.models.User;


public class ProfileFragment extends Fragment {

    private RecyclerView rProfile;
    private RecyclerView rUser;
    protected List<Feed> feeds;
    protected List<User> user;
    private UserAdapter userAdapter;
    private FeedAdapter feedAdapter;
    private static String tag = "ProfileFragment";


    public ProfileFragment() {
        //Toast.makeText(getContext(), "profile", Toast.LENGTH_SHORT).show();
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
        rProfile = view.findViewById(R.id.rProfile);
        rUser = view.findViewById(R.id.rUser);

        feeds = new LinkedList<>();
        user = new LinkedList<>();
        // Create Adapter
        feedAdapter = new FeedAdapter(getContext(), feeds);
        userAdapter = new UserAdapter(getContext(), user);
        // Set Adapter
        rProfile.setAdapter(feedAdapter);
        rUser.setAdapter(userAdapter);
        // Set layout manager
        rProfile.setLayoutManager(new LinearLayoutManager(getContext()));
        rUser.setLayoutManager(new LinearLayoutManager(getContext()));

        queryUser();
        queryPosts();

    }

    protected void queryPosts() {
        Toast.makeText(getContext(), "post here", Toast.LENGTH_SHORT).show();
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
                for (Feed feed : feeds) { //added username here
                    Log.i(tag, "feed: " + feed.getDescription() + ", by " + feed.getUser().getUsername());
                    Toast.makeText(getContext(), "post here", Toast.LENGTH_SHORT).show();
                }
                feedAdapter.clear();
                feedAdapter.addAll(feeds);
            }
        });
    }

    protected void queryUser() {
        Toast.makeText(getContext(), "user here", Toast.LENGTH_SHORT).show();
        ParseQuery<User> query = ParseQuery.getQuery(User.class);
        //query.include(User.key_user);
        query.whereEqualTo(User.key_user, ParseUser.getCurrentUser());
        //query.setLimit(1);
        query.findInBackground(new FindCallback<User>() {
            @Override
            public void done(List<User> users, ParseException e) {
                if (e != null) {
                    Log.e(tag, "not null!", e);
                    return;
                }
                for (User user : users) { //added username here
                    Log.i(tag, "user: " + user.getDescription() + ", by " + user.getUser().getUsername());
                    Toast.makeText(getContext(), "post here", Toast.LENGTH_SHORT).show();
                }
                //userAdapter.clear();
                //userAdapter.addAll(users);
            }
        });
    }

}