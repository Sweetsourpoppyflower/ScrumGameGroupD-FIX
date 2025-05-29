public class SpelerStatus {
    private String naam;
    private int huidigeKamer;
    private String status;
    private int scrumKennis;
    private int aantalSleutels;
    private SpelerRepository spelerRepository;

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setHuidigeKamer(int huidigeKamer) {
        this.huidigeKamer = huidigeKamer;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setScrumKennis(int scrumKennis) {
        this.scrumKennis = scrumKennis;
    }

    public void setAantalSleutels(int aantalSleutels) {
        this.aantalSleutels = aantalSleutels;
    }

    public String getNaam() {
        return naam;
    }

    public int getHuidigeKamer() {
        return huidigeKamer;
    }

    public String getStatus() {
        return status;
    }

    public int getScrumKennis() {
        return scrumKennis;
    }

    public int getAantalSleutels() {
        return aantalSleutels;
    }

}
