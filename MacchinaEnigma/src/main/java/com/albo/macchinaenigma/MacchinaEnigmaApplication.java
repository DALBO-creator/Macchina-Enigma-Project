package com.albo.macchinaenigma;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MacchinaEnigmaApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MacchinaEnigmaApplication.class.getResource("MacchinaEnigma-view.fxml"));
        // allarga la finestra da 520 a 800 per fare spazio al testo a destra
        Scene scene = new Scene(fxmlLoader.load(), 800, 700);
        stage.setTitle("Macchina Enigma");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}