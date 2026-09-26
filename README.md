# Zypharion Client

Fabric-Client-Mod fuer Minecraft 26.2. Benoetigt Fabric Loader, Fabric API und Java 25.

## Installation

1. Fabric Loader fuer Minecraft 26.2 installieren.
2. [zypharion-client-26.2.jar](https://github.com/zypharionstudios/fabric-example-mod/releases/latest) herunterladen und in den Ordner `.minecraft/mods` legen. Alternativ das Artefakt `Zypharion-Client-Minecraft-26.2` im jeweiligen [GitHub-Actions-Lauf](https://github.com/zypharionstudios/fabric-example-mod/actions/workflows/publish.yml) herunterladen und entpacken.
3. Minecraft mit dem Fabric-Profil starten.
4. Das Menue mit der Akzenttaste direkt vor Backspace oeffnen. Die Taste laesst sich unter Optionen > Steuerung neu belegen.

## Module

- `Auto Totem`: tauscht ein Totem aus dem Inventar in die Nebenhand.
- `Fly`: schaltet Fliegen nur ein, wenn Minecraft dem Spieler bereits Flugrechte gibt.
- `Freecam`: freie Client-Kamera; die Spielfigur bleibt am Ausgangspunkt.
- `Fullbright`: erhoeht die Gamma-Einstellung und stellt sie beim Ausschalten wieder her.
- `No Fog`: entfernt den Umgebungsnebel.
- `X-Ray`: zeigt Erze und Ancient Debris; andere Blockmodelle werden ausgeblendet.

Die Modulschalter werden in `.minecraft/config/zypharion-client.properties` gespeichert. Fly setzt keine serverseitigen Flugregeln ausser Kraft.

## Build

GitHub Actions validiert und veroeffentlicht das eingecheckte JAR ohne Gradle. Minecraft 26.2 verlangt Java 25.
