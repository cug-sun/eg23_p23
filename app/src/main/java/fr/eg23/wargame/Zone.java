package fr.eg23.wargame;

import androidx.annotation.NonNull;

public enum Zone {
    gym,
    bde,
    library,
    admin,
    industry;

    @NonNull
    @Override
    public String toString() {
        switch (this.ordinal()){
            case 0:
                return "Halle Sportive";
            case 1:
                return "BDE";
            case 2:
                return "Bibliothèque";
            case 3:
                return "Quartier Administratif";
            case 4:
                return "Halles Industrielles";
            default:
                return null;
        }
    }
}
