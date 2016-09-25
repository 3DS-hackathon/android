package com.github.dan4ik95dv.app.di.module.fragment;

import android.content.Context;

import com.github.dan4ik95dv.app.di.scope.fragment.PeoplesScope;
import com.github.dan4ik95dv.app.di.scope.fragment.TasksScope;
import com.github.dan4ik95dv.app.ui.presenter.PeoplesPresenter;
import com.github.dan4ik95dv.app.ui.presenter.TasksPresenter;

import dagger.Module;
import dagger.Provides;

@PeoplesScope
@Module
public class PeoplesModule {
    public Context context;

    public PeoplesModule(Context context) {
        this.context = context;
    }

    @Provides
    public PeoplesPresenter providePeoplesPresenter() {
        return new PeoplesPresenter(context);
    }
}
