package nl.bestego;

import java.util.ArrayList;

public class Kassa {
    private double omzet;

    public double getOmzet() {
        return omzet;
    }

    public void setOmzet(double omzet) {
        this.omzet = omzet;
    }

    public double verzamelOmzet(ArrayList<Attractie> attracties ){
        double omzet = 0;
        for (Attractie attractie: attracties){
            omzet += attractie.getOmzet();
        }
        return omzet;
    }

}
