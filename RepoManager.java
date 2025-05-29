public class RepoManager {
    private SpelerRepository spelerRepository;

    public void saveSpelerStatus(SpelerStatus status) {
        if (spelerRepository == null) {
            spelerRepository = new SpelerRepository();
        }
        spelerRepository.saveSpelerStatus(status);
    }

    public SpelerStatus getSpelerStatus(String naam) {
        if (spelerRepository == null) {
            spelerRepository = new SpelerRepository();
        }
        
        SpelerStatus status = new SpelerStatus();
        status.setNaam(naam);
        status.setStatus(spelerRepository.getStatus(naam));
        status.setHuidigeKamer(spelerRepository.getHuidigeKamer(naam));
        status.setScrumKennis(spelerRepository.getScrumKennis(naam));
        
        return status;
    }

    public void updatePositie(String naam, int nieuwePositie) {
        if (spelerRepository == null) {
            spelerRepository = new SpelerRepository();
        }
        spelerRepository.updatePositie(naam, nieuwePositie);
    }

    public void updateScrumKennis(String naam, int scrumKennis) {
        if (spelerRepository == null) {
            spelerRepository = new SpelerRepository();
        }
        spelerRepository.updateScrumKennis(naam, scrumKennis);
    }
    
    public void clearBezochteKamers(String naam) {
        if (spelerRepository == null) {
            spelerRepository = new SpelerRepository();
        }
        spelerRepository.clearBezochteKamers(naam);
    }
}
