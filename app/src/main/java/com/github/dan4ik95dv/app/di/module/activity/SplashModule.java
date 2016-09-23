package com.github.dan4ik95dv.app.di.module.activity;

import android.content.Context;

import com.github.dan4ik95dv.app.di.scope.activity.SplashScope;
import com.github.dan4ik95dv.app.ui.presenter.SplashPresenter;

import dagger.Module;
import dagger.Provides;

@SplashScope
@Module
public class SplashModule {
    public Context context;

    public SplashModule(Context context) {
        this.context = context;
    }

    @Provides
    public SplashPresenter provideSplashPresenter() {
        return new SplashPresenter(context);
    }
}
