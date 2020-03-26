package nl.bestego;

import java.util.ArrayList;
import java.util.Random;

public class Kermis {

    ArrayList<Attractie> attracties = new ArrayList<>();
    ArrayList<Bezoeker> bezoekers = new ArrayList<>();

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
        for (Attractie attractie : attracties) {
            if (attractie.isActief()) {
                System.out.println("Actief : " + attractie);
            } else {
                System.out.println("Passief: " + attractie);
            }
        }
    }

    public void start() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {

            for (int n = 0; n < random.nextInt(attracties.size()); n++) {
                startStopRandomAttractie("start");
            }
            for (int n = 0; n < random.nextInt(attracties.size()); n++) {
                startStopRandomAttractie("stop");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                ;
            }
            toonActiviteiten();
        }
    }

    void registreerAttracties() {
        attracties.add(new Attractie("botsauto"));
        attracties.add(new Attractie("spin"));
        attracties.add(new Attractie("spiegelpaleis"));
        attracties.add(new Attractie("spookhuis"));
        attracties.add(new Attractie("hawaii"));
        attracties.add(new Attractie("ladderklimmen"));
    }

    void registreerBezoeker(int aantal){
        for (int b=1;b<=aantal;b++){
            bezoekers.add(new Bezoeker());
        }
    }

}
