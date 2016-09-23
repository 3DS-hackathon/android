package com.github.dan4ik95dv.app.di.module;

import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.github.dan4ik95dv.app.BuildConfig;
import com.github.dan4ik95dv.app.io.api.ConnectionDetector;
import com.github.dan4ik95dv.app.model.realm.RealmDouble;
import com.github.dan4ik95dv.app.model.realm.RealmFloat;
import com.github.dan4ik95dv.app.model.realm.RealmInteger;
import com.github.dan4ik95dv.app.model.realm.RealmString;
import com.github.dan4ik95dv.app.util.Constants;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.RealmList;
import io.realm.RealmObject;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {

    public NetModule() {
    }


    @Provides
    @Singleton
    ConnectionDetector provideConnectionDetector(Context context) {
        return new ConnectionDetector(context);
    }


    @Provides
    @Singleton
    Gson provideGson() {
        Type tokenInteger = new TypeToken<RealmList<RealmInteger>>() {
        }.getType();
        Type tokenString = new TypeToken<RealmList<RealmString>>() {
        }.getType();
        Type tokenDouble = new TypeToken<RealmList<RealmDouble>>() {
        }.getType();
        Type tokenFloat = new TypeToken<RealmList<RealmFloat>>() {
        }.getType();

        return new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })

                .registerTypeAdapter(tokenInteger, new TypeAdapter<RealmList<RealmInteger>>() {
                    @Override
                    public void write(JsonWriter out, RealmList<RealmInteger> value) throws IOException {
                        // Ignore
                    }

                    @Override
                    public RealmList<RealmInteger> read(JsonReader in) throws IOException {
                        RealmList<RealmInteger> list = new RealmList<>();
                        in.beginArray();
                        while (in.hasNext()) {
                            list.add(new RealmInteger(in.nextInt()));
                        }
                        in.endArray();
                        return list;
                    }
                })
                .registerTypeAdapter(tokenString, new TypeAdapter<RealmList<RealmString>>() {
                    @Override
                    public void write(JsonWriter out, RealmList<RealmString> value) throws IOException {
                        // Ignore
                    }

                    @Override
                    public RealmList<RealmString> read(JsonReader in) throws IOException {
                        RealmList<RealmString> list = new RealmList<>();
                        in.beginArray();
                        while (in.hasNext()) {
                            list.add(new RealmString(in.nextString()));
                        }
                        in.endArray();
                        return list;
                    }
                })
                .registerTypeAdapter(tokenDouble, new TypeAdapter<RealmList<RealmDouble>>() {
                    @Override
                    public void write(JsonWriter out, RealmList<RealmDouble> value) throws IOException {
                        // Ignore
                    }

                    @Override
                    public RealmList<RealmDouble> read(JsonReader in) throws IOException {
                        RealmList<RealmDouble> list = new RealmList<>();
                        in.beginArray();
                        while (in.hasNext()) {
                            list.add(new RealmDouble(in.nextDouble()));
                        }
                        in.endArray();
                        return list;
                    }
                })
                .registerTypeAdapter(tokenFloat, new TypeAdapter<RealmList<RealmFloat>>() {
                    @Override
                    public void write(JsonWriter out, RealmList<RealmFloat> value) throws IOException {
                        // Ignore
                    }

                    @Override
                    public RealmList<RealmFloat> read(JsonReader in) throws IOException {
                        RealmList<RealmFloat> list = new RealmList<>();
                        in.beginArray();
                        while (in.hasNext()) {
                            list.add(new RealmFloat((float) in.nextDouble()));
                        }
                        in.endArray();
                        return list;
                    }
                })
                .create();
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(Context context) {
        File httpCacheDirectory = new File(context.getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        return new Cache(httpCacheDirectory, cacheSize);
    }


    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        return new OkHttpClient().newBuilder()
                //.cache(cache)
                .connectTimeout(Constants.Api.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.Api.READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constants.Api.WRITE_TIMEOUT, TimeUnit.SECONDS)
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
    }


    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.DEBUG ? Constants.Api.API_URL_DEV : Constants.Api.API_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }


}
