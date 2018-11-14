package Matrix;

import MuttLab.MuttLab;
import IOHandler.TextHandler;

/**
 * This class is responsible for matrix creation 
 * and queries such as getting a matrix
 * There are two constructors - one takes a string which is parsed
 * the other takes a 2D float array and does not need parsing
 * Each matrix object stores 1 specific matrix which can be retrieved
 * to perform operations on
 */

public final class Matrix
{        
    TextHandler text = new TextHandler();
    private float[][] matrix;
    private String original;
    private static boolean parseSucceed;
    
    public Matrix(String input) 
    {     
        original = input;
        parseMatrix(input);
    }
    
    public Matrix(float[][] input) 
    {     
        matrix = input;
        showMatrix(input);
        addToList();
    }
    
    public String getString()
    {
        return original;
    }
    
    private void parseMatrix(String rep)
    {                   
        int close = rep.indexOf(']');
        if (close != -1) rep = rep.substring(0, close);
       
        String[] rows = rep.split(";");
              
        float[][] mat = new float[rows.length][];
        
        for (int r = 0; r < rows.length; r++) 
        {
            String[] elements = rows[r].trim().split(" ");
            mat[r] = new float[elements.length];
            for(int c = 0; c < elements.length; c++)
            {   
                try 
                {
                    mat[r][c] = Float.parseFloat(elements[c].trim());
                } catch (NumberFormatException e)
                {
                    text.printInvalidInput();
                    parseSucceed = false;
                    return;
                }
            }
        }
       
        for(int row = 1 ; row < mat.length; ++row)
        {
            for(int column =0; column<mat[row].length;++column)
            {
                if (mat[row].length != mat[row-1].length)
                {
                    text.printInvalidInput();
                    parseSucceed = false;
                    return;
                }
            }
        }
        parseSucceed = true;
        matrix = mat;
        showMatrix(matrix);
        MuttLab.matrixList.add(this);
    }
      
    public void showMatrix(float[][] impl) {
        for (int r = 0; r < impl.length; r ++) {
            for (int c = 0; c < impl[r].length; c++) {
                text.print(impl[r][c]);
                if (c < impl[r].length - 1)
                    text.print(' ');
            }
            if (r < impl.length -1)
                text.print("; ");   
        }
    }
    
    public float[][] getMatrix()
    {
        return matrix;
    }
    
   //This function is called in a constructor and is done to avoid compiler warnings
    private void addToList()
    {
         MuttLab.matrixList.add(this);
    }
    
    public static boolean getParseSucceed()
    {
        return parseSucceed;
    }
}
