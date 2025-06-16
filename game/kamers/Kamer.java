package game.kamers;

import game.hints.HintFactory;
import game.hints.HintProvider;
import game.jokers.Joker;
import game.jokers.JokerAcceptor;
import game.vraagstrategieen.VraagStrategie;
import game.monsters.Monster;
import game.spel.CommandoVerwerker;
import game.speler.Speler;
import game.layouts.CLIFormatter;
import game.spel.Vertraag;
import game.spel.Spel;

import java.sql.SQLException;
import java.util.Scanner;

public abstract class Kamer implements JokerAcceptor {
    protected String beschrijving;
    protected VraagStrategie vraag;
    protected Monster monster;
    protected Boolean heeftAssistent = false;
    protected CommandoVerwerker commandoVerwerker;

    public Kamer(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public void setCommandoVerwerker(CommandoVerwerker commandoVerwerker) {
        this.commandoVerwerker = commandoVerwerker;
    }

    public abstract void geefHint();

    public void geefExtraSleutel(Speler speler) {
        System.out.println("🔑 Je hebt een extra sleutel gevonden in de " + getKamerNaam() + "!");
        speler.setAantalSleutels(speler.getAantalSleutels() + 1);
    }

    public abstract void betreed() throws SQLException, InterruptedException;
    
    protected abstract String getKamerNaam();

    protected void stelVraag() throws SQLException, InterruptedException {
        Scanner scanner = new Scanner(System.in);

        CLIFormatter.LijnVoorVraag();
        System.out.println("❓ Om de " + monster.getNaam() + " te verslaan, moet je deze vraag beantwoorden:");
        Vertraag.inMilliseconden(300);
        vraag.toonVraag();
        Vertraag.inMilliseconden(300);
        System.out.println("\n⌨️ Je kunt een commando invoeren of direct je antwoord geven.");

        boolean isCorrect = false;
        while (!isCorrect) {
            System.out.print("\nJouw invoer: ");
            String input = scanner.nextLine();

            if (isCommando(input)) {
                commandoVerwerker.verwerkCommand(input, Spel.getHuidigeSpeler());

                CLIFormatter.LijnVoorVraag();
                System.out.println("❓ Om de " + monster.getNaam() + " te verslaan, moet je deze vraag beantwoorden:");
                vraag.toonVraag();
            } else {
                isCorrect = vraag.controleerAntwoord(input);
                if (isCorrect) {
                    System.out.println(vraag.positieveFeedback());
                    monster.versla(Spel.getHuidigeSpeler());
                    CLIFormatter.LijnNaVraag();
                } else {
                    System.out.println(vraag.negatieveFeedback());
                    System.out.println("⚔️ De " + monster.getNaam() + " valt opnieuw aan!");

                    vraagOmHint();
                }
            }
        }
    }

    private boolean isCommando(String input) {
        return input.equals("gebruik joker") ||
                input.equals("status") ||
                input.equals("joker") ||
                input.equals("assistent") ||
                input.equals("stop");
    }


    protected void vraagOmHint() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("💡 Wil je een hint? (ja/nee): ");
        String antwoord = scanner.nextLine().toLowerCase();
        
        if (antwoord.equals("ja") || antwoord.equals("j")) {
            HintProvider hintProvider = HintFactory.createHintProvider(vraag);
            
            System.out.println("\n💡 HINT: " + hintProvider.getHint() + "\n");
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
