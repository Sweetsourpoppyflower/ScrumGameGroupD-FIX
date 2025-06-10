public class KamerRetro extends Kamer {
    private Boolean heeftAssistent = false;
    private String retroDetails;

    public KamerRetro(String beschrijving, String retroDetails) {
        super(beschrijving);
        this.retroDetails = retroDetails;
        this.vraag = maakRetroVraag();
        this.monster = new Draak();
    }
    
    private VraagStrategie maakRetroVraag() {
        String vraag = "Wat is het primaire doel van een Sprint Retrospective?";
        String[] opties = {
            "Het beoordelen van de kwaliteit van het opgeleverde product",
            "Het plannen van de volgende sprint",
            "Het verbeteren van het werkproces van het team",
            "Het demonstreren van de voltooide functionaliteiten aan stakeholders"
        };
        String correctAntwoord = "Het verbeteren van het werkproces van het team";
        
        return new MeerkeuzeStrategie(
            vraag, 
            opties, 
            correctAntwoord,
            "Correct! De Sprint Retrospective is inderdaad bedoeld om het werkproces van het team te verbeteren.",
            "Helaas, dat is niet correct. De Sprint Retrospective is bedoeld om het werkproces van het team te verbeteren."
        );
    }

    @Override
    void geefHint() {
        System.out.println("Hint: De Sprint Retrospective is bedoeld om het werkproces van het team te verbeteren.");
    }

    @Override
    protected String getKamerNaam() {
        return "Retrospective Kamer";
    }

    @Override
    public void betreed() {
        System.out.println(KamerAsciiLayouts.getRetroLayout());
        System.out.println("Welkom in de Retrospective Kamer!");
        System.out.println(beschrijving);
        System.out.println("Retro Details: " + retroDetails);
        
        System.out.println("\nEr verschijnt een " + monster.getNaam() + "!");
        System.out.println(monster.beschrijving());
        monster.aanval();
        stelVraag();
    }

    public void toonRetro() {
        System.out.println(retroDetails);
    }

    public Boolean heeftAssistent() {
        return heeftAssistent;
    }
}
