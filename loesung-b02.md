# Lösung zu Blatt 02: Git Branches, JUnit Basics; CI-Pipeline

## Überblick

Dieses Dokument enthält meine Lösungen zu Blatt 02.

Die Bearbeitung besteht aus drei Teilen:

1. Git-Spiel
2. Katzen-Cafe
3. Remotes und CI-Pipeline


---

## 1. Git-Spiel: Experiment 1

### Aufgabe

Ich ändere eine Datei, die im Branch `end` nicht verändert wurde. Danach erstelle ich auf `master` einen Commit und merge anschließend `end` in `master`.

### Vorbereitung

Für dieses Experiment verwende ich einen Klon:

```bash
cd D:\aLearning\prog2\b02\gitquest-experiments
git clone https://github.com/Programmiermethoden-CampusMinden/prog2_ybel_gitquest.git exp1
cd exp1
```

Ich prüfe die Branches:

```bash
git branch -a
```

Danach prüfe ich, welche Dateien sich zwischen `master` und `end` unterscheiden:

```bash
git diff --name-only master..origin/end
```

Dabei sehe ich, dass im Branch `end` die Dateien `questlog.md`, `rucksack.md` und `stats.md` verändert wurden. Deshalb verwende ich `hero.md`, weil diese Datei im Branch `end` nicht verändert wurde.

### Änderung auf `master`

Ich ändere `hero.md` und füge am Ende hinzu:

```markdown
Markus überprüfte seine Ausrüstung noch einmal.
```

Danach erstelle ich einen Commit:

```bash
git add hero.md
git commit -m "Add hero note"
```

### Merge

Anschließend merge ich den Branch `end` in `master`:

```bash
git merge origin/end
```

### Beobachtung

Der Merge funktioniert ohne Konflikt.

Der Grund ist, dass ich auf `master` die Datei `hero.md` geändert habe, während `end` andere Dateien geändert hat, nämlich `questlog.md`, `rucksack.md` und `stats.md`.

Git kann diese Änderungen automatisch zusammenführen, weil sie nicht dieselbe Datei und nicht dieselbe Stelle betreffen.

### Konfliktlösung

Es gab keinen Konflikt.

Zur Kontrolle verwende ich:

```bash
git status
git log --oneline --graph --all -10
```


---

## 2. Git-Spiel: Experiment 2

### Aufgabe

Ich ändere eine Datei, die auch im Branch `end` verändert wurde. Dabei achte ich darauf, eine andere Stelle in der Datei zu verändern. Danach erstelle ich auf `master` einen Commit und merge anschließend `end` in `master`.

### Vorbereitung

Für dieses Experiment verwende ich einen Klon:

```bash
cd D:\aLearning\prog2\b02\gitquest-experiments
git clone https://github.com/Programmiermethoden-CampusMinden/prog2_ybel_gitquest.git exp2
cd exp2
```

Ich prüfe zuerst die Branches:

```bash
git branch -a
```

Danach prüfe ich, welche Dateien zwischen `master` und `end` unterschiedlich sind:

```bash
git diff --name-only master..origin/end
```

Dabei sehe ich, dass unter anderem `questlog.md` im Branch `end` verändert wurde.

### Änderung auf `master`

Ich ändere in `questlog.md` den Anfang des Textes.

Vorher:

```text
In einer düsteren und mysteriösen Welt
```

Nachher:

```text
In einer sehr düsteren und mysteriösen Welt
```

Damit ändere ich eine Stelle am Anfang der Datei. Der Branch `end` verändert dagegen das Ende der Geschichte.

Danach erstelle ich einen Commit:

```bash
git add questlog.md
git commit -m "Describe dungeon atmosphere"
```

### Merge

Anschließend merge ich `end` in `master`:

```bash
git merge origin/end
```

### Beobachtung

Der Merge funktioniert ohne Konflikt.

Obwohl beide Branches die Datei `questlog.md` verändern, liegen die Änderungen an unterschiedlichen Stellen. Deshalb kann Git die Änderungen automatisch zusammenführen.

### Konfliktlösung

Es gab keinen Konflikt.

Zur Kontrolle verwende ich:

```bash
git status
git log --oneline --graph --all -10
```


---

## 3. Git-Spiel: Experiment 3

### Aufgabe

In diesem Experiment ändere ich eine Stelle, die auch im Branch `end` verändert wurde. Dabei teste ich zwei Fälle:

1. Die Änderung auf `master` ist identisch zu der Änderung in `end`.
2. Die Änderung auf `master` ist anders als die Änderung in `end`.

---

### 3.1 Identische Änderung

Für den ersten Fall verwende ich einen Klon:

