/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers;

import Matrix.Matrix;
import MuttLab.MuttLab;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import static java.util.stream.Collectors.toList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author paul
 */
public class AddMenuController implements Initializable {

    @FXML
    private Button discardAndAdd;
    @FXML
    private Button padLeftAndAdd; 
    @FXML
    private Button padRightAndAdd; 
    @FXML
    private Button closeButton;
    @FXML
    private Label statusLabel;
    
    /**
    * indexCount will store the amount of lines
    * that are in the CSV file and the amount of
    * values per line (these will be the same as
    * we can only add elementwise on square matrices
    * index is used to compare the current line to the
    * master line
    */
    long indexCount;
    long count;
    
    /**
    * These functions are triggered by clicking the corresponding buttons
    * you need to call add and pass in a function that returns a List<String>
    * as a parameter
     * @throws java.io.IOException
    */
    public void handleDiscardButton() throws IOException {
        add(discard());
    }
    public void handlePadLeftButton() throws IOException {
        add(padLeft());
    }
    public void handlePadRightButton() throws IOException {
        add(padRight());
    }
        
    /**
    * Initializes the controller class.
     * @param url
     * @param rb
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        closeButton.defaultButtonProperty().bind(closeButton.focusedProperty());
        discardAndAdd.defaultButtonProperty().bind(discardAndAdd.focusedProperty());
        padLeftAndAdd.defaultButtonProperty().bind(padLeftAndAdd.focusedProperty());
        padRightAndAdd.defaultButtonProperty().bind(padRightAndAdd.focusedProperty());     
    }  
    
    /**
    * Loads a file, gets the count of the first line
    * then iterates through and returns a new list
    * that only contains elements of the same size 
    * as the first.
    */
    private List<String> discard() throws IOException
    {
        File file = loadFile();
        List<String> filtered = new ArrayList();        
        if (file != null)
        {
            List<String> inputFile = Files.lines(file.toPath()).collect(toList());
                                    
            indexCount = Arrays
            .stream(inputFile.get(0).split(" ")).map(s -> s.replaceAll(",", ""))
            .map(Integer::parseInt)
            .count();  
            
            for (int i = 0; i < inputFile.size(); ++i)
            {  
                count = Arrays
                .stream(inputFile.get(i).split(" ")).map(s -> s.replaceAll(",", ""))
                .map(Integer::parseInt)
                .count();                           
                
                if (count == indexCount)
                {
                    filtered.add(inputFile.get(i));
                }
            } 
        }
        return filtered;
    }
    
    /**
    * Loads a file, gets the count of the first line
    * then iterates through and returns a new list
    * that adds 0s to the beginning of the string so
    * the count matches the index count.    * 
    */
    private List<String> padLeft() throws IOException
    {
        File file = loadFile();
        List<String> inputFile = new ArrayList();
        
        if (file != null)
        {
            inputFile = Files.lines(file.toPath()).collect(toList());
                        
            String[] longest = new String[1];
            longest [0] = inputFile.stream().max(Comparator.comparingInt(String::length)).get();
            
            indexCount = Arrays
            .stream(longest[0].split(" ")).map(s -> s.replaceAll(",", ""))
            .map(Integer::parseInt)
            .count(); 
                        
            for (int i = 0; i < inputFile.size(); ++i)
            {  
                count = Arrays
                .stream(inputFile.get(i).split(" ")).map(s -> s.replaceAll(",", ""))
                .map(Integer::parseInt)
                .count();                           
                            
                if (count != indexCount)
                {
                    StringBuilder builder = new StringBuilder(inputFile.get(i));
                    long difference = indexCount - count;
                    for (int j = 0; j < difference; j++)
                    {
                        builder.insert(0, "0, ");
                    }
                    inputFile.set(i, builder.toString());
                }     
            }
        }   
        return inputFile;
    }
    
    /**
    * Loads a file, gets the count of the first line
    * then iterates through and returns a new list
    * that adds 0s to the end of the string so
    * the count matches the index count.    * 
    */
    private List<String> padRight() throws IOException
    {
        File file = loadFile();
        List<String> inputFile = new ArrayList();
                
        if (file != null)
        {
            inputFile = Files.lines(file.toPath()).collect(toList());
                       
            String[] longest = new String[1];
            longest [0] = inputFile.stream().max(Comparator.comparingInt(String::length)).get();
            
            indexCount = Arrays
            .stream(longest[0].split(" ")).map(s -> s.replaceAll(",", ""))
            .map(Integer::parseInt)
            .count(); 
            
            for (int i = 0; i < inputFile.size(); ++i)
            {  
                count = Arrays
                .stream(inputFile.get(i).split(" ")).map(s -> s.replaceAll(",", ""))
                .map(Integer::parseInt)
                .count();                           
                          
                if (count != indexCount)
                {
                    StringBuilder builder = new StringBuilder(inputFile.get(i));
                    long difference = indexCount - count;
                    for (int j = 0; j < difference; j++)
                    {
                        builder.append(", 0");
                    }
                    inputFile.set(i, builder.toString());
                }
            }
        }
        return inputFile;
    }
    
    public void close() throws IOException
    {
        closeButton.getScene().getWindow().hide();
    }
    
    /**
    * This function takes in a list of string, parses it and then
    * sums element wise, converts back to a string in order to 
    * create a new matrix which is added to the matrix list.
    * @param input
    * @throws java.io.IOException
    */
    public void add(List<String> input) throws IOException
    {
        List<String[]> split = new ArrayList();
        List<Integer> numbers = new ArrayList();
        
        input.forEach((s) -> {
        split.add(s.split(" "));
        });
                            
        split.forEach((s) -> {
        Arrays.stream(s).map(h -> h.replaceAll(",", ""))
        .mapToInt(Integer::parseInt)
        .forEach(e -> {
        numbers.add(e);
            });
        });
            
        int[] results = new int[(int)indexCount];
                        
        for (int i = 0; i < (int)indexCount; i++)
        {
            for (int j = 0; j< numbers.size(); j+=(int)indexCount)
            {
                results[i]+=numbers.get(j+i);
            }
        }
            
        statusLabel.setText("Operation Completed!");
        String temp = Arrays.toString(results);
        StringBuilder builder = new StringBuilder(temp);
        builder.replace(0, 1, "");
        builder.replace(builder.length()-1, builder.length(), "");
        
        for(int i = 0; i < builder.length()-1; i++)
        {
            if (builder.charAt(i+1)== ',')
            {
                builder.replace(i+1, i+2, "");
            }
        }
        
        Matrix m = new Matrix( builder.toString());
        MuttLab.mats.add(m.getString());
        close();
    }

    private File loadFile()
    {
        statusLabel.setText("");
        FileChooser load = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        load.getExtensionFilters().add(extFilter);
        File file = load.showOpenDialog(null); 
        return file;
    }
}
