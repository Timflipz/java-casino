package taak.grotetaakcasi;

import java.util.ArrayList;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.scene.layout.AnchorPane;

public class KaartView {

    private AnchorPane roofView;
    private BlackJackView blackjackview;
    private BlackJackModel model;
    private ArrayList<ImageView> spelerImageViews;
    private ArrayList<ImageView> dealerImageViews;

    public KaartView(AnchorPane roofView, BlackJackView blackjackview, BlackJackModel model,
                     ArrayList<ImageView> spelerImageViews, ArrayList<ImageView> dealerImageViews) {
        this.roofView = roofView;
        this.blackjackview = blackjackview;
        this.model = model;
        this.spelerImageViews = spelerImageViews;
        this.dealerImageViews = dealerImageViews;
    }
    
    public void kaartenZichtbaarMaken(int spelerhandgrootte,
                                      int dealerhandgrootte,
                                      ArrayList<ImageView> spelerImageViews,
                                      ArrayList<ImageView> dealerImageViews){
        
        for (int i = 0; i < spelerImageViews.size(); i++) {
            if (i < spelerhandgrootte) { 
                spelerImageViews.get(i).setVisible(true);
            }
        }
        
        for (int i = 0; i < dealerImageViews.size(); i++) {
            if (i < dealerhandgrootte) { 
                dealerImageViews.get(i).setVisible(true);
            }
        }
    }

    public void kaartBeweging(ImageView kaart, double eindX, double eindY, double duurInSeconden) {
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

            kaartenZichtbaarMaken(model.getSpelerHandGrootte(),
                                                 model.getDealerHandGrootte(),
                                                 spelerImageViews,
                                                 dealerImageViews);
        });

        animatie.play();
    }

    public void beginSpelNaInzetten() {
        
        for (int i = 0; i < 2; i++) {
            ImageView kaart = new ImageView();
            kaart.setImage(model.getSpelerKaartAfbeelding(i));
            roofView.getChildren().add(kaart);

            double eindX = 66 + i * 93;
            double eindY = 290;

            kaartBeweging(kaart, eindX, eindY, 0.5);
        }

        ImageView omgekeerdeKaart = new ImageView();
        omgekeerdeKaart.setImage(model.getOmgekeerdeKaartAfbeelding());
        roofView.getChildren().add(omgekeerdeKaart);
        omgekeerdeKaart.setFitWidth(87); 
        omgekeerdeKaart.setFitHeight(123);
        double eindX = 66;  
        double eindY = 122; 
        kaartBeweging(omgekeerdeKaart, eindX, eindY, 0.5);

        ImageView dealerKaart = new ImageView();
        dealerKaart.setImage(model.getDealerKaartAfbeelding(1));
        roofView.getChildren().add(dealerKaart);
        dealerKaart.setFitWidth(87); 
        dealerKaart.setFitHeight(123);
        double eindX2 = 66 + 93;  
        double eindY2 = 122; 
        kaartBeweging(dealerKaart, eindX2, eindY2, 0.5);
    }

    public void toonNieuweKaart(int handGrootte) {
        
        ImageView nieuweKaart = new ImageView();
        nieuweKaart.setImage(model.getSpelerKaartAfbeelding(handGrootte - 1));

        roofView.getChildren().add(nieuweKaart);

        double eindX = 66 + (handGrootte - 1) * 93;
        double eindY = 290;

        kaartBeweging(nieuweKaart, eindX, eindY, 0.5);
    }
    
    public void kaartenToevoegenAanAfbeeldingenlijst() {
        
        for (int i = 0; i < spelerImageViews.size(); i++) {
            if (i < model.getSpelerHandGrootte()) {
                if (!spelerImageViews.get(i).isVisible()) {   
                    spelerImageViews.get(i).setImage(model.getSpelerKaartAfbeelding(i));
                    spelerImageViews.get(i).setVisible(false);
                } 
            }
        }

        for (int i = 1; i < dealerImageViews.size(); i++) {
            if (i < model.getDealerHandGrootte()) {
                if (!dealerImageViews.get(i).isVisible()) {
                    dealerImageViews.get(0).setVisible(false);
                    dealerImageViews.get(i).setImage(model.getDealerKaartAfbeelding(i));
                    dealerImageViews.get(i).setVisible(false);
                }
            }
        }
    }
    
    public void resetSpel() {
    dealerImageViews.get(0).setImage(model.getOmgekeerdeKaartAfbeelding());

    for (ImageView spelerKaart : spelerImageViews) {
        spelerKaart.setVisible(false);
    }
    
    for (ImageView dealerKaart : dealerImageViews) {
        dealerKaart.setVisible(false);
    }
    }
}
