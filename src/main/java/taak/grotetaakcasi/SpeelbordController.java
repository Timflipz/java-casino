package taak.grotetaakcasi;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SpeelbordController {

    @FXML
    private Label algemeenBudgetLabel;
    
    @FXML 
    private AnchorPane anchorPane;

    @FXML
    private Button blackJackButton;

    @FXML
    private Button hogerLagerButton;
    
    @FXML
    private Button mines;
    
    @FXML
    private Label menuLabel;
    @FXML
    private Button geldActies;
    
    public void initialize(){
        App.getBedragen().getTotaleBedrag();
        algemeenBudgetLabel.setText("Budget: " + App.getBedragen().getTotaleBedrag());
        maakTafel();
    }
    
    @FXML
    public void blackJackOpenen() throws IOException{
        App.setRoot("blackjack");
    }
    
    public void hogerLagerOpenen() throws IOException{
        App.setRoot("hogerlager"); 
    } 
    public void openMines() throws IOException{
        App.setRoot("mines");
    }
    public void update() throws IOException{
        App.setRoot("Tafel");
    }
    public void maakTafel(){
        Rectangle tafel = new Rectangle(0, 80, 640, 590);
        tafel.setFill(Color.GREEN);
        anchorPane.getChildren().add(tafel);
        tafel.toBack();
    }
}
