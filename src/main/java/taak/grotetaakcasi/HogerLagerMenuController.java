package taak.grotetaakcasi;

<<<<<<< HEAD
import javafx.util.Duration;
=======
>>>>>>> 75ff89e5e40d1b1efd192cfa215d589d28d10a4b
import java.util.Random;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
<<<<<<< HEAD
=======
import javafx.util.Duration;
>>>>>>> 75ff89e5e40d1b1efd192cfa215d589d28d10a4b

public class HogerLagerMenuController {

    @FXML
    private ImageView AfbeeldingBoven, AfbeeldingMidden, AfbeeldingOnder, AfbeeldingKaarten;

    @FXML
<<<<<<< HEAD
    private Pane BeginPane, EindPane, HeleSpelPane, SpeelPane, AfbeeldingPane;

    @FXML
    private Label BeginLabel, Afsluiten, InzetOnthouden, JuistFout, InzetLabel;

    @FXML
    private Button EndGame, SpeelButton, OkeButton, HogerButton, LagerButton;

    @FXML
=======
    private ImageView AfbeeldingOnder;
    
    @FXML
    private ImageView AfbeeldingKaarten;
    
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
>>>>>>> 75ff89e5e40d1b1efd192cfa215d589d28d10a4b
    private TextField Inzet;

    @FXML
    private AnchorPane RootPane;


    @FXML
    private AfbeeldingVerplaatsing afbeeldingVerplaatsing;
    private final KaartenDeck deck = new KaartenDeck();
    private int TotaalBedrag;
    private int VorigGetal;
    private int HuidigGetal;
    private int InzetBedrag;
    private boolean SpelerKiestHoger;

    @FXML
<<<<<<< HEAD
    void HogerKnop(ActionEvent event) {
        SpelerKiestHoger = true;
        
        controleerKeuze();

        SpeelButton.setVisible(false);
        HogerButton.setDisable(false);
        LagerButton.setDisable(false);

        AfbeeldingKaarten.setVisible(false);
=======
    private Label randomGetal;
    
  
    @FXML
    private int inzetBedrag;
    
    private int totaalBedrag;
    
    private int vorigGetal;
    
    private int huidigGetal; 
    
    private final Random random = new Random();
   
    @FXML
    void HogerKnop(ActionEvent event) {
        
        speelButton.setDisable(false);
        hogerButton.setDisable(true);
        lagerButton.setDisable(true);
        
      
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
               
    }
    
       
    @FXML
    void lagerKnop(ActionEvent event) {
        
        speelButton.setDisable(false);
        hogerButton.setDisable(true);
        lagerButton.setDisable(true);
        
        
        
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
>>>>>>> 75ff89e5e40d1b1efd192cfa215d589d28d10a4b
        
        verplaatsAfbeelding();

<<<<<<< HEAD
        PauseTransition pauze = new PauseTransition(Duration.seconds(2));
        pauze.setOnFinished(e -> {
            KaartenDeck.Kaart getrokkenKaart = deck.trekKaart();
            if (getrokkenKaart != null) {
                AfbeeldingKaarten.setImage(getrokkenKaart.getAfbeelding());
            }
            AfbeeldingKaarten.setVisible(true);
        });
        pauze.play();
=======
    
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
>>>>>>> 75ff89e5e40d1b1efd192cfa215d589d28d10a4b
    }

    @FXML
    void LagerKnop(ActionEvent event) {
        
        SpelerKiestHoger = false;

        afbeeldingVerplaatsing.verplaatsAfbeelding(RootPane);

        SpeelButton.setVisible(false);
        HogerButton.setDisable(false);
        LagerButton.setDisable(false);

        AfbeeldingKaarten.setVisible(false);

        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> {
        controleerKeuze();

        KaartenDeck.Kaart getrokkenKaart = deck.trekKaart();
        if (getrokkenKaart != null) {
            AfbeeldingKaarten.setImage(getrokkenKaart.getAfbeelding());
        }
        AfbeeldingKaarten.setVisible(true);
        });
        pause.play();
    }

