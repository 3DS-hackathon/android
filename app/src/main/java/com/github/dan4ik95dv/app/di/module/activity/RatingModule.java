package com.github.dan4ik95dv.app.di.module.activity;

import android.content.Context;

import com.github.dan4ik95dv.app.di.scope.activity.RatingScope;
import com.github.dan4ik95dv.app.ui.presenter.RatingPresenter;
import com.github.dan4ik95dv.app.util.Progress;

import dagger.Module;
import dagger.Provides;

@RatingScope
@Module
public class RatingModule {
    public Context context;

    public RatingModule(Context context) {
        this.context = context;
    }

    @Provides
    public Progress provideProgress() {
        return new Progress(context);
    }

    @Provides
    public RatingPresenter provideRatingPresenter() {
        return new RatingPresenter(context);
    }
}
