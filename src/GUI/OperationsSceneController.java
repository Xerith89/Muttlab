/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Commands.CommandHandler;
import Matrix.Matrix;
import MuttLab.MuttLab;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author paul
 */
public class OperationsSceneController implements Initializable {
    
    @FXML
    private Button back;
    
    CommandHandler comHandler = new CommandHandler();
         
    ObservableList<Matrix> matList = FXCollections.observableList(MuttLab.matrixList);
    
    @FXML
    private ListView<String> matrices = new ListView();
              
    @FXML
    private void add(ActionEvent event) throws IOException {
        executeCommand("+");
    }
    
    @FXML
    private void scale()
    {   
    }
    
    @FXML
    private void stream()
    {   
    }
    
    @FXML
    private void multiplyPoint()
    {   
        executeCommand(".*");
    }
    
    @FXML
    private void subt(ActionEvent event) throws IOException {
        executeCommand("-");
    }
    
    @FXML
    private void multiply(ActionEvent event) throws IOException {
        executeCommand("*");
    }
    
     @FXML
    private void dupe(ActionEvent event) throws IOException {
        executeCommand("dup");
    }
    
    @FXML
    private void back(ActionEvent event) throws IOException {
    
    Stage stage; 
    Parent root;
    stage=(Stage)back.getScene().getWindow();
     
    root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
       
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }
    
    private void executeCommand(String comm)
    {
        String[] commAndArg = new String[1] ;
        commAndArg[0] = comm;
        comHandler.executeCommands(commAndArg);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (int i = 0; i < MuttLab.matrixList.size(); i++)
        {
           matrices.getItems().add(Arrays.deepToString(matList.get(i).getMatrix()));
        }
    }    
    
}
