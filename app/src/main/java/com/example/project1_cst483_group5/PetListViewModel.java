package com.example.project1_cst483_group5;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.project1_cst483_group5.db.Pet;
import com.example.project1_cst483_group5.db.PetRepository;

import java.util.List;

public class PetListViewModel {


    private String name;
    private Integer id;
    private String type;
    private String gender;


    public PetListViewModel(Integer id, String name, String type, String gender) {


        this.id = id;
        this.name = name;
        this.type = type;

        this.gender = gender;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
