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
        if (nummer.equals("Boer") || nummer.equals("Dame") || nummer.equals("Heer")) {
            return 10;
        } else if (nummer.equals("Aas")) {
            return 11;
        } else {
            return Integer.parseInt(nummer);
        }
    }

    public Image getAfbeelding() {
        String afb = "/afbeeldingen/" + symbool + "_" + nummer + ".png";
        return new Image(getClass().getResourceAsStream(afb));
    }
    
    public String toString() {
        return symbool + " " + nummer;
    }
}