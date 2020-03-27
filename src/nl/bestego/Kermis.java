package nl.bestego;

import java.util.ArrayList;
import java.util.Random;

public class Kermis {

    ArrayList<Attractie> attracties = new ArrayList<>();

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

    public void start() { //ToDo: CONTINUE here; ombouwen naar klok / kaartjes distributie(bezoekerKooptKaartje
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
        attracties.add(new Botsautos("botsauto-1",2.50,10,400));
        attracties.add(new Spin("spin-1",2.25,20,500));
        attracties.add(new Spiegelpaleis("spiegelpaleis-1",2.75,5,300));
        attracties.add(new Spookhuis("spookhuis-1",3.20 ,10,400));
        attracties.add(new Hawaii("hawaii-1",2.9 , 8, 250));
        attracties.add(new LadderKlimmen("ladderklimmen-1", 5.00,1,10));
    }


}
