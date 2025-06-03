public class Zwaard extends Weapon {

    private int schade;

    public Zwaard(int schade) {
        this.schade = schade;
    }

    public int getSchade() {
        return schade;
    }

    @Override
    public void attack() {
        System.out.println("Je valt aan met het zwaard en deelt " + schade + " schade uit aan de monster.");
    }
}
