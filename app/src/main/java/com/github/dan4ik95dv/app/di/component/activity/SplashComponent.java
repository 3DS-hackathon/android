package com.github.dan4ik95dv.app.di.component.activity;

import com.github.dan4ik95dv.app.di.module.activity.SplashModule;
import com.github.dan4ik95dv.app.di.scope.activity.SplashScope;
import com.github.dan4ik95dv.app.ui.activity.SplashActivity;
import com.github.dan4ik95dv.app.util.Progress;

import dagger.Component;


@SplashScope
@Component(modules = SplashModule.class)
public interface SplashComponent {

    void inject(SplashActivity activity);

}
