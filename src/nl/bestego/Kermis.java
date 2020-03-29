package nl.bestego;

import java.util.ArrayList;
import java.util.Random;

public class Kermis {

    static boolean geopend = false;
    ArrayList<Attractie> attracties = new ArrayList<>();
    Kassa kassa = new Kassa();
    BelastingInspecteur belastinginspecteur = new BelastingInspecteur();
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

    private void toonStatus() {
        System.out.println("\n===== STATUS VAN DE KERMIS =====");
        System.out.println(toonDatumTijd());
        String formatHeader = "%20s | %10s | %10s | %10s | %10s | %10s | %10s\n";
        String formatAttractie = "%20s | %10s | %10s | %10s | %10.2f | %10s | %10s \n";
        System.out.printf(formatHeader, "Naam attractie", "Draait", "Wachtrij", "Kaartjes", "Omzet", "Onderhoud", "Belasting");
        String onderhoud;
        double belasting;
        for (Attractie attractie : attracties) {
            if (attractie instanceof RisicoRijkeAttractie) {
                onderhoud = ((RisicoRijkeAttractie) attractie).getRondes() + "/" + ((RisicoRijkeAttractie) attractie).getDraailimiet();
            } else {
                onderhoud = "-";
            }
            if (attractie instanceof GokAttractie){
               belasting = ((GokAttractie)attractie).getKansSpelBelasting();
            } else {
                belasting = 0;
            }
            System.out.printf(formatAttractie, attractie.getNaam(), attractie.isActief() ? "Ja" : "Nee", attractie.getWachtrij() + "/" + attractie.getCapaciteit(),
                    attractie.getKaartjes(), attractie.getOmzet(), onderhoud, belasting == 0 ? "-" : String.format("%10.2f",belasting));
        }
        //System.out.printf("Totale omzet: %.2f | Belating\n", kassa.getOmzet()-kassa.getBetaaldeKansSpelBelasting());
        toonBelastingen();


    }

    private void toonBelastingen(){
        String formatHeader = "\n%20s | %20s | %20s \n";
        System.out.printf(formatHeader,"Netto omzet","Betaalde belasting","Te betalen belasting" );
        String formatContent = "%20.2f | %20.2f | %20.2f \n";
        System.out.printf(formatContent,kassa.getOmzet()-kassa.getBetaaldeKansSpelBelasting(),
                kassa.getBetaaldeKansSpelBelasting(), (kassa.getOmzetKansSpelBelasting()-kassa.getOmzetWaaroverBelastingIsBetaald())*belastinginspecteur.kansSpelBelastingPercentage);
    }

    public void start() {
        Kermis.geopend = true;

        InputHandler ih = new InputHandler();
        int aantalKaartjes;
        LOOP:
        do {
            aantalKaartjes = 0;
            ronde++;
            String prompt = "\nMaak een keuze voor de volgende ronde:\n";
            prompt += "\t- aantal bezoekers per attractie 0..99 of (R)andom\n";
            prompt += "\t- (B)elasting innen\n";
            prompt += "\t- (O)nderhoud uitvoeren\n";
            prompt += "\t- (S)toppen\n";
            String input = ih.getInput("[0-9]+|[a-zA-Z]+", prompt, "ongeldige invoer, probeer opnieww:");

            if (input.toLowerCase().matches("[a-z]+")) {
                switch (input.toLowerCase().charAt(0)) {
                    case 'b': belastinginspecteur.belastingInnen(kassa);
                        break;
                    case 'o':
                        onderhoudUitvoeren();
                        break;
                    case 'r': verdeelBezoekers();
                        System.out.println("Todo: nog implementeren");
                        break;
                    case 's':
                        stop();
                        break LOOP;
                    default:
                        System.out.println("Programmafout: onbekende keuze: " + input);
                }
            }

            System.out.println("\n===== VERANDERINGEN OP DE KERMIS =====");
            if (input.matches("[0-9]+")) {
                aantalKaartjes = Integer.valueOf(input);
                verdeelBezoekers(aantalKaartjes);
            }

            kassa.verzamelOmzetten(attracties);
            toonStatus();
        } while (true);
    }

    public void stop() {
        Kermis.geopend = false;
        for (Attractie attractie : attracties) {
            attractie.stop();
        }
        kassa.verzamelOmzetten(attracties);
        toonStatus();
    }

    public void onderhoudUitvoeren() {
        for (Attractie attractie : attracties) {
            if (attractie instanceof RisicoRijkeAttractie) {
                ((RisicoRijkeAttractie) attractie).opstellingskeuring();
            }
        }
    }

    private void verdeelBezoekers(int aantal) {
        for (Attractie attractie : attracties) {
            attractie.bezoekerKooptKaartje(aantal);
            attractie.verder();
        }
    }

    private void verdeelBezoekers() {
        Random rnd = new Random();
        int aantal = 0;
        for (Attractie attractie : attracties) {
            aantal = (int)(Math.round(rnd.nextFloat()*attractie.capaciteit*1.7));
            attractie.bezoekerKooptKaartje(aantal);
            attractie.verder();
        }
    }

    private void belastingInnen(){
        for (Attractie attractie : attracties) {
            if (attractie instanceof GokAttractie) {
                ((GokAttractie) attractie).betaalKansSpelBelasting();
            }
        }
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
     *
     * @return : datum en tijd
     */
    private String toonDatumTijd() {
        return ("\"Tijd\": " + ronde);
    }


}
