/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author paul
 */
public class QuitSceneController implements Initializable {

    
    @FXML
    Button cancelButton;
    @FXML
    Button yesButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cancelButton.defaultButtonProperty().bind(cancelButton.focusedProperty());
        yesButton.defaultButtonProperty().bind(yesButton.focusedProperty());
    } 
    
    public void quit()
    {
        Platform.exit();
    }
    
    public void cancel()
    {
        cancelButton.getScene().getWindow().hide();
    }
    
}
