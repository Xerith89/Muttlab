/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import static java.util.stream.Collectors.toList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author paul
 */
public class LoadSceneController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
      @FXML
    private Button backButton;
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void loadSumMax() throws IOException
    {
        FileChooser load = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        load.getExtensionFilters().add(extFilter);
        File file = load.showOpenDialog(null); 
                
        if (file != null)
        {
            List<String> inputFile = Files.lines(file.toPath()).collect(toList());
            List<Integer> result = new ArrayList();
            for (int i = 0; i < inputFile.size(); i++)
                {    
                    result.add(Arrays
                    .stream(inputFile.get(i).split(" ")).map(s -> s.replaceAll(",", ""))  
                    .mapToInt(Integer::parseInt)
                    .sum()); 
                }
                 
            MuttLab.MuttLab.mats.add(inputFile.get(result.indexOf(Collections.max(result))));
             
            System.out.println("The maximal matrix according to the sum is " + 
                   inputFile.get(result.indexOf(Collections.max(result))) + " Total: " + 
                    result.get(result.indexOf(Collections.max(result))));
            
        }
    }
    
    public void loadSumMin() throws IOException
    {
        FileChooser load = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        load.getExtensionFilters().add(extFilter);
        File file = load.showOpenDialog(null); 
                
        if (file != null)
        {
            List<String> inputFile = Files.lines(file.toPath()).collect(toList());
            List<Integer> result = new ArrayList();
            for (int i = 0; i < inputFile.size(); i++)
                {    
                    result.add(Arrays
                    .stream(inputFile.get(i).split(" ")).map(s -> s.replaceAll(",", ""))  
                    .mapToInt(Integer::parseInt)
                    .sum()); 
                }
                
            MuttLab.MuttLab.mats.add(inputFile.get(result.indexOf(Collections.min(result))));
            System.out.println("The minimal matrix according to the sum is " + 
            inputFile.get(result.indexOf(Collections.min(result))) + " Total: " + 
            result.get(result.indexOf(Collections.min(result))));
        }
    }
    
    public void loadMaxEle() throws IOException
    {
        FileChooser load = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        load.getExtensionFilters().add(extFilter);
        File file = load.showOpenDialog(null); 
                
        if (file != null)
        {
            List<String> inputFile = Files.lines(file.toPath()).collect(toList());
            List<Integer> result = new ArrayList();
            for (int i = 0; i < inputFile.size(); i++)
                {    
                    result.add(Arrays
                    .stream(inputFile.get(i).split(" ")).map(s -> s.replaceAll(",", ""))  
                    .mapToInt(Integer::parseInt) 
                    .max().getAsInt());
                }
            
            MuttLab.MuttLab.mats.add(inputFile.get(result.indexOf(Collections.max(result))));
            System.out.println(inputFile.get(result.indexOf(Collections.max(result))));
            
        }
    }
    
    public void loadMinEle() throws IOException
    {
        FileChooser load = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        load.getExtensionFilters().add(extFilter);
        File file = load.showOpenDialog(null); 
                
        if (file != null)
        {
            List<String> inputFile = Files.lines(file.toPath()).collect(toList());
            List<Integer> result = new ArrayList();
            for (int i = 0; i < inputFile.size(); i++)
                {    
                    result.add(Arrays
                    .stream(inputFile.get(i).split(" ")) 
                    .filter((s) -> s.matches("\\d+"))  
                    .mapToInt(Integer::parseInt)  
                    .min().getAsInt());
                }
            
            MuttLab.MuttLab.mats.add(inputFile.get(result.indexOf(Collections.min(result))));
            System.out.println(inputFile.get(result.indexOf(Collections.min(result))));
        }
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
    
    Stage stage; 
    Parent root;
    stage=(Stage)backButton.getScene().getWindow();
     
    root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
       
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }


}
