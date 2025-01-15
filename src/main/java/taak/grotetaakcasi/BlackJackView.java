package taak.grotetaakcasi;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

public class BlackJackView {
    private BlackJackModel model;
    private AnchorPane roofView;
    private ArrayList<ImageView> spelerImageViews;
    private ArrayList<ImageView> dealerImageViews;

    public BlackJackView(BlackJackModel model, AnchorPane roofView) {
        this.model = model;
        this.roofView = roofView;
        this.spelerImageViews = maakImageViewLijst(10, 66, 290, 93);
        this.dealerImageViews = maakImageViewLijst(10, 66, 122, 93);
    }

    public void setupTable() {
        Rectangle tafel = new Rectangle(0, 90, 640, 590);
        tafel.setFill(Color.GREEN);
        roofView.getChildren().add(tafel);
        tafel.toBack();
        
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

    public void kaartenBeweging(ImageView kaart, double eindX, double eindY, double duurInSeconden) {
        kaart.setLayoutX(500);
        kaart.setLayoutY(122);
        kaart.setFitWidth(87);  
        kaart.setFitHeight(123);

        TranslateTransition animatie = new TranslateTransition();
        animatie.setNode(kaart);
        animatie.setDuration(Duration.seconds(duurInSeconden));
        animatie.setByX(eindX - 500);
        animatie.setByY(eindY - 122);

        animatie.setOnFinished(event -> {
            kaart.setTranslateX(0);
            kaart.setTranslateY(0);
            kaart.setLayoutX(eindX);
            kaart.setLayoutY(eindY);
            roofView.getChildren().remove(kaart);
            kaartenZichtbaarMaken();
        });

        animatie.play();
    }

    public void kaartenZichtbaarMaken() {
        for (int i = 0; i < spelerImageViews.size(); i++) {
            if (i < model.getSpelerHandGrootte()) {
                spelerImageViews.get(i).setVisible(true);
            }
        }
        
        for (int i = 0; i < dealerImageViews.size(); i++) {
            if (i < model.getDealerHandGrootte()) {
                dealerImageViews.get(i).setVisible(true);
            }
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

    public void resetKaarten() {
        for (int i = 0; i < spelerImageViews.size(); i++) {
            spelerImageViews.get(i).setVisible(false);
        }
        
        for (int i = 0; i < dealerImageViews.size(); i++) {
            dealerImageViews.get(i).setVisible(false);
        }
    }

    public void updateDealerKaarten(int index) {
        if (index == 0) {
            dealerImageViews.get(0).setImage(model.getOmgekeerdeKaartAfbeelding());
        } else {
            dealerImageViews.get(index).setImage(model.getDealerKaart(index));
        }
    }

    public void showCard(ImageView kaart, int handIndex, double eindX, double eindY) {
        kaart.setImage(model.getSpelerKaart(handIndex));
        roofView.getChildren().add(kaart);
        kaartenBeweging(kaart, eindX, eindY, 0.5);
    }

    
    public void showMessage(String message) {
        // Placeholder for a message label on the view
        // e.g. displaying the result of the round
    }
}
