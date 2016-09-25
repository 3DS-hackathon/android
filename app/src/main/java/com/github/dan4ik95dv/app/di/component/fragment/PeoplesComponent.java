package com.github.dan4ik95dv.app.di.component.fragment;

import com.github.dan4ik95dv.app.di.module.fragment.PeoplesModule;
import com.github.dan4ik95dv.app.di.scope.fragment.PeoplesScope;
import com.github.dan4ik95dv.app.ui.fragment.PeoplesFragment;

import dagger.Component;


@PeoplesScope
@Component(modules = PeoplesModule.class)
public interface PeoplesComponent {

    void inject(PeoplesFragment fragment);

}
