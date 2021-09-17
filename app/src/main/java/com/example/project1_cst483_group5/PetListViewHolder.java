package com.example.project1_cst483_group5;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1_cst483_group5.db.PetViewModel;

/**
 * The type Pet list view holder.
 */
public class PetListViewHolder extends RecyclerView.ViewHolder {


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
     * The Gender.
     */
    TextView gender;
    /**
     * The Trash btn.
     */
    ImageView trashBtn;
    /**
     * The Fav info btn.
     */
    ImageView favInfoBtn;
    /**
     * The Id.
     */
    TextView id;

    /**
     * Instantiates a new Pet list view holder.
     *
     * @param itemView the item view
     */
    public PetListViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.tvNameFav);
        type = itemView.findViewById(R.id.tvTypeFav);

        gender = itemView.findViewById(R.id.tvGenderFav);
        id = itemView.findViewById(R.id.tvIdFav);


        trashBtn = itemView.findViewById(R.id.ivTrash);
        favInfoBtn = itemView.findViewById(R.id.ivInfoFav);
    }

    /**
     * Bind data.
     *
     * @param viewModel the view model
     */
    public void bindData(final PetListViewModel viewModel) {
        name.setText(viewModel.getName());
        type.setText(viewModel.getType());

        gender.setText(viewModel.getGender());
        id.setText(String.valueOf(viewModel.getId()));

    }
}

