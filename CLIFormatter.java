public class CLIFormatter {
    
    public static void header(String naam) {
        System.out.println("╔═══════════════════════════════════════════════════════════════════════════════════════════════Groep D═══╗");
        System.out.println("                                " + naam);
        System.out.println("╚═══Groep D═══════════════════════════════════════════════════════════════════════════════════════════════╝");
    }
    
    public static void menuHeader(String naam) {
        System.out.println("════════════════════════════════════════════════════════════════════════════════════════════════Groep D════");
        System.out.println("                       " + naam);
        System.out.println("═══════════════════════════════════════════════════════════════════════════════════════════════════════════");
    }
    
    public static void LijnVoorVraag() {
        System.out.println("\n\n═══════════════════════════════════════════════════════════════════════════════════════════════════════════");
    }
    
    public static void LijnNaVraag() {
        System.out.println("═══════════════════════════════════════════════════════════════════════════════════════════════════════════\n");
    }
}
