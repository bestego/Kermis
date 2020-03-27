package nl.bestego;

abstract class Attractie implements Klok {
    boolean actief = false;
    int capaciteit;     // max aantal bezoekers per ronde
    int kaartjes;       // verkochte kaartjes
    double minimumBezetting = 0.6;  // bij deze bezettingsgraad start attractie indien wachtrij leeg
    String naam;
    double omzet;
    double oppervlakte;
    double prijs;
    int wachtrij;


    Attractie() {
    }

    Attractie(String naam, double prijs, int capaciteit, double oppervlakte) {
        this.naam = naam;
        this.prijs = prijs;
        this.capaciteit = capaciteit;
        this.oppervlakte = oppervlakte;
    }

    public String getNaam() {
        return naam;
    }

    public boolean isActief() {
        return actief;
    }

    public void bezoekerKooptKaartje(int aantal) {
        wachtrij += aantal;
    }

    private boolean checkVoldoendeBezoekers() {
        return wachtrij >= minimumBezetting * capaciteit;
    }

    public void start() {
        wachtrij = wachtrij > capaciteit ? wachtrij - capaciteit : 0;
        System.out.println("(Attractie " + naam + " is gestart)");
        actief = true;
    }

    public void stop() {
        System.out.println("(Attractie " + naam + " is gestopt)");
        actief = false;
    }

    public void vervolg() {
        if (!isActief() && checkVoldoendeBezoekers()) start();
        if (isActief() && checkVoldoendeBezoekers()) stop();
    }

    public String toString() {
        return naam;
    }
}
