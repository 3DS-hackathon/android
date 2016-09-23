package com.github.dan4ik95dv.app.di.module;


import com.github.dan4ik95dv.app.io.api.RestInterface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ClientModule {

    public ClientModule() {
    }

    @Singleton
    @Provides
    public RestInterface providesRestInterface(Retrofit retrofit) {
        return retrofit.create(RestInterface.class);
    }

}
