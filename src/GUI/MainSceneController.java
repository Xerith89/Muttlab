/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author paul
 */
public class MainSceneController implements Initializable {
    
    @FXML
    BorderPane rootPane;
       
    @FXML
    private void handleOpsButton(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("OperationsScene.fxml"));
             
    }
    
    @FXML
    private void handleNewButton(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
    }
    
    @FXML
    private void handleLoadButton(ActionEvent event) {
        System.out.println("You clicked me!");
    }
    
    @FXML
    private void handleSaveButton(ActionEvent event) {
        System.out.println("You clicked me!");
    }
    @FXML
    private void handleScriptButton(ActionEvent event) {
        System.out.println("You clicked me!");
    }
    
    @FXML
    private void handleQuitButton(ActionEvent event) {
       Platform.exit();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
        
}
