/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers;

import Matrix.Matrix;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author paul
 */
public class LoadSceneController implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private Button loadSumMax;
    @FXML
    private Button loadSumMin;
    @FXML
    private Button loadMaxEle;
    @FXML
    private Button loadMinEle;
    @FXML
    private Button loadMaxElem;
    @FXML
    private Button loadMinElem;
    @FXML
    private Button add;
    @FXML
    private Button scaleSave;
    @FXML
    private Button filterSave;
    @FXML
    private TextArea display;
    
    /**
    * Initializes the controller class.
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadSumMax.defaultButtonProperty().bind(loadSumMax.focusedProperty());
        loadSumMin.defaultButtonProperty().bind(loadSumMin.focusedProperty());
        loadMaxEle.defaultButtonProperty().bind(loadMaxEle.focusedProperty());
        loadMinEle.defaultButtonProperty().bind(loadMinEle.focusedProperty());
        loadMaxElem.defaultButtonProperty().bind(loadMaxElem.focusedProperty());
        loadMinElem.defaultButtonProperty().bind(loadMinElem.focusedProperty());
        add.defaultButtonProperty().bind(add.focusedProperty());
        scaleSave.defaultButtonProperty().bind(scaleSave.focusedProperty());
        filterSave.defaultButtonProperty().bind(filterSave.focusedProperty());
        backButton.defaultButtonProperty().bind(backButton.focusedProperty());
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
    
    public void loadAndAdd() throws IOException
    {  
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXML/AddMenu.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Load and Add");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)backButton).getScene().getWindow() );
        stage.show(); 
    } 
    
    public void filterAndSave() throws IOException
    {
        FileChooser load = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        load.getExtensionFilters().add(extFilter);
        File file = load.showOpenDialog(null); 
                
        if (file != null)
        {
            List<String> inputFile = Files.lines(file.toPath()).collect(toList());
                        
            Dialog<String> inputDialog = new Dialog<>();
            inputDialog.setTitle("Filter and Save");
            inputDialog.setHeaderText("Enter a Length to Filter On");
            DialogPane dialogPane = inputDialog.getDialogPane();
            dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
            TextField input = new TextField("1");
            dialogPane.setContent(new VBox(8, input));
            Platform.runLater(input::requestFocus);
            inputDialog.setResultConverter(new Callback<ButtonType, String>() {
                @Override
                public String call(ButtonType button) {
                    if (button == ButtonType.OK) {
                        
                        List<String> filtered = new ArrayList();
                        for (int i = 0; i < inputFile.size(); ++i)
                        {  
                            long count = Arrays
                            .stream(inputFile.get(i).split(" ")).map(s -> s.replaceAll(",", ""))
                            .map(Integer::parseInt)
                            .count();                           
                            
                           if (count == Integer.parseInt(input.getText()))
                           {
                               filtered.add(inputFile.get(i));
                           }
                        }
                        
                     
                   FileChooser save = new FileChooser();
        
                   FileChooser.ExtensionFilter extFilter1 = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
                        save.getExtensionFilters().add(extFilter1);
        
                        File dest = save.showSaveDialog(null);
                        if (dest != null) {
                             try (FileWriter fw = new FileWriter(dest, true)) {
                             PrintWriter pw = new PrintWriter(fw);
                             filtered.forEach(e-> {
                                 System.out.println(e);
                                 pw.println(e+",");
                             });
                             } catch (IOException e) {
                                System.err.println(e.getMessage());
                            }

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("MuttLab");
                        alert.setHeaderText("Save File");
                        alert.setContentText("File has been saved.");

                        alert.showAndWait();
                     }
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
            inputDialog.setHeaderText("Enter a Number to Scale by");
            DialogPane dialogPane = inputDialog.getDialogPane();
            dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
            TextField input = new TextField("1");
            dialogPane.setContent(new VBox(8, input));
            Platform.runLater(input::requestFocus);
            inputDialog.setResultConverter(new Callback<ButtonType, String>() {
                
                @Override
                public String call(ButtonType button) {
                    if (button == ButtonType.OK) {
                        
                        List<String> parsed = new ArrayList();
                                                
                        for (int i = 0; i < inputFile.size(); i++)
                        {   
                            List<Integer> numbers = new ArrayList();
                            List<Integer> scaled = new ArrayList();
                            numbers = Arrays
                            .stream(inputFile.get(i).split(" ")).map(s -> s.replaceAll(",", ""))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());

                           numbers.forEach(e -> {
                                try{scaled.add(e*Integer.parseInt(input.getText()));
                              } catch (NumberFormatException t){System.out.println("Invalid Input");}
                           });
                                                        
                            StringBuilder builder = new StringBuilder(scaled.toString());
                            builder.deleteCharAt(0);
                            builder.deleteCharAt(builder.length()-1);
                            parsed.add(builder.toString());
                        }
                        
                        FileChooser save = new FileChooser();
        
                        FileChooser.ExtensionFilter extFilter1 = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
                        save.getExtensionFilters().add(extFilter1);
        
                        File dest = save.showSaveDialog(null);
                        if (dest != null) {
                             try (FileWriter fw = new FileWriter(dest, true)) {
                             PrintWriter pw = new PrintWriter(fw);
                             parsed.forEach(e-> {
                                 System.out.println(e);
                                 pw.println(e+",");
                             });
                             } catch (IOException e) {
                                System.err.println(e.getMessage());
                            }

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("MuttLab");
                        alert.setHeaderText("Save File");
                        alert.setContentText("File has been saved.");

                        alert.showAndWait();
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

        root = FXMLLoader.load(getClass().getResource("/GUI/FXML/MainScene.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
