package com.github.dan4ik95dv.app.di.module.activity;

import android.content.Context;

import com.github.dan4ik95dv.app.di.scope.activity.DepartmentScope;
import com.github.dan4ik95dv.app.ui.presenter.DepartmentPresenter;
import com.github.dan4ik95dv.app.util.Progress;

import dagger.Module;
import dagger.Provides;

@DepartmentScope
@Module
public class DepartmentModule {
    public Context context;

    public DepartmentModule(Context context) {
        this.context = context;
    }

    @Provides
    public Progress provideProgress() {
        return new Progress(context);
    }

    @Provides
    public DepartmentPresenter provideDepartmentPresenter() {
        return new DepartmentPresenter(context);
    }
}
