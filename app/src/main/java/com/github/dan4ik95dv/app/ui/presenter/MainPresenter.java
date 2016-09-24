package com.github.dan4ik95dv.app.ui.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.github.dan4ik95dv.app.application.App;
import com.github.dan4ik95dv.app.io.api.RestInterface;
import com.github.dan4ik95dv.app.model.user.Level;
import com.github.dan4ik95dv.app.model.user.User;
import com.github.dan4ik95dv.app.ui.view.MainMvpView;
import com.github.dan4ik95dv.app.util.Constants;

import javax.inject.Inject;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainPresenter implements Presenter<MainMvpView> {

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    RestInterface restInterface;

    @Inject
    Realm realm;


    private String token;
    private MainMvpView mainMvpView;

    public MainPresenter(Context context) {
        App.getInstance().getClientComponent().inject(this);
        token = sharedPreferences.getString(Constants.Configs.TOKEN, null);
        attachView((MainMvpView) context);
    }


    @Override
    public void attachView(MainMvpView view) {
        this.mainMvpView = view;
    }

    @Override
    public void detachView() {
        this.mainMvpView = null;
    }

    public void init() {
    }

}
