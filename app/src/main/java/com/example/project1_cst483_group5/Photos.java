package com.example.project1_cst483_group5;

import java.util.List;

public class Photos {

    String small;
    String medium;
    String large;
    String full;

    public Photos(String small, String medium, String large, String full) {
        this.small = small;
        this.medium = medium;
        this.large = large;
        this.full = full;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getFull() {
        return full;
    }

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
