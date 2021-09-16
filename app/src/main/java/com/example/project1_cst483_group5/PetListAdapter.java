package com.example.project1_cst483_group5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1_cst483_group5.db.Pet;
import com.example.project1_cst483_group5.db.PetViewModel;

import java.util.ArrayList;
import java.util.List;

public class PetListAdapter extends RecyclerView.Adapter {

    private List<PetListViewModel> petListViewModels = new ArrayList<>();
    private PetViewModel petVM;


    public PetListAdapter(List<PetListViewModel> petViewModels, PetViewModel petViewModel) {
        this.petListViewModels = petViewModels;
        this.petVM = petViewModel;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new PetListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((PetListViewHolder) holder).bindData(petListViewModels.get(position));
   
    }

    @Override
    public int getItemCount() {
        return petListViewModels.size();
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.favorites_item;
    }
}

