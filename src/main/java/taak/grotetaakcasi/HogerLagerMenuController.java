package taak.grotetaakcasi;

import javafx.util.Duration; 
import java.util.Random;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class HogerLagerMenuController {
    @FXML
    private ImageView AfbeeldingBoven;

    @FXML
    private ImageView AfbeeldingMidden;

    @FXML
    private ImageView AfbeeldingOnder;
    
    @FXML
    private ImageView AfbeeldingHarten;

    @FXML
    private ImageView AfbeeldingKlaveren;

    @FXML
    private ImageView AfbeeldingKoeken;

    @FXML
    private ImageView AfbeeldingSchuppen;
    @FXML
    private Pane BeginScherm;

    @FXML
    private Pane EindPane;

    @FXML
    private Pane HeleSpel;
    
    @FXML
    private Pane SpeelPane;

    @FXML
    private Label BeginLabel;
    
    @FXML
    private AnchorPane rootPane;
     
    @FXML
    private Button endGame;
    
    @FXML
    private Button speelButton;
    
    @FXML
    private Label afsluiten;
    
    @FXML
    private Label InzetOnthouden;
   
    @FXML
    private TextField Inzet;
    
    @FXML
    private Button OkeButton;    

    @FXML
    private Button hogerButton;

    @FXML
    private Label JuistFout;
    
    @FXML
    private Button lagerButton;

    @FXML
    private Label randomGetal;
    
  
    @FXML
    private int inzetBedrag;
    
    private int totaalBedrag;
    
    private int vorigGetal;
    
    private int huidigGetal; 
    
    private final Random random = new Random();
    
@FXML
    private ImageView AfbeeldingSchoppen;

    private final String IMAGE_PATH = "images/";

    
    private void toonKaarten() {
        
        loadCardImages(AfbeeldingHarten, "harten");
        loadCardImages(AfbeeldingKlaveren, "klaveren");
        loadCardImages(AfbeeldingKoeken, "koeken");
        loadCardImages(AfbeeldingSchoppen, "schoppen");
    }

    
    private void loadCardImages(ImageView imageView, String suit) {
        
        for (int i = 1; i <= 13; i++) {
            Image cardImage = new Image(IMAGE_PATH + suit + i + ".png"); 
            imageView.setImage(cardImage); 
        }
    }

    @FXML
    void genereerKaarten(ActionEvent event) {
        // Genereer de kaarten voor alle vier de suites
        toonKaarten();
    }
    @FXML
    void HogerKnop(ActionEvent event) {
        speelButton.setDisable(false);
        hogerButton.setDisable(true);
        lagerButton.setDisable(true);
        
        genereerVolgendeKaart();
        if (huidigGetal > vorigGetal) {
            randomGetal.setText("Nieuw getal: " + huidigGetal);
            JuistFout.setText("Correct!");
            JuistFout.setTextFill(Color.GREEN);
            totaalBedrag += inzetBedrag;
        } else {
            JuistFout.setText("Fout!");
            randomGetal.setText("Nieuw getal: " + huidigGetal);
            JuistFout.setTextFill(Color.RED);
            totaalBedrag -= inzetBedrag;
        }
        if (totaalBedrag <= 0) {
            
            EindPane.setVisible(true);
            EindPane.toFront();
            endGame.setVisible(true);
            endGame.setText("Menu");
            
            afsluiten.setText("FOUT! Je hebt geen geld meer! Druk op End Game!");
            afsluiten.setFont(Font.font("System", FontWeight.BOLD, 55)); 
            afsluiten.setTextFill(Color.RED); 
            afsluiten.setWrapText(true);
            
            HeleSpel.setVisible(true);
            
            BeginLabel.setVisible(false);
            SpeelPane.setVisible(false);
            
        }
        InzetOnthouden.setText("Inzet: " + inzetBedrag + " Totaal: "+ totaalBedrag);
        updateKaart();        
    }
    
       
    @FXML
    void lagerKnop(ActionEvent event) {
        
        speelButton.setDisable(false);
        hogerButton.setDisable(true);
        lagerButton.setDisable(true);
        
        genereerVolgendeKaart();
        
        if (huidigGetal < vorigGetal) 
        {
            JuistFout.setText("Correct!");
            JuistFout.setTextFill(Color.GREEN);
            randomGetal.setText("Nieuw getal: " + huidigGetal);
            totaalBedrag += inzetBedrag;
        }
        else {
            JuistFout.setText("Fout!");
            JuistFout.setTextFill(Color.RED);
            randomGetal.setText("Nieuw getal: " + huidigGetal);
            totaalBedrag -= inzetBedrag;
        } 
        if (totaalBedrag <= 0) {      
            
            EindPane.setVisible(true);
            EindPane.toFront();
            endGame.setVisible(true);
            endGame.setText("Menu");
            
            afsluiten.setText("FOUT! Je hebt geen geld meer! Druk op End Game!");
            afsluiten.setFont(Font.font("System", FontWeight.BOLD, 55)); 
            afsluiten.setTextFill(Color.RED); 
            afsluiten.setWrapText(true);
            
            HeleSpel.setVisible(true);
          
            BeginLabel.setVisible(false);
            SpeelPane.setVisible(false);
     
        }
        
        InzetOnthouden.setText("Inzet: " + inzetBedrag + " Totaal: "+ totaalBedrag);   
        updateKaart();
    
    }
    
    @FXML
    void initialize() {
        
        // Laad de afbeeldingen vanuit de resources-map
    Image imageBoven = new Image(getClass().getResourceAsStream("/images/1.png"));
    Image imageMidden = new Image(getClass().getResourceAsStream("/images/1.png"));
    Image imageOnder = new Image(getClass().getResourceAsStream("/images/1.png"));

    // Koppel de afbeeldingen aan de ImageView-componenten
    AfbeeldingBoven.setImage(imageBoven);
    AfbeeldingMidden.setImage(imageMidden);
    AfbeeldingOnder.setImage(imageOnder);

    // (Optioneel) Schaal de afbeeldingen om in de beschikbare ruimte te passen
    AfbeeldingBoven.setFitWidth(100);
    AfbeeldingBoven.setFitHeight(150);
    AfbeeldingBoven.setPreserveRatio(true);

    AfbeeldingMidden.setFitWidth(100);
    AfbeeldingMidden.setFitHeight(150);
    AfbeeldingMidden.setPreserveRatio(true);

    AfbeeldingOnder.setFitWidth(100);
    AfbeeldingOnder.setFitHeight(150);
    AfbeeldingOnder.setPreserveRatio(true);
        
        BeginScherm.toFront();
        BeginScherm.setVisible(true);
            OkeButton.setDisable(true);
            
        EindPane.setVisible(false);
        
        SpeelPane.setVisible(false);
            hogerButton.setDisable(true);
            lagerButton.setDisable(true);
        
        Rectangle blauweTafel = new Rectangle(0, 90, 640,590);
        blauweTafel.setFill(Color.BLUE);
        rootPane.getChildren().addAll(blauweTafel);
        blauweTafel.toBack();
      
        Inzet.textProperty().addListener((observable, oldValue, newValue) -> {
    if (newValue.trim().isEmpty() || !newValue.matches("\\d+")) { 
        OkeButton.setDisable(true);
        InzetOnthouden.setText("Inzet: ");
    } else {
        OkeButton.setDisable(false);
        InzetOnthouden.setText("Inzet: " + newValue);
    }
});
        
    }
    
        
    @FXML
    void Startspel(ActionEvent event) {
        
        verplaatsAfbeelding();
        AfbeeldingBoven.setVisible(true); 
        
        inzetBedrag = Integer.parseInt(Inzet.getText());
        InzetOnthouden.setText("Inzet:" +  inzetBedrag); 
        
        BeginScherm.setVisible(false);
        
        SpeelPane.setVisible(true);   
            speelButton.setDisable(true);
            hogerButton.setDisable(false);
            lagerButton.setDisable(false);
        
    }

    private void genereerVolgendeKaart() {        
        huidigGetal = random.nextInt(13) + 1;
    }

    private void updateKaart() {
        vorigGetal = huidigGetal;
    }
    
    void verplaatsAfbeelding() {
    
    double startX = 471;
    double startY = 24;
    double EindeX = 77;
    double EindeY = 108;

    
    AfbeeldingBoven.setLayoutX(startX);
    AfbeeldingBoven.setLayoutY(startY);

    
    TranslateTransition Verplaatsing = new TranslateTransition(Duration.seconds(2), AfbeeldingBoven);
    Verplaatsing.setToX(EindeX - startX); 
    Verplaatsing.setToY(EindeY - startY); 
   
    
    Verplaatsing.setOnFinished(event -> {
        AfbeeldingBoven.setVisible(false);
        AfbeeldingBoven.setLayoutX(startX);
        AfbeeldingBoven.setLayoutY(startY);

    });
    Verplaatsing.play();
    }

    @FXML
    void Activeren(ActionEvent event) {   
        BeginLabel.setText("Hoger      Lager    ");
        BeginScherm.setVisible(false);
            
        SpeelPane.toFront();
        SpeelPane.setVisible(true);
        EindPane.toBack();
        
    }
    
    @FXML
    void eindigen(ActionEvent event) {
    //App.setRoot("Tafel");
    }
   
}

    
    


