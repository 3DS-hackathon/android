package com.github.dan4ik95dv.app.di.module.fragment;

import android.content.Context;

import com.github.dan4ik95dv.app.di.scope.fragment.TasksScope;
import com.github.dan4ik95dv.app.ui.presenter.TasksPresenter;

import dagger.Module;
import dagger.Provides;

@TasksScope
@Module
public class TasksModule {
    public Context context;

    public TasksModule(Context context) {
        this.context = context;
    }

    @Provides
    public TasksPresenter provideTasksPresenter() {
        return new TasksPresenter(context);
    }
}
