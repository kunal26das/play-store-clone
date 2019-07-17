package com.emre1s.playstore.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emre1s.playstore.R;
import com.emre1s.playstore.models.Review;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private List<Review> reviewList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReviewAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ViewHolder holder, int position) {
        Log.d("ReviewsAdapter", reviewList.get(position).getId());
        Picasso.get().load(reviewList.get(position).getUserImage())
                .placeholder(R.drawable.placeholder_icon).into(holder.userImage);
        holder.userName.setText(reviewList.get(position).getUserName());
        holder.ratingBar.setRating(reviewList.get(position).getScore());
        holder.reviewDate.setText(reviewList.get(position).getDate());
        holder.reviewContent.setText(reviewList.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CircularImageView userImage;
        private TextView userName;
        private RatingBar ratingBar;
        private TextView reviewDate;
        private TextView reviewContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userImage = itemView.findViewById(R.id.user_image);
            userName = itemView.findViewById(R.id.user_name);
            ratingBar = itemView.findViewById(R.id.score);
            reviewDate = itemView.findViewById(R.id.review_date);
            reviewContent = itemView.findViewById(R.id.review_text);
            ratingBar.setProgress(R.color.black);
        }
    }
}