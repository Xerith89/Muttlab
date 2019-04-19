package Main;

import java.util.HashMap;
import java.util.Map;

/**
 * This class can be utilised to implement support for
 * special matrices such as large or sparse and does
 * so efficiently
 * 
 */
public class SpecialMatrix {
    
    private final int maxCols;
    private final int maxRows;
    private final int capacity;
          
    private final Map<Integer, Float> spMatrix = new HashMap();
          
    public SpecialMatrix(int maxC, int maxR)
    {
        this.maxCols = maxC;
        this.maxRows = maxR;
        capacity = maxCols*maxRows;
       
        for(int i = 0; i < capacity; i++)
        {
            spMatrix.put(i, null);
        }
    } 
    
    public void setElement(int index, float value)
    {
        spMatrix.put(index, value);
    }
    
    public float getElement(int index)
    {
        return spMatrix.get(index);
    }
    
    //returns the index and value of elements that are not null
    public void getAllElements()
    {
        spMatrix.entrySet().stream().filter((t)-> t.getValue() != null )
                .forEach((t)-> System.out.println(
                        "Index: "+(t.getKey()+ " " + "Value: "+ t.getValue())));
    }
    
    //Takes a lower and higher range and an array of float values and then loops through and sets each element
    public void setRange(int lower, int higher, float[] values)
    {
        for (int i = lower; i <= higher; i++)
        {
            spMatrix.put(i, values[i]);
        }
    }
    
}
