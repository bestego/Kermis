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

    public void Opstellingskeuring() {
        rondes = 0;
    }

    public void start() {
        if (rondes <= draailimiet) {
            //start(isActief(),getCapaciteit(),getWachtrij());
            super.start();
            rondes++;
        }
    }

    public void bezoekerKooptKaartje(int aantal){
        if (rondes <= draailimiet){
            super.bezoekerKooptKaartje(aantal);
        }
    }

}
