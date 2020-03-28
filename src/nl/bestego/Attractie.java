package nl.bestego;

abstract class Attractie {
    int capaciteit;     // max aantal bezoekers per ronde
    String naam;
    double oppervlakte;
    double prijs;
    private boolean actief = false;
    private int kaartjes;       // verkochte kaartjes
    private double minimumBezetting = 0.6;  // bij deze bezettingsgraad start attractie indien wachtrij leeg
    private double omzet;
    private int wachtrij;
    private int wachtronde;


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
        kaartjes += aantal;
        omzet += aantal * prijs;
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

    public void verder() {
        if (isActief()) {
            if (checkVoldoendeBezoekers()) {
                start();
            } else {
                stop();
            }
        } else {
            if (checkVoldoendeBezoekers()) start();
        }
    }

    public String toString() {
        return naam;
    }

    public int getCapaciteit() {
        return capaciteit;
    }

    public int getKaartjes() {
        return kaartjes;
    }

    public double getOmzet() {
        return omzet;
    }

    public int getWachtrij() {
        return wachtrij;
    }
}
