package edu.qc.seclass.fridgemeal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

import edu.qc.seclass.fridgemeal.R;
import edu.qc.seclass.fridgemeal.models.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>  {

    private Context context;
    private List<User> users;
    private TextView username;
    private TextView description;
    private ImageView proPic;
    //private User user;


    public UserAdapter(Context context, List<User> users){
        this.context = context;
        this.users = users;
        //  this.user = user;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = users.get(position);
        holder.bind(user);
    }

    @Override
    public int getItemCount() {
        //if(feeds == null) return 0;
        return users.size();
    }

    public void clear() {
        users.clear();
        notifyDataSetChanged();
    }


// Add a list of items -- change to type used

    public void addAll(List<User> post) {
        users.addAll(post);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            proPic = itemView.findViewById(R.id.proPic);
            description = itemView.findViewById(R.id.description);

        }

        public void bind(User user) {
            description.setText(user.getDescription());
            username.setText(user.getUser().getUsername());
            ParseFile imageFile = user.getImage();
            if(imageFile != null) {
                Glide.with(context).load(user.getImage().getUrl()).into(proPic);
            }
        }
    }
}