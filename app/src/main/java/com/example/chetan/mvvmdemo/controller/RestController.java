package com.example.chetan.mvvmdemo.controller;

import android.content.Context;
import android.view.View;

import com.example.chetan.mvvmdemo.R;
import com.example.chetan.mvvmdemo.api.PeopleResponse;
import com.example.chetan.mvvmdemo.application.PeopleApplication;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by chetan on 08/01/17.
 */

public class RestController {

    private PeopleApplication application;
    private Subscription subscription;
    private PeopleFetch peopleFetch;

    public RestController(Context context){
        application = PeopleApplication.create(context);
    }

    public void setFetchPeople(PeopleFetch peopleFetch){
        this.peopleFetch = peopleFetch;
    }

    public Subscription fetchPeople(String url){
        subscription = application.getPeopleService().fetchPeople(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(application.subscribeScheduler())
                .subscribe(new Action1<PeopleResponse>() {
                    @Override
                    public void call(PeopleResponse peopleResponse) {
                        peopleFetch.onSuccess(peopleResponse);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        peopleFetch.onFailure(throwable);
                    }
                });

        return subscription;
    }


    public interface PeopleFetch{
        void onSuccess(PeopleResponse peopleResponse);
        void onFailure(Throwable throwable);
    }

}
