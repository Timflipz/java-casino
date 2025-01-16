package taak.grotetaakcasi;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;

public class EindSpel {

    private Label Afsluiten;
    private Button EndGame;
    private Pane EindPane;
    private Pane HeleSpelPane;
    private Pane SpeelPane;
    private Label BeginLabel;

    public EindSpel(Label afsluiten, Button endGame, Pane eindPane, Pane heleSpelPane, Pane speelPane, Label beginLabel) {
        this.Afsluiten = afsluiten;
        this.EndGame = endGame;
        this.EindPane = eindPane;
        this.HeleSpelPane = heleSpelPane;
        this.SpeelPane = speelPane;
        this.BeginLabel = beginLabel;
    }

    public void controleerTotaalBedrag(int totaalBedrag) {
        if (totaalBedrag <= 0) {
            EindPane.setVisible(true);
            EindPane.toFront();

            EndGame.setVisible(true);
            EndGame.setText("Menu");

            Afsluiten.setText("FOUT! Je hebt geen geld meer! Druk op End Game!");
            Afsluiten.setFont(Font.font("System", FontWeight.BOLD, 55));
            Afsluiten.setTextFill(Color.RED);
            Afsluiten.setWrapText(true);

            HeleSpelPane.setVisible(true);

            BeginLabel.setVisible(false);
            SpeelPane.setVisible(false);
        }
    }
}
