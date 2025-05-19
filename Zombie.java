public class Zombie implements Monster {
    private int levenspunten;

    public Zombie() {
        this.levenspunten = 50;
    }

    @Override
    public void aanval() {
        System.out.println("🧟‍🧟🧟🧟🧟‍🧟‍  ️De zombie bijt langzaam maar hard! 🧟‍🧟🧟🧟🧟‍🧟‍");
    }

    @Override
    public String getNaam() {
        return "Zombie";
    }

    @Override
    public int getLevenspunten() {
        return levenspunten;
    }

    @Override
    public void versla(Speler speler) {
        System.out.println("De zombie is neergehaald.");
        speler.verhoogScrumKennis(levenspunten);
    }

    @Override
    public String beschrijving() {
        return "Een trage, rottende zombie die toch gevaarlijk dichtbij kan komen.";
    }
}

