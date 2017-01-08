package com.example.chetan.mvvmdemo.view.activities;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.chetan.mvvmdemo.R;
import com.example.chetan.mvvmdemo.databinding.ActivityPeopleBinding;
import com.example.chetan.mvvmdemo.model.People;
import com.example.chetan.mvvmdemo.view.adapters.PeopleAdapter;
import com.example.chetan.mvvmdemo.viewmodel.PeopleViewModel;
import com.example.chetan.mvvmdemo.viewmodel.PeopleViewModelContract;

import java.util.List;

public class PeopleActivity extends AppCompatActivity implements PeopleViewModelContract.MainView{

    ActivityPeopleBinding binding;
    PeopleViewModel viewModel;
    PeopleViewModelContract.MainView mainView = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        setSupportActionBar(binding.toolbar);
        setupListPeopleView(binding.listPeople);
    }

    private void initBinding(){
        binding = DataBindingUtil.setContentView(this,R.layout.activity_people);
        viewModel = new PeopleViewModel(mainView,getContect());
        binding.setMainViewModel(viewModel);
    }

    private void setupListPeopleView(RecyclerView listPeople){
        PeopleAdapter adapter = new PeopleAdapter();
        listPeople.setAdapter(adapter);
        listPeople.setLayoutManager(new LinearLayoutManager(getContect()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.destroy();
    }

    @Override
    public Context getContect() {
        return PeopleActivity.this;
    }

    @Override
    public void loadData(List<People> people) {
        PeopleAdapter adapter = (PeopleAdapter) binding.listPeople.getAdapter();
        adapter.setPeopleList(people);
    }
}
