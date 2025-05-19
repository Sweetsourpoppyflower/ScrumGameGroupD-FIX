import java.util.Random;

public class HintFactory {
    private static Random random = new Random();
    
    public static HintProvider createHintProvider(VraagStrategie vraagStrategie) {
        if (random.nextBoolean()) {
            return new HelpHintProvider(vraagStrategie);
        } else {
            return new FunnyHintProvider();
        }
    }
}
