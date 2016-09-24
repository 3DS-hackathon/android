package com.github.dan4ik95dv.app.di.module.activity;

import android.content.Context;

import com.github.dan4ik95dv.app.di.scope.activity.MoreTaskScope;
import com.github.dan4ik95dv.app.ui.presenter.MoreTaskPresenter;
import com.github.dan4ik95dv.app.util.Progress;

import dagger.Module;
import dagger.Provides;

@MoreTaskScope
@Module
public class MoreTaskModule {
    public Context context;

    public MoreTaskModule(Context context) {
        this.context = context;
    }

    @Provides
    public Progress provideProgress() {
        return new Progress(context);
    }

    @Provides
    public MoreTaskPresenter provideMoreTaskPresenter() {
        return new MoreTaskPresenter(context);
    }
}
