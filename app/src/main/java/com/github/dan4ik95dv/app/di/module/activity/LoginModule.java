package com.github.dan4ik95dv.app.di.module.activity;

import android.content.Context;

import com.github.dan4ik95dv.app.di.scope.activity.LoginScope;
import com.github.dan4ik95dv.app.ui.presenter.LoginPresenter;

import dagger.Module;
import dagger.Provides;

@LoginScope
@Module
public class LoginModule {
    public Context context;

    public LoginModule(Context context) {
        this.context = context;
    }

    @Provides
    public LoginPresenter provideLoginPresenter() {
        return new LoginPresenter(context);
    }
}
