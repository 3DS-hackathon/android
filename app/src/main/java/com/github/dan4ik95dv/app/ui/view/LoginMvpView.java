package com.github.dan4ik95dv.app.ui.view;

public interface LoginMvpView extends MvpView {
    void showError();

    void tokenError();

    void tokenResponse();
}
