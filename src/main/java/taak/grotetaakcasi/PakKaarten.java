package taak.grotetaakcasi;

import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class PakKaarten {
    
    private ArrayList<Kaart> kaarten = new ArrayList<>();

    public PakKaarten() {
        String[] symbolen = {"Harten", "Schoppen", "Ruiten", "Klaveren"};
        String[] kaartNrs = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Boer", "Dame", "Heer", "Aas"}; //bron chatgpt; veel makkelijker dan arraylist

        for (String symbool : symbolen) {
            for (String nummer : kaartNrs) {
                kaarten.add(new Kaart(symbool, nummer));
            }
        }
    }

    public void kaartenSchudden() {
        Collections.shuffle(kaarten); //bron chatgpt
    }

    public Kaart trekKaart() {
        
        Kaart getrokkenKaart = kaarten.get(0);
        kaarten.remove(0);

        String bestandsNaam = "/afbeeldingen/" + getrokkenKaart.getSymbool() + "_" + getrokkenKaart.getNummer() + ".png"; //bron chatgpt
        Image kaartAfbeelding = new Image(getClass().getResourceAsStream(bestandsNaam)); //bron chatgpt

        return getrokkenKaart;
    }

    public int getAantalKaarten() {
        return kaarten.size(); //bron oracle
    }
    
    public boolean isLeeg() {
    return kaarten.isEmpty(); //bron oracle
}
    
}
