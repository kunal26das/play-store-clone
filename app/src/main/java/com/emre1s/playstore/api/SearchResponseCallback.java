package com.emre1s.playstore.api;

import java.util.List;

public interface SearchResponseCallback {
    void onSuccess(List<String> suggestions);

    void onFailure();
}
