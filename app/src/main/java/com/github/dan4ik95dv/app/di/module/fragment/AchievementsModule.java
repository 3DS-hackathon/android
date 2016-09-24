package com.github.dan4ik95dv.app.di.module.fragment;

import android.content.Context;

import com.github.dan4ik95dv.app.di.scope.fragment.AchievementsScope;
import com.github.dan4ik95dv.app.ui.presenter.AchievementsPresenter;

import dagger.Module;
import dagger.Provides;

@AchievementsScope
@Module
public class AchievementsModule {
    public Context context;

    public AchievementsModule(Context context) {
        this.context = context;
    }

    @Provides
    public AchievementsPresenter provideAchievementsPresenter() {
        return new AchievementsPresenter(context);
    }
}
