    module taak.grotetaakcasi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    // Openen voor FXML
    opens taak.grotetaakcasi to javafx.fxml;
    opens taak.grotetaakcasi.mines to javafx.fxml;  // Zorg ervoor dat dit goed is

    // Exporteren van de pakketten
    exports taak.grotetaakcasi;
    exports taak.grotetaakcasi.mines;  // Zorg ervoor dat dit juist is
}