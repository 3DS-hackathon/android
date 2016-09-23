package com.github.dan4ik95dv.app.di.module.activity;

import android.content.Context;

import com.github.dan4ik95dv.app.di.scope.activity.MainScope;
import com.github.dan4ik95dv.app.ui.presenter.MainPresenter;

import dagger.Module;
import dagger.Provides;

@MainScope
@Module
public class MainModule {
    public Context context;

    public MainModule(Context context) {
        this.context = context;
    }

    @Provides
    public MainPresenter provideMainPresenter() {
        return new MainPresenter(context);
    }
}
