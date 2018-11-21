package GUI.Controllers;

import Commands.CommandHandler;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
        //For some reason, space is the default fire button so this sets it to enter for the buttons.
        loadButton.defaultButtonProperty().bind(loadButton.focusedProperty());
        newButton.defaultButtonProperty().bind(newButton.focusedProperty());
        saveButton.defaultButtonProperty().bind(saveButton.focusedProperty());
        opsButton.defaultButtonProperty().bind(opsButton.focusedProperty());
        scriptButton.defaultButtonProperty().bind(scriptButton.focusedProperty());
        quitButton.defaultButtonProperty().bind(quitButton.focusedProperty());
    }
    
    /**
    * Swaps to the operations scene by calling swapScene.
    */
    @FXML
    private void handleOpsButton(ActionEvent event) throws IOException {
    swapScene("/GUI/FXML/OperationsScene.fxml");
    }
    
    /**
    * Swaps to the New Matrix scene by calling swapScene.
    */
    @FXML
    private void handleNewButton(ActionEvent event) throws IOException {
    swapScene("/GUI/FXML/NewMatrixScene.fxml");
    }
    
    /**
    * Swaps to the Load scene by calling swapScene.
    */
    @FXML
    private void handleLoadButton(ActionEvent event) throws IOException {
    swapScene("/GUI/FXML/LoadScene.fxml");     
    }
    
    /**
    * Opens a file chooser and saves all matrices as a text document.
    */
    @FXML
    private void handleSaveButton(ActionEvent event) {
        FileChooser save = new FileChooser();
        FileChooser.ExtensionFilter extFilter1 = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        save.getExtensionFilters().add(extFilter1);
              
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
        
    /**
    * Loads and runs a script in .txt format.
    */
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
    
    /**
    * Opens a modal window that prompts the user to confirm whether they are sure
    * they want to quit or not.
    */
    @FXML
    private void handleQuitButton(ActionEvent event) throws IOException {
        
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXML/QuitScene.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Quit MuttLab");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)quitButton).getScene().getWindow() );
        stage.show(); 
    }
      
    /**
    * Gets the current window and uses that to load a new resource 
    * which is then set as the scene.
    */
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
