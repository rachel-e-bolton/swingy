package helpers;

public class GeneralHelpers {
    public static void ClearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
