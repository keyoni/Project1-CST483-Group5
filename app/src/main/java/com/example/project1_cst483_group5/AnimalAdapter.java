package com.example.project1_cst483_group5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter {

    private List<AnimalViewModel> animalViewModels = new ArrayList<>();

    public AnimalAdapter(List<AnimalViewModel> animalViewModels) {
        this.animalViewModels = animalViewModels;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new AnimalViewHolder(view);
}

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((AnimalViewHolder) holder).bindData(animalViewModels.get(position));

    }

    @Override
    public int getItemCount() {
        return animalViewModels.size();
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.search_item;
    }
}