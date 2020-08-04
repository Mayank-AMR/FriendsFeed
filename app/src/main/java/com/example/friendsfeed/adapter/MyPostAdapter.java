package com.example.friendsfeed.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.friendsfeed.R;
import com.example.friendsfeed.SharedPreference.SharedPrefManager;
import com.example.friendsfeed.dialog.BigImageDialog;
import com.example.friendsfeed.model.MyPostsContainer;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 22-04-2020 12:36 PM
 */
public class MyPostAdapter extends RecyclerView.Adapter<MyPostAdapter.PostViewHolder> {
    private static final String TAG = "MyPostAdapter";
    private Context mContext;
    private ArrayList<MyPostsContainer> rawDataContainerList;

    public MyPostAdapter(Context mContext, ArrayList<MyPostsContainer> rawDataContainers) {
        this.mContext = mContext;
        this.rawDataContainerList = rawDataContainers;
    }


    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: start");
        View v = LayoutInflater.from(mContext).inflate(R.layout.home_screen_main_view, parent, false);

        Log.d(TAG, "onCreateViewHolder: end");
        return new PostViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final PostViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: start");
        MyPostsContainer currentContainer = rawDataContainerList.get(position);

        String img = "https://scontent-bom1-1.xx.fbcdn.net/v/t1.0-9/s960x960/80809852_1546578392163557_7946516008221540352_o.jpg?_nc_cat=101&_nc_sid=85a577&_nc_ohc=FUJqvaGhGwYAX9DZ6oI&_nc_ht=scontent-bom1-1.xx&_nc_tp=7&oh=92442c7b22f0e9453c2d02a323678b3b&oe=5EC4A3D5";
        Glide.with(mContext).load(img).centerCrop().into(holder.userPicImage);

        if (SharedPrefManager.getInstance(mContext).isSignIn()) {
            SharedPrefManager sp = SharedPrefManager.getInstance(mContext);
            String fullName = sp.getLoginAuthentication().getFullName();
            String userName = "@" + sp.getLoginAuthentication().getUserName();
            holder.fullName.setText(fullName);
            holder.userName.setText(userName);
        }

        if (currentContainer.getPost_image().equals("NULL")) {
            holder.postImage.setVisibility(View.GONE);
            holder.postImageContainerCardView.setVisibility(View.GONE);
        } else {
            final String postImageURL = "http://diready.co/storage/users/images/" + currentContainer.getPost_image();
            Glide.with(mContext).load(postImageURL).centerCrop().into(holder.postImage);

            holder.postImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //showImage(postImageURL,view.getContext());
                    FragmentManager manager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                    BigImageDialog.newInstance(postImageURL).show(manager,"");
                }
            });
        }
        holder.postTitle.setText(currentContainer.getPost_id());
        holder.postTitle.setVisibility(View.GONE);
        holder.postDescription.setText(currentContainer.getPost());
        holder.postLikeButton.setText(currentContainer.getLikes_count());
        holder.postCommentButton.setText(currentContainer.getComments_count());
        if (currentContainer.isSelf_like_status()) {
            holder.postLikeButton.setBackgroundColor(Color.BLUE);
        }
        Log.d(TAG, "onBindViewHolder: end");
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: == " + rawDataContainerList.size());
        return rawDataContainerList.size();
    }

//    public void showImage(String uriImage,Context mContext) {
//
//        Dialog builder = new Dialog(mContext);
//        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        builder.getWindow().setBackgroundDrawable(
//                new ColorDrawable(android.graphics.Color.TRANSPARENT));
//        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialogInterface) {
//                //nothing;
//            }
//        });
//        ImageView imageView = new ImageView(mContext);
////        imageView.setImageURI(imageUri);
//        PhotoViewAttacher pAttacher = new PhotoViewAttacher(imageView);
//        Glide.with(mContext).load(uriImage).placeholder(R.drawable.a18).into(imageView);
//        pAttacher.update();
//        builder.addContentView(imageView, new /*RelativeLayout.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT)*/
//
//                ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,
//                ConstraintLayout.LayoutParams.MATCH_PARENT));
//        builder.show();
//    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "PostViewHolder";
        ImageView userPicImage;
        TextView fullName;
        TextView userName;
        ImageView postImage;
        TextView postTitle;
        TextView postDescription;
        Button postLikeButton;
        Button postCommentButton;

        CardView postImageContainerCardView;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d(TAG, "PostViewHolder: start");
            userPicImage = itemView.findViewById(R.id.user_pic_imageView);
            fullName = itemView.findViewById(R.id.user_full_name_textView);
            userName = itemView.findViewById(R.id.user_name_textView);
            postImage = itemView.findViewById(R.id.post_image_imageView);
            postTitle = itemView.findViewById(R.id.post_title_textView);
            postDescription = itemView.findViewById(R.id.post_description_textView);
            postLikeButton = itemView.findViewById(R.id.button_likes);
            postCommentButton = itemView.findViewById(R.id.button_comment);

            postImageContainerCardView = itemView.findViewById(R.id.post_Image_container_cardView);
            Log.d(TAG, "PostViewHolder: end");
        }
    }
}
