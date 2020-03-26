package nl.bestego;

import java.util.ArrayList;

public class Attractie {
    private ArrayList<Bezoeker> bezoeker = new ArrayList<>();  //todo: kan ook getal zijn ipv opject
    private int capaciteit;     // max aantal bezoekers per ronde
    private double prijs;
    private int kaartjesVolgendeRonde;
    private int kaartjesTotaal;
    private double minimumBezetting = 0.6;  // bij deze bezettingsgraad start attractie indien wachtrij leeg
    private double omzet;
    private double oppervlakte;
    private ArrayList<Bezoeker> wachtrij = new ArrayList<>();
    private String naam;
    private boolean actief = false;

    Attractie(){}

    Attractie(String naam) {
        this.naam = naam;
    }

    public String getNaam() {
        return naam;
    }

    public boolean isActief(){
        return actief;
    }

    public void start() {
        System.out.println("(Attractie " + naam + " is gestart)");
        actief = true;
    }

    public void stop() {
        System.out.println("(Attractie " + naam + " is gestopt)");
        actief = false;
    }

    public String toString(){
        return naam;
    }
}
