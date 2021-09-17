package com.example.project1_cst483_group5;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type Single animal.
 */
public class SingleAnimal {
    /**
     * The Animal.
     */
    @SerializedName("animal")
    Animal animal;

    /**
     * Instantiates a new Single animal.
     *
     * @param animal the animal
     */
    public SingleAnimal(Animal animal) {
        this.animal = animal;
    }

    /**
     * Gets animal.
     *
     * @return the animal
     */
    public Animal getAnimal() {
        return animal;
    }

    /**
     * Sets animal.
     *
     * @param animals the animals
     */
    public void setAnimal(Animal animals) {
        this.animal = animal;
    }
}
