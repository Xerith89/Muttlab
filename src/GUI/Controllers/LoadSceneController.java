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
    public TextArea display;
    File file;
    private List<String> inputFile;
        
    /**
    * Initializes the controller class.
     * @param url
     * @param rb
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //space is the default fire button so this sets it to enter for the buttons.
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
    
    /**
    * This is used to get the maximum result from a list of integers.
    * @return 
    * @throws java.io.IOException
    */   
    private List<Integer> getMax() throws IOException
    {
        List<Integer> result = new ArrayList();        
        if (!inputFile.isEmpty())
        {
            for (int i = 0; i < inputFile.size(); i++)
            {    
                result.add(Arrays
                .stream(inputFile.get(i).split(" ")).map(s -> s.replaceAll(",", ""))  
                .mapToInt(Integer::parseInt)
                .max().getAsInt()); 
            }
        }
        return result;
    }
    
    /**
    * This is used to get the sum from a list of integers.
    * @return 
    * @throws java.io.IOException
    */
    private List<Integer> getSum() throws IOException
    {
        List<Integer> result = new ArrayList();
        for (int i = 0; i < inputFile.size(); i++)
        {    
            result.add(Arrays
            .stream(inputFile.get(i).split(" ")).map(s -> s.replaceAll(",", ""))  
            .mapToInt(Integer::parseInt)
            .sum()); 
        }
        return result;
    }
    
    /**
    * This is used to get the minimum result from a list of integers.
    * @return 
    * @throws java.io.IOException
    */   
    private List<Integer> getMin() throws IOException
    {
        List<Integer> result = new ArrayList();        
        if (!inputFile.isEmpty())
        {
            for (int i = 0; i < inputFile.size(); i++)
            {    
                result.add(Arrays
                .stream(inputFile.get(i).split(" ")).map(s -> s.replaceAll(",", ""))  
                .mapToInt(Integer::parseInt)
                .min().getAsInt()); 
            }
        }
        return result;
    }
    
    private int minCollection(List<Integer> input)
    {
        return input.indexOf(Collections.min(input));
    }
    
    private int maxCollection(List<Integer> input)
    {
        return input.indexOf(Collections.max(input));
    }
    
    private void processResult(int indexOne, int indexTwo)
    {
        String untrimmed = inputFile.get(indexOne);
        String trimmed = " " + untrimmed.replaceAll(",", "");
        Matrix m = new Matrix(trimmed);
        MuttLab.MuttLab.mats.add(m.getString());
        display.setText(inputFile.get(indexTwo));
    }
    
    /**
    * uses the max function to get the biggest sum.
    * @throws java.io.IOException
    */   
    public void sumMax() throws IOException
    {
        loadFile();
        List<Integer> result = new ArrayList(); 
        result = getSum();
        if (!result.isEmpty()){
        processResult(maxCollection(result), maxCollection(result));
        }
    }
    
    /**
    * uses the min function to get the smallest sum.
    * @throws java.io.IOException
    */ 
    public void sumMin() throws IOException
    {
        loadFile();
        List<Integer> result = new ArrayList(); 
        result = getSum();
        if (!result.isEmpty()){
        processResult(minCollection(result), minCollection(result));
        }
    }
    
    /**
    * uses the max function to get the matrix with the biggest element
    * If the user hasn't initially loaded then they will be prompted to.
    * @throws java.io.IOException
    */ 
    public void maxEleMax() throws IOException
    {
        loadFile();
        List<Integer> result = new ArrayList(); 
        result = getMax();  
        if (!result.isEmpty()){
        processResult(maxCollection(result), maxCollection(result));
        }
    }
    
    /**
    * uses the min function to get the matrix who's smallest element is
    * bigger than any others 
    * If the user hasn't initially loaded then they will be prompted to.
    * @throws java.io.IOException
    */
    public void maxEleMin() throws IOException
    {
        loadFile();
        List<Integer> result = new ArrayList(); 
        result = getMin();
        if (!result.isEmpty()){
        processResult(maxCollection(result), maxCollection(result));
        }
    }
 
    /**
    * uses the max function to get the matrix who's biggest element is
    * smaller than any others 
    * If the user hasn't initially loaded then they will be prompted to.
    * @throws java.io.IOException
    */
    public void minEleMax() throws IOException
    {
        loadFile();
        List<Integer> result = new ArrayList(); 
        result = getMax();
        if (!result.isEmpty()){
        processResult(minCollection(result), minCollection(result));
        }
    }
    
    /**
    * uses the min function to get the matrix who's smallest element is
    * smaller than any others 
    * If the user hasn't initially loaded then they will be prompted to.
    * @throws java.io.IOException
    */
    public void minEleMin() throws IOException
    {
        loadFile();
        List<Integer> result = new ArrayList(); 
        result = getMin();
        if (!result.isEmpty()){
        processResult(minCollection(result), minCollection(result));
        }
    }
    
    /**
    * Changes scene to Add Menu.
    * @throws java.io.IOException
    */
    public void loadAndAdd() throws IOException
    {  
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXML/AddMenu.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Load and Add");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)backButton).getScene().getWindow() );
        stage.showAndWait();
        if (!AddMenuController.summedMats.isEmpty())
        {
            display.setText(AddMenuController.summedMats.get(AddMenuController.summedMats.size()-1));
        }
    } 
    
    /**
    * Prompts the user for a file and matrix length to
    * filter on and then create a file to be saved.
     * @param title
     * @param headerText
     * @return 
    * @throws java.io.IOException
    */
    
    public String dialogBox(String title, String headerText) throws IOException
    {          
        Dialog<String> inputDialog = new Dialog<>();
        inputDialog.setTitle(title);
        inputDialog.setHeaderText(headerText);
        DialogPane dialogPane = inputDialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        TextField input = new TextField("1");
        dialogPane.setContent(new VBox(8, input));
        Platform.runLater(input::requestFocus);
        inputDialog.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK)
            {
                return input.getText();
            }
            return input.getText();
        });        
        inputDialog.showAndWait();
        return input.getText();
    }
    
    public void filterAndSave() throws IOException
    { 
        loadFile();
        int filter = 1;
        try{filter = Integer.parseInt(dialogBox("Filter and Save", "Enter a Length to Filter on"));}
        catch (NumberFormatException e){System.err.println("Invalid number");}
        List<String> filtered = new ArrayList();
        for (int i = 0; i < inputFile.size(); ++i)
        {
            long count = Arrays
            .stream(inputFile.get(i).split(" "))
            .map(s -> s.replaceAll(",", ""))
            .map(Integer::parseInt)
            .count();
                       
            if (count == filter)
            {
                filtered.add(inputFile.get(i));
            }
        }
        saveFile(filtered);       
    }
    
    /**
    * Prompts the user for a file and number to scale to and 
    * will create a file where each element has been multiplied
    * by that number.
    * @throws java.io.IOException
    */
    public void scaleAndSave() throws IOException
    {
        loadFile();
        List<String> parsed = new ArrayList();
        int scaler = 1;
        try{scaler = Integer.parseInt(dialogBox("Scale and Save","Enter a Number to Scale on"));
        } catch (NumberFormatException t){System.out.println("Invalid Input");}          
        for (int i = 0; i < inputFile.size(); i++)
        {
            List<Integer> numbers = new ArrayList();
            List<Integer> scaled = new ArrayList();
            numbers = Arrays
            .stream(inputFile.get(i).split(" "))
            .map(s -> s.replaceAll(",", ""))
            .map(Integer::parseInt)
            .collect(Collectors.toList());
            final int s = scaler;
        
            numbers.forEach((Integer e) -> {
            scaled.add(e*s);
            });
            StringBuilder builder = new StringBuilder(scaled.toString());
            builder.deleteCharAt(0);
            builder.deleteCharAt(builder.length()-1);
            parsed.add(builder.toString());
        }
        saveFile(parsed);            
    } 
    
    /**
    * File loading function.
    * @throws java.io.IOException
    */
    private void loadFile() throws IOException
    {
        FileChooser load = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        load.getExtensionFilters().add(extFilter);
        file = load.showOpenDialog(null); 
        inputFile = new ArrayList();
        if (file != null)
        {
            inputFile = Files.lines(file.toPath()).collect(toList());
        }
    }
    
    /**
    * Save file function.
    * @throws java.io.IOException
    */
    private void saveFile(List<String> parsed)
    {
        FileChooser save = new FileChooser();
        FileChooser.ExtensionFilter extFilter1 = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        save.getExtensionFilters().add(extFilter1);
        File dest = save.showSaveDialog(null);
        if (dest != null) 
        {
            try (FileWriter fw = new FileWriter(dest, true)) {
            PrintWriter pw = new PrintWriter(fw);
            parsed.forEach(e-> {
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

    /**
    * Returns to the previous scene.
    * @param event
    * @throws java.io.IOException
    */
    public void back(ActionEvent event) throws IOException {
    
        Stage stage; 
        Parent root;
        stage=(Stage)backButton.getScene().getWindow();

        root = FXMLLoader.load(getClass().getResource("/GUI/FXML/MainScene.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}