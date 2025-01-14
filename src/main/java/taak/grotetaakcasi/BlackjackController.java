package taak.grotetaakcasi;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
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
            stapelAfbeelding.setLayoutX(500 - (i * 10));  // Verschillende X-waarden
            stapelAfbeelding.setLayoutY(122);
            stapelAfbeelding.setFitHeight(123.0);
            stapelAfbeelding.setFitWidth(87.0);
            stapelAfbeelding.setImage(new Image(getClass().getResourceAsStream("/afbeeldingen/OmgekeerdeKaart.png")));
            roofView.getChildren().add(stapelAfbeelding);
        }


        spelerImageViews = imageViewLijst(5, 66, 290, 93);
        dealerImageViews = imageViewLijst(5, 66, 122, 93);

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

        dealerKaartenLabel.setVisible(true);
        spelersKaartenLabel.setVisible(true);

        model.startGame();
        updateUI();
        
        spelerImageViews[0].setVisible(false);
        spelerImageViews[1].setVisible(false);
        dealerImageViews[1].setVisible(false);
        dealerImageViews[0].setImage(model.getOmgekeerdeKaartAfbeelding());
        dealerImageViews[0].setVisible(false);
    }

    @FXML
    public void terugNaarMenu(ActionEvent event) throws IOException {
        App.setRoot("menu");
    }

    @FXML
    public void hitten(ActionEvent event) {
        
        model.nieuwPakWanneerLeeg();
        model.spelerTrekKaart();
        updateUI();

        if (model.isSpelerBoven21()) {
            uitslagLabel.setText("De dealer wint.");
            volgendSpel();
        }
    }
    
    @FXML
    public void standen(ActionEvent event) {
        dealerImageViews[0].setImage(model.getDealerKaart(0));

        model.dealerSpelen();
        updateUI();

        double inzet = Double.parseDouble(inzetText.getText());
        String uitslag = model.bepaalUitslag(inzet);

        uitslagLabel.setText(uitslag);
        budgetBlackJackLabel.setText("Budget: " + model.getBudget());

        volgendSpel();
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
                updateUI();
            } else {
                App.setRoot("Tafel");
            }
       
    }

    private void updateUI() {
        for (int i = 0; i < spelerImageViews.length; i++) {
            if (i < model.getSpelerHandGrootte()) {
                spelerImageViews[i].setImage(model.getSpelerKaart(i));
                spelerImageViews[i].setVisible(true);
            } else {
                spelerImageViews[i].setVisible(false);
            }
        }

        for (int i = 1; i < dealerImageViews.length; i++) {
            if (i < model.getDealerHandGrootte()) {
                dealerImageViews[i].setImage(model.getDealerKaart(i));
                dealerImageViews[i].setVisible(true);
            } else {
                dealerImageViews[i].setVisible(false);
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
                 
                    updateUI();
                    spelerImageViews[0].setVisible(false);
                    spelerImageViews[1].setVisible(false);
                    dealerImageViews[1].setVisible(false);
                    dealerImageViews[0].setImage(model.getOmgekeerdeKaartAfbeelding());
                    dealerImageViews[0].setVisible(false);
                    inzetText.setText("");
                    inzetLabel.setText("Inzet: ");
                });
            }
        };
        timer.schedule(taak, 2000);
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
