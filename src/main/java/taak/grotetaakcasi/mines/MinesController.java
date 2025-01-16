/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taak.grotetaakcasi.mines;



import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import taak.grotetaakcasi.App;

public class MinesController {

    @FXML private Button button1, button2, button3, button4, button5,
            button6, button7, button8, button9, button10,
            button11, button12, button13, button14, button15,
            button16, button17, button18, button19, button20,
            button21, button22, button23, button24, button25;

    @FXML private TextField inzetField;
    @FXML private Label geldLabel;   // Voor huidig spelgeld
    @FXML private Label budgetLabel; // Voor totaalbudget
    @FXML private Button terugNaarMenuButton;
    @FXML private Button innenButton;
    @FXML private Button restartButton;  // Herstartknop

    private double geld = 0.0;  // Start met 0, in plaats van 100
    private double inzet = 0.0;
    private double gewonnenGeld = 0.0;
    private final double vermenigvuldigingsFactor = 1.25;
    private final List<Button> buttons = new ArrayList<>();
    private final List<Boolean> isBomb = new ArrayList<>();
    
    @FXML
    public void gaNaarMenu(ActionEvent event) throws IOException {
        App.setRoot("menu");
    }

    @FXML
    public void initialize() {
        budgetLabel.setText("Budget: €" + String.format("%.2f", App.getBedragen().getTotaleBedrag()));
        geldLabel.setText("Geld: €" + String.format("%.2f", geld));
        buttons.addAll(List.of(button1, button2, button3, button4, button5, button6, button7, button8, button9, button10,
                button11, button12, button13, button14, button15, button16, button17, button18, button19, button20,
                button21, button22, button23, button24, button25));
        resetGame();
    }

    private void resetGame() {
        isBomb.clear();
        for (int i = 0; i < 20; i++) isBomb.add(false);
        for (int i = 0; i < 5; i++) isBomb.add(true);
        Collections.shuffle(isBomb);
        for (Button button : buttons) {
            button.setGraphic(null);
            button.setDisable(false);
            button.setOpacity(1);
        }
        // Zet de herstartknop weer zichtbaar als het spel wordt gereset
        restartButton.setVisible(true);
    }

    @FXML
    public void handleInzetButtonClick() {
        try {
            double nieuweInzet = Double.parseDouble(inzetField.getText());
            if (App.getBedragen().getTotaleBedrag() >= nieuweInzet) {
                inzet = nieuweInzet;
                App.getBedragen().geldInnen(inzet);
                updateBudgetLabel();
                geld = inzet;  // Het geld wordt nu gelijk aan de inzet
                geldLabel.setText("Geld: €" + String.format("%.2f", geld));
            } else {
                inzetField.setText("Onvoldoende budget!");
            }
        } catch (NumberFormatException e) {
            inzetField.setText("Ongeldige invoer!");
        }
    }

    @FXML
   
public void handleButtonClick(ActionEvent event) {
    Button clickedButton = (Button) event.getSource();
    int index = buttons.indexOf(clickedButton);

    // Controleer of het op een bom is
    if (isBomb.get(index)) {
        // Toon de afbeelding van de bom
        Image bombImage = new Image(getClass().getResourceAsStream("/afbeeldingen/bom.png"));
        ImageView imageView = new ImageView(bombImage);
        setImageViewSize(imageView, clickedButton);
        clickedButton.setGraphic(imageView);
        clickedButton.setDisable(true);  // Deactiveer de knop waarop de bom zit
        
        // Zet de inzet op 0 omdat het spel voorbij is
        geld = 0.0;
        geldLabel.setText("Geld: €" + String.format("%.2f", geld));

        // Deactiveer alle andere knoppen, spel is voorbij
        for (Button button : buttons) {
            button.setDisable(true);
        }

        // Optioneel: Toon een bericht dat het spel is afgelopen
        showGameOverMessage();
    } else {
        // Als er geen bom is, toon een diamant en verhoog de inzet
        Image diamondImage = new Image(getClass().getResourceAsStream("/afbeeldingen/diamant.png"));
        ImageView imageView = new ImageView(diamondImage);
        setImageViewSize(imageView, clickedButton);
        clickedButton.setGraphic(imageView);
        clickedButton.setDisable(true);  // Deactiveer de geklikte knop

         // Bereken de winst (zonder de originele inzet)
        double winst = inzet * (vermenigvuldigingsFactor - 1);  // De winst is de extra opbrengst
        gewonnenGeld += winst;  // Voeg de winst toe aan gewonnenGeld (i.p.v. geld)
        inzet *= vermenigvuldigingsFactor;  // Verhoog de inzet voor de volgende ronde
        geldLabel.setText("Geld: €" + String.format("%.2f", geld + gewonnenGeld));  // Laat de totale winst zien
    }
}


    private void showGameOverMessage() {
        // Toon een simpele pop-up of boodschap voor het einde van het spel
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("Je hebt een bom gevonden!");
        alert.setContentText("Je kunt niet meer verder spelen.");
        alert.showAndWait();
    }

    @FXML
    public void handleInnenButtonClick() {
    double totaalBedrag = geld + gewonnenGeld;  // Het totaalbedrag is het geld dat je had + de winst
    App.getBedragen().voegBedragToe(totaalBedrag);  // Voeg het totaalbedrag toe aan het totaalbudget
    gewonnenGeld = 0.0;  // Reset de gewonnenGeld na het innen
    resetGame();  // Reset het spel
    updateBudgetLabel();  // Werk het budgetlabel bij
    geldLabel.setText("Geld: €0.00");  // Zet het huidige geld naar 0
}

    private void updateBudgetLabel() {
        budgetLabel.setText("Budget: €" + String.format("%.2f", App.getBedragen().getTotaleBedrag()));
    }

    private void toonAfbeelding(Button button, String afbeeldingPad) {
        Image image = new Image(getClass().getResourceAsStream(afbeeldingPad));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(button.getWidth());
        imageView.setFitHeight(button.getHeight());
        imageView.setPreserveRatio(true);
        button.setGraphic(imageView);
    }

    private void setImageViewSize(ImageView imageView, Button button) {
        // Stel de grootte van de afbeelding in op basis van de grootte van de knop
        double width = button.getWidth();
        double height = button.getHeight();
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setPreserveRatio(true);  // Zorg ervoor dat de verhoudingen behouden blijven
    }

    @FXML
    public void restartGame() {
        // Zet het bord terug naar de beginstaat
        resetGame();

        // Zet het geld terug naar 0
        geld = 0.0;
        geldLabel.setText("Geld: €" + String.format("%.2f", geld));
        
        // Zet de inzet terug naar 0
        inzet = 0.0;
        inzetField.clear();

       
      
    }
}
