/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Commands.CommandHandler;
import Matrix.Matrix;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author paul
 */
public class LoadSceneController implements Initializable {

    /**
     * Initializes the controller class.
     */
    CommandHandler commHandler = new CommandHandler();
    @FXML
    private Button backButton;
      
    @FXML
    private TextArea display;
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        display.setEditable(false);
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
            String untrimmed = inputFile.get(result.indexOf(Collections.max(result)));
            String trimmed = " " + untrimmed.replaceAll(",", "");
            Matrix m = new Matrix(trimmed);
            MuttLab.MuttLab.mats.add(m.getString());
            display.setText(inputFile.get(result.indexOf(Collections.max(result))));
             
            System.out.println("The maximal matrix according to the sum is " + inputFile.get(result.indexOf(Collections.max(result))));
            System.out.println("This has been added to the matrix list");
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
                
            String untrimmed = inputFile.get(result.indexOf(Collections.min(result)));
            String trimmed = " " + untrimmed.replaceAll(",", "");
            Matrix m = new Matrix(trimmed);
            MuttLab.MuttLab.mats.add(m.getString());
            display.setText(inputFile.get(result.indexOf(Collections.min(result))));
            System.out.println("The minimal matrix according to the sum is " + inputFile.get(result.indexOf(Collections.min(result))));
            System.out.println("This has been added to the matrix list");
        }
    }
    
    public void loadMaxEleMax() throws IOException
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
            
            String untrimmed = inputFile.get(result.indexOf(Collections.max(result)));
            String trimmed = " " + untrimmed.replaceAll(",", "");
            Matrix m = new Matrix(trimmed);
            MuttLab.MuttLab.mats.add(m.getString());
            System.out.println("The maximal matrix according to <M " + (inputFile.get(result.indexOf(Collections.max(result)))));
            display.setText(inputFile.get(result.indexOf(Collections.max(result))));
            System.out.println("This has been added to the matrix list");
            
        }
    }
    
    public void loadMaxEleMin() throws IOException
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
            
            String untrimmed = inputFile.get(result.indexOf(Collections.min(result)));
            String trimmed = " " + untrimmed.replaceAll(",", "");
            Matrix m = new Matrix(trimmed);
            MuttLab.MuttLab.mats.add(m.getString());
            System.out.println("The maximal matrix according to <m " +(inputFile.get(result.indexOf(Collections.min(result)))));
            display.setText(inputFile.get(result.indexOf(Collections.min(result))));
            System.out.println("This has been added to the matrix list");
        }
    }
    
    public void loadMinEleMax() throws IOException
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
                 
            String untrimmed = inputFile.get(result.indexOf(Collections.min(result)));
            String trimmed = " " + untrimmed.replaceAll(",", "");
            Matrix m = new Matrix(trimmed);
            MuttLab.MuttLab.mats.add(m.getString());
            System.out.println("The minimal matrix according to <M " + (inputFile.get(result.indexOf(Collections.min(result)))));
            display.setText(inputFile.get(result.indexOf(Collections.min(result))));
            System.out.println("This has been added to the matrix list");
            
        }
    }
    
    public void loadMinEleMin() throws IOException
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
                    .min().getAsInt());
                }
            
            String untrimmed = inputFile.get(result.indexOf(Collections.min(result)));
            String trimmed = " " + untrimmed.replaceAll(",", "");
            Matrix m = new Matrix(trimmed);
            MuttLab.MuttLab.mats.add(m.getString());
            display.setText(inputFile.get(result.indexOf(Collections.min(result))));
            System.out.println("The minimal matrix according to <m " +(inputFile.get(result.indexOf(Collections.min(result)))));
            System.out.println("This has been added to the matrix list");
        }
    }
    
    public void loadAndAdd()
    {
        
    } 
    
    public void trimAndSave() throws IOException
    {
        FileChooser load = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        load.getExtensionFilters().add(extFilter);
        File file = load.showOpenDialog(null); 
                
        if (file != null)
        {
            List<String> inputFile = Files.lines(file.toPath()).collect(toList());
                        
            Dialog<String> inputDialog = new Dialog<>();
            inputDialog.setTitle("Trim and Save");
            inputDialog.setHeaderText("Enter a New Vector Length...");
            DialogPane dialogPane = inputDialog.getDialogPane();
            dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
            TextField input = new TextField("1");
            dialogPane.setContent(new VBox(8, input));
            Platform.runLater(input::requestFocus);
            inputDialog.setResultConverter(new Callback<ButtonType, String>() {
                @Override
                public String call(ButtonType button) {
                    if (button == ButtonType.OK) {
                        
                
 
                }
                    return null;
                }
            });
           inputDialog.showAndWait(); 
             
        } 
    }
    
    
    public void scaleAndSave() throws IOException
    {
        FileChooser load = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        load.getExtensionFilters().add(extFilter);
        File file = load.showOpenDialog(null); 
                
        if (file != null)
        {
            List<String> inputFile = Files.lines(file.toPath()).collect(toList());
                        
            Dialog<String> inputDialog = new Dialog<>();
            inputDialog.setTitle("Scale and Save");
            inputDialog.setHeaderText("Enter a Scaler...");
            DialogPane dialogPane = inputDialog.getDialogPane();
            dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
            TextField input = new TextField("1");
            dialogPane.setContent(new VBox(8, input));
            Platform.runLater(input::requestFocus);
            inputDialog.setResultConverter(new Callback<ButtonType, String>() {
                @Override
                public String call(ButtonType button) {
                    if (button == ButtonType.OK) {
                        
                    List<Integer> result = new ArrayList();
                    
                    for (int i = 0; i < inputFile.size(); i++)
                    {    
                       /* result.add(Arrays
                        .stream(inputFile.get(i).split(" ")).map(s -> s.replaceAll(",", ""))  
                        .mapToInt(Integer::parseInt).peek(e -> System.out.println(e)).toArray());
                        
                        */
                    }                  
                }
                return null;
                }
            });
           inputDialog.showAndWait();   
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
