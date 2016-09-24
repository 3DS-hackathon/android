package com.github.dan4ik95dv.app.ui.view;

import com.github.dan4ik95dv.app.model.user.User;

public interface ProfileMvpView extends MvpView {
    void showError();

    void fillUserProfile(User user);
}
