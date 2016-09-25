package com.github.dan4ik95dv.app.di.module.fragment;

import android.content.Context;

import com.github.dan4ik95dv.app.di.scope.fragment.AchievementsDepartmentsScope;
import com.github.dan4ik95dv.app.ui.presenter.AchievementsDepartmentsPresenter;

import dagger.Module;
import dagger.Provides;

@AchievementsDepartmentsScope
@Module
public class AchievementsDepartmentsModule {
    public Context context;

    public AchievementsDepartmentsModule(Context context) {
        this.context = context;
    }

    @Provides
    public AchievementsDepartmentsPresenter provideAchievementsDepartmentsPresenter() {
        return new AchievementsDepartmentsPresenter(context);
    }
}
