package com.github.dan4ik95dv.app.di.component.activity;

import com.github.dan4ik95dv.app.di.module.activity.AddRequestModule;
import com.github.dan4ik95dv.app.di.module.activity.MoreTaskModule;
import com.github.dan4ik95dv.app.di.scope.activity.MoreTaskScope;
import com.github.dan4ik95dv.app.ui.activity.AddRequestActivity;
import com.github.dan4ik95dv.app.ui.activity.MoreTaskActivity;
import com.github.dan4ik95dv.app.util.Progress;

import dagger.Component;


@MoreTaskScope
@Component(modules = AddRequestModule.class)
public interface AddRequestComponent {

    Progress getProgress();

    void inject(AddRequestActivity activity);

}
