package taak.grotetaakcasi;

import javafx.scene.image.Image;
import taak.grotetaakcasi.App;
import taak.grotetaakcasi.PakKaarten;
import taak.grotetaakcasi.SpelerEnDealer;

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
    
    public void nieuwPakWanneerLeeg(){
        if (pakKaarten.getAantalKaarten()<4){
            this.pakKaarten = new PakKaarten();
        }
    }

    public void startGame() {
        nieuwPakWanneerLeeg();
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

    public String bepaalUitslag(double inzet) {
        int spelerScore = speler.berekenWaardeHand();
        int dealerScore = dealer.berekenWaardeHand();
        String uitslag = ""; 

        if (dealerScore > 21 || spelerScore > dealerScore) {
            App.getBedragen().voegBedragToe(inzet * 2); 
            uitslag = "Je wint!";
        } else if (dealerScore == spelerScore) {
            App.getBedragen().voegBedragToe(inzet);  
            uitslag = "Het is een gelijkspel.";
        }else{
            uitslag = "De dealer wint";
        }

        return uitslag; 
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

    public void reset() {
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
    
    public Image getOmgekeerdeKaartAfbeelding() {
        String afb = "/afbeeldingen/OmgekeerdeKaart.png";
        return new Image(getClass().getResourceAsStream(afb));
    }
    
    public int getSpelerHandGrootte() {
    return speler.getHand().size();
}

    public int getDealerHandGrootte() {
        return dealer.getHand().size();
    }

}
