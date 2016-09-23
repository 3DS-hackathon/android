package com.github.dan4ik95dv.app.di.component;


import com.github.dan4ik95dv.app.di.module.ApplicationModule;
import com.github.dan4ik95dv.app.di.module.StorageModule;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {ApplicationModule.class, StorageModule.class})
@Singleton
public interface ApplicationComponent {

}
