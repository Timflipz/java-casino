package taak.grotetaakcasi;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Kaart {

    private String symbool;
    private String nummer;

    public Kaart(String symbool, String nummer) {
        this.symbool = symbool;
        this.nummer = nummer;
    }
    
    public String getSymbool() {
        return symbool;
    }

    public String getNummer() {
        return nummer;
    }

    public int getWaarde() {
        if (nummer == "Boer" || nummer == "Dame" || nummer == "Heer") {
            return 10;
        } else if (nummer == "Aas") {
            return 11;
        } else {
            return Integer.parseInt(nummer); //BRON CHATGPT
        }
    }

    public Image getAfbeelding() {
        String afb = "/afbeeldingen/" + symbool + "_" + nummer + ".png";//BRON CHATGPT
        return new Image(getClass().getResourceAsStream(afb));//BRON CHATGPT
    }


}