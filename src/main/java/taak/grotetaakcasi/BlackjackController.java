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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import taak.grotetaakcasi.App;
import taak.grotetaakcasi.PakKaarten;
import taak.grotetaakcasi.SpelerEnDealer;

public class BlackjackController implements Initializable {
    private PakKaarten pakKaarten;
    private SpelerEnDealer speler;
    private SpelerEnDealer dealer;
    
    @FXML
    private Label sout;
    
    @FXML
    private ImageView afbeeldingDealer;

    @FXML
    private ImageView afbeeldingDealer2;

    @FXML
    private ImageView afbeeldingDealer3;

    @FXML
    private ImageView afbeeldingDealer4;

    @FXML
    private ImageView afbeeldingDealer5;

    @FXML
    private ImageView afbeeldingSpeler;

    @FXML
    private ImageView afbeeldingSpeler2;

    @FXML
    private ImageView afbeeldingSpeler3;

    @FXML
    private ImageView afbeeldingSpeler4;

    @FXML
    private ImageView afbeeldingSpeler5;

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
    
    @FXML
    private ImageView pakje1;

    @FXML
    private ImageView pakje2;

    @FXML
    private ImageView pakje3;

    private ImageView[] afbeeldingSpelerArray;
    private ImageView[] afbeeldingDealerArray;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        budgetBlackJackLabel.setText("Budget: " + App.getBedragen().getTotaleBedrag());

        Rectangle tafel = new Rectangle(0, 90, 640, 590);
        tafel.setFill(Color.GREEN);
        roofView.getChildren().addAll(tafel);
        
        tafel.toBack();

        afbeeldingSpeler.toFront();
        afbeeldingSpeler2.toFront();
        afbeeldingSpeler3.toFront();
        afbeeldingSpeler4.toFront();
        afbeeldingSpeler5.toFront();
        
        afbeeldingDealer.toFront();
        afbeeldingDealer2.toFront();
        afbeeldingDealer3.toFront();
        afbeeldingDealer4.toFront();
        afbeeldingDealer5.toFront();
        
        hitKnop.setDisable(true); 
        standKnop.setDisable(true);
        
        dealerKaartenLabel.setVisible(true);
        spelersKaartenLabel.setVisible(true);
        
        afbeeldingSpeler.setVisible(false);
        afbeeldingSpeler2.setVisible(false);
        afbeeldingSpeler3.setVisible(false);
        afbeeldingSpeler4.setVisible(false);
        afbeeldingSpeler5.setVisible(false);

        afbeeldingDealer.setVisible(false);
        afbeeldingDealer2.setVisible(false);
        afbeeldingDealer3.setVisible(false);
        afbeeldingDealer4.setVisible(false);
        afbeeldingDealer5.setVisible(false);
        
        pakKaarten = new PakKaarten();
        pakKaarten.kaartenSchudden();

        speler = new SpelerEnDealer();
        dealer = new SpelerEnDealer();

        speler.voegKaartToe(pakKaarten.trekKaart());
        speler.voegKaartToe(pakKaarten.trekKaart());
        dealer.voegKaartToe(pakKaarten.trekKaart());
        dealer.voegKaartToe(pakKaarten.trekKaart());

        updateLabels();
        
