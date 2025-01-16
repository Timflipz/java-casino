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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import taak.grotetaakcasi.App;

public class MinesController {

    @FXML private Button button1, button2, button3, button4, button5,
            button6, button7, button8, button9, button10,
            button11, button12, button13, button14, button15,
            button16, button17, button18, button19, button20,
            button21, button22, button23, button24, button25;

    @FXML private TextField inzetField;
    @FXML private Label geldLabel;  
    @FXML private Label budgetLabel; 
    @FXML private Button terugNaarMenuButton;
    @FXML private Button innenButton;
    @FXML private Label minesLabel;
    @FXML private Label hoeveelLabel;
    @FXML private AnchorPane anchorPane;
    @FXML private GridPane roosterGridPane;
    
  

    private double geld = 0.0;  
    private double inzet = 0.0;
    private double gewonnenGeld = 0.0;
    private final double vermenigvuldigingsFactor = 1.15;
    private final List<Button> buttons = new ArrayList<>();
    private final List<Boolean> isBomb = new ArrayList<>();
    
    @FXML
    public void gaNaarMenu(ActionEvent event) throws IOException {
        App.setRoot("menu");
    }

    @FXML
    public void initialize() {
        budgetLabel.setText("Budget: " + String.format("%.1f", App.getBedragen().getTotaleBedrag()));
        geldLabel.setText("Huidige speelbedrag: " + String.format("%.1f", geld));
        buttons.addAll(List.of(button1, button2, button3, button4, button5, button6, button7, button8, button9, button10,
                button11, button12, button13, button14, button15, button16, button17, button18, button19, button20,
                button21, button22, button23, button24, button25));
        
        maakTafel();
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
    }    
       
    

    @FXML
    public void handleInzetButtonClick() {
        try {
            double nieuweInzet = Double.parseDouble(inzetField.getText());
            if (App.getBedragen().getTotaleBedrag() >= nieuweInzet) {
                inzet = nieuweInzet;
                App.getBedragen().geldInnen(inzet);
                updateBudgetLabel();
                geld = inzet;  
                geldLabel.setText("Huidige speelbedrag: " + String.format("%.1f", geld));
            } else {
                inzetField.setText("Onvoldoende budget!");
            }
        } catch (NumberFormatException e) {
            inzetField.setText("Ongeldige invoer!");
        } finally {
        inzetField.clear();
        }
    }
    
    @FXML
    public void handleButtonClick(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        int index = buttons.indexOf(clickedButton);

   
         if (isBomb.get(index)) {
   
        Image bombImage = new Image(getClass().getResourceAsStream("/afbeeldingen/bom.png"));
        ImageView imageView = new ImageView(bombImage);
        setImageViewSize(imageView, clickedButton);
        clickedButton.setGraphic(imageView);
        clickedButton.setDisable(true);  
       
        geld = 0.0;
        geldLabel.setText("Huidige speelbedrag: " + String.format("%.1f", geld));
       
        for (Button button : buttons) {
            button.setDisable(true);
        }
        

        showGameOverMessage();
        
        resetGame();
    } else {
       
        Image diamondImage = new Image(getClass().getResourceAsStream("/afbeeldingen/diamant.png"));
        ImageView imageView = new ImageView(diamondImage);
        setImageViewSize(imageView, clickedButton);
        clickedButton.setGraphic(imageView);
        clickedButton.setDisable(true);  

        double winst = inzet * (vermenigvuldigingsFactor - 1); 
        gewonnenGeld += winst;  
        inzet *= vermenigvuldigingsFactor;  
        geldLabel.setText("Huidige speelbedrag: " + String.format("%.1f", geld + gewonnenGeld));  
    }
}


    private void showGameOverMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("Je hebt een bom gevonden!");
        alert.setContentText("Je kunt niet meer verder spelen.");
        alert.showAndWait();
    }

    @FXML
    public void handleInnenButtonClick() {
    double totaalBedrag = geld + gewonnenGeld; 
    App.getBedragen().voegBedragToe(totaalBedrag);  
    gewonnenGeld = 0.0;  
    resetGame();  
    updateBudgetLabel();  
    geldLabel.setText("Huidige speelbedrag: 0,0");  
}

    private void updateBudgetLabel() {
        budgetLabel.setText("Budget: " + String.format("%.1f", App.getBedragen().getTotaleBedrag()));
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
        
        double width = button.getWidth();
        double height = button.getHeight();
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setPreserveRatio(true);  
    }
    public void maakTafel(){
        Rectangle tafel = new Rectangle(0, 90, 640, 590);
        tafel.setFill(Color.LIGHTGREEN);
        anchorPane.getChildren().add(tafel);
        tafel.toBack();
    }
    @FXML
    public void restartGame() {
       
        resetGame();

        geld = 0.0;
        geldLabel.setText("Huidig speelbedrag: " + String.format("%.1f", geld));
        
        inzet = 0.0;
        inzetField.clear();

    }
}
