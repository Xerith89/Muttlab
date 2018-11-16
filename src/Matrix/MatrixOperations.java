package Matrix;

import MuttLab.MuttLab;
import IOHandler.TextHandler;

/**
 * This class holds all of our mathematical matrix
 * operations and an instance of text handler for message printing
 * results are generally calculated by looping through the current and
 * previous 2D float arrays and storing them in the new results array
 * This is then used to create a new matrix object calling the constructor
 * that takes a float array rather than a string - this constructor
 * calls a private function in the matrix class that automatically adds
 * the new matrix to the Matrix List.
 * 
 */

public class MatrixOperations {
    
    //These variables are cached as they will be used a lot and so would clutter the code
    int currentEntry = MuttLab.matrixList.size()-1;
    int prevEntry = MuttLab.matrixList.size()-2;
    
    TextHandler text = new TextHandler();
    
    private static boolean commandSuccess = false;
    
    public static boolean getOperationSuccess()
    {
        return commandSuccess;
    }
    
    /*Each of the functions in this class create a new 2D float array to store the result operation
    * The function then loop through the two matrices applying the operator and storing them in result
    * The result variable is then used with the constructor to create a new matrix
    */
    public void add()
    {
       if ( MuttLab.matrixList.size() < 2)
       {
           text.printInvalidAmount();
           commandSuccess = false;
           return;
       }
     
        float[][] current = MuttLab.matrixList.get(currentEntry).getMatrix();
        float[][] previous = MuttLab.matrixList.get(prevEntry).getMatrix();
            
        if (current.length != previous.length || current[0].length != previous[0].length) 
        {
            text.printInvalidOp();
            commandSuccess = false;
            return;
        }
        
        float[][] result = new float[current.length][current[0].length];     
        for (int r = 0; r < current.length; r ++) 
        {
            for (int c = 0; c < current[r].length; c++) 
            {
                result[r][c] = current[r][c] + previous[r][c];
            }
        }
        commandSuccess = true;        
        Matrix m = new Matrix (result);
    }
           
    public void subtract()
    {
       if ( MuttLab.matrixList.size() < 2)
       {
           text.printInvalidAmount();
           commandSuccess = false;
           return;
       }
            float[][] current = MuttLab.matrixList.get(currentEntry).getMatrix();
            float[][] previous = MuttLab.matrixList.get(prevEntry).getMatrix();
            float[][] result = new float[current.length][current[0].length]; 
            
            if (current.length != previous.length || current[0].length != previous[0].length) 
            {
                text.printInvalidOp();
                commandSuccess = false;
                return;
            }
            
            for (int r = 0; r < current.length; r ++) 
            {
                for (int c = 0; c < current[r].length; c++) 
                {
                    result[r][c] = previous[r][c] - current[r][c];
                }
            }
        commandSuccess = true;
        Matrix m = new Matrix (result);  
    }
    
    public void multiply()
    { 
       if ( MuttLab.matrixList.size() < 2)
       {
           text.printInvalidAmount();
           commandSuccess = false;
           return;
       }
            float[][] current = MuttLab.matrixList.get(currentEntry).getMatrix();
            float[][] previous = MuttLab.matrixList.get(prevEntry).getMatrix();
            float[][] result = new float[previous.length][current[0].length]; 
                                
        if (previous[0].length != current.length) {
            text.printInvalidOp();
            commandSuccess = false;
            return;
        }
                
        for (int r = 0; r < previous.length; r++) {
            for (int c = 0; c < current[0].length; c++) { 
                float sum = 0.0f;
                for (int x = 0; x < previous[r].length; x++) {
                    sum += previous[r][x] * current[x][c];
                }
                result[r][c] = sum;
            }
        }
        Matrix m = new Matrix (result);
        commandSuccess = true;
    }
    
    public void multiplyScalar(String scale)
    {
        if ( MuttLab.matrixList.isEmpty())
        {
            text.printInvalidAmount();
            commandSuccess = false;
            return;
        }
        
        float[][] current = MuttLab.matrixList.get(currentEntry).getMatrix();
        float[][] result = new float[current.length][current[0].length]; 
        
        float scalar;
        try { 
            scalar = Float.parseFloat(scale);
        } catch (NumberFormatException e)  
                {
                    text.printInvalidInput();
                    commandSuccess = false;
                    return;
                }
        
        for (int r = 0; r < current.length; r++) {
            for (int c = 0; c < current[0].length; c++) { 
                result[r][c] = scalar * current[r][c];
            }
        } 
        Matrix m = new Matrix (result);
        commandSuccess = true;
    }
             
    public void multiplyElementwise()
    {
        if ( MuttLab.matrixList.size() < 2)
        {
           text.printInvalidAmount();
           commandSuccess = false;
           return;
        }
            float[][] current = MuttLab.matrixList.get(currentEntry).getMatrix();
            float[][] previous = MuttLab.matrixList.get(prevEntry).getMatrix();
            float[][] result = new float[current.length][current[0].length]; 
            
        if (current.length != previous.length ||
            current[0].length != previous[0].length) {
            text.printInvalidOp();
            commandSuccess = false;
            return;
        }
        
        for (int r = 0; r < current.length; r ++) {
            for (int c = 0; c < current[r].length; c++) 
            {
                result[r][c] = previous[r][c] * current[r][c];
            }
        }
        Matrix m = new Matrix (result);
        commandSuccess = true;
    }
}
