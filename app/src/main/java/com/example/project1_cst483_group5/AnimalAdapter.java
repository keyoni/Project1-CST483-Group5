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

public class AnimalAdapter extends RecyclerView.Adapter {

    private List<AnimalViewModel> animalViewModels = new ArrayList<>();
    private PetViewModel petVM;
    private  Integer userId;
    private String auth;
    private SingleAnimal animalResult;
    private Animal temp;
    Context context;

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
                Toast.makeText(view.getContext(), "Favorited" + userId.toString(), Toast.LENGTH_SHORT).show();
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
                Log.d("API TEST", "inside info onclick ANIMAL");
                Log.d("API TEST", ((AnimalViewHolder) holder).id.getText().toString());

                Call<SingleAnimal> animalCall = PetFinderClient.getInstance().petFinderApi.getAnimalById(" Bearer " + auth,  parseInt(((AnimalViewHolder) holder).id.getText().toString()));

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
                        LayoutInflater inflater  = LayoutInflater.from(context);
                        View  dialogLayout = inflater.inflate(R.layout.custom_more_info, null);
                        builder.setView(dialogLayout);
                        TextView nameMoreInfo = (TextView) dialogLayout.findViewById(R.id.tvNameMoreInfo);
                        nameMoreInfo.setText((temp.getmName()));

                        TextView ageMoreInfo = (TextView) dialogLayout.findViewById(R.id.tvAgeMoreInfo);
                        ageMoreInfo.setText((temp.getmAge()));

                        TextView genderMoreInfo = (TextView) dialogLayout.findViewById(R.id.tvGenderMoreInfo);
                        genderMoreInfo.setText((temp.getmGender()));

                        TextView descMoreInfo = (TextView) dialogLayout.findViewById(R.id.tvDescMoreInfo);
                        descMoreInfo.setText((temp.getmDescription()));

                        TextView sizeMoreInfo = (TextView) dialogLayout.findViewById(R.id.tvSizeMoreInfo);
                        sizeMoreInfo.setText((temp.getmSize()));

                        TextView statusMoreInfo = (TextView) dialogLayout.findViewById(R.id.tvStatusMoreInfo);
                        statusMoreInfo.setText((temp.getmStatus()));




                        ImageView picture  = dialogLayout.findViewById(R.id.ivPicture);
                        if(temp.mPhoto.isEmpty()) {
                            Picasso.get().load(R.drawable.error_pic)
                                    .resize(300, 300)
                                    .centerCrop()
                                    .into(picture);

                        }else {
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
                        Log.d("API TEST", "hello failure");
                        Log.d("API TEST", t.getMessage());

                    }
                });


            }

        });

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
//                    PetFinderApi petFinderApi = retrofit.create(PetFinderApi.class);
//
//
//
//                //Call<Animal> animalCall = PetFinderClient.getInstance().petFinderApi.getAnimalById(" Bearer " + auth,  parseInt(((AnimalViewHolder) holder).id.getText().toString()));
//                Call<SingleAnimal> animalCall = petFinderApi.getAnimalById(" Bearer " + auth,  parseInt(((AnimalViewHolder) holder).id.getText().toString()));
//                animalCall.enqueue(new Callback<SingleAnimal>(){
//                    @Override
//                    public void onResponse(Call<SingleAnimal> call, Response<SingleAnimal> response) {
//                        Log.d("API TEST", "inside SINGLE ANIMAL");
//                        if (!response.isSuccessful()) {
//                            Log.d("API TEST","Code: " + response.code());
//                            return;
//                        }
//
//                        animalResult = response.body();
//                        Log.d("API TEST", animalResult.getAnimal().mName + "");
//
//
//
//
//
//
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<SingleAnimal> call, Throwable t) {
//                        Log.d("API TEST", "hello failure");
//                        Log.d("API TEST", t.getMessage());
//
//                    }
//                });

//
//                showAlertDialog(view.getContext(), this);
////                Toast.makeText(view.getContext(), "CLICK ON INFO Model Page", Toast.LENGTH_SHORT).show();
//            }
//            private void showAlertDialog(Context context, View.OnClickListener onClickListener) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setTitle("More Info!");
//                //builder.setMessage(((AnimalViewHolder) holder).id.getText().toString() + " " +     ((AnimalViewHolder) holder).type.getText().toString()+ " " +
//               //builder.setMessage((animalResult.getAnimal().getmName()));
//             builder.setMessage(("I'm Broken"));
//                builder.show();
        //}
////
//        });
//
//        }

    @Override
    public int getItemCount() {
        return animalViewModels.size();
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.search_item;
    }
}
