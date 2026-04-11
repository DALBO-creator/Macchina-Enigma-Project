package com.albo.macchinaenigma;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

public class MacchinaEnigmaController {

    // Display dei 3 rotori (lettera corrente + sopra + sotto)
    @FXML private Label labelRotoreSinistro;
    @FXML private Label labelRotoreSinistroSu;
    @FXML private Label labelRotoreSinistroGiu;
    @FXML private Label labelRotoreMedio;
    @FXML private Label labelRotoreMedioSu;
    @FXML private Label labelRotoreMedioGiu;
    @FXML private Label labelRotoreDestro;
    @FXML private Label labelRotoreDestroSu;
    @FXML private Label labelRotoreDestroGiu;

    // Righe del pannello lampade
    @FXML private HBox lampRow1;
    @FXML private HBox lampRow2;
    @FXML private HBox lampRow3;

    // Righe della tastiera
    @FXML private HBox keyRow1;
    @FXML private HBox keyRow2;
    @FXML private HBox keyRow3;

    // Layout tastiera tedesca (come nell'immagine)
    private static final String[] RIGA1 = {"Q","W","E","R","T","Z","U","I","O"};
    private static final String[] RIGA2 = {"A","S","D","F","G","H","J","K"};
    private static final String[] RIGA3 = {"P","Y","X","C","V","B","N","M","L"};

    // Vettore bottoni tastiera, indice = lettera - 'A'
    private Button[] buttons = new Button[26];

    // Vettore lampade, stesso indice
    private Label[] lampade = new Label[26];

    // Logica Enigma
    private MacchinaEnigma macchina = new MacchinaEnigma();

    @FXML
    void initialize() {
        // Crea le lampade e i tasti per ogni riga
        creaLampade(RIGA1, lampRow1);
        creaLampade(RIGA2, lampRow2);
        creaLampade(RIGA3, lampRow3);

        creaTasti(RIGA1, keyRow1);
        creaTasti(RIGA2, keyRow2);
        creaTasti(RIGA3, keyRow3);

        // Mostra la posizione iniziale dei rotori
        aggiornaRotori();
    }

    // Crea le lampade (cerchi spenti) per una riga e le aggiunge all'HBox
    private void creaLampade(String[] lettere, HBox riga) {
        for (String s : lettere) {
            Label lamp = new Label(s);
            lamp.setPrefSize(40, 40);
            lamp.setStyle(stileLampadaSpenta());
            // Salva la lampada nel vettore usando l'indice della lettera
            lampade[s.charAt(0) - 'A'] = lamp;
            riga.getChildren().add(lamp);
        }
    }
    