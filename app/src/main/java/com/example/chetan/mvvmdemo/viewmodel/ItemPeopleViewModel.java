package com.example.chetan.mvvmdemo.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.support.v7.view.menu.MenuView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.chetan.mvvmdemo.model.People;
import com.example.chetan.mvvmdemo.view.activities.PeopleDetailActivity;

/**
 * Created by chetan on 08/01/17.
 */

public class ItemPeopleViewModel extends BaseObservable {

    private People people;
    private Context context;

    public ItemPeopleViewModel(People people,Context context){
        this.people = people;
        this.context = context;
    }


    public String getFullName() {
        people.fullName =
                people.name.title + "." + people.name.firts + " " + people.name.last;
        return people.fullName;
    }

    public String getCell() {
        return people.cell;
    }

    public String getMail() {
        return people.mail;
    }

    public String getPictureProfile() {
        return people.picture.medium;
    }

    @BindingAdapter("imageUrl") public static void setImageUrl(ImageView imageView,String url){
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public void setPeople(People people){
        this.people = people;
        notifyChange();
    }

    public void onItemClick(View view){
        context.startActivity(PeopleDetailActivity.launchDetail(view.getContext(),people));
    }


}
