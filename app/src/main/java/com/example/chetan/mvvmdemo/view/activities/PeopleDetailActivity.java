package com.example.chetan.mvvmdemo.view.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Parcelable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.chetan.mvvmdemo.R;
import com.example.chetan.mvvmdemo.databinding.ActivityPeopleDetailBinding;
import com.example.chetan.mvvmdemo.model.People;
import com.example.chetan.mvvmdemo.viewmodel.PeopleDetailViewModel;
import com.example.chetan.mvvmdemo.viewmodel.PeopleDetailViewModelContract;

public class PeopleDetailActivity extends AppCompatActivity implements PeopleDetailViewModelContract.DetailView{

    private static final String EXTRA_PEOPLE = "EXTRA_PEOPLE";
    private ActivityPeopleDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_people_detail);
        setSupportActionBar(binding.toolbar);
        displayHomeAsUpEnable();
        getExtraFromIntent();
    }

    @Override
    public Context getContext() {
        return PeopleDetailActivity.this;
    }

    private void displayHomeAsUpEnable(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public static Intent launchDetail(Context context, People  people){
        Intent intent = new Intent(context,PeopleDetailActivity.class);
        intent.putExtra(EXTRA_PEOPLE, people);
        return intent;
    }

    private void getExtraFromIntent(){
        People people = (People) getIntent().getSerializableExtra(EXTRA_PEOPLE);
        PeopleDetailViewModel viewModel = new PeopleDetailViewModel(people);
        binding.setPeopleDetailViewModel(viewModel);
        setTitle(people.name.title + "." + people.name.firts + " " + people.name.last);
    }
}
