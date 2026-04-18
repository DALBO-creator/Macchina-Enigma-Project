# Documentazione Tecnica - Simulatore Macchina Enigma

## Descrizione del Progetto

Questo progetto costituisce una riproduzione logica in Java e JavaFX dell'apparato di crittografia elettromeccanico tedesco per eccellenza: la Macchina Enigma. L'architettura software ricrea le strutture hardware originali e l'algoritmo di codifica in tempo reale su ogni singola pressione di un tasto.

## Contribuzioni

- Albo Davide: Modelli dati dei componenti `Rotore`, `Riflettore` e `PannelloPrese`.
- Righi Riccardo: Sistema logico di incapsulamento hardware (`MacchinaEnigma`) e controllo dei meccanismi di rotazione a cascata.
- Derkach Oleksandr: Architettura dell'interfaccia FXML e controller JavaFX.

---

## Architettura e Funzionamento

La macchina Enigma e' essenzialmente uno scambiatore polialfabetico. Ad ogni azionamento meccanico di un tasto, il percorso temporaneo del flusso di segnale si modifica tramite un sistema di ruote cablate interne.

La proprieta' crittografica intrinseca del modello e' la riflessione simmetrica. Dato uno stato interno (X), se la lettera _A_ viene transcodificata in _B_, la lettera _B_ verra' inevitabilmente transcodificata in _A_. Di conseguenza, la logica di codifica e decodifica e' un processo singolo bidirezionale vincolato ad azzerare il dispositivo alla configurazione di partenza pattuita tra le parti intercettanti.

**Flusso del segnale per singolo carattere:**
```text
Tasto Premuto -> Pannello Prese -> Rotore 3 -> Rotore 2 -> Rotore 1 -> Riflettore -> Rotore 1 -> Rotore 2 -> Rotore 3 -> Pannello Prese -> Lampadina d'Uscita (Lettera Cifrata)
```

---

## Dettaglio dei Moduli Implementati

### 1. Rotore (Albo Davide)

Il nucleo della componente crittografica. Sviluppato a partire dalla documentazione storica delle armate tedesche, implementa tre mappature di rotori standard su dischi a 26 contatti.

Configurazioni mappate storicamente:
- Rotore I: `EKMFLGDQVZNTOWYHXUSPAIBRCJ`
- Rotore II: `AJDKSIRUXBLHWTMCQGZNPYFVOE`
- Rotore III: `BDFHJLCPRTXVZNYEIWGAKMUSQO`

La rotazione hardware e' sequenziale a cascata. L'inserimento di ogni carattere provoca lo scatto in avanti del terzo rotore ("Rotore Destro"). Al completamento di 26 scatti, una tacca di ingaggio induce la rotazione del rotore adiacente e cosi' via.

### 2. Riflettore (Albo Davide)

Il componente terminale che funge da "specchio" elettrico ritornando passivamente la corrente nei rotori in senso inverso utilizzando lo standard Riflettore B (mappatura `YRUHQSLDPXNGOKMIEBFZCWVJAT`). Il Riflettore e' una struttura passiva statica (non prevede rotazioni).

### 3. Pannello Prese / Steckerbrett (Albo Davide)

Si tratta del livello di crittografia basato sull'operatore situato alle due estremita' del circuito (prima dell'ingresso nei rotori, o immediatamente prima dell'accensione della lampadina). Permette di incrociare elettricamente le corrispondenze di due caratteri (es. A con C).

### 4. MacchinaEnigma (Righi Riccardo)

Rappresenta l'astrazione di alto livello che unifica l'hardware.
La classe intercetta la richiesta di traduzione (`cifra(char c)`) del carattere prelevato dal controller in una singola stringa di esecuzione, iterando prima il passo di azionamento meccanico di ogni rotore, per poi sottomettere il carattere all'intero circuito di passaggio. Coordina il salvataggio o reset dello stato globale della macchina.

### 5. Interfaccia FXML e Controller (Derkach Oleksandr)

Il front-end si distacca dall'interazione console, offrendo un simulacro visivo dello Steckerbrett originale, della pulsantiera QWERTZ e delle lampadine luminose.
Recentemente ristrutturato, il layout visivo e' diviso orizzontalmente in due `VBox`:
- **Simulator Hardware (sinistra):** I tre monitor rotore (indicanti lo sfalsamento attuale del disco), il tabellone di output a lampadine, tastiera fittizia interattiva, e il tabellone configuratore di prese alla base.
- **Log Session Reporter (destra):** Monitoraggio a due console di testo ("Testo in chiaro", "Testo criptato") che accumulano passivamente i flussi d'ingresso e d'uscita al fine di offrire uno storico.

La pressione sul pulsante a schermo o la ricezione dell'evento JavaFX generato dalla tastiera informatica, istanzia `MacchinaEnigmaController.premi(char c)`. Questo fa scattare in sequenza logica l'animazione della lampadina e l'accodamento dello storico a schermo nelle Text Area a lato.

---

## Esecuzione di prova

```text
Condizione Hardware: Rotori bloccati a [A, A, A], Nessun ponte sul Pannello.

TEST DI CIFRATURA SIMMETRICA
Testo Originario: V U C F E L Z R R T
Testo Cifrato:    H E L L O W O R L D

Per decriptare con successo la sequenza H E L L O W O R L D, il processo esige il "Reset" per impostare i rotori nella medesima sequenza [A, A, A]. Eseguendo l'input cifrato, la logica restituira' coerentemente V U C F E L Z R R T.
```
