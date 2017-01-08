package com.example.chetan.mvvmdemo.viewmodel;

import android.content.Context;

import com.example.chetan.mvvmdemo.model.People;

import java.util.List;

/**
 * Created by chetan on 08/01/17.
 */

public interface PeopleViewModelContract {
    interface MainView{
        Context getContect();
        void loadData(List<People> people);
    }

    interface ViewModel{
        void destroy();
    }
}
