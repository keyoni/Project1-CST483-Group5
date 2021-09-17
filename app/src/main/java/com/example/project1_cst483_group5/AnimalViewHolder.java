package com.example.project1_cst483_group5;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * The type Animal view holder.
 */
public class AnimalViewHolder extends RecyclerView.ViewHolder {
    //Help from https://willowtreeapps.com/ideas/android-fundamentals-working-with-the-recyclerview-adapter-and-viewholder-pattern

    /**
     * The Name.
     */
    TextView name;
    /**
     * The Type.
     */
    TextView type;
    /**
     * The Age.
     */
    TextView age;
    /**
     * The Gender.
     */
    TextView gender;
    /**
     * The Fav btn.
     */
    ImageView favBtn;
    /**
     * The Info btn search.
     */
    ImageView infoBtnSearch;
    /**
     * The Id.
     */
    TextView id;

    /**
     * Instantiates a new Animal view holder.
     *
     * @param itemView the item view
     */
    public AnimalViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.tvNameSearch);
        type = itemView.findViewById(R.id.tvTypeSearch);
        age = itemView.findViewById(R.id.tvAgeSearch);
        gender = itemView.findViewById(R.id.tvGenderSearch);
        id = itemView.findViewById(R.id.tvIdSearch);


        favBtn = itemView.findViewById(R.id.ivFav);
        infoBtnSearch = itemView.findViewById(R.id.ivInfo);
    }

    /**
     * Bind data.
     *
     * @param viewModel the view model
     */
    public void bindData(final AnimalViewModel viewModel) {
        name.setText(viewModel.getName());
        type.setText(viewModel.getType());
        age.setText(viewModel.getAge());
        gender.setText(viewModel.getGender());
        id.setText(String.valueOf(viewModel.getId()));

    }
}
