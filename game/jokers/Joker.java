package game.jokers;

import game.kamers.Kamer;

public abstract class Joker {
    protected boolean isUsed = false;
    

    public abstract boolean kanGebruiktWordenIn(Kamer kamer);
    

    public void gebruikIn(Kamer kamer) {
        if (isUsed) {
            System.out.println("Deze joker is al gebruikt.");
            return;
        }
        
        if (!kanGebruiktWordenIn(kamer)) {
            System.out.println("Deze joker kan niet in deze kamer worden gebruikt.");
            return;
        }
        

        doeGebruikIn(kamer);
        isUsed = true;
    }

    protected abstract void doeGebruikIn(Kamer kamer);
    
    public boolean isUsed() {
        return isUsed;
    }
    
    public void setUsed(boolean used) {
        this.isUsed = used;
    }
    
    public abstract String beschrijving() throws InterruptedException;

}
