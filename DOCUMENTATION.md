# 📖 Documentazione Tecnica — Macchina Enigma

## Descrizione del progetto

Questo progetto è una simulazione software della macchina Enigma, il celebre dispositivo crittografico utilizzato dalla Germania durante la Seconda Guerra Mondiale. L'applicazione è sviluppata in Java con interfaccia grafica JavaFX e riproduce fedelmente il meccanismo di cifratura originale, comprensivo di rotori, riflettore e pannello prese.

Il progetto è stato realizzato da tre studenti: Albo Davide, che ha sviluppato i componenti hardware simulati (rotori, riflettore e pannello prese); Righi Riccardo, che ha implementato la logica centrale di cifratura e il meccanismo di rotazione; Derkach Oleksandr, che ha curato l'interfaccia grafica e l'integrazione tra UI e backend.

---

## Come funziona la macchina Enigma

La macchina Enigma originale era un dispositivo elettromeccanico composto da una tastiera, una serie di rotori intercambiabili, un riflettore e un pannello prese. Ogni volta che l'operatore premeva un tasto, un segnale elettrico percorreva un tragitto preciso attraverso tutti i componenti e accendeva una lampada corrispondente alla lettera cifrata.

La caratteristica fondamentale della macchina è la sua simmetria: se si cifra la lettera A e si ottiene la lettera X, cifrando X con la stessa configurazione iniziale si ottiene di nuovo A. Questo significa che lo stesso processo vale sia per cifrare che per decifrare, a patto di partire esattamente dalla stessa configurazione.

Il percorso del segnale ad ogni pressione di tasto è il seguente: la lettera entra nel pannello prese, che può scambiarla con un'altra se è stata configurata una coppia. Successivamente attraversa i tre rotori da destra verso sinistra, poi rimbalza nel riflettore e ripercorre i rotori in senso inverso da sinistra verso destra. Infine passa di nuovo nel pannello prese prima di produrre la lettera cifrata in uscita.

```
Tasto → PannelloPrese → Rotore Destro → Rotore Medio → Rotore Sinistro
      → Riflettore
      → Rotore Sinistro → Rotore Medio → Rotore Destro → PannelloPrese
      → Lettera cifrata
```

---

## I componenti

### Rotore — Albo Davide

Il rotore è il componente principale e il più complesso. È un disco fisico che contiene un cablaggio interno fisso, rappresentato nel codice da un array di 26 caratteri. Il disco può ruotare, il che cambia il punto di ingresso del segnale e quindi il risultato della cifratura. Nella simulazione sono disponibili tre rotori storici con le mappature originali:

| Rotore | Mappatura |
|---|---|
| I | `EKMFLGDQVZNTOWYHXUSPAIBRCJ` |
| II | `AJDKSIRUXBLHWTMCQGZNPYFVOE` |
| III | `BDFHJLCPRTXVZNYEIWGAKMUSQO` |

Ad ogni lettera premuta il rotore più a destra avanza di una posizione, esattamente come le lancette di un orologio. Quando compie un giro completo, fa avanzare il rotore centrale di una posizione, e quando anche questo completa un giro fa avanzare il rotore sinistro. Questo meccanismo a cascata garantisce che la configurazione cambi continuamente e che la stessa lettera non venga mai cifrata allo stesso modo due volte di seguito.

**Metodi:**

| Metodo | Descrizione |
|---|---|
| `Rotore(int numero)` | Costruttore, accetta 1, 2 o 3 |
| `ruota()` | Avanza la posizione di una tacca |
| `setPosizione(char c)` | Imposta la posizione iniziale |
| `getPosizione()` | Restituisce la posizione corrente |
| `cifraAvanti(char c)` | Percorso ingresso → riflettore |
| `cifraIndietro(char c)` | Percorso riflettore → uscita |

---

### Riflettore — Albo Davide

Il riflettore è il componente più semplice. Contiene una mappatura fissa di 26 lettere (Riflettore B storico) ma non ruota mai. Il suo scopo è rimandare indietro il segnale verso i rotori, ed è quello che rende il processo simmetrico. La mappatura è simmetrica per definizione: se la lettera A viene riflessa in Y, allora Y viene necessariamente riflessa in A.

**Metodi:**

| Metodo | Descrizione |
|---|---|
| `rifletti(char c)` | Restituisce la lettera riflessa |

---

