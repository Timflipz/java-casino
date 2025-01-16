
package taak.grotetaakcasi;

import javafx.scene.image.Image;

public class HogerLagerModel {
    private double totaalBedrag;   
    private double inzetBedrag;    
    private int vorigGetal;        
    private int huidigGetal;       
    private boolean spelerKiestHoger; 

    
    

    // Controleer of de keuze van de speler correct is
  

    // Stel de inzet van de speler in
    public void setInzet(double inzet) {
        this.inzetBedrag = inzet; 
    }

    // Verkrijg het huidige totaalbedrag van de speler
    public double getTotaalBedrag() {
        return totaalBedrag;
    }

    // Controleer of de speler genoeg geld heeft om een inzet te plaatsen
    public boolean isInzetGeldig() {
        return inzetBedrag <= totaalBedrag;
    }

 
}