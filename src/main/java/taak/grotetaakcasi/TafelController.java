
package taak.grotetaakcasi;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class TafelController {

    @FXML
    private TextField budgetIngeven;
    
    @FXML
    private Label budgetLabel;
    @FXML
    private Button voegToe;
    @FXML
    private Button menuKnop;
    @FXML
    private Button inGeld;
    
   public void initialize(){
        App.getBedragen().getTotaleBedrag();
        budgetLabel.setText("Budget: " + App.getBedragen().getTotaleBedrag());
    }
    
   @FXML
    public void GeldToevoegen (ActionEvent event) {    
        if (Double.parseDouble(budgetIngeven.getText()) > 0) {
            App.getBedragen().voegBedragToe(Double.parseDouble(budgetIngeven.getText()));
            budgetLabel.setText("Budget: " + App.getBedragen().getTotaleBedrag());
        } else {
            System.out.println("Het ingevoerde budget moet groter zijn dan 0.");
        }
    }   
    
    @FXML
    private void geldInnen(ActionEvent event) {
        if (Double.parseDouble(budgetIngeven.getText()) > 0) {
            App.getBedragen().geldInnen(Double.parseDouble(budgetIngeven.getText()));
            budgetLabel.setText("Budget: " + App.getBedragen().getTotaleBedrag());
        } else {
            System.out.println("Het ingevoerde bedrag moet groter zijn dan 0.");
        }
    }

    @FXML
    public void gaNaarMenu(ActionEvent event) throws IOException {
            App.setRoot("menu");
    }

    @FXML
    private void update(KeyEvent event) {
    }
}