package com.example.project1_cst483_group5;

import static java.lang.Integer.parseInt;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1_cst483_group5.db.Pet;
import com.example.project1_cst483_group5.db.PetViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The type Pet list adapter.
 */
public class PetListAdapter extends RecyclerView.Adapter {

    private List<PetListViewModel> petListViewModels = new ArrayList<>();
    private final PetViewModel petVM;
    private final String auth;
    private SingleAnimal animalResult;
    private Animal temp;
    /**
     * The Context.
     */
    Context context;


    /**
     * Instantiates a new Pet list adapter.
     *
     * @param petViewModels the pet view models
     * @param petViewModel  the pet view model
     * @param auth          the auth
     * @param context       the context
     */
    public PetListAdapter(List<PetListViewModel> petViewModels, PetViewModel petViewModel, String auth, Context context) {
        this.petListViewModels = petViewModels;
        this.petVM = petViewModel;
        this.auth = auth;
        this.context = context;
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

        ((PetListViewHolder) holder).trashBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Refresh to see updated list", Toast.LENGTH_SHORT).show();

                petVM.deletePet(parseInt(((PetListViewHolder) holder).id.getText().toString()));
            }
        });

        ((PetListViewHolder) holder).favInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Call<SingleAnimal> animalCall = PetFinderClient.getInstance().petFinderApi.getAnimalById(" Bearer " + auth, parseInt(((PetListViewHolder) holder).id.getText().toString()));

                animalCall.enqueue(new Callback<SingleAnimal>() {
                    @Override
                    public void onResponse(Call<SingleAnimal> call, Response<SingleAnimal> response) {
                        if (!response.isSuccessful()) {
                            Log.d("API TEST", "Code: " + response.code());
                            // you don't see this...
                            if (response.code() == 404) {
                                Toast.makeText(view.getContext(), "Pet was adopted <3" + ((PetListViewHolder) holder).id.getText().toString(), Toast.LENGTH_SHORT).show();
                                petVM.deletePet(parseInt(((PetListViewHolder) holder).id.getText().toString()));
                            }
                            return;
                        }

                        animalResult = response.body();
                        temp = animalResult.getAnimal();
                        Log.d("API TEST", animalResult.getAnimal().mName + "");
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        LayoutInflater inflater = LayoutInflater.from(context);
                        View dialogLayout = inflater.inflate(R.layout.custom_more_info, null);
                        builder.setView(dialogLayout);
                        TextView nameMoreInfo = dialogLayout.findViewById(R.id.tvNameMoreInfo);
                        nameMoreInfo.setText((temp.getmName()));

                        TextView ageMoreInfo = dialogLayout.findViewById(R.id.tvAgeMoreInfo);
                        ageMoreInfo.setText((temp.getmAge()));

                        TextView genderMoreInfo = dialogLayout.findViewById(R.id.tvGenderMoreInfo);
                        genderMoreInfo.setText((temp.getmGender()));

                        TextView descMoreInfo = dialogLayout.findViewById(R.id.tvDescMoreInfo);
                        descMoreInfo.setText((temp.getmDescription()));

                        TextView sizeMoreInfo = dialogLayout.findViewById(R.id.tvSizeMoreInfo);
                        sizeMoreInfo.setText((temp.getmSize()));

                        TextView statusMoreInfo = dialogLayout.findViewById(R.id.tvStatusMoreInfo);
                        statusMoreInfo.setText((temp.getmStatus()));


                        ImageView picture = dialogLayout.findViewById(R.id.ivPicture);
                        if (temp.mPhoto.isEmpty()) {
                            Picasso.get().load(R.drawable.error_pic)
                                    .resize(300, 300)
                                    .centerCrop()
                                    .into(picture);

                        } else {
                            Picasso.get().load(temp.mPhoto.get(0).full)
                                    .resize(300, 300)
                                    .centerCrop()
                                    .error(R.drawable.error_pic)
                                    .into(picture);
                        }

                        builder.show();
                    }


                    @Override
                    public void onFailure(Call<SingleAnimal> call, Throwable t) {
                        Log.d("API TEST", t.getMessage());

                    }
                });


            }

        });


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

