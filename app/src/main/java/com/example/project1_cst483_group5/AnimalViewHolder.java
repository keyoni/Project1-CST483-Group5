package com.example.project1_cst483_group5;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AnimalViewHolder extends RecyclerView.ViewHolder {
    //Help from https://willowtreeapps.com/ideas/android-fundamentals-working-with-the-recyclerview-adapter-and-viewholder-pattern

    TextView name;
    TextView type;
    TextView age;
    TextView gender;
    ImageView favBtn;
    ImageView infoBtnSearch;

    public AnimalViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.tvNameSearch);
        type = itemView.findViewById(R.id.tvTypeSearch);
        age = itemView.findViewById(R.id.tvAgeSearch);
        gender = itemView.findViewById(R.id.tvGenderSearch);

       favBtn = itemView.findViewById(R.id.ivFav);
       infoBtnSearch = itemView.findViewById(R.id.ivInfo);
    }

    public void bindData(final AnimalViewModel viewModel) {
        name.setText(viewModel.getName());
        type.setText(viewModel.getType());
        age.setText(viewModel.getAge());
        gender.setText(viewModel.getGender());
    }
}
