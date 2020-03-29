package nl.bestego;

public class Attractie {
    int capaciteit;     // max aantal bezoekers per ronde
    String naam;
    double oppervlakte;
    double prijs;
    boolean actief = false;
    int wachtrij;
    private int kaartjes;       // verkochte kaartjes
    final private double minimumBezetting = 0.6;  // bij deze bezettingsgraad start attractie indien wachtrij leeg
    private double omzet;
    private double omzetWaaroverBelastingIsGeheven;
    private int wachtronde;
    final private int wachtLimiet = 2;

    public double getOmzetWaaroverBelastingIsGeheven() {
        return omzetWaaroverBelastingIsGeheven;
    }

    public void setOmzetWaaroverBelastingIsGeheven(double omzetWaaroverBelastingIsGeheven) {
        this.omzetWaaroverBelastingIsGeheven = omzetWaaroverBelastingIsGeheven;
    }


    Attractie() {
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
        System.out.printf("%s: gestart\n", naam);
                actief = true;
    }

    public void stop() {
        System.out.printf("%s: gestopt\n", naam);
        actief = false;
        if ( ! Kermis.geopend) {  //ToDo: pass argument top stop methos instead
            bezoekerKooptKaartje(-wachtrij);
            System.out.printf("%s: Kermis gestopt, bezoekers in de wachtrij krijgen geld terug\n", naam);
        }
    }

    public void verder() throws Exception{
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
                if ( wachtrij > 0 && wachtronde >= wachtLimiet){
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
