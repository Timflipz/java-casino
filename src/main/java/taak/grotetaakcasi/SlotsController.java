package taak.grotetaakcasi;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import taak.grotetaakcasi.App;

public class SlotsController implements Initializable{

    private String[] symbols = {
        "Afbeelding1 casino.png", "afbeeldingkraai casino.png", "banaan casino.png", 
        "bar casino.png", "watermeloen casino.png", "citroen casino.png", 
        "zeven.png", "kers casino.png", "klok casino.png"
    };

    private double totaleWinst = 0; 

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imageView1;

    @FXML
    private ImageView imageView2;

    @FXML
    private Label labelWinst;
    
    @FXML
    private Button terugNaarMenuButton;

    @FXML
    private Label lblwinsttext;

    @FXML
    private ImageView imageView3;

    @FXML
    private Label labelInzet;

    @FXML
    private TextField textInzet;

    @FXML
    private Label label1Afbeelding;

    @FXML
    private Label label2Afbeelding;

    @FXML
    private Label label3Afbeelding;

    @FXML
    private Label labelTotaleWinst;

    @FXML
    private Label lblWinstTotaal;
    @FXML
    private Label algemeenBudgetLabel;

    @FXML
    private AnchorPane rootView;

    @FXML
    private Button spinKnop;

    @FXML
    public void gaNaarMenu (ActionEvent event) throws IOException{
           App.setRoot ("menu");
    }        
    
    public void initialize(URL url, ResourceBundle rb) {
        updateBudgetLabel();
    }
    
    @FXML
    void handleSpin(ActionEvent event) {
        String inzetText = textInzet.getText();
        if (!inzetText.matches("\\d+")) {
            labelWinst.setText("Voer een geldig bedrag in!");
            return;
        }
        
        double nieuweInzet = Double.parseDouble(textInzet.getText());
            if (App.getBedragen().getTotaleBedrag() >= nieuweInzet) {
                App.getBedragen().geldInnen(nieuweInzet);
                updateBudgetLabel(); 
                labelInzet.setText("Huidig speelbedrag: " + String.format("%.1f", nieuweInzet));

        
                algemeenBudgetLabel.setText("Budget: " + App.getBedragen().getTotaleBedrag());

                int inzet = Integer.parseInt(inzetText);
                Random random = new Random();

                Timeline flikkerTimeline = new Timeline();
                flikkerTimeline.setCycleCount(30);

                KeyFrame flikkerFrame = new KeyFrame( 
                    Duration.millis(100), 
                    e -> {
                        int randomNumber1 = random.nextInt(symbols.length);
                        int randomNumber2 = random.nextInt(symbols.length);
                        int randomNumber3 = random.nextInt(symbols.length);

                        setImage(imageView1, randomNumber1);
                        setImage(imageView2, randomNumber2);
                        setImage(imageView3, randomNumber3);
                    }
                );

                flikkerTimeline.getKeyFrames().add(flikkerFrame);

                setImageViewProperties(imageView1);
                setImageViewProperties(imageView2);
                setImageViewProperties(imageView3);

                flikkerTimeline.setOnFinished(e -> {
                    int eindnummer1 = random.nextInt(symbols.length);
                    int eindnummer2 = random.nextInt(symbols.length);
                    int eindnummer3 = random.nextInt(symbols.length);

                    setImage(imageView1, eindnummer1);
                    setImage(imageView2, eindnummer2);
                    setImage(imageView3, eindnummer3);

                    double winst = berekenWinst(eindnummer1, eindnummer2, eindnummer3, inzet);

                    if (winst > 0) {
                        labelWinst.setText("€" + winst);
                    } else {
                        labelWinst.setText("Je hebt verloren: €" + Math.abs(winst));
                    }

                    totaleWinst += winst;
                    labelTotaleWinst.setText("Totale winst: €" + totaleWinst);
                });

                flikkerTimeline.play();

        } else {
                textInzet.setText("Onvoldoende budget!");
            }
    }

    private void setImageViewProperties(ImageView imageView) {
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        imageView.setPreserveRatio(true);
    }

    private void setImage(ImageView imageView, int randomNumber) {
        URL imageUrl = getClass().getResource("/afbeeldingen/" + symbols[randomNumber]);
        if (imageUrl == null) {
            System.out.println("Afbeelding niet gevonden: " + symbols[randomNumber]);
        } else {
            imageView.setImage(new Image(imageUrl.toExternalForm()));
        }
    }

    private double berekenWinst(int eindnummer1, int eindnummer2, int eindnummer3, int inzet) {
        double winst = 0;
        if (eindnummer1 == eindnummer2 && eindnummer2 == eindnummer3) {
            winst = (double) inzet * 2; 
            App.getBedragen().voegBedragToe(winst);
        } else if (eindnummer1 == eindnummer2 || eindnummer2 == eindnummer3 || eindnummer1 == eindnummer3) {
            winst = (double) (inzet * 1.5);
            App.getBedragen().voegBedragToe(winst);
        } else {
            winst = 0;
        }
        return winst;
    }
    private void updateBudgetLabel() {
        algemeenBudgetLabel.setText("Budget: " + String.format("%.1f", App.getBedragen().getTotaleBedrag()));
    }

}


        


