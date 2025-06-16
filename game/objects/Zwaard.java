package game.objects;

public class Zwaard implements Weapon, Readable {

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

    @Override
    public void showMessage(String message) {
        System.out.println("Dit is een interactief object dat informatie geeft over het zwaard.");
        System.out.println("Informatie: " + message);
    }
}
