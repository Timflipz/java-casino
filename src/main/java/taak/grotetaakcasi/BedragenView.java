package taak.grotetaakcasi;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class BedragenView {
    private Label budgetLabel;
    private Label inzetLabel;

        public BedragenView(AnchorPane root) {
        budgetLabel = new Label("Budget: ");
        budgetLabel.setLayoutX(14);
        budgetLabel.setLayoutY(14);

        inzetLabel = new Label("Inzet: ");
        inzetLabel.setLayoutX(310);
        inzetLabel.setLayoutY(447);

        root.getChildren().addAll(budgetLabel, inzetLabel);
    }
        
        
}

