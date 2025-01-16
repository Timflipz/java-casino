package taak.grotetaakcasi;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import taak.grotetaakcasi.App;

public class SlotsController {

    private String[] symbols = {
        "Afbeelding1 casino.png", "afbeeldingkraai casino.png", "banaan casino.png", 
        "bar casino.png", "watermeloen casino.png", "citroen casino.png", 
        "zeven.png", "kers casino.png", "klok casino.png"
    };

    private int totaleWinst = 0; // Houdt de totale winst bij

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
    private AnchorPane rootView;

    @FXML
    private Button spinKnop;

    @FXML
    public void gaNaarMenu (ActionEvent event) throws IOException{
           App.setRoot ("menu");
    }        
    @FXML
    void handleSpin(ActionEvent event) {
        
        String inzetText = textInzet.getText();
        if (!inzetText.matches("\\d+")) {
            labelWinst.setText("Voer een geldig bedrag in!");
            return;
        }

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

                imageView1.setImage(new Image(getClass().getResource("/afbeeldingen/" + symbols[randomNumber1]).toExternalForm()));
                imageView2.setImage(new Image(getClass().getResource("/afbeeldingen/" + symbols[randomNumber2]).toExternalForm()));
                imageView3.setImage(new Image(getClass().getResource("/afbeeldingen/" + symbols[randomNumber3]).toExternalForm()));
            }
        );

        flikkerTimeline.getKeyFrames().add(flikkerFrame);
        
        imageView1.setFitWidth(200);
            imageView1.setFitHeight(200);
            imageView1.setPreserveRatio(true);

            imageView2.setFitWidth(200);
            imageView2.setFitHeight(200);
            imageView2.setPreserveRatio(true);

            imageView3.setFitWidth(200);
            imageView3.setFitHeight(200);
            imageView3.setPreserveRatio(true);

       
        flikkerTimeline.setOnFinished(e -> {
            int finalNumber1 = random.nextInt(symbols.length);
            int finalNumber2 = random.nextInt(symbols.length);
            int finalNumber3 = random.nextInt(symbols.length);

            Image image1 = new Image(getClass().getResource("/afbeeldingen/" + symbols[finalNumber1]).toExternalForm());
            Image image2 = new Image(getClass().getResource("/afbeeldingen/" + symbols[finalNumber2]).toExternalForm());
            Image image3 = new Image(getClass().getResource("/afbeeldingen/" + symbols[finalNumber3]).toExternalForm());

            
            imageView1.setImage(image1);
            imageView2.setImage(image2);
            imageView3.setImage(image3);

            
            imageView1.setFitWidth(200);
            imageView1.setFitHeight(200);
            imageView1.setPreserveRatio(true);

            imageView2.setFitWidth(200);
            imageView2.setFitHeight(200);
            imageView2.setPreserveRatio(true);

            imageView3.setFitWidth(200);
            imageView3.setFitHeight(200);
            imageView3.setPreserveRatio(true);

            
            int winst = 0;
            if (finalNumber1 == finalNumber2 && finalNumber2 == finalNumber3) {
                winst = inzet * 2; 
            } else if (finalNumber1 == finalNumber2 || finalNumber2 == finalNumber3 || finalNumber1 == finalNumber3) {
                winst = (int) Math.round(inzet * 1.5); 
            } else {
                winst = -inzet; 
            }

            
            if (winst > 0) {
                labelWinst.setText("€" + winst);
            } else {
                labelWinst.setText("Je hebt verloren: €" + Math.abs(winst));
            }

         
            totaleWinst += winst; 
            labelTotaleWinst.setText("Totale winst: €" + totaleWinst);
        });

        
        flikkerTimeline.play();
    }
}


        


