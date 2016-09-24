package com.github.dan4ik95dv.app.di.module.fragment;

import android.content.Context;

import com.github.dan4ik95dv.app.di.scope.fragment.TasksScope;
import com.github.dan4ik95dv.app.ui.presenter.CurrentTasksPresenter;

import dagger.Module;
import dagger.Provides;

@TasksScope
@Module
public class CurrentTasksModule {
    public Context context;

    public CurrentTasksModule(Context context) {
        this.context = context;
    }

    @Provides
    public CurrentTasksPresenter provideCurrentTasksPresenter() {
        return new CurrentTasksPresenter(context);
    }
}
