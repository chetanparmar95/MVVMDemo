package com.example.chetan.mvvmdemo.view.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chetan.mvvmdemo.R;
import com.example.chetan.mvvmdemo.databinding.ItemPeopleBinding;
import com.example.chetan.mvvmdemo.model.People;
import com.example.chetan.mvvmdemo.viewmodel.ItemPeopleViewModel;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by chetan on 08/01/17.
 */

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleAdapterViewHolder> {

    private List<People> peopleList;

    public PeopleAdapter(){
        this.peopleList = Collections.emptyList();
    }

    @Override
    public PeopleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemPeopleBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_people,parent,false);
        return new PeopleAdapterViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(PeopleAdapterViewHolder holder, int position) {
        holder.bindPeople(peopleList.get(position));
    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }

    public void setPeopleList(List<People> peopleList){
        this.peopleList = peopleList;
        notifyDataSetChanged();
    }

    public static class PeopleAdapterViewHolder extends RecyclerView.ViewHolder{

        ItemPeopleBinding binding;
        public PeopleAdapterViewHolder(ItemPeopleBinding binding) {
            super(binding.itemPeople);
            this.binding = binding;
        }

        void bindPeople(People people){
            if(binding.getPeopleViewModel() == null){
                binding.setPeopleViewModel(new ItemPeopleViewModel(people,itemView.getContext()));
            }else{
                binding.getPeopleViewModel().setPeople(people);
            }
        }
    }
}
