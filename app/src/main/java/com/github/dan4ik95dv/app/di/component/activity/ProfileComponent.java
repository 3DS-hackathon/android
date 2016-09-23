package com.github.dan4ik95dv.app.di.component.activity;

import com.github.dan4ik95dv.app.di.module.activity.ProfileModule;
import com.github.dan4ik95dv.app.di.scope.activity.ProfileScope;
import com.github.dan4ik95dv.app.ui.activity.ProfileActivity;

import dagger.Component;


@ProfileScope
@Component(modules = ProfileModule.class)
public interface ProfileComponent {

    void inject(ProfileActivity activity);

}
