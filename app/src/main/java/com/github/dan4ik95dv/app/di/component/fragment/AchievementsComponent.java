package com.github.dan4ik95dv.app.di.component.fragment;

import com.github.dan4ik95dv.app.di.module.fragment.AchievementsModule;
import com.github.dan4ik95dv.app.di.scope.fragment.AchievementsScope;
import com.github.dan4ik95dv.app.ui.fragment.AchievementsFragment;

import dagger.Component;


@AchievementsScope
@Component(modules = AchievementsModule.class)
public interface AchievementsComponent {

    void inject(AchievementsFragment fragment);

}
