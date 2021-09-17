package com.example.project1_cst483_group5;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.project1_cst483_group5.db.Pet;
import com.example.project1_cst483_group5.db.PetRepository;

import java.util.List;

/**
 * The type Pet list view model.
 */
public class PetListViewModel {


    private String name;
    private Integer id;
    private String type;
    private String gender;


    /**
     * Instantiates a new Pet list view model.
     *
     * @param id     the id
     * @param name   the name
     * @param type   the type
     * @param gender the gender
     */
    public PetListViewModel(Integer id, String name, String type, String gender) {


        this.id = id;
        this.name = name;
        this.type = type;
        this.gender = gender;
    }


    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets gender.
     *
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets gender.
     *
     * @param gender the gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }


}
