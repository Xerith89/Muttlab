/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author paul
 */
public class AddMenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button cancelButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void discardAndAdd()
    {
        
    }
    
    public void padLeftAndAdd()
    {
        
    }
    
    public void padRightAndAdd()
    {
        
    }
    
    public void cancel() throws IOException
    {
        cancelButton.getScene().getWindow().hide();
    }
    
}
