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
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        closeButton.defaultButtonProperty().bind(closeButton.focusedProperty());
        discardAndAdd.defaultButtonProperty().bind(discardAndAdd.focusedProperty());
        padLeftAndAdd.defaultButtonProperty().bind(padLeftAndAdd.focusedProperty());
        padRightAndAdd.defaultButtonProperty().bind(padRightAndAdd.focusedProperty());
    }  
    
    public void discardAndAdd() throws IOException
    {
        statusLabel.setText("");
        FileChooser load = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        load.getExtensionFilters().add(extFilter);
        File file = load.showOpenDialog(null); 
                
        if (file != null)
        {
            List<String> inputFile = Files.lines(file.toPath()).collect(toList());
            List<String> filtered = new ArrayList();
            List<String[]> split = new ArrayList();
            List<Integer> numbers = new ArrayList();     
                       
            long indexCount = Arrays
            .stream(inputFile.get(0).split(" ")).map(s -> s.replaceAll(",", ""))
            .map(Integer::parseInt)
            .count();  
            
            int[] results = new int[(int)indexCount];
         
            for (int i = 0; i < inputFile.size(); ++i)
            {  
                long count = Arrays
                .stream(inputFile.get(i).split(" ")).map(s -> s.replaceAll(",", ""))
                .map(Integer::parseInt)
                .count();                           
                            
                if (count == indexCount)
                {
                    filtered.add(inputFile.get(i));
                }
            } 
                        
            filtered.forEach((s) -> {
                split.add(s.split(" "));
            });
                            
            split.forEach((s) -> {
                Arrays.stream(s).map(h -> h.replaceAll(",", ""))
                .mapToInt(Integer::parseInt)
                .forEach(e -> {
                    numbers.add(e);
                });
            });
                        
            for (int i = 0; i < (int)indexCount; i++)
            {
                for (int j = 0; j< numbers.size(); j+=(int)indexCount)
                {
                    results[i]+=numbers.get(i);
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
        }
    }
    
    public void padLeftAndAdd() throws IOException
    {
        statusLabel.setText("");
        FileChooser load = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        load.getExtensionFilters().add(extFilter);
        File file = load.showOpenDialog(null); 
        
        List<String[]> split = new ArrayList();
        List<Integer> numbers = new ArrayList(); 
        
        if (file != null)
        {
            List<String> inputFile = Files.lines(file.toPath()).collect(toList());
                        
            String[] longest = new String[1];
            longest [0] = inputFile.stream().max(Comparator.comparingInt(String::length)).get();
            
            long indexCount = Arrays
            .stream(longest[0].split(" ")).map(s -> s.replaceAll(",", ""))
            .map(Integer::parseInt)
            .count(); 
            
            int[] results = new int[(int)indexCount];
            
            for (int i = 0; i < inputFile.size(); ++i)
            {  
                long count = Arrays
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
                        
            inputFile.forEach((s) -> {
                split.add(s.split(" "));
            });
                            
            split.forEach((s) -> {
                Arrays.stream(s).map(h -> h.replaceAll(",", ""))
                .mapToInt(Integer::parseInt)
                .forEach(e -> {
                    numbers.add(e);
                });
            });
                               
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
        }   
    }
    
    public void padRightAndAdd() throws IOException
    {
        statusLabel.setText("");
        FileChooser load = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        load.getExtensionFilters().add(extFilter);
        File file = load.showOpenDialog(null);
        
        List<String[]> split = new ArrayList();
        List<Integer> numbers = new ArrayList(); 
                
        if (file != null)
        {
            List<String> inputFile = Files.lines(file.toPath()).collect(toList());
                       
            String[] longest = new String[1];
            longest [0] = inputFile.stream().max(Comparator.comparingInt(String::length)).get();
            
            long indexCount = Arrays
            .stream(longest[0].split(" ")).map(s -> s.replaceAll(",", ""))
            .map(Integer::parseInt)
            .count(); 
            
            int[] results = new int[(int)indexCount];
             
            for (int i = 0; i < inputFile.size(); ++i)
            {  
                long count = Arrays
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
            
               inputFile.forEach((s) -> {
                split.add(s.split(" "));
            });
                            
            split.forEach((s) -> {
                Arrays.stream(s).map(h -> h.replaceAll(",", ""))
                .mapToInt(Integer::parseInt)
                .forEach(e -> {
                    numbers.add(e);
                });
            });
                               
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
        }   
    }
    
    public void close() throws IOException
    {
        closeButton.getScene().getWindow().hide();
    }
    
}
