package taak.grotetaakcasi;

import javafx.util.Duration;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HogerLagerMenuController {

    @FXML
    private ImageView AfbeeldingBoven, AfbeeldingMidden, AfbeeldingOnder, AfbeeldingKaarten;

    @FXML
    private Pane BeginPane, EindPane, HeleSpelPane, SpeelPane, AfbeeldingPane;

    @FXML
    private Label BeginLabel, Afsluiten, InzetOnthouden, JuistFout, InzetLabel;

    @FXML
    private Button EndGame, SpeelButton, OkeButton, HogerButton, LagerButton;

    @FXML
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
    void HogerKnop(ActionEvent event) {
        SpelerKiestHoger = true;
        
        controleerKeuze();

        SpeelButton.setVisible(false);
        HogerButton.setDisable(false);
        LagerButton.setDisable(false);

        AfbeeldingKaarten.setVisible(false);
        
        verplaatsAfbeelding();

        PauseTransition pauze = new PauseTransition(Duration.seconds(2));
        pauze.setOnFinished(e -> {
            KaartenDeck.Kaart getrokkenKaart = deck.trekKaart();
            if (getrokkenKaart != null) {
                AfbeeldingKaarten.setImage(getrokkenKaart.getAfbeelding());
            }
            AfbeeldingKaarten.setVisible(true);
        });
        pauze.play();
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


