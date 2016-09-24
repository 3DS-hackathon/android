package com.github.dan4ik95dv.app.di.component.fragment;

import com.github.dan4ik95dv.app.di.module.fragment.CurrentTasksModule;
import com.github.dan4ik95dv.app.di.scope.fragment.CurrentTasksScope;
import com.github.dan4ik95dv.app.ui.fragment.CurrentTasksFragment;

import dagger.Component;


@CurrentTasksScope
@Component(modules = CurrentTasksModule.class)
public interface CurrentTasksComponent {

    void inject(CurrentTasksFragment fragment);

}
