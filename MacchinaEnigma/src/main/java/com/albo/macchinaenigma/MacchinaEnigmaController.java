package com.albo.macchinaenigma;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MacchinaEnigmaController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}