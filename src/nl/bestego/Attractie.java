package nl.bestego;

public class Attractie {
    int capaciteit;     // max aantal bezoekers per ronde
    String naam;
    double oppervlakte;
    double prijs;
    boolean actief = false;
    int wachtrij;
    private int kaartjes;       // verkochte kaartjes
    private double minimumBezetting = 0.6;  // bij deze bezettingsgraad start attractie indien wachtrij leeg
    private double omzet;

    public double getOmzetWaaroverBelastingIsGeheven() {
        return omzetWaaroverBelastingIsGeheven;
    }

    public void setOmzetWaaroverBelastingIsGeheven(double omzetWaaroverBelastingIsGeheven) {
        this.omzetWaaroverBelastingIsGeheven = omzetWaaroverBelastingIsGeheven;
    }

    double omzetWaaroverBelastingIsGeheven;
    private int wachtronde;
    private int wachtLimiet = 2;


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
        System.out.printf("(%s: gestart)\n", naam);
                actief = true;
        //start(isActief(), getCapaciteit(), getWachtrij());
    }

    public void start(boolean actief, int capaciteit, int wachtrij) {
        this.wachtrij = wachtrij > capaciteit ? wachtrij - capaciteit : 0;
        System.out.printf("(%s: gestart)\n", naam);
        this.actief = true;
    }

    public void stop() {
        System.out.printf("(%s: gestopt)\n", naam);
        actief = false;
        if ( ! Kermis.geopend) {
            bezoekerKooptKaartje(-wachtrij);
            System.out.printf("(%s: Kermis gestopt, bezoekers in de wachtrij krijgen geld terug)\n", naam);
        }
    }

    public void verder() {
        if (isActief()) {
            if (checkVoldoendeBezoekers()) {
                start();
            } else {
                stop();
            }
        } else {
            if (checkVoldoendeBezoekers()) {
                start();
            } else {
                if ( wachtronde >= wachtLimiet){
                    wachtronde = 0;
                    start();
                } else {
                    wachtronde++;
                }
            }
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