        afbeeldingSpelerArray = new ImageView[] {afbeeldingSpeler, afbeeldingSpeler2, afbeeldingSpeler3, afbeeldingSpeler4, afbeeldingSpeler5};
        afbeeldingDealerArray = new ImageView[] {afbeeldingDealer, afbeeldingDealer2, afbeeldingDealer3, afbeeldingDealer4, afbeeldingDealer5};
    }
    
    @FXML
    public void terugNaarMenu(ActionEvent event) throws IOException {
        App.setRoot("menu");
    }

    @FXML
    public void hitten(ActionEvent event) {
        speler.voegKaartToe(pakKaarten.trekKaart());

        for (int i = 0; i < speler.getHand().size(); i++) {
            if (i < 5) {
                afbeeldingSpelerArray[i].setImage(speler.getHand().get(i).getAfbeelding());
                afbeeldingSpelerArray[i].setVisible(true);
            }
        }

        if (speler.berekenWaardeHand() > 21) {
            uitslagLabel.setText("De dealer wint.");
            volgendSpel();
        }
    }

    @FXML
    public void standen(ActionEvent event) throws IOException {
        while (dealer.berekenWaardeHand() < 17) {
            dealer.voegKaartToe(pakKaarten.trekKaart());
        }

        for (int i = 0; i < dealer.getHand().size(); i++) {
            if (i < 5) {
                afbeeldingDealerArray[i].setImage(dealer.getHand().get(i).getAfbeelding());
                afbeeldingDealerArray[i].setVisible(true);
            }
        }

        int spelerScore = speler.berekenWaardeHand();
        int dealerScore = dealer.berekenWaardeHand();

        if (dealerScore > 21 || spelerScore > dealerScore) {
            uitslagLabel.setText("Je wint!");
            App.getBedragen().voegBedragToe((Double.parseDouble(inzetText.getText())) * 2);
            budgetBlackJackLabel.setText("Bedrag: " + App.getBedragen().getTotaleBedrag());
        } else if (dealerScore > spelerScore) {
            uitslagLabel.setText("De dealer wint.");
        } else {
            uitslagLabel.setText("Het is een gelijkspel.");
            App.getBedragen().voegBedragToe(Double.parseDouble(inzetText.getText()));
            budgetBlackJackLabel.setText("Bedrag: " + App.getBedragen().getTotaleBedrag());
        }
        volgendSpel();
    }

    @FXML
    public void inzetten(ActionEvent event) throws IOException {
        double inzet = Double.parseDouble(inzetText.getText());
        if (inzet <= App.getBedragen().getTotaleBedrag()) {
            App.getBedragen().geldInnen(inzet);
            inzetLabel.setText("Inzet: " + inzet);
            budgetBlackJackLabel.setText("Budget: " + App.getBedragen().getTotaleBedrag());
            hitKnop.setDisable(false); 
            standKnop.setDisable(false);
            
            dealerKaartenLabel.setVisible(true);
            spelersKaartenLabel.setVisible(true);
            
            afbeeldingSpeler.setVisible(true);
            afbeeldingSpeler2.setVisible(true);
            afbeeldingSpeler3.setVisible(true);
            afbeeldingSpeler4.setVisible(true);
            afbeeldingSpeler5.setVisible(true);

            afbeeldingDealer.setVisible(true);
            afbeeldingDealer2.setVisible(true);
            afbeeldingDealer3.setVisible(true);
            afbeeldingDealer4.setVisible(true);
            afbeeldingDealer5.setVisible(true);
        } else {
            sout.setText("niet genoeg geld");
            App.setRoot("Tafel");
        }
    }
    
    public void update() {
        if (pakKaarten.isLeeg()) {
            pakKaarten = new PakKaarten(); 
            pakKaarten.kaartenSchudden(); 
        }

        speler.resetHand();
        dealer.resetHand();

        hitKnop.setDisable(true); 
        standKnop.setDisable(true);

        for (int i = 0; i < 5; i++) {
            afbeeldingSpelerArray[i].setVisible(false);
            afbeeldingDealerArray[i].setVisible(false);
        }

        speler.voegKaartToe(pakKaarten.trekKaart());
        speler.voegKaartToe(pakKaarten.trekKaart());
        
        dealer.voegKaartToe(pakKaarten.trekKaart());
        dealer.voegKaartToe(pakKaarten.trekKaart());
       
        for (int i = 0; i < 5; i++) {
            afbeeldingSpelerArray[i].setImage(null);
            afbeeldingDealerArray[i].setImage(null);
        }
        
        uitslagLabel.setText(null);
        inzetText.clear();
        inzetLabel.setText("Inzet: ");
        updateLabels();
    }
        
    public void volgendSpel() {
        Timer timer = new Timer();
        TimerTask taak = new TimerTask() {
            public void run() {
                Platform.runLater(() -> update());
            }
        };
        timer.schedule(taak, 2000);
    }

    public void updateLabels() {
        afbeeldingSpeler.setImage(speler.getHand().get(0).getAfbeelding());
        afbeeldingSpeler2.setImage(speler.getHand().get(1).getAfbeelding());
        afbeeldingDealer.setImage(dealer.getHand().get(0).getAfbeelding());
        afbeeldingDealer2.setImage(dealer.getHand().get(1).getAfbeelding());
    }
}