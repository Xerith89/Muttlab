/*
 * This class holds the implementation for all of the
 * menu functions as nested classes within the outer commands class
 * There is one method which takes an Optional as a param
 * the function then branches depending on whether
 * a parameter is required
 * When performing matrix operations, an instance of
 * the MatrixOperations class needs to be instantiated 
 */
package Commands;

import Matrix.Matrix;
import Matrix.MatrixOperations;
import MuttLab.MuttLab;
import IOHandler.TextHandler;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class Commands {
    
    TextHandler text = new TextHandler();
                    
    public class NewMatrix implements CommandInterface
    {
        @Override
        public void executeCommand(Optional<String> arg) {
            if (arg.isPresent())
            {
               //Uses our string based constructor
               Matrix matrix = new Matrix(arg.get()); 
               MuttLab.mats.add(matrix.getString());
            }
            else 
            {
                text.printInvalidInput();
            }
        }
    }
    
    public class ShowHelp implements CommandInterface
    {       
        @Override
        public void executeCommand(Optional<String> arg) {
            if (!arg.isPresent())
            {
                text.printLine("Your commands are: ");
                text.printLine( CommandHandler.commsMap.keySet());
            }
            else
            {
                text.printInvalidArg();
            }
        }
    }
    
    public class SubtractMatrix implements CommandInterface
    {
        
        @Override
        public void executeCommand(Optional<String> arg) {
            
            if (!arg.isPresent())
            {
                MatrixOperations matOps = new MatrixOperations();
                matOps.subtract();
            }
            else
            {
                text.printInvalidArg();
            }
        }
    }
    
   public class AddMatrix implements CommandInterface
    {
        @Override
        public void executeCommand(Optional<String> arg) {
           
            if (!arg.isPresent())
            {
                MatrixOperations matOps = new MatrixOperations();
                matOps.add();
            }
            else
            {
                text.printInvalidArg();
            }
        }
    }
    
    public class MultiplyMatrix implements CommandInterface
    {
        @Override
        public void executeCommand(Optional<String> arg) {
           
            if (arg.isPresent())
            {
                
                MatrixOperations matOps = new MatrixOperations();
                matOps.multiplyScalar(arg.get());
            }
            else
            {
                MatrixOperations matOps = new MatrixOperations();
                matOps.multiply();
            }
        }
    }
    
    public class MultiplyByElements implements CommandInterface
    {

        @Override
        public void executeCommand(Optional<String> arg) {
            
           if (arg.isPresent())
           {
               text.printInvalidArg();
           }
           else
           {
               MatrixOperations matOps = new MatrixOperations();
               matOps.multiplyElementwise();
           }
        }  
    }
    
    public class SaveMatrix implements CommandInterface
    {
        @Override
        public void executeCommand(Optional<String> arg) {
            
            if (!arg.isPresent())
            {
                //If we have no argument then print a message
                text.printInvalidArg();
            }
            else
            {
                //Get the optional and save it to a string
                String outputName = arg.get();
                //Create a new output writer using our string as a file name
                try (FileWriter fw = new FileWriter(outputName, true)) {
                PrintWriter pw = new PrintWriter(fw);
                
                //Iterate through our Matrix list with a lambda applied to each element
                MuttLab.matrixList.forEach((Matrix m) -> {
                    pw.print("[ ");
                    //Loop through rows and columns and written using the various printwriter.print overloads
                        for (int r = 0; r < m.getMatrix().length; r++) {
                            for (int c = 0; c < m.getMatrix()[r].length; c++) {
                                pw.print(m.getMatrix()[r][c]);
                                if (c < m.getMatrix()[r].length - 1) {
                                    pw.print(' ');
                                }
                            }
                            if (r < m.getMatrix().length - 1) {
                                pw.print("; ");
                            }
                        }
                        pw.println(" ]");
                    });
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }
    
    public class Script implements CommandInterface
    {
        @Override
        public void executeCommand(Optional<String> arg) {
            
        if (!arg.isPresent()) //if we have no arg then we have no script name
        {
               text.printInvalidArg();
        }
        else
        {
            //otherwise we save the string from the optional
            String scriptName = arg.get(); 
            //Create a command handler to run within the script function
            CommandHandler handler = new CommandHandler();
            //Try to read the file, if it doesn't exist then print a message
            try (FileReader fileReader = new FileReader(scriptName)) 
            {   
                //Turns the script name into a path and reads all lines of the file into a list
                List<String> scriptLines = Files.readAllLines(Paths.get(scriptName), Charset.defaultCharset());
                //Turn the list into an array of Strings
                String[] commArray = scriptLines.toArray(new String[scriptLines.size()]);
                //Iterate through the array and split the commands and arguments by space  
                for (String command : commArray) 
                {
                    String[] commAndArg = command.split(" ", 2);
                    //Execute the command
                    handler.executeCommands(commAndArg);   
                }
            } catch (FileNotFoundException ex) {
                text.printLine("Cannot find " + scriptName);
            } catch (IOException ex) {
                throw new RuntimeException("Panic: script barfed!");
                }  
            }
        }     
    }
    
    public class DupeMatrix implements CommandInterface 
    {
        @Override
        public void executeCommand(Optional<String> arg) {
            
           if (arg.isPresent())
           {
                text.printInvalidArg();
           }
           else
           {
                if (MuttLab.matrixList.isEmpty())
                {
                    text.printInvalidAmount();
                    return;
                }    
            //Gets the last matrix in our matrix list
            Matrix lastMatrix = MuttLab.matrixList.get(MuttLab.matrixList.size()-1);
            //Creates a new matrix, using the constructor that takes a 2D float array.
            //Get last matrix returns a 2D array of floats
            Matrix m = new Matrix(lastMatrix.getMatrix());
           }
        }
    }
    
    public class QuitProgram implements CommandInterface    {

        @Override
        public void executeCommand(Optional<String> arg) {
            
            //Either way we will quit by setting running to false
            if (arg.isPresent() || !arg.isPresent())
            MuttLab.setRunningFalse();
        }   
    }
    
      public class ShowMatrices implements CommandInterface    {

        @Override
        public void executeCommand(Optional<String> arg) {
            
            if (arg.isPresent())
            {
             text.printInvalidArg();
            }
            else
            {
                //Iterate over the array with a lambda that invokes the show matrix function of
                //each element
                MuttLab.matrixList.forEach((m) -> {
                m.showMatrix(m.getMatrix());
                });
            }
        }       
    }
      
      public class CloneMatrix implements CommandInterface    
      {
        @Override
        public void executeCommand(Optional<String> arg) {
            
            //If we have argument, try to parse it as an int
            if (arg.isPresent())
            {
                try { 
                    int index = Integer.parseInt(arg.get())-1;
                    //If the index is with less or equal to the amount of elements
                    if (index <= MuttLab.matrixList.size()-1)
                    {
                        //Create a new matrix using our 2D float array constructor
                        Matrix m = new Matrix(MuttLab.matrixList.get(index).getMatrix());
                    }
                    else
                    {
                        text.printInvalidInput();
                    }
                } catch (NumberFormatException e)
                {
                    text.printInvalidInput();
                }
            }
            else
            {
                text.printInvalidAmount();
            }
        }     
    }
}
