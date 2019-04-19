package Main;

/**
 * This is the main class for the MuttLab application
 * Singleton class pattern implemented
 * @author Richard Jones
 * @version 2018.09.05
 */
public class Main {
   public static void main(String[] args)  {
        MuttLab ml = MuttLab.getInstance();
        ml.exec();
    }
}
