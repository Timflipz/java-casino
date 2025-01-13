package taak.grotetaakcasi;

public class Bedragen {
    private double totaalBudget = 0;  

    public void voegBedragToe(double bedrag) {
        totaalBudget += bedrag; 
    }

    public void geldInnen(double bedrag) {
        if (totaalBudget >= bedrag) {
            totaalBudget -= bedrag; 
        } else {
            System.out.println("Actie niet mogelijk.");
        }
    }
    
    public double getTotaleBedrag() {
        return totaalBudget;
    }
}

