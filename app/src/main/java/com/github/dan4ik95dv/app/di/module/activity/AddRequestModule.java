package com.github.dan4ik95dv.app.di.module.activity;

import android.content.Context;

import com.github.dan4ik95dv.app.di.scope.activity.AddRequestScope;
import com.github.dan4ik95dv.app.di.scope.activity.MoreTaskScope;
import com.github.dan4ik95dv.app.ui.presenter.AddRequestPresenter;
import com.github.dan4ik95dv.app.ui.presenter.MoreTaskPresenter;

import dagger.Module;
import dagger.Provides;

@AddRequestScope
@Module
public class AddRequestModule {
    public Context context;

    public AddRequestModule(Context context) {
        this.context = context;
    }

    @Provides
    public AddRequestPresenter provideAddRequestPresenter() {
        return new AddRequestPresenter(context);
    }
}
