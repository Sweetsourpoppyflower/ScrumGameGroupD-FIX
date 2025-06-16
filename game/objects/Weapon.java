package game.objects;

public interface Weapon {

    default void attack() {
        System.out.println("Je valt het object aan.");
    }
}
