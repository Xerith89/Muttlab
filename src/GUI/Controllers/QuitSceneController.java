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
 * Handles exiting MuttLab.
 */
public class QuitSceneController implements Initializable {
    
    @FXML
    Button cancelButton;
    @FXML
    Button yesButton;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //space is the default fire button so this sets it to enter for the buttons.
        cancelButton.defaultButtonProperty().bind(cancelButton.focusedProperty());
        yesButton.defaultButtonProperty().bind(yesButton.focusedProperty());
    } 
   
    /**
     * Exit MuttLab.
     */
    public void quit()
    {
        Platform.exit();
    }
    
    /**
    * return to MuttLab without quitting.
    */
    public void cancel()
    {
        cancelButton.getScene().getWindow().hide();
    }
    
}
