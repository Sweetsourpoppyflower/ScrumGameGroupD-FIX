package game.jokers;

public class JokerManager {
    private Joker HuidigeJoker;

    public void setJoker(Joker joker) {
        this.HuidigeJoker = joker;
    }
    public Joker getJoker() {
        return HuidigeJoker;
    }
    public boolean hasJoker() {
        return HuidigeJoker != null;
    }

}
