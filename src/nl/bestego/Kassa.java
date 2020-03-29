package nl.bestego;

import java.util.ArrayList;

public class Kassa {

    private double omzet;
    private double omzetKansSpelBelasting;
    private double omzetWaaroverBelastingIsBetaald;
    private double betaaldeKansSpelBelasting;


    public double getOmzet() {
        return omzet;
    }


    public void verzamelOmzetten(ArrayList<Attractie> attracties) {
        omzet = 0;
        omzetKansSpelBelasting = 0;
        for (Attractie attractie : attracties) {
            omzet += attractie.getOmzet();
            if (attractie instanceof GokAttractie) {
                omzetKansSpelBelasting += attractie.getOmzet();
            }
        }
    }

    public double getOmzetKansSpelBelasting() {
        return omzetKansSpelBelasting;
    }

    public void setOmzetKansSpelBelasting(double omzetKansSpelBelasting) {
        this.omzetKansSpelBelasting = omzetKansSpelBelasting;
    }

    public double getOmzetWaaroverBelastingIsBetaald() {
        return omzetWaaroverBelastingIsBetaald;
    }

    public void setOmzetWaaroverBelastingIsBetaald(double omzetWaaroverBelastingIsBetaald) {
        this.omzetWaaroverBelastingIsBetaald = omzetWaaroverBelastingIsBetaald;
    }

    public double getBetaaldeKansSpelBelasting() {
        return betaaldeKansSpelBelasting;
    }

    public void setBetaaldeKansSpelBelasting(double betaaldeKansSpelBelasting) {
        this.betaaldeKansSpelBelasting = betaaldeKansSpelBelasting;
    }

    public void betaalKansSpelBelasting(double percentage){
        double belastingPlichtig = omzetKansSpelBelasting - omzetWaaroverBelastingIsBetaald;
        double belasting = percentage * belastingPlichtig;
        betaaldeKansSpelBelasting += belasting;
        omzetWaaroverBelastingIsBetaald += belastingPlichtig;
    }
}
