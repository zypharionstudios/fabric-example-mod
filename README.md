# Zypharion Client

Fabric-Client-Mod fuer Minecraft 26.2. Benoetigt Fabric Loader, Fabric API und Java 25.

## Installation

1. Fabric Loader fuer Minecraft 26.2 installieren.
2. `zypharion-client-26.2.jar` in den Ordner `.minecraft/mods` legen.
3. Minecraft mit dem Fabric-Profil starten.
4. Das Menue im Spiel mit `´` oeffnen. Die Taste laesst sich unter Optionen > Steuerung neu belegen.

## Module

- `Auto Totem`: tauscht ein Totem aus dem Inventar in die Nebenhand.
- `Fly`: schaltet Fliegen nur ein, wenn Minecraft dem Spieler bereits Flugrechte gibt.
- `Freecam`: freie Kamera in einer Einzelspielerwelt.
- `Fullbright`: erhoeht die Gamma-Einstellung und stellt sie beim Ausschalten wieder her.
- `No Fog`: entfernt den Umgebungsnebel.
- `X-Ray`: zeigt Erze und Ancient Debris; andere Blockmodelle werden ausgeblendet.

Die Modulschalter werden in `.minecraft/config/zypharion-client.properties` gespeichert. Freecam und Auto Totem sind fuer eigene Welten oder Server gedacht, auf denen die jeweilige Nutzung erlaubt ist.

## Manuell Gebautes JAR

Das beiliegende JAR wurde ohne Gradle mit Java 25 `javac` und `jar` erstellt und gegen die lokal installierte Minecraft-26.2-Runtime sowie die vorhandenen Fabric-Abhaengigkeiten kompiliert.
