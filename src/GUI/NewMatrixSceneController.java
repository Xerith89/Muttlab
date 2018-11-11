/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author paul
 */
public class NewMatrixSceneController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button back;
    
    @FXML
    private TextField display;
    
    @FXML
    private void input(ActionEvent event) throws IOException {
       display.setText("Bla");
    }
    
    @FXML
    private void clear(ActionEvent event) throws IOException {
        display.clear();
    }
    
    @FXML
    private void newMat(ActionEvent event) throws IOException {
        //call command handler add on the string matrix
        //may need to do some checking here for valid format
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
