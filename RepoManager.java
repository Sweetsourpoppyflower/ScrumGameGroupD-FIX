public class RepoManager { // Beheert de opslag en het ophalen van spelerstatussen
    private SpelerRepository spelerRepository; // Repository voor spelerstatussen


    public void saveSpelerStatus(SpelerStatus status) { // Slaat de status van een speler op in de repository
        if (spelerRepository == null) { // Controleert of de spelerRepository al is geïnitialiseerd
            spelerRepository = new SpelerRepository(); // Als dat niet het geval is, wordt een nieuwe instantie gemaakt
        }
        spelerRepository.saveSpelerStatus(status); // Slaat de status op in de repository
    }

    public SpelerStatus getSpelerStatus(String naam) { // Haalt de status van een speler op uit de repository
        if (spelerRepository == null) { // Controleert of de spelerRepository al is geïnitialiseerd
            spelerRepository = new SpelerRepository(); // Als dat niet het geval is, wordt een nieuwe instantie gemaakt
        }
        
        SpelerStatus status = new SpelerStatus(); // Maakt een nieuwe SpelerStatus instantie aan
        status.setNaam(naam); // Zet de naam van de speler in de status
        status.setStatus(spelerRepository.getStatus(naam)); // Haalt de status van de speler op
        status.setHuidigeKamer(spelerRepository.getHuidigeKamer(naam)); // Haalt de huidige kamer van de speler op
        status.setScrumKennis(spelerRepository.getScrumKennis(naam)); // Haalt de scrum kennis van de speler op
        
        return status;
    }

    public void updatePositie(String naam, int nieuwePositie) { // Update de positie van een speler in de repository
        if (spelerRepository == null) { // Controleert of de spelerRepository al is geïnitialiseerd
            spelerRepository = new SpelerRepository(); // Als dat niet het geval is, wordt een nieuwe instantie gemaakt
        }
        spelerRepository.updatePositie(naam, nieuwePositie); // Update de positie van de speler in de repository
    }

    public void updateScrumKennis(String naam, int scrumKennis) { // Update de scrum kennis van een speler in de repository
        if (spelerRepository == null) { // Controleert of de spelerRepository al is geïnitialiseerd
            spelerRepository = new SpelerRepository(); // Als dat niet het geval is, wordt een nieuwe instantie gemaakt
        }
        spelerRepository.updateScrumKennis(naam, scrumKennis); // Update de scrum kennis van de speler in de repository
    }
    
    public void clearBezochteKamers(String naam) { // Leegt de lijst van bezochte kamers voor een speler in de repository
        if (spelerRepository == null) { // Controleert of de spelerRepository al is geïnitialiseerd
            spelerRepository = new SpelerRepository(); // Als dat niet het geval is, wordt een nieuwe instantie gemaakt
        }
        spelerRepository.clearBezochteKamers(naam); // Leegt de lijst van bezochte kamers voor de speler in de repository
    }
}
