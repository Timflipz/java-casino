package taak.grotetaakcasi;

import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KaartenDeck {

    public class Kaart {
        private String naam;
        private int waarde;
        private Image afbeelding;

        public Kaart(String naam, int waarde, Image afbeelding) {
            this.naam = naam;
            this.waarde = waarde;
            this.afbeelding = afbeelding;
        }

        public int getWaarde() {
            return waarde;
        }

        public Image getAfbeelding() {
            return afbeelding;
        }

        @Override
        public String toString() {
            return naam + " (" + waarde + ")";
        }
    }

    private List<Kaart> deck = new ArrayList<>();

    // Constructor die alle kaarten toevoegt
    public KaartenDeck() {
        String[] suits = {"Harten", "Klaveren", "Ruiten", "Schoppen"};
        String[] waarden = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Boer", "Dame", "Heer", "Aas"};

        // Voeg kaarten toe voor elke suit en waarde
        for (String suit : suits) {
            for (String waardeStr : waarden) {
                int waarde = getKaartWaarde(waardeStr);  // Haal de numerieke waarde op van de kaart
                String naam = waardeStr + " " + suit;
                Image afbeelding = new Image(getClass().getResourceAsStream("/afbeeldingen/" + suit + "_" + waardeStr + ".png"));
                deck.add(new Kaart(naam, waarde, afbeelding));
            }
        }
    }

    // Haal de numerieke waarde op van de kaart (Boer = 11, Vrouw = 12, etc.)
    private int getKaartWaarde(String waarde) {
        switch (waarde) {
            case "Boer": return 11;
            case "Dame": return 12;
            case "Heer": return 13;
            case "Aas": return 14;
            default: return Integer.parseInt(waarde); // Voor 2-10
        }
    }

    // Methode om de kaarten te schudden
    public void schudKaarten() {
        Collections.shuffle(deck); // Schud de kaarten
    }

    // Trek een kaart uit het deck
    public Kaart trekKaart() {
        if (deck.size() > 0) {
            return deck.remove(0); // Verwijder en retourneer de bovenste kaart
        } else {
            return null; // Geen kaarten meer
        }
    }
}

