public abstract class Monster {
    protected int levenspunten;
    
    public abstract void aanval();
    public abstract String getNaam();
    public abstract String beschrijving();
    
    public int getLevenspunten() {
        return levenspunten;
    }
    
    public void versla(Speler speler) {
        System.out.println("💪 Je hebt de " + getNaam() + " verslagen!");
        speler.verhoogScrumKennis(levenspunten);
        Vertraag.inSeconden(1);
    }
    
    public void ontvangSchade(int schade) {
        levenspunten -= schade;
        System.out.println("💥 De " + getNaam() + " ontvangt " + schade + " schade. Levenspunten: " + levenspunten);
        if (levenspunten <= 0) {
            System.out.println("💀 De " + getNaam() + " is verslagen!");
        } else {
            System.out.println("❤️ De " + getNaam() + " is nog steeds in leven met " + levenspunten + " levenspunten.");
        }
    }
}
