package Main;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * This class is adapted from the "World of Zuul" application's Parser. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This parser reads user input and tries to interpret it as a command. Every 
 * time it is called it reads a line from the terminal and  * tries to interpret
 * the line as a command. It returns the command  as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Parser 
{
    private Scanner reader;         // source of command input

    /**
     * Create a parser to read from the terminal window.
     * 
     */
    public Parser() 
    {
        reader = new Scanner(System.in);
    }
    
    public String[] getCommand() 
    {
        
        System.out.print("> ");     // print prompt
        String inputLine = reader.nextLine(); // the full input line

        // Find command and argument on the line.
        String[] result = inputLine.split(" ", 2);
                   
        return result;
    }
           
}
