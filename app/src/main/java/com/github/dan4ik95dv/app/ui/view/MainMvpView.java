package com.github.dan4ik95dv.app.ui.view;

import com.github.dan4ik95dv.app.model.user.User;

public interface MainMvpView extends MvpView {
    void showError();

    void fillHeaderView(User user);


}
