package nl.bestego;

public class Attractie {
    double prijs;
    double oppervlakte;
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
