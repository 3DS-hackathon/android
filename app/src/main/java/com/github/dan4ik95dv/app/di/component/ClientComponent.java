package com.github.dan4ik95dv.app.di.component;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.github.dan4ik95dv.app.di.module.ApplicationModule;
import com.github.dan4ik95dv.app.di.module.ClientModule;
import com.github.dan4ik95dv.app.di.module.NetModule;
import com.github.dan4ik95dv.app.di.module.StorageModule;
import com.github.dan4ik95dv.app.io.api.ConnectionDetector;
import com.github.dan4ik95dv.app.io.api.RestInterface;
import com.github.dan4ik95dv.app.ui.presenter.AchievementsDepartmentsPresenter;
import com.github.dan4ik95dv.app.ui.presenter.AchievementsPresenter;
import com.github.dan4ik95dv.app.ui.presenter.AddRequestPresenter;
import com.github.dan4ik95dv.app.ui.presenter.CurrentTasksPresenter;
import com.github.dan4ik95dv.app.ui.presenter.DepartmentPresenter;
import com.github.dan4ik95dv.app.ui.presenter.LoginPresenter;
import com.github.dan4ik95dv.app.ui.presenter.MainPresenter;
import com.github.dan4ik95dv.app.ui.presenter.MoreTaskPresenter;
import com.github.dan4ik95dv.app.ui.presenter.PeoplesPresenter;
import com.github.dan4ik95dv.app.ui.presenter.ProfilePresenter;
import com.github.dan4ik95dv.app.ui.presenter.SplashPresenter;
import com.github.dan4ik95dv.app.ui.presenter.TasksPresenter;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import io.realm.Realm;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
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


    Realm getDefaultRealm();

    ConnectionDetector getConnectionDetector();

    /*Inject presenters*/
    void inject(SplashPresenter presenter);

    void inject(LoginPresenter presenter);

    void inject(MainPresenter presenter);

    void inject(ProfilePresenter presenter);

    void inject(TasksPresenter presenter);

    void inject(CurrentTasksPresenter presenter);

    void inject(AchievementsDepartmentsPresenter presenter);

    void inject(AchievementsPresenter presenter);

    void inject(MoreTaskPresenter presenter);

    void inject(AddRequestPresenter presenter);

    void inject(DepartmentPresenter presenter);

    void inject(PeoplesPresenter presenter);

}
