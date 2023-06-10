package fr.eg23.wargame;

import androidx.annotation.NonNull;

public enum AiType {
    Defensive,
    Offensive,
    Random;

    @NonNull
    @Override
    public String toString() {
        switch (this.ordinal()){
            case 0:
                return "Défensif";
            case 1:
                return "Offensif";
            case 2:
                return "Aléatoire";
            default:
                return null;
        }
    }
}
