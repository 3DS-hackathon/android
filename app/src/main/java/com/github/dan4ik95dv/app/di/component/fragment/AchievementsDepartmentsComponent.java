package com.github.dan4ik95dv.app.di.component.fragment;

import com.github.dan4ik95dv.app.di.module.fragment.AchievementsDepartmentsModule;
import com.github.dan4ik95dv.app.di.scope.fragment.AchievementsDepartmentsScope;
import com.github.dan4ik95dv.app.di.scope.fragment.AchievementsScope;
import com.github.dan4ik95dv.app.ui.fragment.AchievementsDepartmentsFragment;
import com.github.dan4ik95dv.app.ui.fragment.AchievementsFragment;

import dagger.Component;


@AchievementsDepartmentsScope
@Component(modules = AchievementsDepartmentsModule.class)
public interface AchievementsDepartmentsComponent {

    void inject(AchievementsDepartmentsFragment fragment);

}