    @FXML
    void Activeren(ActionEvent event) {
        BeginLabel.setText("Hoger      Lager    ");
        BeginPane.setVisible(false);
        SpeelPane.toFront();
        SpeelPane.setVisible(true);
        EindPane.toBack();

    }

    @FXML
    void Eindigen(ActionEvent event) {
        //App.setRoot("Tafel");
    }

    @FXML
    void StartSpel(ActionEvent event) {
        
        deck.schudKaarten();
        AfbeeldingKaarten.setVisible(false);

        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> AfbeeldingKaarten.setVisible(true));
        pause.play();

        AfbeeldingBoven.setVisible(true);

        verplaatsAfbeelding();

        InzetBedrag = Integer.parseInt(Inzet.getText());

        BeginPane.setVisible(false);
        SpeelPane.setVisible(true);

        SpeelButton.setDisable(true);
        HogerButton.setDisable(false);
        LagerButton.setDisable(false);

        PakKaarten pak = new PakKaarten();

        pak.kaartenSchudden();

        KaartenDeck.Kaart getrokkenKaart = deck.trekKaart();

        if (getrokkenKaart != null) {
            VorigGetal = getrokkenKaart.getWaarde();
        }
    }

    @FXML
    void initialize() {

        afbeeldingVerplaatsing = new AfbeeldingVerplaatsing(AfbeeldingBoven);

        Image imageAchterkant = new Image(getClass().getResourceAsStream("/images/1.png"));

        AfbeeldingBoven.setImage(imageAchterkant);
        AfbeeldingMidden.setImage(imageAchterkant);
        AfbeeldingOnder.setImage(imageAchterkant);

        AfbeeldingBoven.setPreserveRatio(false);
        AfbeeldingMidden.setPreserveRatio(false);
        AfbeeldingOnder.setPreserveRatio(false);

        BeginPane.toFront();
        BeginPane.setVisible(true);
        OkeButton.setDisable(true);
        EndGame.setVisible(true);
        EindPane.setVisible(false);
        SpeelPane.setVisible(false);
        HogerButton.setDisable(true);
        LagerButton.setDisable(true);

        Rectangle blauweTafel = new Rectangle(0, 90, 640, 590);
        blauweTafel.setFill(Color.BLUE);
        RootPane.getChildren().addAll(blauweTafel);
        blauweTafel.toBack();

        Inzet.textProperty().addListener((observable, OudeWaarde, NieuweWaarde) -> {
            if (NieuweWaarde.trim().isEmpty() || !NieuweWaarde.matches("\\d+")) {
                OkeButton.setDisable(true);
                InzetLabel.setText("Inzet: " + NieuweWaarde);
            } else {

                OkeButton.setDisable(false);
                InzetLabel.setText("Inzet: " + NieuweWaarde);
            }
        });
        InzetLabel.setText("Ingezet: " + InzetBedrag);
        InzetOnthouden.setText("Budget: " + App.getBedragen().getTotaleBedrag());

    }
    
    void verplaatsAfbeelding() {
        afbeeldingVerplaatsing.verplaatsAfbeelding(RootPane);
    }
<<<<<<< HEAD

    private void controleerKeuze() {
        KaartenDeck.Kaart getrokkenKaart = deck.trekKaart();
        HuidigGetal = getrokkenKaart.getWaarde();

        boolean isCorrect = (SpelerKiestHoger && HuidigGetal > VorigGetal)
                || (!SpelerKiestHoger && HuidigGetal < VorigGetal);

        if (isCorrect) {
            JuistFout.setText("Correct!");
            JuistFout.setTextFill(Color.GREEN);
            App.getBedragen().voegBedragToe((Double.parseDouble(Inzet.getText())) * 2);
        } else {
            JuistFout.setText("Fout!");
            JuistFout.setTextFill(Color.RED);
            InzetOnthouden.setText("Budget: " + App.getBedragen().getTotaleBedrag());
            App.getBedragen().geldInnen(InzetBedrag);
        }

        VorigGetal = HuidigGetal;
        InzetOnthouden.setText("Budget: " + App.getBedragen().getTotaleBedrag());
    }

}


=======
   
}
>>>>>>> 75ff89e5e40d1b1efd192cfa215d589d28d10a4b
