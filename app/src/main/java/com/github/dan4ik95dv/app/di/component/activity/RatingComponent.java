package com.github.dan4ik95dv.app.di.component.activity;

import com.github.dan4ik95dv.app.di.module.activity.RatingModule;
import com.github.dan4ik95dv.app.di.scope.activity.RatingScope;
import com.github.dan4ik95dv.app.ui.activity.RatingActivity;

import dagger.Component;


@RatingScope
@Component(modules = RatingModule.class)
public interface RatingComponent {


    void inject(RatingActivity activity);

}
