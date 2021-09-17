package com.example.project1_cst483_group5;

import java.util.List;

/**
 * The type Photos.
 */
public class Photos {

    /**
     * The Small.
     */
    String small;
    /**
     * The Medium.
     */
    String medium;
    /**
     * The Large.
     */
    String large;
    /**
     * The Full.
     */
    String full;

    /**
     * Instantiates a new Photos.
     *
     * @param small  the small
     * @param medium the medium
     * @param large  the large
     * @param full   the full
     */
    public Photos(String small, String medium, String large, String full) {
        this.small = small;
        this.medium = medium;
        this.large = large;
        this.full = full;
    }

    /**
     * Gets small.
     *
     * @return the small
     */
    public String getSmall() {
        return small;
    }

    /**
     * Sets small.
     *
     * @param small the small
     */
    public void setSmall(String small) {
        this.small = small;
    }

    /**
     * Gets medium.
     *
     * @return the medium
     */
    public String getMedium() {
        return medium;
    }

    /**
     * Sets medium.
     *
     * @param medium the medium
     */
    public void setMedium(String medium) {
        this.medium = medium;
    }

    /**
     * Gets large.
     *
     * @return the large
     */
    public String getLarge() {
        return large;
    }

    /**
     * Sets large.
     *
     * @param large the large
     */
    public void setLarge(String large) {
        this.large = large;
    }

    /**
     * Gets full.
     *
     * @return the full
     */
    public String getFull() {
        return full;
    }

    /**
     * Sets full.
     *
     * @param full the full
     */
    public void setFull(String full) {
        this.full = full;
    }

    @Override
    public String toString() {
        return "Photos{" +
                "small='" + small + '\'' +
                ", medium='" + medium + '\'' +
                ", large='" + large + '\'' +
                ", full='" + full + '\'' +
                '}';
    }
}
