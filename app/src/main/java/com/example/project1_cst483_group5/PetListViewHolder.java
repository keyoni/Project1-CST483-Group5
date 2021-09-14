package com.example.project1_cst483_group5;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1_cst483_group5.db.PetViewModel;

public class PetListViewHolder extends RecyclerView.ViewHolder {



    //Help from https://willowtreeapps.com/ideas/android-fundamentals-working-with-the-recyclerview-adapter-and-viewholder-pattern

    TextView name;
    TextView type;

    TextView gender;
    ImageView trashBtn;
    ImageView favInfoBtn;
    TextView id;

    public PetListViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.tvNameSearch);
        type = itemView.findViewById(R.id.tvTypeSearch);

        gender = itemView.findViewById(R.id.tvGenderSearch);
        id = itemView.findViewById(R.id.tvIdSearch);


        trashBtn = itemView.findViewById(R.id.ivFav);
        favInfoBtn = itemView.findViewById(R.id.ivInfo);
    }

    public void bindData ( final PetListViewModel viewModel){
        name.setText(viewModel.getName());
        type.setText(viewModel.getType());

        gender.setText(viewModel.getGender());
        id.setText(String.valueOf(viewModel.getId()));

    }
}
