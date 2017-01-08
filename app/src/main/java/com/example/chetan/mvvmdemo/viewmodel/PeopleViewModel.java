package com.example.chetan.mvvmdemo.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.chetan.mvvmdemo.R;
import com.example.chetan.mvvmdemo.api.PeopleResponse;
import com.example.chetan.mvvmdemo.api.PeopleService;
import com.example.chetan.mvvmdemo.application.PeopleApplication;
import com.example.chetan.mvvmdemo.controller.RestController;
import com.google.common.base.Throwables;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by chetan on 08/01/17.
 */

public class PeopleViewModel implements PeopleViewModelContract.ViewModel , RestController.PeopleFetch{

    public ObservableInt peopleProgress;
    public ObservableInt peopleList;
    public ObservableInt peopleLabel;
    public ObservableField<String> messageLabel;

    private PeopleViewModelContract.MainView mainView;
    private Context context;
    private Subscription subscription;

    private RestController controller;

    public PeopleViewModel(@NonNull PeopleViewModelContract.MainView mainView, @NonNull Context context){
        this.mainView = mainView;
        this.context = context;
        this.peopleProgress = new ObservableInt(View.GONE);
        this.peopleList = new ObservableInt(View.GONE);
        this.peopleLabel = new ObservableInt(View.VISIBLE);
        this.messageLabel = new ObservableField<>(context.getString(R.string.default_loading_people));
        this.controller = new RestController(this.context);
    }

    public void onClickFabLoad(View view){
        initViews();
        fetchPeople();
    }

    private void initViews(){
        this.peopleLabel.set(View.GONE);
        this.peopleList.set(View.GONE);
        this.peopleProgress.set(View.VISIBLE);
    }

    private void fetchPeople(){
        //api call
        final String URL = "http://api.randomuser.me/?results=10&nat=en";
        unSubscribeFromObservable();
        controller.setFetchPeople(this);
        subscription  = controller.fetchPeople(URL);
    }

    @Override
    public void destroy() {
        reset();
    }

    private void unSubscribeFromObservable(){
        if(subscription != null && !subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }
    private void reset(){
        unSubscribeFromObservable();
        this.mainView = null;
        this.context = null;
        this.subscription = null;
    }


    @Override
    public void onSuccess(PeopleResponse peopleResponse) {
        peopleProgress.set(View.GONE);
        peopleLabel.set(View.GONE);
        peopleList.set(View.VISIBLE);
        if(mainView != null){
            mainView.loadData(peopleResponse.getPeopleList());
        }
    }

    @Override
    public void onFailure(Throwable throwable) {
        throwable.printStackTrace();
        messageLabel.set(context.getString(R.string.error_loading_people));
        peopleProgress.set(View.GONE);
        peopleLabel.set(View.VISIBLE);
        peopleList.set(View.GONE);
    }
}
