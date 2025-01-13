package taak.grotetaakcasi;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

    private ArrayList<ImageView> afbeeldingSpelerLijst = new ArrayList<>();
    private ArrayList<ImageView> afbeeldingDealerLijst = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Rectangle tafel = new Rectangle(0, 90, 640, 590);
        tafel.setFill(Color.GREEN);
        roofView.getChildren().addAll(tafel);
        tafel.toBack();
        
        hitKnop.setDisable(true);
        standKnop.setDisable(true);
        
        model = new BlackJackModel();
        budgetBlackJackLabel.setText("Budget: " + App.getBedragen().getTotaleBedrag());

        dealerKaartenLabel.setVisible(true);
        spelersKaartenLabel.setVisible(true);
        
        model.startGame();

        for (int i = 0; i < 10; i++) {
            ImageView spelerKaart = new ImageView();
            spelerKaart.setVisible(false);
            afbeeldingSpelerLijst.add(spelerKaart);
            roofView.getChildren().add(spelerKaart);

            ImageView dealerKaart = new ImageView();
            dealerKaart.setVisible(false);
            afbeeldingDealerLijst.add(dealerKaart);
            roofView.getChildren().add(dealerKaart);
        }
        
        

       updateSpelerLabels();
       updateDealerLabels();
    }

    @FXML
    public void inzetten(ActionEvent event) throws IOException {
        double inzet = Double.parseDouble(inzetText.getText());
        if (!model.isInzetGeldig(inzet)) {
            App.setRoot("Tafel");
        } else {
            model.setInzet(inzet);
            inzetLabel.setText("Inzet: " + inzet);
            budgetBlackJackLabel.setText("Budget: " + model.getBudget());
            hitKnop.setDisable(false);
            standKnop.setDisable(false);

            updateSpelerLabels();
            updateDealerLabels();
            
            for (ImageView imageView : afbeeldingSpelerLijst) {
                imageView.setVisible(true);
            }
            for (ImageView imageView : afbeeldingDealerLijst) {
                
                imageView.setVisible(true);
            }
        }
    }

    @FXML
    public void hitten(ActionEvent event) {
        model.spelerTrekKaart();
        updateSpelerLabels();

        if (model.isSpelerBoven21()) {
            uitslagLabel.setText("dealer wint");
            hitKnop.setDisable(true);
            volgendSpel();
        }
    }

    @FXML
    public void standen(ActionEvent event) throws IOException {
        model.dealerSpelen();
        updateDealerLabels();
        
        model.bepaalUitslag(Double.parseDouble(inzetText.getText()));

        String uitslag = model.bepaalUitslag(Double.parseDouble(inzetText.getText()));

        uitslagLabel.setText(uitslag);

        volgendSpel();
    }

     @FXML
    public void updateSpelerLabels() {
        for (int i = 0; i < model.getSpelerHandGrootte(); i++) {
            if (afbeeldingSpelerLijst.get(i).getImage() == null) {
                afbeeldingSpelerLijst.get(i).setImage(model.getSpelerKaart(i));
                afbeeldingSpelerLijst.get(i).setLayoutX(66.0 + i * 93);
                afbeeldingSpelerLijst.get(i).setLayoutY(290.0);
                afbeeldingSpelerLijst.get(i).setFitHeight(123.0);
                afbeeldingSpelerLijst.get(i).setFitWidth(87.0);
                
            }
        }
    }
        

    public void updateDealerLabels() {
        for (int i = 0; i < model.getDealerHandGrootte(); i++) {
            if (afbeeldingDealerLijst.get(i).getImage() == null) {
                afbeeldingDealerLijst.get(i).setImage(model.getDealerKaart(i));
                afbeeldingDealerLijst.get(i).setLayoutX(66.0 + i * 93);
                afbeeldingDealerLijst.get(i).setLayoutY(122.0);
                afbeeldingDealerLijst.get(i).setFitHeight(123.0);
                afbeeldingDealerLijst.get(i).setFitWidth(87.0);
                
            }
        }
    }

    public void volgendSpel() {
        Timer timer = new Timer();
        TimerTask taak = new TimerTask() {
            public void run() {
                Platform.runLater(() -> resetSpel());
            }
        };
        timer.schedule(taak, 2000);
    }

    @FXML
    public void terugNaarMenu(ActionEvent event) throws IOException {
        App.setRoot("menu");
    }

    public void resetSpel() {
        model.resetGame();

        hitKnop.setDisable(true);
        standKnop.setDisable(true);

        for (ImageView imageView : afbeeldingSpelerLijst) {
            imageView.setImage(null);
            imageView.setVisible(false);

        }
        for (ImageView imageView : afbeeldingDealerLijst) {
            imageView.setImage(null);
            imageView.setVisible(false);
            
        }

        uitslagLabel.setText(null);
        inzetLabel.setText(null);
        inzetText.setText(null);


    }
}
