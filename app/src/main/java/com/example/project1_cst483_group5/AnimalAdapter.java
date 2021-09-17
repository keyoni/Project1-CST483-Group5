package com.example.project1_cst483_group5;

import static java.lang.Integer.parseInt;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1_cst483_group5.db.Pet;
import com.example.project1_cst483_group5.db.PetViewModel;
import com.example.project1_cst483_group5.db.UserViewModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * The type Animal adapter.
 */
public class AnimalAdapter extends RecyclerView.Adapter {

    private List<AnimalViewModel> animalViewModels = new ArrayList<>();
    private final PetViewModel petVM;
    private final Integer userId;
    private final String auth;
    private SingleAnimal animalResult;
    private Animal temp;
    /**
     * The Context.
     */
    Context context;

    /**
     * Instantiates a new Animal adapter.
     *
     * @param animalViewModels the animal view models
     * @param petViewModel     the pet view model
     * @param userId           the user id
     * @param auth             the auth
     * @param context          the context
     */
    public AnimalAdapter(List<AnimalViewModel> animalViewModels, PetViewModel petViewModel, Integer userId, String auth, Context context) {
        this.animalViewModels = animalViewModels;
        this.petVM = petViewModel;
        this.userId = userId;
        this.auth = auth;
        this.context = context;
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
                Toast.makeText(context,"Favorite!",Toast.LENGTH_LONG).show();
                Pet tempPet = new Pet(userId, ((AnimalViewHolder) holder).name.getText().toString(),
                        ((AnimalViewHolder) holder).type.getText().toString(),
                        ((AnimalViewHolder) holder).gender.getText().toString(),
                        parseInt(((AnimalViewHolder) holder).id.getText().toString()));

                petVM.insert(tempPet);
            }
        });

        ((AnimalViewHolder) holder).infoBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Call<SingleAnimal> animalCall = PetFinderClient.getInstance().petFinderApi.getAnimalById(" Bearer " + auth, parseInt(((AnimalViewHolder) holder).id.getText().toString()));

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

                                    .into(picture);

                        } else {
                            Picasso.get().load(temp.mPhoto.get(0).large)
                                    .resize(300, 300)
                                    .error(R.drawable.error_pic)
                                    .into(picture);
                        }

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
        return animalViewModels.size();
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.search_item;
    }
}



//                    Gson gson = new GsonBuilder().serializeNulls().create();
//
//                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//                    OkHttpClient client = new OkHttpClient.Builder()
//                            .addInterceptor(interceptor)
//                            .build();
//
//                    Retrofit retrofit = new Retrofit.Builder()
//                            .baseUrl("https://api.petfinder.com/v2/")
//                            .addConverterFactory(GsonConverterFactory.create())
//                            .client(client)
//                            .build();
//