```bash
cd D:\aLearning\prog2\b02\gitquest-experiments
git clone https://github.com/Programmiermethoden-CampusMinden/prog2_ybel_gitquest.git exp3-identisch
cd exp3-identisch
```

Ich ändere in `questlog.md` die letzte Zeile so, dass sie identisch zur Änderung im Branch `end` ist.

Danach erstelle ich auf `master` einen Commit:

```bash
git add questlog.md
git commit -m "Add final"
```

Anschließend merge ich `end` in `master`:

```bash
git merge origin/end
```

#### Beobachtung

Der Merge funktioniert ohne Konflikt.

#### Erklärung

Git erkennt, dass die Änderung auf `master` identisch zu der Änderung in `end` ist. Deshalb muss Git an dieser Stelle nicht zwischen zwei unterschiedlichen Versionen entscheiden. Die übrigen Änderungen aus `end` können automatisch übernommen werden.

---

### 3.2 Andere Änderung

Für den zweiten Fall verwende ich wieder einen Klon:

```bash
cd D:\aLearning\prog2\b02\gitquest-experiments
git clone https://github.com/Programmiermethoden-CampusMinden/prog2_ybel_gitquest.git exp3-anders
cd exp3-anders
```

Ich ändere in `questlog.md` dieselbe Stelle wie im Branch `end`, aber mit einem anderen Text.

Änderung auf `master`:

```text
Schließlich erreichte Markus sein Ziel nicht.
```

Danach erstelle ich einen Commit:

```bash
git add questlog.md
git commit -m "Add alternative final"
```

Anschließend merge ich `end` in `master`:

```bash
git merge origin/end
```

#### Beobachtung

Der Merge erzeugt einen Konflikt in `questlog.md`.

#### Erklärung

Der Konflikt entsteht, weil sowohl `master` als auch `end` dieselbe Stelle in derselben Datei verändert haben, aber mit unterschiedlichem Inhalt. Git kann nicht automatisch entscheiden, welche Version richtig ist.

In `questlog.md` erscheinen Konfliktmarker:

```text
<<<<<<< HEAD
Version aus master
=======
Version aus origin/end
>>>>>>> origin/end
```

#### Konfliktlösung

Ich öffne `questlog.md`, entferne die Konfliktmarker und entscheide mich für die finale Version aus `end`, weil dort die Geschichte vollständig beendet wird.

Danach schließe ich den Merge ab:

```bash
git add questlog.md
git commit
```

Zur Kontrolle verwende ich:

```bash
git status
git log --oneline --graph --all -10
```


---

## 4. Git-Spiel: Experiment 4

### Aufgabe

Dieses Experiment ist ähnlich wie Experiment 2. Ich ändere eine Datei, die auch im Branch `end` verändert wurde, aber an einer anderen Stelle. Danach setze ich den Branch `end` auf die Spitze von `master`, bevor ich `end` in `master` merge.

### Vorbereitung

Ich verwende wieder einen Klon:

```bash
cd D:\aLearning\prog2\b02\gitquest-experiments
git clone https://github.com/Programmiermethoden-CampusMinden/prog2_ybel_gitquest.git exp4
cd exp4
```

### Änderung auf `master`

Ich ändere in `questlog.md` den Anfang des Textes.

Vorher:

```text
# Questlog In einer düsteren und mysteriösen Welt
```

Nachher:

```text
# Questlog In einer besonders düsteren und mysteriösen Welt
```

Danach erstelle ich auf `master` einen Commit:

```bash
git add questlog.md
git commit -m "Describe another dungeon atmosphere"
```

### Branch `end` auf die Spitze von `master` setzen

Zuerst erstelle ich einen lokalen Branch `end`, der auf `origin/end` basiert:

```bash
git checkout -b end origin/end
```

Danach setze ich `end` auf die Spitze von `master`:

```bash
git reset --hard master
```

Dadurch zeigt `end` auf denselben Commit wie `master`.

### Merge

Danach wechsle ich zurück zu `master`:

```bash
git checkout master
```

Anschließend merge ich `end` in `master`:

```bash
git merge end
```

### Beobachtung

Git meldet, dass bereits alles aktuell ist.

### Erklärung

Der Branch `end` wurde vorher auf die Spitze von `master` gesetzt. Dadurch zeigen `master` und `end` auf denselben Commit. Beim Merge gibt es deshalb keine neuen Änderungen, die übernommen werden müssten.

### Konfliktlösung

Es gab keinen Konflikt. Eine Konfliktlösung war nicht notwendig.

Zur Kontrolle verwende ich:

```bash
git status
git log --oneline --graph --all -10
```


---

## 5. Katzen-Café: Gradle

### Aufgabe

Für das Cat-Café-Projekt soll eine Gradle-Konfiguration erstellt werden. Das Projekt soll über Gradle gebaut, gestartet, getestet und formatiert werden können.

