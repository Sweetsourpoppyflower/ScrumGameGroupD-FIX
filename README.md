# Scrum Escape Game

## 🎮 Over het project
Scrum Escape Game is een leerzame CLI-based avonturenspel dat je meeneemt door de wereld van Scrum en het bedrijfsleven. Spelers navigeren door verschillende kamers die Scrumprocessen vertegenwoordigen, beantwoorden vragen over Scrum-concepten, en verslaan monsters door hun Scrum-kennis te demonstreren.

## ✨ Kenmerken
- **Educatieve gameplay**: Leer Scrum-concepten terwijl je speelt
- **Meerdere kamers**: Verken kamers die verschillende Scrumprocessen vertegenwoordigen (Backlog, Planning, Daily, Review, Retro, en Finale)
- **Monster battles**: Versla monsters door Scrum-vragen correct te beantwoorden
- **Joker systeem**: Gebruik speciale jokers om hulp te krijgen bij moeilijke vragen
- **Assistenten**: Krijg hulp van assistenten die in bepaalde kamers beschikbaar zijn
- **Voortgangssysteem**: Je voortgang wordt bijgehouden in een database
- **Command-line interface**: Eenvoudige tekstgebaseerde interface met ASCII-art

## 🏗️ Projectstructuur
```
game/
├── assistent/     # Assistenten die de speler kunnen helpen
├── db_conn/       # Database connectie en SQL helpers
├── hints/         # Hint systeem voor vragen
├── jokers/        # Speciale items die de speler kan gebruiken
├── kamers/        # Verschillende kamers in het spel
├── layouts/       # UI-formatting en layouts
├── monsters/      # Monsters die de speler moet verslaan
├── objects/       # Game objecten zoals wapens
├── spel/          # Core game klassen en logica
├── speler/        # Speler-gerelateerde klassen
└── vraagstrategieen/ # Verschillende strategieën voor vragen
```

## 🚀 Installatie en setup
1. Zorg ervoor dat je Java 11 hebt geïnstalleerd
2. Zorg ervoor dat je Maven hebt geïnstalleerd
3. Zorg ervoor dat je MySQL hebt geïnstalleerd en draaiend
4. Clone de repository:
   ```
   git clone https://github.com/yourusername/ScrumGameGroupD.git
   cd ScrumGameGroupD
   ```
5. Configureer de database verbinding in `game/db_conn/DBconnectie.java`
6. Bouw het project met Maven:
   ```
   mvn clean package
   ```
7. Start het spel:
   ```
   java -jar target/ScrumGameGroupD-1.0-SNAPSHOT.jar
   ```

## 🎲 Hoe te spelen
1. Start het spel en voer je naam in
2. Kies een joker om te gebruiken tijdens het spel
3. Navigeer door de kamers door vragen te beantwoorden over Scrum
4. Gebruik commando's zoals:
   - `status` - Bekijk je huidige status
   - `joker` - Gebruik je joker
   - `assistent` - Vraag hulp aan de assistent (indien beschikbaar)
   - `stop` - Stop het spel

## 🛠️ Technologieën
- Java 11
- Maven
- MySQL

## 👥 Bijdragers
- **Abdulkadir Atik**
- **Andio Garia**
- **Faouzane Djamgbedja**

## 📝 Definition of Ready
Voor bijdragers aan het project is er een [Definition of Ready](Definition_of_Ready.md) document beschikbaar dat de criteria beschrijft voor user stories die klaar zijn voor implementatie.
