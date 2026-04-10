# 🔐 Macchina Enigma — Simulatore JavaFX

Simulazione della macchina Enigma sviluppata in JavaFX come progetto scolastico.
Il programma riproduce il funzionamento crittografico della macchina usata
dalla Germania nella Seconda Guerra Mondiale.

## 👥 Autori

| Nome | Ruolo |
|---|---|
| **Albo Davide** | Backend – Componenti Enigma (`Rotore`, `Riflettore`, `PannelloPrese`) |
| **Righi Riccardo** | Backend – Logica di cifratura e rotazione (`MacchinaEnigma`) |
| **Derkach Oleksandr** | Frontend – Interfaccia JavaFX (FXML + Controller) |

## ⚙️ Requisiti

- Java 17+
- JavaFX 17+
- Maven 3.8+

## 🚀 Come avviare

```bash
git clone https://github.com/...
cd macchina-enigma
mvn javafx:run
```

## 🎮 Come usare

1. Imposta la posizione iniziale dei 3 rotori (A–Z)
2. Inserisci le coppie del pannello prese — opzionale (es. `AB CD EF`)
3. Clicca **Applica**
4. Premi i tasti della tastiera a schermo oppure i tasti fisici
5. La lettera cifrata appare evidenziata, il testo si accumula nel campo output

Per **decifrare**: usa la stessa configurazione iniziale e inserisci il testo cifrato.

## ✅ Esempio

```
Configurazione: rotori AAA, nessun pannello prese

Input:  HELLO
Output: MFNCU

Reset alla stessa configurazione:
Input:  MFNCU
Output: HELLO  ✅
```

## 📁 Struttura del progetto

```
src/main/java/com/albo/macchinaenigma/
├── MacchinaEnigmaApplication.java   # Avvia l'applicazione
├── MacchinaEnigmaController.java    # Collega UI e logica
├── MacchinaEnigma.java              # Logica di cifratura completa
├── Rotore.java                      # Singolo rotore con mappatura
├── Riflettore.java                  # Riflettore fisso
└── PannelloPrese.java               # Plugboard configurabile

src/main/resources/com/albo/macchinaenigma/
└── MacchinaEnigma-view.fxml         # Layout interfaccia
```
