    module taak.grotetaakcasi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens taak.grotetaakcasi to javafx.fxml;
    exports taak.grotetaakcasi;
}
