package com.github.dan4ik95dv.app.di.module;

import android.app.Application;
import android.content.Context;

import com.github.dan4ik95dv.app.application.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private Application mApp;

    public ApplicationModule(Application app) {
        mApp = app;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApp;
    }

    @Provides
    Context provideApplicationContext() {
        return App.getInstance();
    }

//    @Provides
//    @Singleton
//    RefWatcher provideRefWatcher() {
//        return LeakCanary.install(mApp);
//    }

}