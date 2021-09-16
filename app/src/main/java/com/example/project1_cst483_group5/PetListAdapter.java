package com.example.project1_cst483_group5;

import static java.lang.Integer.parseInt;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetListAdapter extends RecyclerView.Adapter {

    private List<PetListViewModel> petListViewModels = new ArrayList<>();
    private PetViewModel petVM;
    private String auth;
    private SingleAnimal animalResult;
    private Animal temp;
    Context context;


    public PetListAdapter(List<PetListViewModel> petViewModels, PetViewModel petViewModel,  String auth, Context context) {
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
                //Toast.makeText(view.getContext(), "deleted but not really" + ((PetListViewHolder) holder).id.getText().toString() ,Toast.LENGTH_SHORT).show();

                petVM.deletePet(parseInt(((PetListViewHolder) holder).id.getText().toString()));
            }
        });

        ((PetListViewHolder) holder).favInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("API TEST", "inside info onclick ANIMAL");
                Log.d("API TEST", ((PetListViewHolder) holder).id.getText().toString());

                Call<SingleAnimal> animalCall = PetFinderClient.getInstance().petFinderApi.getAnimalById(" Bearer " + auth,  parseInt(((PetListViewHolder) holder).id.getText().toString()));

                animalCall.enqueue(new Callback<SingleAnimal>() {
                    @Override
                    public void onResponse(Call<SingleAnimal> call, Response<SingleAnimal> response) {
                        Log.d("API TEST", "inside SINGLE ANIMAL");
                        if (!response.isSuccessful()) {
                            Log.d("API TEST", "Code: " + response.code());
                            return;
                        }

                        animalResult = response.body();
                        temp = animalResult.getAnimal();
                        Log.d("API TEST", animalResult.getAnimal().mName + "");
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("More Info!");
                        builder.setMessage(animalResult.getAnimal().mName);
                        builder.show();

                    }

                    @Override
                    public void onFailure(Call<SingleAnimal> call, Throwable t) {
                        Log.d("API TEST", "hello failure");
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

