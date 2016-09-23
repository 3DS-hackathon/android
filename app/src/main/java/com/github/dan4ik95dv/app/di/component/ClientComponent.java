package com.github.dan4ik95dv.app.di.component;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.github.dan4ik95dv.app.di.module.ApplicationModule;
import com.github.dan4ik95dv.app.di.module.BodyResponse;
import com.github.dan4ik95dv.app.di.module.ClientModule;
import com.github.dan4ik95dv.app.di.module.NetModule;
import com.github.dan4ik95dv.app.di.module.StorageModule;
import com.github.dan4ik95dv.app.io.api.ConnectionDetector;
import com.github.dan4ik95dv.app.io.api.RestInterface;
import com.github.dan4ik95dv.app.ui.presenter.SplashPresenter;
import com.google.android.gms.analytics.Tracker;
import com.google.gson.Gson;

import java.util.HashMap;

import javax.inject.Singleton;

import dagger.Component;
import io.realm.Realm;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {ClientModule.class, ApplicationModule.class, NetModule.class, StorageModule.class})
public interface ClientComponent {

    SharedPreferences getSharedPreferences();

    Application getApplication();

    Context getApplicationContext();

    RestInterface getRestInterface();

    Gson getGson();

    Cache getOkHttpCache();

    OkHttpClient OkHttpClient();

    Retrofit getRetrofit();

    Converter<ResponseBody, BodyResponse> getConverter();

    Realm getDefaultRealm();

    ConnectionDetector getConnectionDetector();

    /*Inject presenters*/
    void inject(SplashPresenter presenter);


}
