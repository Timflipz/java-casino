package taak.grotetaakcasi;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class BedragenView {
    private Label budgetLabel;
    private Label inzetLabel;

        public BedragenView(AnchorPane root) {
            
        budgetLabel = new Label("Budget: " + App.getBedragen());
        budgetLabel.setLayoutX(14);
        budgetLabel.setLayoutY(14);
        budgetLabel.setFont(new Font(18));

        inzetLabel = new Label("Inzet: ");
        inzetLabel.setLayoutX(310);
        inzetLabel.setLayoutY(447);
        inzetLabel.setFont(new Font(24));
        inzetLabel.setText("Inzet: ");
        inzetLabel.setTextFill(Color.GOLD);
        

        root.getChildren().addAll(budgetLabel, inzetLabel);
    }
        
        public Label getBudgetLabel(){
            return budgetLabel;
        }
        
        public Label getInzetLabel(){
            return inzetLabel;
        }
        
        public void updateBedragLabel(){
            budgetLabel.setText("" + App.getBedragen());
        }
        
        public void resetInzetLabel(){
            inzetLabel.setText("Inzet: ");
        }
        
        public void updateInzetLabel(double inzet){
            inzetLabel.setText("Inzet: " + inzet);
        }
        
        
}

