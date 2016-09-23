package com.github.dan4ik95dv.app.di.module.activity;

import android.content.Context;

import com.github.dan4ik95dv.app.di.scope.activity.ProfileScope;
import com.github.dan4ik95dv.app.ui.presenter.ProfilePresenter;

import dagger.Module;
import dagger.Provides;

@ProfileScope
@Module
public class ProfileModule {
    public Context context;

    public ProfileModule(Context context) {
        this.context = context;
    }

    @Provides
    public ProfilePresenter provideProfilePresenter() {
        return new ProfilePresenter(context);
    }
}
