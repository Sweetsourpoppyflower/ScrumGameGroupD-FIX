public class HintJoker extends Joker {
    @Override
    public boolean kanGebruiktWordenIn(Kamer kamer) {
        return true;
    }

    @Override
    protected void doeGebruikIn(Kamer kamer) {
        System.out.println("🎭 Hint Joker geactiveerd!");
        kamer.geefHint();
    }

    @Override
    public String beschrijving() throws InterruptedException {
        System.out.println("💡 Dit is een hint joker. Je kan deze joker gebruiken om hints te krijgen voor de vragen in de kamers.");
        Thread.sleep(2000);
        return "";
    }
}
