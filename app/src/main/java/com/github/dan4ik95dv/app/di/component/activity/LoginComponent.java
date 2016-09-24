package com.github.dan4ik95dv.app.di.component.activity;

import com.github.dan4ik95dv.app.di.module.activity.LoginModule;
import com.github.dan4ik95dv.app.di.scope.activity.LoginScope;
import com.github.dan4ik95dv.app.ui.activity.LoginActivity;
import com.github.dan4ik95dv.app.util.Progress;

import dagger.Component;


@LoginScope
@Component(modules = LoginModule.class)
public interface LoginComponent {

    Progress getProgress();

    void inject(LoginActivity activity);

}
