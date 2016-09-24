package com.github.dan4ik95dv.app.di.component.activity;

import com.github.dan4ik95dv.app.di.module.activity.MainModule;
import com.github.dan4ik95dv.app.di.scope.activity.MainScope;
import com.github.dan4ik95dv.app.ui.activity.MainActivity;
import com.github.dan4ik95dv.app.util.Progress;

import dagger.Component;


@MainScope
@Component(modules = MainModule.class)
public interface MainComponent {

    Progress getProgress();

    void inject(MainActivity activity);

}
