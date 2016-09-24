package com.github.dan4ik95dv.app.di.component.activity;

import com.github.dan4ik95dv.app.di.module.activity.DepartmentModule;
import com.github.dan4ik95dv.app.di.scope.activity.DepartmentScope;
import com.github.dan4ik95dv.app.ui.activity.DepartmentActivity;
import com.github.dan4ik95dv.app.util.Progress;

import dagger.Component;


@DepartmentScope
@Component(modules = DepartmentModule.class)
public interface DepartmentComponent {

    Progress getProgress();

    void inject(DepartmentActivity activity);

}
