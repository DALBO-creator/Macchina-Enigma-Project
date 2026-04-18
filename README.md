# Macchina Enigma - Simulatore Software

Simulazione della macchina Enigma sviluppata in JavaFX.
Il programma riproduce fedelmente il funzionamento crittografico del dispositivo elettromeccanico storico.

## Autori e Contributi

| Nominativo | Ruolo e Componenti Sviluppati |
|---|---|
| Albo Davide | Backend - Strutture dati hardware: `Rotore`, `Riflettore`, `PannelloPrese` |
| Righi Riccardo | Backend - Logica di orchestrazione e algoritmo di cifratura: `MacchinaEnigma` |
| Derkach Oleksandr | Frontend - Integrazione UI/UX (JavaFX, FXML, Controller) |

## Requisiti di Sistema

- Java Development Kit (JDK) 17 o superiore
- JavaFX 17 o superiore
- Apache Maven 3.8 o superiore

## Modalita' di Esecuzione

```bash
git clone <repository_url>
cd MacchinaEnigma
mvn clean javafx:run
```

## Istruzioni di Utilizzo

1. L'interfaccia si presenta divisa in due pannelli: la simulazione hardware a sinistra e il log testuale a destra.
2. In basso, nel Pannello Prese, inserire le coppie di connessione desiderate (es. "AB CD EF").
3. Premere "Applica" per confermare la configurazione hardware iniziale.
4. Premere i tasti della tastiera circolare simulata, oppure interagire mediante la tastiera fisica del computer.
5. Il percorso del segnale accendera' la lampada crittografata corrispondente.
6. A destra, le TextArea "TESTO IN CHIARO" e "TESTO CRIPTATO" memorizzeranno progressivamente le sessioni in ingresso e in uscita.

La cifratura e' simmetrica: per decifrare un messaggio, e' sufficiente resettare la macchina alla configurazione iniziale esatta e immettere il testo cifrato.

## Esempio di Verifica della Simmetria

```
Configurazione inziale: Rotori posizionati in [A, A, A], nessun cavo nel pannello prese.

Fase 1 (Cifratura):
Input:  H E L L O
Output: V U C F E

Fase 2 (Decifratura con Reset a [A, A, A]):
Input:  V U C F E
Output: H E L L O
```

## Struttura del Progetto

```text
src/main/java/com/albo/macchinaenigma/
├── MacchinaEnigmaApplication.java
├── MacchinaEnigmaController.java
├── MacchinaEnigma.java
├── Rotore.java
├── Riflettore.java
└── PannelloPrese.java

src/main/resources/com/albo/macchinaenigma/
└── MacchinaEnigma-view.fxml
```
