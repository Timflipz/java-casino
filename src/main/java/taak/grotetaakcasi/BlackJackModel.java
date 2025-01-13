package taak.grotetaakcasi;

import javafx.scene.image.Image;

public class BlackJackModel {
    private SpelerEnDealer speler;
    private SpelerEnDealer dealer;
    private PakKaarten pakKaarten;
    private double inzet;

    public BlackJackModel() {
        speler = new SpelerEnDealer();
        dealer = new SpelerEnDealer();
        pakKaarten = new PakKaarten();
    }

    public void startGame() {
        pakKaarten.kaartenSchudden();
        speler.resetHand();
        dealer.resetHand();
        speler.voegKaartToe(pakKaarten.trekKaart());
        speler.voegKaartToe(pakKaarten.trekKaart());
        dealer.voegKaartToe(pakKaarten.trekKaart());
        dealer.voegKaartToe(pakKaarten.trekKaart());
    }

    public void spelerTrekKaart() {
        speler.voegKaartToe(pakKaarten.trekKaart());
    }

    public void dealerSpelen() {
        while (dealer.berekenWaardeHand() < 17) {
            dealer.voegKaartToe(pakKaarten.trekKaart());
        }
    }

    public String bepaalUitslag() {
        int spelerScore = speler.berekenWaardeHand();
        int dealerScore = dealer.berekenWaardeHand();

        if (dealerScore > 21 || spelerScore > dealerScore) {
            App.getBedragen().voegBedragToe(inzet * 2); 
            return "Je wint!";
        } else if (dealerScore > spelerScore) {
            return "De dealer wint.";
        } else {
            App.getBedragen().voegBedragToe(inzet);  
            return "Het is een gelijkspel.";
        }
    }

    public boolean isInzetGeldig(double inzet) {
        return inzet <= App.getBedragen().getTotaleBedrag();  
    }

    public void setInzet(double inzet) {
        this.inzet = inzet;
        App.getBedragen().geldInnen(inzet);  
    }

    public boolean isSpelerBoven21() {
        return speler.berekenWaardeHand() > 21;
    }

    public void resetGame() {
        startGame();
    }

    public double getBudget() {
        return App.getBedragen().getTotaleBedrag(); 
    }

    public Image getSpelerKaart(int index) {
        return speler.getHand().get(index).getAfbeelding();
    }

    public Image getDealerKaart(int index) {
        return dealer.getHand().get(index).getAfbeelding();
    }
}
