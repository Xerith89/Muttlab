/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Commands.CommandHandler;
import Matrix.Matrix;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    
    CommandHandler comHandler = new CommandHandler();
      
    private String input = "";
        
    @FXML
    private Button back;
    
    @FXML
    TextField display;
        
    @FXML
    private void input(ActionEvent event) throws IOException {
       setInput (input+((Button)event.getSource()).getText());
    }
    
     @FXML
    private void space(ActionEvent event) throws IOException {
       setInput(input+ " ");
    }
    
    @FXML
    private void clear(ActionEvent event) throws IOException {
        setInput("");
    }
    
    @FXML
    private void newMat(ActionEvent event) throws IOException {
       
        String[] commAndArg = new String[2] ;
        commAndArg[0] = "[";
        commAndArg[1] = " "+input;
        comHandler.executeCommands(commAndArg);
        setInput("");
        
        if (Matrix.getParseSucceed())
        {
            addSuccess("Matrix Succesfully Added.");
        }
        else
        {
            addSuccess("Error : Invalid Matrix.");
        }
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
    
    private void addSuccess(String result)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("MuttLab");
        alert.setHeaderText("Add Matrix");
        alert.setContentText(result);

        alert.showAndWait();
    }
    
    private void setInput(String str)
    {
       input =str;
       display.setText(input);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
