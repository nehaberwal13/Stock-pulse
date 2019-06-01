
package com.app.criteria_parser.di.module;

import android.app.Application;
import android.content.Context;

import com.app.criteria_parser.data.AppDataManager;
import com.app.criteria_parser.data.DataManager;
import com.app.criteria_parser.data.model.Example;
import com.app.criteria_parser.data.remote.ApiHelper;
import com.app.criteria_parser.data.remote.AppApiHelper;
import com.app.criteria_parser.utils.rx.AppSchedulerProvider;
import com.app.criteria_parser.utils.rx.SchedulerProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {


    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }



    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }


    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }


    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }


}
