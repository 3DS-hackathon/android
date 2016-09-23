package com.github.dan4ik95dv.app.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module(includes = ApplicationModule.class)
public class StorageModule {

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Context applicationContext) {
        return PreferenceManager.getDefaultSharedPreferences(applicationContext);
    }

    @Provides
    Realm provideDefaultRealm() {
        return Realm.getDefaultInstance();
    }


}
