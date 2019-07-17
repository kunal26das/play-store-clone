package com.emre1s.playstore.api;

import com.emre1s.playstore.models.Permission;

import java.util.List;

public interface PermissionResponseCallback {
    void onSuccess(List<Permission> permissions);

    void onFailure();
}
