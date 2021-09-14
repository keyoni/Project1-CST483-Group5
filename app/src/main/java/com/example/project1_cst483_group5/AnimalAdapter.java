package com.example.project1_cst483_group5;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1_cst483_group5.db.Pet;
import com.example.project1_cst483_group5.db.PetViewModel;
import com.example.project1_cst483_group5.db.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter {

    private List<AnimalViewModel> animalViewModels = new ArrayList<>();
    private PetViewModel petVM;


    public AnimalAdapter(List<AnimalViewModel> animalViewModels, PetViewModel petViewModel) {
        this.animalViewModels = animalViewModels;
        this.petVM = petViewModel;
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
        ((AnimalViewHolder) holder).favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Favorited", Toast.LENGTH_SHORT).show();
                Pet tempPet = new Pet(1, ((AnimalViewHolder) holder).name.getText().toString(),
                        ((AnimalViewHolder) holder).type.getText().toString(),
                        ((AnimalViewHolder) holder).gender.getText().toString(),
                        Integer.parseInt(((AnimalViewHolder) holder).id.getText().toString()));

                petVM.insert(tempPet);
            }
        });

        ((AnimalViewHolder) holder).infoBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = Favorites.getIntent(getApplicationContext(),auth);
//                intent.putExtra("SEARCH_COM_PROJ1_G5_AUTH",auth);
//                startActivity(intent);
                Toast.makeText(view.getContext(), "CLICK ON INFO Model Page", Toast.LENGTH_SHORT).show();
            }
        });

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
