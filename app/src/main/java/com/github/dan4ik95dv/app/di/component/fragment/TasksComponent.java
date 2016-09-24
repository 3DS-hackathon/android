package com.github.dan4ik95dv.app.di.component.fragment;

import com.github.dan4ik95dv.app.di.module.fragment.TasksModule;
import com.github.dan4ik95dv.app.di.scope.fragment.TasksScope;
import com.github.dan4ik95dv.app.ui.fragment.TasksFragment;

import dagger.Component;


@TasksScope
@Component(modules = TasksModule.class)
public interface TasksComponent {

    void inject(TasksFragment fragment);

}
