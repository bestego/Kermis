package nl.bestego;

public class RisicoRijkeAttractie extends Attractie {
    int draailimiet;
    int rondes;


    public int getDraailimiet() {
        return draailimiet;
    }

    public void setDraailimiet(int draailimiet) {
        this.draailimiet = draailimiet;
    }

    public int getRondes() {
        return rondes;
    }

    public void setRondes(int rondes) {
        this.rondes = rondes;
    }

    public void opstellingskeuring() {
        rondes = 0;
    }

    public void start() {
        if (rondes <= draailimiet) {
            //start(isActief(),getCapaciteit(),getWachtrij());
            super.start();
            rondes++;
        }
    }

    public void verder() throws Exception{
        if (isActief() && rondes > draailimiet) {
            stop();
            bezoekerKooptKaartje(-getWachtrij());
            System.out.printf("%s: gestopt voor onderhoud, klanten krijgen geld terug\n", getNaam());
            throw new Exception(getNaam() + ": heeft onderhoud nodig");
        } else {
            super.verder();
        }
    }

    public void bezoekerKooptKaartje(int aantal) {
        if (rondes <= draailimiet || aantal < 0) {
            super.bezoekerKooptKaartje(aantal);
        }
    }

}