### PannelloPrese — Albo Davide

Il pannello prese, chiamato in tedesco Steckerbrett, è un pannello che permetteva agli operatori di collegare coppie di lettere tra loro tramite cavi. Nella simulazione si configura inserendo coppie di lettere come testo, ad esempio `"AB CD EF"`, il che fa sì che A e B si scambino tra loro, così come C e D, e così via. Se non viene configurato nessuno scambio, il pannello è trasparente e non ha alcun effetto sulla cifratura.

**Metodi:**

| Metodo | Descrizione |
|---|---|
| `configura(String coppie)` | Imposta gli scambi (es. `"AB CD EF"`) |
| `scambia(char c)` | Restituisce la lettera scambiata |

---

### MacchinaEnigma — Righi Riccardo

Classe centrale che coordina tutti i componenti ed esegue la cifratura completa. Istanzia i tre rotori, il riflettore e il pannello prese, e gestisce la rotazione a cascata. Il metodo `cifra()` implementa l'intero percorso del segnale ed è lo stesso sia per cifrare che per decifrare.

**Rotazione a cascata:**
- Il rotore destro ruota ad ogni lettera premuta
- Quando il destro torna a posizione 0 (giro completo), ruota il medio
- Quando il medio torna a posizione 0, ruota il sinistro

**Metodi:**

| Metodo | Descrizione |
|---|---|
| `cifra(char c)` | Esegue l'intero percorso di cifratura |
| `setPosizioniIniziali(char, char, char)` | Imposta la posizione iniziale dei 3 rotori |
| `configuraPannello(String coppie)` | Configura il pannello prese |
| `reset()` | Riporta tutti i rotori in posizione A |

---

### MacchinaEnigmaController — Derkach Oleksandr

Controller JavaFX che collega l'interfaccia grafica alla logica di cifratura. Gestisce i bottoni A–Z, la tastiera fisica, i ComboBox dei rotori e l'aggiornamento dell'output.

**Elementi FXML:**

| fx:id | Tipo | Descrizione |
|---|---|---|
| `gridButton` | GridPane | Griglia bottoni A–Z |
| `comboRotoreSinistro` | ComboBox | Posizione iniziale rotore sinistro |
| `comboRotoreMedio` | ComboBox | Posizione iniziale rotore medio |
| `comboRotoreDestro` | ComboBox | Posizione iniziale rotore destro |
| `fieldPannelloPrese` | TextField | Coppie plugboard (es. `AB CD`) |
| `labelOutput` | Label | Ultima lettera cifrata |
| `fieldTesto` | TextField | Testo cifrato accumulato |
| `labelStato` | Label | Barra di stato |

---

## L'interfaccia grafica

L'interfaccia è divisa in tre sezioni principali. In alto è presente la sezione di configurazione, dove l'utente può impostare la posizione iniziale di ciascuno dei tre rotori scegliendo una lettera tra A e Z, e inserire le coppie del pannello prese. Un bottone Applica permette di confermare la configurazione, mentre il bottone Reset riporta tutto alla posizione iniziale.

Al centro è presente la tastiera simulata con i 26 bottoni delle lettere dell'alfabeto, disposti in una griglia. I bottoni rispondono sia al click del mouse che alla pressione dei corrispondenti tasti fisici sulla tastiera del computer.

In basso è presente la sezione di output, che mostra in grande l'ultima lettera cifrata e accumula progressivamente il testo cifrato completo in un campo di testo dedicato. Un bottone permette di cancellare il testo accumulato per iniziare una nuova sessione.

---

## Come si usa

Per cifrare un messaggio bisogna prima impostare la configurazione desiderata, scegliendo la posizione iniziale dei rotori e le eventuali coppie del pannello prese, e premere Applica. Dopodiché è sufficiente premere i tasti uno alla volta: ogni lettera viene cifrata istantaneamente e il risultato appare a schermo.

Per decifrare un messaggio ricevuto bisogna impostare esattamente la stessa configurazione iniziale usata in fase di cifratura, premere Applica, e inserire il testo cifrato. L'output sarà il messaggio originale.

---

## Esempio di verifica simmetria

```
Configurazione: rotori AAA, nessun pannello prese

Cifratura:
  Input:  H E L L O
  Output: M F N C U

Reset alla stessa configurazione AAA:
Decifratura:
  Input:  M F N C U
  Output: H E L L O  ✅
```
