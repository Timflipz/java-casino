package taak.grotetaakcasi;

import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class AfbeeldingVerplaatsing {
    private ImageView afbeeldingBoven;
    private AnchorPane Anchorpane;
    
    public AfbeeldingVerplaatsing(ImageView afbeeldingBoven) {
        
        this.afbeeldingBoven = afbeeldingBoven;
    }

    public void verplaatsAfbeelding(AnchorPane Anchorpane) {
        double startX = 471;
        double startY = 24;
        double EindeX = 77;
        double EindeY = 100;

        afbeeldingBoven.setLayoutX(startX);
        afbeeldingBoven.setLayoutY(startY);

        TranslateTransition verplaatsing = new TranslateTransition(Duration.seconds(2), afbeeldingBoven);
        verplaatsing.setToX(EindeX - startX);
        verplaatsing.setToY(EindeY - startY);
        
        verplaatsing.setOnFinished(event -> {
            afbeeldingBoven.setTranslateX(77);
            afbeeldingBoven.setTranslateY(100);
            afbeeldingBoven.setVisible(false);
            afbeeldingBoven.setLayoutX(startX);
            afbeeldingBoven.setLayoutY(startY);
            Anchorpane.getChildren().remove(afbeeldingBoven);
            
        });
        verplaatsing.play();
    }
    public void kaartBeweging(ImageView kaart, double eindX, double eindY, double duurInSeconden) {
        kaart.setLayoutX(500);  
        kaart.setLayoutY(122);
        kaart.setFitWidth(87);  
        kaart.setFitHeight(123);

        TranslateTransition verplaatsing = new TranslateTransition();
        verplaatsing.setNode(kaart);
        verplaatsing.setDuration(Duration.seconds(duurInSeconden));
        verplaatsing.setByX(eindX - 500);
        verplaatsing.setByY(eindY - 122);

        verplaatsing.setOnFinished(event -> {
            kaart.setTranslateX(0);
            kaart.setTranslateY(0);
            kaart.setLayoutX(eindX);
            kaart.setLayoutY(eindY);
            Anchorpane.getChildren().remove(kaart);
        });

        verplaatsing.play();
    }

}
