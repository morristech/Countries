package com.countries.di.module;

import android.app.Application;

import com.countries.data.remote.CountryFetcher;
import com.countries.data.remote.CountryFetcherImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Tosin Onikute.
 */

@Module
public class AppModule {

    Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }

    @Provides
    CountryFetcher provideDataManager(CountryFetcherImpl appDataManager) {
        return appDataManager;
    }
}