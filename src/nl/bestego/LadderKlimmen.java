package nl.bestego;

public class LadderKlimmen extends Attractie implements GokAttractie{
    LadderKlimmen(String naam, double prijs, int capaciteit, double oppervlakte) {
        this.naam = naam;
        this.prijs = prijs;
        this.capaciteit = capaciteit;
        this.oppervlakte = oppervlakte;
    }

    @Override
    public double getKansSpelBelasting() {
        return 0.3*(getOmzet()-getOmzetWaaroverBelastingIsGeheven());
    }

    @Override
    public void betaalKansSpelBelasting(){
        setOmzetWaaroverBelastingIsGeheven(getOmzet());
    }
}
