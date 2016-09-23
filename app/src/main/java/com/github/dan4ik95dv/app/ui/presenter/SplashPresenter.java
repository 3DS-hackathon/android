package com.github.dan4ik95dv.app.ui.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;

import com.github.dan4ik95dv.app.application.App;
import com.github.dan4ik95dv.app.di.module.BodyResponse;
import com.github.dan4ik95dv.app.io.api.ConnectionDetector;
import com.github.dan4ik95dv.app.io.api.RestInterface;
import com.github.dan4ik95dv.app.ui.view.SplashMvpView;
import com.github.dan4ik95dv.app.util.AndroidUtils;
import com.github.dan4ik95dv.app.util.Constants;

import javax.inject.Inject;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;


public class SplashPresenter implements Presenter<SplashMvpView> {

    @Inject
    Realm realm;
    @Inject
    SharedPreferences sharedPreferences;
    @Inject
    RestInterface restInterface;
    @Inject
    Converter<okhttp3.ResponseBody, BodyResponse> converter;
    @Inject
    ConnectionDetector connectionDetector;

    private SplashMvpView splashMvpView;
    private Context context;
    private String token;
    private Boolean startFirst;
    private Bundle bundle = null;
    Callback callback = new Callback() {
        @Override
        public void onResponse(Call call, Response response) {
            if (response.isSuccessful() && response.body() != null) {
                if (response.body() instanceof BodyResponse) {
                    if (((BodyResponse) response.body()).getHttpCode() == 200) {
                        splashMvpView.startApp(bundle);
                    }
                }
            }
        }

        @Override
        public void onFailure(Call call, Throwable t) {
            if (!TextUtils.isEmpty(token)) {
                if (splashMvpView != null)
                    splashMvpView.startOffline(bundle);

            } else {
                if (splashMvpView != null)
                    splashMvpView.showError();
            }
        }

    };

    public SplashPresenter(Context context) {
        this.context = context;
        App.getInstance().getClientComponent().inject(this);
        this.token = sharedPreferences.getString(Constants.Configs.TOKEN, "");
        attachView((SplashMvpView) context);
    }

    @Override
    public void attachView(SplashMvpView view) {
        this.splashMvpView = view;
    }

    @Override
    public void detachView() {
        this.splashMvpView = null;
        realm.close();
    }

    public void init() {

        if (connectionDetector.isConnectingToInternet()) {
                String deviceId = AndroidUtils.getDeviceId(context);
//                restInterface.deviceRegister(Constants.Api.CITY,
//                        deviceId,
//                        Constants.Api.DEVICE_TYPE,
//                        AndroidUtils.getAppVersion(context)).enqueue(callback);
        } else if (!TextUtils.isEmpty(token)) {
            if (splashMvpView != null)
                splashMvpView.startOffline(bundle);
        } else {
            if (splashMvpView != null)
                splashMvpView.showError();
        }
    }


    public void resetTokenDevice() {
        sharedPreferences.edit().putString(Constants.Configs.TOKEN, "").apply();
        token = "";
        init();
    }
}
