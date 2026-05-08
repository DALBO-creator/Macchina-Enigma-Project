package com.albo.macchinaenigma;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class MacchinaEnigmaController {

    @FXML private Label labelRotoreSinistro;
    @FXML private Label labelRotoreSinistroSu;
    @FXML private Label labelRotoreSinistroGiu;
    @FXML private Label labelRotoreMedio;
    @FXML private Label labelRotoreMedioSu;
    @FXML private Label labelRotoreMedioGiu;
    @FXML private Label labelRotoreDestro;
    @FXML private Label labelRotoreDestroSu;
    @FXML private Label labelRotoreDestroGiu;

    @FXML private HBox lampRow1;
    @FXML private HBox lampRow2;
    @FXML private HBox lampRow3;

    @FXML private HBox keyRow1;
    @FXML private HBox keyRow2;
    @FXML private HBox keyRow3;

    @FXML private HBox plugRow1;
    @FXML private HBox plugRow2;
    @FXML private HBox plugRow3;

    @FXML private TextField fieldPannelloPrese;

    @FXML private TextArea testoInChiaroArea;
    @FXML private TextArea testoCriptatoArea;

    private static final String[] RIGA1 = {"Q","W","E","R","T","Z","U","I","O"};
    private static final String[] RIGA2 = {"A","S","D","F","G","H","J","K"};
    private static final String[] RIGA3 = {"P","Y","X","C","V","B","N","M","L"};
    private static final String[][] COLORI_PLUG = {
            {"#aaddaa", "#114411"},  //verde
            {"#aaddff", "#114455"},  //azzurro
            {"#ffddaa", "#553311"},  //arancione
            {"#ffaadd", "#551133"},  //rosa
            {"#ddaaff", "#331155"},  //viola
            {"#aaffdd", "#115533"},  //menta
            {"#ffaaaa", "#551111"},  //rosso pastello
            {"#ffffaa", "#555511"},  //giallo
            {"#aaffff", "#115555"},  //ciano
            {"#ffddff", "#553355"},  //lilla
            {"#ddffaa", "#335511"},  //verde lime
            {"#ffd5aa", "#553311"},  //arancino..
            {"#aabbff", "#111155"},  //blu pastello
    };

    private Button[] buttons = new Button[26];

    private Label[] lampade = new Label[26];

    private Label[] plugLabels = new Label[26];

    private MacchinaEnigma macchina = new MacchinaEnigma();

    @FXML
    void pulisciTesti() {
        testoInChiaroArea.clear();
        testoCriptatoArea.clear();
    }

    @FXML
    void initialize() {
        creaLampade(RIGA1, lampRow1);
        creaLampade(RIGA2, lampRow2);
        creaLampade(RIGA3, lampRow3);

        creaTasti(RIGA1, keyRow1);
        creaTasti(RIGA2, keyRow2);
        creaTasti(RIGA3, keyRow3);

        creaPlug(RIGA1, plugRow1);
        creaPlug(RIGA2, plugRow2);
        creaPlug(RIGA3, plugRow3);

        aggiornaRotori();
    }

    private void creaLampade(String[] lettere, HBox riga) {
        for (String s : lettere) {
            Label lamp = new Label(s);
            lamp.setPrefSize(40, 40);
            lamp.setStyle(stileLampadaSpenta());
            lampade[s.charAt(0) - 'A'] = lamp;
            riga.getChildren().add(lamp);
        }
    }

    private void creaTasti(String[] lettere, HBox riga) {
        for (String s : lettere) {
            Button btn = new Button(s);
            btn.setPrefSize(40, 40);
            btn.setStyle(stileTasto());
            final char finalLettera = s.charAt(0);
            btn.setOnAction(e -> premi(finalLettera));
            buttons[finalLettera - 'A'] = btn;
            riga.getChildren().add(btn);
        }
    }

    private void creaPlug(String[] lettere, HBox riga) {
        for (String s : lettere) {
            Label plug = new Label(s);
            plug.setPrefSize(40, 40);
            plug.setStyle(stilePlug());
            plugLabels[s.charAt(0) - 'A'] = plug;
            riga.getChildren().add(plug);
        }
    }

    private void premi(char letteraOriginale) {
        spegniTutteLeLampade();

        char letteraCriptata = macchina.cifra(letteraOriginale);

        if (testoInChiaroArea != null && testoCriptatoArea != null) {
            testoInChiaroArea.appendText(String.valueOf(letteraOriginale));
            testoCriptatoArea.appendText(String.valueOf(letteraCriptata));
        }

        Label lampTarget = lampade[letteraCriptata - 'A'];
        if (lampTarget != null) {
            lampTarget.setStyle(stileLampadaAccesa());
            PauseTransition pausa = new PauseTransition(Duration.millis(500));
            pausa.setOnFinished(e -> lampTarget.setStyle(stileLampadaSpenta()));
            pausa.play();
        }

        aggiornaRotori();
    }

    private void aggiornaRotori() {
        aggiornaDisplay(labelRotoreSinistro, labelRotoreSinistroSu, labelRotoreSinistroGiu,
                macchina.getRotoreSinistro());
        aggiornaDisplay(labelRotoreMedio, labelRotoreMedioSu, labelRotoreMedioGiu,
                macchina.getRotoreMedio());
        aggiornaDisplay(labelRotoreDestro, labelRotoreDestroSu, labelRotoreDestroGiu,
                macchina.getRotoreDestro());
    }


    // Frecce rotori

    @FXML
    public void rotoreSinistroSu() {
        macchina.getRotoreSinistro().ruota();
        aggiornaRotori();
    }

    @FXML
    public void rotoreSinistroGiu() {
        macchina.getRotoreSinistro().ruotaIndietro();
        aggiornaRotori();
    }

    @FXML
    public void rotoreMedioSu() {
        macchina.getRotoreMedio().ruota();
        aggiornaRotori();
    }

    @FXML
    public void rotoreMedioGiu() {
        macchina.getRotoreMedio().ruotaIndietro();
        aggiornaRotori();
    }

    @FXML
    public void rotoreDestroSu() {
        macchina.getRotoreDestro().ruota();
        aggiornaRotori();
    }

    @FXML
    public void rotoreDestroGiu() {
        macchina.getRotoreDestro().ruotaIndietro();
        aggiornaRotori();
    }

    private void aggiornaDisplay(Label centro, Label su, Label giu, Rotore r) {
        char corrente = (char) ('A' + r.getPosizione());
        char sopra    = (char) ('A' + (r.getPosizione() + 1) % 26);
        char sotto    = (char) ('A' + (r.getPosizione() + 25) % 26);
        centro.setText("" + corrente);
        su.setText("" + sopra);
        giu.setText("" + sotto);
    }

    private void spegniTutteLeLampade() {
        for (Label l : lampade) {
            if (l != null) l.setStyle(stileLampadaSpenta());
        }
    }

    @FXML
    public void applicaConfigurazione() {
        String testo = fieldPannelloPrese.getText();
        macchina.configuraPannello(testo);

        // resetta i colori dei vari plug
        for (Label l : plugLabels) {
            if (l != null) l.setStyle(stilePlug());
        }

        // colora diversamente ogni scambio
        String[] parti = testo.toUpperCase().split(" ");
        for (int i = 0; i < parti.length; i++) {
            String coppia = parti[i];
            if (coppia.length() == 2) {
                // Prende il colore corrispondente dall'array di colori
                String[] colore = COLORI_PLUG[i % COLORI_PLUG.length];
                char a = coppia.charAt(0);
                char b = coppia.charAt(1);
                if (plugLabels[a - 'A'] != null)
                    plugLabels[a - 'A'].setStyle(stilePlug() +
                            "-fx-background-color: " + colore[0] + ";" +
                            "-fx-text-fill: " + colore[1] + ";");
                if (plugLabels[b - 'A'] != null)
                    plugLabels[b - 'A'].setStyle(stilePlug() +
                            "-fx-background-color: " + colore[0] + ";" +
                            "-fx-text-fill: " + colore[1] + ";");
            }
        }
    }

    @FXML
    public void resetConfigurazione() {
        macchina.reset();
        fieldPannelloPrese.clear();
        for (Label l : plugLabels) {
            if (l != null) l.setStyle(stilePlug());
        }
        spegniTutteLeLampade();
        aggiornaRotori();
    }

    @FXML
    public void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().isLetterKey()) {
            int pos = keyEvent.getCode().getChar().charAt(0) - 'A';
            buttons[pos].fire();
            buttons[pos].requestFocus();
        }
    }

    private String stileTasto() {
        return "-fx-background-color: #bbbbbb;" +
                "-fx-text-fill: #222222;" +
                "-fx-font-family: 'Courier New';" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 20;" +
                "-fx-border-radius: 20;" +
                "-fx-border-color: #999999;" +
                "-fx-border-width: 1;" +
                "-fx-cursor: hand;";
    }

    private String stileLampadaSpenta() {
        return "-fx-background-color: #cccccc;" +
                "-fx-text-fill: #999999;" +
                "-fx-font-family: 'Courier New';" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 20;" +
                "-fx-border-radius: 20;" +
                "-fx-border-color: #aaaaaa;" +
                "-fx-border-width: 1;" +
                "-fx-alignment: center;";
    }

    private String stileLampadaAccesa() {
        return "-fx-background-color: #ffff99;" +
                "-fx-text-fill: #222222;" +
                "-fx-font-family: 'Courier New';" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 20;" +
                "-fx-border-radius: 20;" +
                "-fx-border-color: #cccc00;" +
                "-fx-border-width: 2;" +
                "-fx-alignment: center;";
    }

    private String stilePlug() {
        return "-fx-background-color: #cccccc;" +
                "-fx-text-fill: #888888;" +
                "-fx-font-family: 'Courier New';" +
                "-fx-background-radius: 20;" +
                "-fx-border-radius: 20;" +
                "-fx-border-color: #aaaaaa;" +
                "-fx-border-width: 1;" +
                "-fx-alignment: center;";
    }
}