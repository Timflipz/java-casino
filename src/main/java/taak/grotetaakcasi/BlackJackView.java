package taak.grotetaakcasi;


import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BlackJackView {
    private AnchorPane roofView;
    private Object model;

    public BlackJackView(AnchorPane roofView) {
        this.roofView = roofView;
    }
    
    public void maakTafel(){
        Rectangle tafel = new Rectangle(0, 90, 640, 590);
        tafel.setFill(Color.GREEN);
        roofView.getChildren().add(tafel);
        tafel.toBack();
    }
    
    public void voegPakKaartenToe(){
        for (int i = 0; i < 3; i++) {
            ImageView stapelAfbeelding = new ImageView();
            stapelAfbeelding.setLayoutX(500 - (i * 10));  
            stapelAfbeelding.setLayoutY(122);
            stapelAfbeelding.setFitHeight(123.0);
            stapelAfbeelding.setFitWidth(87.0);
            stapelAfbeelding.setImage(new Image(getClass().getResourceAsStream("/afbeeldingen/OmgekeerdeKaart.png")));
            roofView.getChildren().add(stapelAfbeelding);
        }
    }
        
    public ArrayList<ImageView> maakImageViewLijst(int aantal, double startX, double startY, double tussenruimte) {
        ArrayList<ImageView> imageViews = new ArrayList<>();
        for (int i = 0; i < aantal; i++) {
            ImageView imageView = new ImageView();
            imageView.setFitWidth(87);
            imageView.setFitHeight(123);
            imageView.setLayoutX(startX + i * tussenruimte);
            imageView.setLayoutY(startY);
            imageView.setVisible(false);
            imageViews.add(imageView);
            roofView.getChildren().add(imageView);
        }
        return imageViews;
    }
} 
