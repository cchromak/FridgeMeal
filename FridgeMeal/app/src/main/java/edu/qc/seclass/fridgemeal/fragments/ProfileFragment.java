package edu.qc.seclass.fridgemeal.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

import edu.qc.seclass.fridgemeal.adapters.ProfileAdapter;
import edu.qc.seclass.fridgemeal.adapters.UserAdapter;
import edu.qc.seclass.fridgemeal.models.Feed;
import edu.qc.seclass.fridgemeal.R;
import edu.qc.seclass.fridgemeal.adapters.FeedAdapter;
import edu.qc.seclass.fridgemeal.models.Profile;
import edu.qc.seclass.fridgemeal.models.User;


public class ProfileFragment extends Fragment {

    private RecyclerView rProfile;
    private RecyclerView rUser;
    protected List<User> posts;
    protected List<User> user;
    private UserAdapter userAdapter;
    private ProfileAdapter profileAdapter;
    private static String tag = "ProfileFragment";
    private Button btnPost;
    private EditText compose;


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

        posts = new LinkedList<>();
        user = new LinkedList<>();
        // Create Adapter
        userAdapter = new UserAdapter(getContext(), user);
        profileAdapter = new ProfileAdapter(getContext(), posts);
        // Set Adapter
        rProfile.setAdapter(profileAdapter);
        rUser.setAdapter(userAdapter);
        // Set layout manager
        rProfile.setLayoutManager(new LinearLayoutManager(getContext()));
        rUser.setLayoutManager(new LinearLayoutManager(getContext()));
        btnPost = view.findViewById(R.id.btnAdd);
        compose = view.findViewById(R.id.eText);
        btnPost.setEnabled(false);

        queryUser();
        queryPosts();

        compose.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Fires right as the text is being changed (even supplies the range of text)

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // Fires right before text is changing
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Fires right after the text has changed
                if(s.length() > 300 || s.length() < 1){
                    Toast.makeText(getContext(), "We can't really chirp that!", Toast.LENGTH_SHORT).show();
                    btnPost.setEnabled(false);
                }
                else btnPost.setEnabled(true);
            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = compose.getText().toString();
                if(content.isEmpty()){
                    Toast.makeText(getContext(), "Can't tweet nothin'!", Toast.LENGTH_LONG).show();
                    return;
                }
                if(content.length() > 400){
                    Toast.makeText(getContext(), "Whoa there! Too many chirpers!", Toast.LENGTH_LONG).show();
                    return;
                }
                //publish tweet via api call
                else {
                    //btnPost.isEnabled();
                    user;
                    Toast.makeText(getContext(), "Status sent!", Toast.LENGTH_LONG).show();
                }
            }

        });

    }

    protected void queryPosts() {
        Toast.makeText(getContext(), "status here", Toast.LENGTH_SHORT).show();
        ParseQuery<User> query = ParseQuery.getQuery(User.class);
        query.include(User.key_user);
        //check.whereEqualTo(User.key_user, ParseUser.getCurrentUser());
        query.setLimit(20);
        query.findInBackground(new FindCallback<User>() {
            @Override
            public void done(List<User> posts, ParseException e) {
                if (e != null) {
                    Log.e(tag, "not null!", e);
                    return;
                }
                for (User user : posts) { //added username here
                    Log.i(tag, "user: " + user.getStatus() + ", by " + user.getUser().getUsername());
                    Toast.makeText(getContext(), "post here", Toast.LENGTH_SHORT).show();
                }
                profileAdapter.clear();
                profileAdapter.addAll(posts);
            }
        });
    }

    protected void queryUser() {
        Toast.makeText(getContext(), "user here", Toast.LENGTH_SHORT).show();
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
                for (User user : users) { //added username here
                    Log.i(tag, "user: " + user.getDescription() + ", by " + user.getUser().getUsername());
                    Toast.makeText(getContext(), "post here", Toast.LENGTH_SHORT).show();
                }
                userAdapter.clear();
                userAdapter.addAll(users);
            }
        });
    }

}