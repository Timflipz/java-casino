package taak.grotetaakcasi;

import java.util.ArrayList;

public class SpelerEnDealer {
    private ArrayList<Kaart> hand = new ArrayList<>();


    public void voegKaartToe(Kaart kaart) {
        hand.add(kaart);
    }

    public ArrayList<Kaart> getHand() {
        return hand;
    }

    public int berekenWaardeHand() {
        int totaal = 0;
        int azen = 0;

        for (Kaart kaart : hand) {
            totaal += kaart.getWaarde();
            if (kaart.getWaarde() == 11) {
                azen++;
            }
        }

        while (totaal > 21 && azen > 0) {
            totaal -= 10;
            azen--;
        }

        return totaal;
    }

    public void resetHand() {
        hand.clear();
    }  
}