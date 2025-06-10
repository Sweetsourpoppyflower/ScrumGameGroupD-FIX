public class aHint implements AssistentActie{
    @Override
    public void voerUit(Kamer kamer, Speler speler) {
        kamer.geefHint();
    }
}
