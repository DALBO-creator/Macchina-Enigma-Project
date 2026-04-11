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
    // Crea i bottoni per una riga e li aggiunge all'HBox
    private void creaTasti(String[] lettere, HBox riga) {
        for (String s : lettere) {
            Button btn = new Button(s);
            btn.setPrefSize(40, 40);
            btn.setStyle(stileTasto());
            // Variabile finale per poter essere usata nella lambda
            final char finalLettera = s.charAt(0);
            // Aggiunge l'evento al bottone
            btn.setOnAction(e -> premi(finalLettera));
            // Salva il bottone nel vettore usando l'indice della lettera
            buttons[finalLettera - 'A'] = btn;
            riga.getChildren().add(btn);
        }
    }

    // Metodo principale chiamato ad ogni pressione di un tasto
    private void premi(char c) {
        // Spegne tutte le lampade prima di accenderne una nuova
        spegniTutteLeLampade();

        // Cifra la lettera tramite la logica Enigma
        char cifrata = macchina.cifra(c);

        // Accende la lampada corrispondente alla lettera cifrata
        lampade[cifrata - 'A'].setStyle(stileLampadaAccesa());

        // Aggiorna il display dei rotori dopo la rotazione
        aggiornaRotori();
    }

    // Aggiorna le label dei 3 rotori mostrando lettera corrente, sopra e sotto
    private void aggiornaRotori() {
        aggiornaDisplay(labelRotoreSinistro, labelRotoreSinistroSu, labelRotoreSinistroGiu,
                macchina.getRotoreSinistro());
        aggiornaDisplay(labelRotoreMedio, labelRotoreMedioSu, labelRotoreMedioGiu,
                macchina.getRotoreMedio());
        aggiornaDisplay(labelRotoreDestro, labelRotoreDestroSu, labelRotoreDestroGiu,
                macchina.getRotoreDestro());
    }

    // Aggiorna un singolo display rotore con la lettera corrente, quella sopra e quella sotto
    private void aggiornaDisplay(Label centro, Label su, Label giu, Rotore r) {
        char corrente = (char) ('A' + r.getPosizione());
        char sopra    = (char) ('A' + (r.getPosizione() + 1) % 26);
        char sotto    = (char) ('A' + (r.getPosizione() + 25) % 26);
        centro.setText("" + corrente);
        su.setText("" + sopra);
        giu.setText("" + sotto);
    }

    // Spegne tutte le lampade riportandole allo stile di default
    private void spegniTutteLeLampade() {
        for (Label l : lampade) {
            if (l != null) l.setStyle(stileLampadaSpenta());
        }
    }

    /**
     * Metodo chiamato quando si preme un tasto sulla tastiera fisica.
     * Calcola l'indice del bottone corrispondente e simula la sua pressione.
     */
    public void onKeyPressed(KeyEvent keyEvent) {
        // Controlla se il tasto premuto è una lettera
        if (keyEvent.getCode().isLetterKey()) {
            // Calcola l'indice nel vettore del bottone corrispondente
            int pos = keyEvent.getCode().getChar().charAt(0) - 'A';
            // Simula la pressione del bottone e lo mette a fuoco
            buttons[pos].fire();
            buttons[pos].requestFocus();
        }
    }

    // --- STILI ---

    private String stileTasto() {
        return "-fx-background-color: #2a2a2a;" +
                "-fx-text-fill: white;" +
                "-fx-font-family: 'Courier New';" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 20;" +
                "-fx-border-radius: 20;" +
                "-fx-border-color: #555;" +
                "-fx-border-width: 1;" +
                "-fx-cursor: hand;";
    }

    private String stileLampadaSpenta() {
        return "-fx-background-color: #1a1a1a;" +
                "-fx-text-fill: #444444;" +
                "-fx-font-family: 'Courier New';" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 20;" +
                "-fx-border-radius: 20;" +
                "-fx-border-color: #333;" +
                "-fx-border-width: 1;" +
                "-fx-alignment: center;";
    }

    private String stileLampadaAccesa() {
        return "-fx-background-color: #ffffaa;" +
                "-fx-text-fill: #222222;" +
                "-fx-font-family: 'Courier New';" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 20;" +
                "-fx-border-radius: 20;" +
                "-fx-border-color: #dddd00;" +
                "-fx-border-width: 2;" +
                "-fx-alignment: center;" +
                "-fx-effect: dropshadow(gaussian, #ffff00, 10, 0.8, 0, 0);";
    }
}