package com.example.chetan.mvvmdemo.application;

import android.app.Application;
import android.content.Context;

import com.example.chetan.mvvmdemo.api.PeopleFactory;
import com.example.chetan.mvvmdemo.api.PeopleService;

import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by chetan on 08/01/17.
 */

public class PeopleApplication extends Application {

    private PeopleService peopleService;
    private Scheduler scheduler;

    private static PeopleApplication get(Context context){
        return (PeopleApplication) context.getApplicationContext();
    }

    public static PeopleApplication create(Context context){
        return get(context);
    }

    public PeopleService getPeopleService() {
        if(peopleService == null) peopleService = PeopleFactory.create();
        return peopleService;
    }

    public void setPeopleService(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    public Scheduler subscribeScheduler(){
        if(scheduler == null) scheduler = Schedulers.io();
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler){
        this.scheduler = scheduler;
    }
}