Das Projekt enthält Java-Code unter:

```text
src/main/java/catcafe
```

---

### Gradle-Konfiguration

Ich habe im Projekt eine Datei `settings.gradle` erstellt:

```groovy
rootProject.name = 'prog2-catcafe-b02'
```

Außerdem habe ich eine Datei `build.gradle` erstellt:

```groovy
plugins {
    id 'java'
    id 'application'
    id 'com.diffplug.spotless' version '8.4.0'
}

group = 'prog2'
version = '1.0.0'

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

application {
    mainClass = 'catcafe.Main'
}

dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter:6.0.1'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}

tasks.named('test') {
    useJUnitPlatform()
}

spotless {
    java {
        googleJavaFormat()
        target 'src/**/*.java'
    }
}
```

---

### Erklärung

Das Plugin `java` aktiviert die normalen Java-Build-Aufgaben.

Das Plugin `application` wird verwendet, damit die Anwendung über Gradle gestartet werden kann. Die Hauptklasse ist:

```groovy
application {
    mainClass = 'catcafe.Main'
}
```

JUnit Jupiter wird als Test-Abhängigkeit eingebunden:

```groovy
testImplementation 'org.junit.jupiter:junit-jupiter:6.0.1'
```

Der Test-Task wird so konfiguriert, dass JUnit verwendet wird:

```groovy
tasks.named('test') {
    useJUnitPlatform()
}
```

Spotless wird verwendet, um den Java-Code mit `google-java-format` zu formatieren:

```groovy
spotless {
    java {
        googleJavaFormat()
        target 'src/**/*.java'
    }
}
```

---

### Gradle Wrapper

Damit das Projekt unabhängig von einer globalen Gradle-Installation gebaut werden kann, habe ich den Gradle Wrapper erzeugt:

```bash
gradle wrapper
```

Danach stehen unter Windows die Gradle-Befehle über `.\gradlew` zur Verfügung.

---

### Lokale Prüfung

Ich prüfe die verfügbaren Gradle-Tasks mit:

```bash
.\gradlew tasks
```

Ich starte die Anwendung mit:

```bash
.\gradlew run
```

Ich führe die Tests aus mit:

```bash
.\gradlew test
```

Ich prüfe die Formatierung mit:

```bash
.\gradlew spotlessCheck
```

Falls nötig, formatiere ich den Code automatisch mit:

```bash
.\gradlew spotlessApply
```


---

## 6. Katzen-Café: JUnit-Tests für `CatCafe`

### Aufgabe

Für die Klasse `CatCafe` sollen mindestens 10 unterschiedliche JUnit-Testfälle erstellt werden. Die Tests sollen nach dem Muster `given - when - then` aufgebaut sein und passende Methodennamen haben.

Ich habe die Tests in folgender Datei erstellt:

```text
src/test/java/catcafe/CatCafeTest.java
```

---

### Getestete Funktionen

Die Klasse `CatCafe` bietet unter anderem folgende Funktionen:

| Methode | Bedeutung |
|---|---|
| `addCat(FelineOverLord cat)` | fügt eine Katze zum Café hinzu |
| `getCatCount()` | gibt die Anzahl der Katzen zurück |
| `getCatByName(String name)` | sucht eine Katze nach Namen |
| `getCatByWeight(int minWeight, int maxWeight)` | sucht eine Katze in einem Gewichtsbereich |

---

### Testfälle

Ich habe folgende Testfälle erstellt:

| Testmethode | Was wird geprüft? |
|---|---|
| `getCatCountReturnsZeroForEmptyCafe` | Ein neues Café enthält am Anfang keine Katzen. |
| `getCatCountReturnsOneAfterAddingOneCat` | Nach dem Hinzufügen einer Katze ist die Anzahl 1. |
| `getCatCountReturnsNumberOfAddedCats` | Nach dem Hinzufügen mehrerer Katzen stimmt die Anzahl. |
| `addCatThrowsNullPointerExceptionForNullCat` | `null` darf nicht als Katze hinzugefügt werden. |
| `getCatByNameReturnsCatWithMatchingName` | Eine vorhandene Katze kann über ihren Namen gefunden werden. |
| `getCatByNameReturnsNullForUnknownName` | Für einen unbekannten Namen wird `null` zurückgegeben. |
| `getCatByNameReturnsNullForNullName` | Für `null` als Name wird `null` zurückgegeben. |
| `getCatByWeightReturnsCatWhenWeightIsInsideRange` | Eine Katze innerhalb des Gewichtsbereichs wird gefunden. |
| `getCatByWeightIncludesLowerLimit` | Die untere Grenze des Gewichtsbereichs ist inklusive. |
| `getCatByWeightExcludesUpperLimit` | Die obere Grenze des Gewichtsbereichs ist exklusiv. |

