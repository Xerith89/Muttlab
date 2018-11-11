package MuttLab;

import IOHandler.Parser;
import IOHandler.TextHandler;
import Matrix.Matrix;
import Commands.CommandHandler;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the main processing class of the MuttLab application. 
 * MuttLab is a very simple matrix manipulating calculator, somewhat inspired
 * by MATLAB. Users can perform a number of calculations on matrices. 
 * That's all. It should really be extended to make it more useful!
 *
 * To perform calculations, create an instance of this class and call the "exec"
 * method.
 *
 *
 * @author Richard Jones
 * @version 2018.09.18
 */

public class MuttLab {

    private static MuttLab muttLab = new MuttLab();
    private final CommandHandler commHandler;
    private final Parser parser;
    private static boolean isRunning = true;
    private static boolean proVersion;
    private final TextHandler text;
    
    public static List<Matrix> matrixList;
      
    /**
     * Create the editor and initialise its parser.
     * Also creates the command handler, list of
     * matrices and a text handler for this class
     */
    
    private MuttLab(){
        
        commHandler = new CommandHandler();
        matrixList = new ArrayList();
        parser = new Parser();
        text = new TextHandler();
    }
    
    //This is for our singleton design
    public static MuttLab getInstance()
    {
        return muttLab;
    }
       
    /**
     * Main exec routine. Loops until the end of the session.
     * 
     * 
     */
    
    public void exec() {
        
        text.printWelcome();
            
        while (isRunning) {
            commHandler.executeCommands(parser.getCommand());
        }
        text.printQuitMessage();
        text.printLine("Thank you for using MuttLab.  Good bye.");
    }
    
    public static void setRunningFalse()
    {
        isRunning = false;
    }
    
    public static boolean checkProVersion()
    {
        return proVersion = true;
    }
}
