package taak.grotetaakcasi;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class BlackjackController implements Initializable {
    private BlackJackModel model;

    @FXML
    private Label sout;

    @FXML
    private Label bjlabel;

    @FXML
    private Label budgetBlackJackLabel;

    @FXML
    private Label dealerKaartenLabel;

    @FXML
    private Button hitKnop;

    @FXML
    private Label inzetLabel;

    @FXML
    private TextField inzetText;

    @FXML
    private Button menuKnop;

    @FXML
    private AnchorPane roofView;

    @FXML
    private Label spelersKaartenLabel;

    @FXML
    private Button standKnop;

    @FXML
    private Label uitslagLabel;

    @FXML
    private Button zetInKnop;

    private ImageView[] spelerImageViews;
    private ImageView[] dealerImageViews;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = new BlackJackModel();
        budgetBlackJackLabel.setText("Budget: " + model.getBudget());

        Rectangle tafel = new Rectangle(0, 90, 640, 590);
        tafel.setFill(Color.GREEN);
        roofView.getChildren().add(tafel);
        tafel.toBack();
        
        for (int i = 0; i < 3; i++) {
            ImageView stapelAfbeelding = new ImageView();
            stapelAfbeelding.setLayoutX(500 - (i * 10));  
            stapelAfbeelding.setLayoutY(122);
            stapelAfbeelding.setFitHeight(123.0);
            stapelAfbeelding.setFitWidth(87.0);
            stapelAfbeelding.setImage(new Image(getClass().getResourceAsStream("/afbeeldingen/OmgekeerdeKaart.png")));
            roofView.getChildren().add(stapelAfbeelding);
        }

        spelerImageViews = imageViewLijst(10, 66, 290, 93);
        dealerImageViews = imageViewLijst(10, 66, 122, 93);

        for (ImageView imageView : spelerImageViews) {
            imageView.setVisible(false);
            roofView.getChildren().add(imageView);
        }

        for (ImageView imageView : dealerImageViews) {
            imageView.setVisible(false);
            roofView.getChildren().add(imageView);
        }

        hitKnop.setDisable(true);
        standKnop.setDisable(true);
        
        hitKnop.toFront();
        standKnop.toFront();

        dealerKaartenLabel.setVisible(true);
        spelersKaartenLabel.setVisible(true);

        model.startGame();
        updateUI();

        dealerImageViews[0].setImage(model.getOmgekeerdeKaartAfbeelding());
        
        
    }

    @FXML
    public void terugNaarMenu(ActionEvent event) throws IOException {
        App.setRoot("menu");
    }
    
    private void beweegKaartNaarPositie(ImageView kaart,double eindX, double eindY, double duurInSeconden) {
        kaart.setLayoutX(500);
        kaart.setLayoutY(122);
        kaart.setFitWidth(87);  
        kaart.setFitHeight(123);

        TranslateTransition animatie = new TranslateTransition();
        animatie.setNode(kaart);
        animatie.setDuration(Duration.seconds(duurInSeconden));
        animatie.setByX(eindX - 500);
        animatie.setByY(eindY - 122);

        animatie.setOnFinished(event -> {
            kaart.setTranslateX(0);
            kaart.setTranslateY(0);
            kaart.setLayoutX(eindX);
            kaart.setLayoutY(eindY);
            roofView.getChildren().remove(kaart);
            zichtbaarMaken();
        });

        animatie.play();
    }

    
    @FXML
    
    public void inzetten(ActionEvent event) throws IOException {
        double inzet = Double.parseDouble(inzetText.getText());
        if (model.isInzetGeldig(inzet)) {
            model.setInzet(inzet);
            inzetLabel.setText("Inzet: " + inzet);
            budgetBlackJackLabel.setText("Budget: " + model.getBudget());
            hitKnop.setDisable(false);
            standKnop.setDisable(false);
            dealerImageViews[0].setVisible(true);

            for (int i = 0; i < 2; i++) {
                ImageView kaart = new ImageView();
                kaart.setImage(model.getSpelerKaart(i));
                roofView.getChildren().add(kaart);

                double eindX = 66 + i * 93;
                double eindY = 290; 

                beweegKaartNaarPositie(kaart, eindX, eindY, 0.5);
            }

            ImageView Omgekeerdekaart = new ImageView();
            Omgekeerdekaart.setImage(model.getOmgekeerdeKaartAfbeelding());
            roofView.getChildren().add(Omgekeerdekaart);
            Omgekeerdekaart.setFitWidth(87); 
            Omgekeerdekaart.setFitHeight(123);
            double eindX = 66;  
            double eindY = 122; 
            beweegKaartNaarPositie(Omgekeerdekaart, eindX, eindY, 0.5);

            ImageView kaart = new ImageView();
            kaart.setImage(model.getDealerKaart(1));
            roofView.getChildren().add(kaart);
            kaart.setFitWidth(87); 
            kaart.setFitHeight(123);
            double eindX2 = 66 + 93;  
            double eindY2 = 122; 
            beweegKaartNaarPositie(kaart, eindX2, eindY2, 0.5);
            
            updateUI();

        } else {
            App.setRoot("Tafel");
        }
    }
    
        @FXML
    public void hitten(ActionEvent event) {
        model.nieuwPakWanneerLeeg();
        model.spelerTrekKaart();
        updateUI();
        
        if (model.isSpelerBoven21()) {
            double inzet = Double.parseDouble(inzetText.getText());
            String uitslag = model.bepaalUitslag(inzet);
            uitslagLabel.setText(uitslag);
            dealerImageViews[0].setImage(model.getDealerKaart(0));
            hitKnop.setDisable(true);
            standKnop.setDisable(true);
            volgendSpel();
        }

        ImageView nieuweKaart = new ImageView();
        nieuweKaart.setImage(model.getSpelerKaart(model.getSpelerHandGrootte() - 1));

        roofView.getChildren().add(nieuweKaart); 

        double eindX = 66 + (model.getSpelerHandGrootte() - 1) * 93;
        double eindY = 290; 

        beweegKaartNaarPositie(nieuweKaart,eindX, eindY, 0.5); 

    }

    @FXML
    public void standen(ActionEvent event) {
        dealerImageViews[0].setImage(model.getDealerKaart(0));

        model.dealerSpelen();
        updateUI();
        zichtbaarMaken();

        double inzet = Double.parseDouble(inzetText.getText());
        String uitslag = model.bepaalUitslag(inzet);
        uitslagLabel.setText(uitslag);
        budgetBlackJackLabel.setText("Budget: " + model.getBudget());

        volgendSpel();
    }
    
    public void zichtbaarMaken(){
        for (int i = 0; i < spelerImageViews.length; i++) {
            if (i < model.getSpelerHandGrootte()) { 
                spelerImageViews[i].setVisible(true);

            }
        }
        
        for (int i = 0; i < dealerImageViews.length; i++) {
            if (i < model.getDealerHandGrootte()) { 
                dealerImageViews[i].setVisible(true);
            }
            
        }
    }


    private void updateUI() {
        
        for (int i = 0; i < spelerImageViews.length; i++) {
            if (i < model.getSpelerHandGrootte()) {
                if (!spelerImageViews[i].isVisible()){   
                    spelerImageViews[i].setImage(model.getSpelerKaart(i));
                    spelerImageViews[i].setVisible(false);
                } 
            }
        }
        
        for (int i = 1; i < dealerImageViews.length; i++) {
            if (i < model.getDealerHandGrootte()) {
                if (!dealerImageViews[i].isVisible()){
                    dealerImageViews[0].setVisible(false);
                    dealerImageViews[i].setImage(model.getDealerKaart(i));
                     dealerImageViews[i].setVisible(false);
                }
            }
        }

     
        uitslagLabel.setText("");
    }

    private void volgendSpel() {
        Timer timer = new Timer();
        TimerTask taak = new TimerTask() {
            public void run() {
                Platform.runLater(() -> {
                    
                    model.startGame();
                    
                    dealerImageViews[0].setImage(model.getOmgekeerdeKaartAfbeelding());
                    
                    for (int i =0;i<5;i++) {
                        spelerImageViews[i].setVisible(false);
                        dealerImageViews[i].setVisible(false);   
                    }
                    
                    uitslagLabel.setText("");     
                    inzetText.setText("");
                    inzetLabel.setText("Inzet: ");
                    
                    hitKnop.setDisable(true);
                    standKnop.setDisable(true);
                    
                    hitKnop.toFront();
                    standKnop.toFront();
                    
                    
                });
            }
        };
        timer.schedule(taak, 3000);
    }

    private ImageView[] imageViewLijst(int aantal, double startX, double startY, double tussenruimte) {
        ImageView[] imageViews = new ImageView[aantal];
        for (int i = 0; i < aantal; i++) {
            ImageView imageView = new ImageView();
            imageView.setFitWidth(87);
            imageView.setFitHeight(123);
            imageView.setLayoutX(startX + i * tussenruimte);
            imageView.setLayoutY(startY);
            imageView.setVisible(false);
            imageViews[i] = imageView;
        }
        return imageViews;
    }
}
