/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Commands.CommandHandler;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author paul
 */

public class MainSceneController implements Initializable {
    
    CommandHandler comHandler = new CommandHandler();
    
    @FXML
    Button loadButton; 
    @FXML
    Button newButton;
    @FXML
    Button saveButton;
    @FXML
    Button opsButton;
    @FXML
    Button scriptButton;
    @FXML
    Button quitButton;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
        loadButton.defaultButtonProperty().bind(loadButton.focusedProperty());
        newButton.defaultButtonProperty().bind(newButton.focusedProperty());
        saveButton.defaultButtonProperty().bind(saveButton.focusedProperty());
        opsButton.defaultButtonProperty().bind(opsButton.focusedProperty());
        scriptButton.defaultButtonProperty().bind(scriptButton.focusedProperty());
        quitButton.defaultButtonProperty().bind(quitButton.focusedProperty());
    }
    
    @FXML
    private void handleOpsButton(ActionEvent event) throws IOException {
    swapScene("OperationsScene.fxml");
    }
    
    @FXML
    private void handleNewButton(ActionEvent event) throws IOException {
    swapScene("NewMatrixScene.fxml");
    }
    
    @FXML
    private void handleLoadButton(ActionEvent event) throws IOException {
    swapScene("LoadScene.fxml");     
    }
        
    @FXML
    private void handleSaveButton(ActionEvent event) {
        FileChooser save = new FileChooser();
        FileChooser.ExtensionFilter extFilter1 = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        save.getExtensionFilters().add(extFilter1);
        save.getExtensionFilters().add(extFilter2);
        
        File dest = save.showSaveDialog(null);
        if (dest != null) {
        String[] commAndArg = new String[2] ;
        commAndArg[0] = "save";
        commAndArg[1] = dest.getPath();
        comHandler.executeCommands(commAndArg);
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("MuttLab");
        alert.setHeaderText("Save File");
        alert.setContentText("File has been saved.");

        alert.showAndWait();
        }  
    }
        
    @FXML
    private void handleScriptButton(ActionEvent event) {
        
        FileChooser load = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        load.getExtensionFilters().add(extFilter);
        File file = load.showOpenDialog(null);   
        if (file != null)
        {
            String[] commAndArg = new String[2] ;
            commAndArg[0] = "script";
            commAndArg[1] = file.getPath();
            comHandler.executeCommands(commAndArg);

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("MuttLab");
            alert.setHeaderText("Load Script");
            alert.setContentText("Script has been run");

            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleQuitButton(ActionEvent event) {
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("MuttLab");
        alert.setHeaderText("Quit");
        alert.setContentText("Are you want to quit?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            Platform.exit();
        }
        else
            event.consume();
    }
       
    private void swapScene(String url) throws IOException
    {
        Stage stage; 
        Parent root;
        stage=(Stage)loadButton.getScene().getWindow();
     
        root = FXMLLoader.load(getClass().getResource(url));
       
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }    
}