---

### Relevanz

Die Testfälle sind relevant, weil sie die wichtigsten öffentlichen Methoden von `CatCafe` prüfen.

Die Methode `getCatCount()` ist wichtig, weil sie zeigt, ob das Hinzufügen von Katzen korrekt funktioniert.

Die Methode `addCat()` wird auch mit einem ungültigen Wert getestet, weil `null` nicht als echte Katze gelten kann.

Die Methode `getCatByName()` wird mit einem vorhandenen Namen, einem unbekannten Namen und `null` getestet. Dadurch werden normale Fälle und Randfälle abgedeckt.

Die Methode `getCatByWeight()` wird besonders ausführlich getestet, weil dort Grenzen wichtig sind. Laut Beschreibung ist `minWeight` inklusive und `maxWeight` exklusiv. Deshalb teste ich genau diese Randbedingungen.

---

### Unterschiede

Die Testfälle sind unterschiedlich, weil sie verschiedene Methoden und verschiedene Situationen prüfen:

- leeres Café
- Hinzufügen einer Katze
- Hinzufügen mehrerer Katzen
- ungültige Eingaben
- Suche nach Namen
- Suche nach Gewicht
- gültige Gewichtsbereiche
- ungültige Gewichtsbereiche

Dadurch testen die Fälle nicht nur denselben Ablauf mit anderen Daten, sondern verschiedene Verhaltensweisen der Klasse `CatCafe`.

---

### Ausführung der Tests

Die Tests führe ich mit Gradle aus:

```bash
.\gradlew test
```

Die Formatierung prüfe ich mit:

```bash
.\gradlew spotlessCheck
```

Falls nötig, formatiere ich automatisch mit:

```bash
.\gradlew spotlessApply
```


---

## 7. Remotes und CI-Pipeline

### Aufgabe

Das Cat-Cafe-Projekt soll in ein eigenes öffentliches GitHub-Repository gepusht werden. Zusätzlich soll eine CI-Pipeline eingerichtet werden.

Die Pipeline soll automatisch prüfen:

1. Build
2. JUnit-Tests
3. Formatierung

---

### Remote Repository

Mein Cat-Cafe-Repository liegt auf GitHub:

```text
https://github.com/Exoriy/prog2-catcafe-b02
```

Ich prüfe die konfigurierten Remotes mit:

```bash
git remote -v
```

Danach pushe ich meine Änderungen mit:

```bash
git push
```

---

### GitHub Actions Workflow

Ich habe folgenden Workflow erstellt:

```text
.github/workflows/ci.yml
```

Inhalt:

```yaml
name: CI

on:
  push:
    branches:
      - master
      - main
  pull_request:
    branches:
      - master
      - main

jobs:
  build-test-format:
    name: Build, test and format check
    runs-on: ubuntu-latest

    steps:
      - name: Checkout repository
        uses: actions/checkout@v4

      - name: Set up Java 25
        uses: actions/setup-java@v4
        with:
          distribution: temurin
          java-version: '25'
          cache: gradle

      - name: Set up Gradle
        uses: gradle/actions/setup-gradle@v4

      - name: Make Gradle Wrapper executable
        run: chmod +x ./gradlew

      - name: Build project
        run: ./gradlew build

      - name: Run tests
        run: ./gradlew test

      - name: Check formatting
        run: ./gradlew spotlessCheck
```

---

### Erklärung der Pipeline

Der Workflow wird bei jedem Push und bei jedem Pull Request auf `master` oder `main` gestartet.

Der Schritt `Checkout repository` lädt den Quellcode in den GitHub-Actions-Runner.

Der Schritt `Set up Java 25` installiert Java 25, damit das Projekt mit der gleichen Java-Version gebaut wird wie lokal.

Der Schritt `Set up Gradle` richtet Gradle für den CI-Lauf ein.

Mit:

```bash
./gradlew build
```

wird das Projekt gebaut.

Mit:

```bash
./gradlew test
```

werden die JUnit-Tests ausgeführt.

Mit:

```bash
./gradlew spotlessCheck
```

wird geprüft, ob der Java-Code korrekt formatiert ist.

Wenn einer dieser Schritte fehlschlägt, schlägt die gesamte Pipeline fehl. Dadurch sieht man direkt auf GitHub, ob der aktuelle Stand des Projekts korrekt baut, alle Tests besteht und den Formatierungsregeln entspricht.

---

### Lokale Prüfung vor dem Push

Vor dem Push habe ich lokal geprüft:

```bash
.\gradlew build
.\gradlew test
.\gradlew spotlessCheck
```

Alle Schritte waren erfolgreich.



