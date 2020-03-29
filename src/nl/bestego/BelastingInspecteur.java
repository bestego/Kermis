package nl.bestego;

public class BelastingInspecteur {

    final double kansSpelBelastingPercentage = 0.30;

    void belastingInnen(Kassa kassa) {
        kassa.betaalKansSpelBelasting(kansSpelBelastingPercentage);
    }
}

