package com.github.dan4ik95dv.app.ui.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;

import com.github.dan4ik95dv.app.application.App;
import com.github.dan4ik95dv.app.io.api.ConnectionDetector;
import com.github.dan4ik95dv.app.io.api.RestInterface;
import com.github.dan4ik95dv.app.model.user.User;
import com.github.dan4ik95dv.app.ui.activity.BaseActivity;
import com.github.dan4ik95dv.app.ui.view.ProfileMvpView;
import com.github.dan4ik95dv.app.util.Constants;

import javax.inject.Inject;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfilePresenter implements Presenter<ProfileMvpView> {


    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    RestInterface restInterface;

    @Inject
    ConnectionDetector connectionDetector;
    @Inject
    Realm realm;
    BaseActivity activity;
    private User user;
    private String token;
    private ProfileMvpView profileMvpView;

    public ProfilePresenter(Context context) {
        App.getInstance().getClientComponent().inject(this);
        activity = (BaseActivity) context;
        token = sharedPreferences.getString(Constants.Configs.TOKEN, null);
        attachView((ProfileMvpView) context);

    }


    @Override
    public void attachView(ProfileMvpView view) {
        this.profileMvpView = view;
    }

    @Override
    public void detachView() {
        this.profileMvpView = null;
    }

    public void init() {
        updateUser();
    }

    public SwipeRefreshLayout.OnRefreshListener getSwipeRefreshLayoutListener() {
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateUser();
            }
        };
    }


    public void logout() {
        profileMvpView.showProgress();
        if (connectionDetector.isConnectingToInternet()) {
            sharedPreferences.edit().remove(Constants.Configs.TOKEN).apply();
            activity.nextToLoginActivity();
        }
    }

    public void updateUser() {
        profileMvpView.showProgress();
        if (connectionDetector.isConnectingToInternet()) {
            restInterface.getUser(token).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        user = response.body();
                        profileMvpView.fillUserProfile(user);
                        profileMvpView.fillHeaderView(user);
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(user);
                        realm.commitTransaction();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    if (profileMvpView != null) {
                        profileMvpView.userError();
                    }
                }
            });
        } else {
            user = realm.where(User.class).findFirst();
            if (user != null) {
                profileMvpView.fillUserProfile(user);
                profileMvpView.fillHeaderView(user);
            }
            profileMvpView.showError();
        }
    }

    public void showDepartment() {
        if (user != null) {
            activity.showCompanyActivity(user.getDepartment().getId());
        }
    }
}
