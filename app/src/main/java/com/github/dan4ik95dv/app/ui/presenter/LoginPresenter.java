package com.github.dan4ik95dv.app.ui.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.github.dan4ik95dv.app.application.App;
import com.github.dan4ik95dv.app.io.api.RestInterface;
import com.github.dan4ik95dv.app.model.user.LoginRequest;
import com.github.dan4ik95dv.app.model.user.Token;
import com.github.dan4ik95dv.app.ui.activity.LoginActivity;
import com.github.dan4ik95dv.app.ui.view.LoginMvpView;
import com.github.dan4ik95dv.app.util.Constants;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Inject;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginPresenter implements Presenter<LoginMvpView> {

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    RestInterface restInterface;

    @Inject
    Realm realm;

    private LoginActivity loginActivity;
    private LoginMvpView loginMvpView;

    public LoginPresenter(Context context) {
        App.getInstance().getClientComponent().inject(this);
        this.loginActivity = (LoginActivity) context;
        attachView((LoginMvpView) context);
    }


    @Override
    public void attachView(LoginMvpView view) {
        this.loginMvpView = view;
    }

    @Override
    public void detachView() {
        this.loginMvpView = null;
    }

    public void init() {

    }

    public void login(String email, String password) {
        restInterface.login(new LoginRequest(email, password)).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (response.isSuccessful() && response.body() != null) {
                    sharedPreferences.edit().putString(Constants.Configs.TOKEN, response.body().getToken()).apply();
                    loginActivity.nextToMainActivity();
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                if (loginMvpView != null)
                    loginMvpView.showError();
            }
        });
    }
}
