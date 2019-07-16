package com.emre1s.playstore.api;

import com.emre1s.playstore.models.Review;

import java.util.List;

public interface ReviewResponseCallback {
    void onSuccess(List<Review> reviews);

    void onFailure();
}
