package com.codepath.queridacamara.fragments;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.queridacamara.Post;
import com.codepath.queridacamara.PostAdapter;
import com.codepath.queridacamara.Profile;
import com.codepath.queridacamara.ProfileAdapter;
import com.codepath.queridacamara.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";

    RecyclerView rvProfile;
    ProfileAdapter profileAdapter;
    List<Profile> profiles;

    RecyclerView rvPosts;
    PostAdapter postAdapter;
    List<Post> allPosts;

    TextView tvProfileUsername;
    ImageView ivProfilePicture;


    public ProfileFragment() {
        // Required empty public constructor
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

        rvProfile = view.findViewById(R.id.rvProfile);
        profiles = new ArrayList<>();
        profileAdapter = new ProfileAdapter(getContext(), profiles);
        rvProfile.setAdapter(profileAdapter);
        rvProfile.setLayoutManager(new LinearLayoutManager(getContext()));

        rvPosts = view.findViewById(R.id.rvPosts);
        allPosts = new ArrayList<>();
        postAdapter = new PostAdapter(getContext(), allPosts);
        rvPosts.setAdapter(postAdapter);
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));

        ivProfilePicture = view.findViewById(R.id.ivProfilePicture);
        tvProfileUsername = view.findViewById(R.id.tvProfileUsername);

        queryProfile();
        queryPosts();
    }

    private void queryProfile() {
        ParseQuery<Profile> query = ParseQuery.getQuery(Profile.class);
        query.include(Profile.KEY_USERNAME);
        query.whereEqualTo(Profile.KEY_USERNAME, ParseUser.getCurrentUser().getUsername());
        query.setLimit(10);
        query.findInBackground(new FindCallback<Profile>() {
            @Override
            public void done(List<Profile> allProfiles, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                profiles.addAll(allProfiles);
                profileAdapter.notifyDataSetChanged();
            }
        });
    }

    private void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        query.setLimit(20);
        query.addDescendingOrder(Post.KEY_CREATED_AT);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                for (Post post : posts) {
                    Log.i(TAG, "Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
                }
                allPosts.addAll(posts);
                postAdapter.notifyDataSetChanged();
            }
        });
    }
}
