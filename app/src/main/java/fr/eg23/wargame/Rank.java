package fr.eg23.wargame;

import androidx.annotation.NonNull;

public enum Rank {
    PRIVATE,
    ELIITE,
    MAITREDEGUERRE;

    @NonNull
    @Override
    public String toString() {
        switch (this.ordinal()){
            case 0:
                return "Soldat";
            case 1:
                return "Elite";
            case 2:
                return "Maitre";
            default:
                return null;
        }

    }
}
