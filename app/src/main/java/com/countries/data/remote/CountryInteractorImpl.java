package com.countries.data.remote;

import android.app.Application;

import com.countries.data.model.Country;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * @author Tosin Onikute.
 *
 * This is a Data Manager implementer class which contains methods, exposed for all the countries related data handling operations
 * Main purpose of this is to decouple your class, thus making it cleaner and testable.
 *
 * fetchCountries method is responsible for handling data retrieval for countries by Filter
 *
 * fetchCountryByAlpha method is responsible for handling data retrieval for a particular country with Alpha3code
 *
 */

public class CountryInteractorImpl implements CountryInteractor {

    private final Application application;

    public CountryInteractorImpl(Application application) {
        this.application = application;
    }

    public Observable<List<Country>> fetchCountries(CountryInterface countryInterface){

            return countryInterface.getCountryByFilter()
                    .flatMap(new Func1<List<Country>, Observable<List<Country>>>() {
                        @Override
                        public Observable<List<Country>> call(List<Country> countries) {
                            return Observable.just(countries);
                        }
                    })
                    .onErrorReturn(new Func1<Throwable, List<Country>>() {
                        @Override
                        public List<Country> call(Throwable thr) {
                            return null;
                        }
                    });

    }


    public Observable<Country> fetchCountryByAlpha(CountryInterface countryInterface, String alpha3code){

        return countryInterface.getCountryByAlpha(alpha3code)
                .flatMap(new Func1<Country, Observable<Country>>() {
                    @Override
                    public Observable<Country> call(Country countries) {
                        return Observable.just(countries);
                    }
                })
                .onErrorReturn(new Func1<Throwable, Country>() {
                    @Override
                    public Country call(Throwable thr) {
                        return null;
                    }
                });

    }






}
