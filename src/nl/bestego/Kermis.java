package nl.bestego;

import java.util.ArrayList;
import java.util.Random;

public class Kermis {

    ArrayList<Attractie> attracties = new ArrayList<>();
    Kassa kassa = new Kassa();
    int ronde;

    Kermis() {
        registreerAttracties();

    }

    private void startStopRandomAttractie(String actie) {
        Random random = new Random();
        Attractie attractie = attracties.get(random.nextInt(attracties.size()));
        switch (actie.toLowerCase()) {
            case "start":
                if (!attractie.isActief()) attractie.start();
                break;
            case "stop":
                if (attractie.isActief()) attractie.stop();
                break;
        }
        if (attractie.isActief()) {
            attractie.stop();
        } else {
            attractie.start();
        }
    }

    private void toonActiviteiten() {
        System.out.println("====================================");
        System.out.println(toonDatumTijd());
        String formatHeader = "%20s | %10s | %10s | %10s | %10s | %10s\n";
        String formatAttractie = "%20s | %10s | %10s | %10s | %10.2f | %10s\n";
        System.out.printf(formatHeader, "Naam attractie","Draait","Wachtrij","Kaartjes","Omzet","Onderhoud");
        for (Attractie attractie : attracties) {
            System.out.printf(formatAttractie, attractie.getNaam(),attractie.isActief()?"Ja":"Nee",attractie.getWachtrij()+"/"+attractie.getCapaciteit(),
                    attractie.getKaartjes(),attractie.getOmzet(), "-");
        }
        System.out.printf("Totale omzet: %.2f\n", kassa.getOmzet());
    }

    public void start() { //ToDo: CONTINUE here; ombouwen naar klok / kaartjes distributie(bezoekerKooptKaartje

        InputHandler ih = new InputHandler();
        do {
            ronde++;
            int aantalKaartjes = Integer.valueOf(ih.getInput("[0-9]+", "Hoeveel kaartjes per attractie voor volgende ronde?:", "ongeldige invoer, probeer opnieww:"));

            for (Attractie attractie : attracties) {
                attractie.bezoekerKooptKaartje(aantalKaartjes);
                attractie.verder();
            }
            kassa.setOmzet(kassa.verzamelOmzet(attracties));
            toonActiviteiten();
        } while (true);
    }

    void registreerAttracties() {
        attracties.add(new Botsautos("botsauto-1", 2.50, 10, 400));
        attracties.add(new Spin("spin-1", 2.25, 20, 500));
        attracties.add(new Spiegelpaleis("spiegelpaleis-1", 2.75, 5, 300));
        attracties.add(new Spookhuis("spookhuis-1", 3.20, 10, 400));
        attracties.add(new Hawaii("hawaii-1", 2.9, 8, 250));
        attracties.add(new LadderKlimmen("ladderklimmen-1", 5.00, 1, 10));
    }

    /**
     * for now: dummy DateTime implementation
     * @return : datum en tijd
     */
    private String toonDatumTijd(){
        return ("\"Tijd\": " + ronde);
    }


}
