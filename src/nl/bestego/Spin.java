package nl.bestego;

public class Spin extends RisicoRijkeAttractie {

    Spin(String naam, double prijs, int capaciteit, double oppervlakte) {
        this.naam = naam;
        this.prijs = prijs;
        this.capaciteit = capaciteit;
        this.oppervlakte = oppervlakte;
        this.draailimiet = 5;
    }
}
