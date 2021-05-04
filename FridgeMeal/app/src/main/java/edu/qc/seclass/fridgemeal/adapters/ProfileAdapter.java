package edu.qc.seclass.fridgemeal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

import edu.qc.seclass.fridgemeal.R;
import edu.qc.seclass.fridgemeal.models.Feed;
import edu.qc.seclass.fridgemeal.models.Profile;
import edu.qc.seclass.fridgemeal.models.User;

public class ProfileAdapter  extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    private Context context;
    private List<User> posts;
    private TextView profileUser;
    private TextView userStatus;
    private ImageView userImage;
    //private User user;


    public ProfileAdapter(Context context, List<User> posts){
        this.context = context;
        this.posts = posts;
        //  this.user = user;
    }

    @NonNull
    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_feed, parent, false);
        return new ProfileAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.ViewHolder holder, int position) {
        User post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        //if(feeds == null) return 0;
        return posts.size();
    }

    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }


// Add a list of items -- change to type used

    public void addAll(List<User> post) {
        posts.addAll(post);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profileUser = itemView.findViewById(R.id.feedUser);
            userImage = itemView.findViewById(R.id.userImage);
            userStatus = itemView.findViewById(R.id.userStatus);

        }

        public void bind(User post) {
            userStatus.setText(post.getStatus());
            profileUser.setText(post.getUser().getUsername());
            ParseFile recipeImageFile = post.getImage();
            if(recipeImageFile != null) {
                Glide.with(context).load(post.getImage().getUrl()).into(userImage);
            }
        }
    }
}
