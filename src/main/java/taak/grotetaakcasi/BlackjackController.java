package taak.grotetaakcasi;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.PauseTransition;
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
    private BlackJackView blackjackview;
    private KaartView kaartView;
    private BedragenView bedragenView;
    
    @FXML
    private Button volgendSpelButton;


    @FXML
    private Label bjlabel;

    @FXML
    private Label dealerKaartenLabel;

    @FXML
    private Button hitKnop;

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

    private ArrayList<ImageView> spelerImageViews;
    private ArrayList<ImageView> dealerImageViews;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = new BlackJackModel();
        blackjackview = new BlackJackView(roofView);
        bedragenView = new BedragenView(roofView);

        blackjackview.maakTafel();
        blackjackview.voegPakKaartenToe();

        spelerImageViews = blackjackview.maakImageViewLijst(10, 66, 290, 93);
        dealerImageViews = blackjackview.maakImageViewLijst(10, 66, 122, 93);
        dealerImageViews.get(0).setImage(model.getOmgekeerdeKaartAfbeelding());
        
        kaartView = new KaartView(roofView, blackjackview, model, spelerImageViews, dealerImageViews);

        hitKnop.setDisable(true);
        standKnop.setDisable(true);
        
        hitKnop.toFront();
        standKnop.toFront();

        dealerKaartenLabel.setVisible(true);
        spelersKaartenLabel.setVisible(true);
        
        bedragenView.getBudgetLabel().setText("Budget: " + model.getBudget());
        

        model.startGame();
        kaartView.kaartenToevoegenAanAfbeeldingenlijst();
    }

    @FXML
    public void terugNaarMenu(ActionEvent event) throws IOException {
        App.setRoot("menu");
    }
    
    @FXML
    public void inzetten(ActionEvent event) throws IOException {
        double inzet = Double.parseDouble(inzetText.getText());
        if (model.isInzetGeldig(inzet)) {
            model.setInzet(inzet);
            
            bedragenView.updateInzetLabel( inzet);
            bedragenView.getBudgetLabel().setText("Budget: " + model.getBudget());
            
            hitKnop.setDisable(false);
            standKnop.setDisable(false);

            kaartView.beginSpelNaInzetten();
            
            kaartView.kaartenToevoegenAanAfbeeldingenlijst();

        } else {
            App.setRoot("Tafel");
        }
    }
    
    @FXML
    public void hitten(ActionEvent event) {
        model.nieuwPakWanneerLeeg();
        model.spelerTrekKaart();
        kaartView.kaartenToevoegenAanAfbeeldingenlijst();
        kaartView.toonNieuweSpelerKaart(model.getSpelerHandGrootte());
        
        if (model.isSpelerBoven21()) {
            double inzet = Double.parseDouble(inzetText.getText());
            String uitslag = model.bepaalUitslag(inzet);
            uitslagLabel.setText(uitslag);
            dealerImageViews.get(0).setImage(model.getDealerKaartAfbeelding(0));
            hitKnop.setDisable(true);
            standKnop.setDisable(true);
            volgendSpelMetTijd();
        } 

    }

    @FXML
    public void standen(ActionEvent event) {
        dealerImageViews.get(0).setImage(model.getDealerKaartAfbeelding(0));

        model.dealerSpelen();
        kaartView.kaartenToevoegenAanAfbeeldingenlijst();
        kaartView.dealerKaartenZichtbaarMaken(model.getDealerHandGrootte(),
                                              spelerImageViews,
                                              dealerImageViews);

        double inzet = Double.parseDouble(inzetText.getText());
        String uitslag = model.bepaalUitslag(inzet);
        uitslagLabel.setText(uitslag);
        bedragenView.getBudgetLabel().setText("Budget: " + model.getBudget());

        volgendSpelMetTijd();
    }
    
    public void volgendSpel() {
        model.startGame();
        kaartView.resetSpel();

        uitslagLabel.setText("");     
        inzetText.setText("");
        bedragenView.resetInzetLabel();

        hitKnop.setDisable(true);
        standKnop.setDisable(true);
        hitKnop.toFront();
        standKnop.toFront();
    }

    public void volgendSpelMetTijd() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    model.startGame();
                    kaartView.resetSpel();

                    uitslagLabel.setText("");     
                    inzetText.setText("");
                    bedragenView.resetInzetLabel();

                    hitKnop.setDisable(true);
                    standKnop.setDisable(true);
                    hitKnop.toFront();
                    standKnop.toFront();
                    timer.cancel();
                });
            }
        }, 3000); 
    }
}
