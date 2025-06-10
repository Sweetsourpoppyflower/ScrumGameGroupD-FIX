import java.util.Scanner;

public abstract class Kamer implements JokerAcceptor {
    protected String beschrijving;
    protected VraagStrategie vraag;
    protected Monster monster;
    protected Boolean heeftAssistent = false;

    public Kamer(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    abstract void geefHint();

    void geefExtraSleutel(Speler speler) {
        System.out.println("Je hebt een extra sleutel gevonden in de " + getKamerNaam() + "!");
        speler.setAantalSleutels(speler.getAantalSleutels() + 1);
    }

    public abstract void betreed();
    
    protected abstract String getKamerNaam();
    
    protected void stelVraag() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nOm de " + monster.getNaam() + " te verslaan, moet je deze vraag beantwoorden:");
        
        vraag.toonVraag();
        
        System.out.print("\nJouw antwoord: ");
        String antwoord = scanner.nextLine();
        
        boolean isCorrect = vraag.controleerAntwoord(antwoord);
        if (isCorrect) {
            System.out.println(vraag.positieveFeedback());
            monster.versla(Spel.getHuidigeSpeler());
        } else {
            System.out.println(vraag.negatieveFeedback());
            System.out.println("De " + monster.getNaam() + " valt opnieuw aan!");
            
            vraagOmHint();
            
            stelVraag();
        }
    }
    
    protected void vraagOmHint() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Wil je een hint? (ja/nee): ");
        String antwoord = scanner.nextLine().toLowerCase();
        
        if (antwoord.equals("ja") || antwoord.equals("j")) {
            HintProvider hintProvider = HintFactory.createHintProvider(vraag);
            
            System.out.println("\nHINT: " + hintProvider.getHint() + "\n");
        }
    }
    
    @Override
    public void accepteer(Joker joker) {
        joker.gebruikIn(this);
    }

    public Boolean heeftAssistent() {
        return heeftAssistent;
    }
}
