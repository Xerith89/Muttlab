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
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
                 
    @FXML
    private Button newButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button backButton;
    @FXML
    private Button spaceButton;
    @FXML
    private Button oneButton;
    @FXML
    private Button twoButton;
    @FXML
    private Button threeButton;
    @FXML
    private Button fourButton;
    @FXML
    private Button fiveButton;
    @FXML
    private Button sixButton;
    @FXML
    private Button sevButton;
    @FXML
    private Button eigButton;
    @FXML
    private Button ninButton;
    @FXML
    private Button zeroButton;
    @FXML
    private Button lineButton;
    @FXML
    private Button pointButton;
    
    @FXML
    private TextField display;
    
    @FXML
    private Label SuccessLabel;
    
    @FXML
    private Label FailureLabel;
    
    Timer timer = new Timer(true);
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        clearButton.defaultButtonProperty().bind(clearButton.focusedProperty());
        newButton.defaultButtonProperty().bind(newButton.focusedProperty());
        backButton.defaultButtonProperty().bind(backButton.focusedProperty());
        zeroButton.defaultButtonProperty().bind(zeroButton.focusedProperty());
        oneButton.defaultButtonProperty().bind(oneButton.focusedProperty());
        twoButton.defaultButtonProperty().bind(twoButton.focusedProperty());
        threeButton.defaultButtonProperty().bind(threeButton.focusedProperty());
        fourButton.defaultButtonProperty().bind(fourButton.focusedProperty());
        fiveButton.defaultButtonProperty().bind(fiveButton.focusedProperty());
        sixButton.defaultButtonProperty().bind(sixButton.focusedProperty());
        sevButton.defaultButtonProperty().bind(sevButton.focusedProperty());
        eigButton.defaultButtonProperty().bind(eigButton.focusedProperty());
        ninButton.defaultButtonProperty().bind(ninButton.focusedProperty());
        pointButton.defaultButtonProperty().bind(pointButton.focusedProperty());
        lineButton.defaultButtonProperty().bind(lineButton.focusedProperty());
    }   
           
    @FXML
    private void input(ActionEvent event) throws IOException {
       display.setText((display.getText()+((Button)event.getSource()).getText()));
    }
    
     @FXML
    private void space(ActionEvent event) throws IOException {
       display.setText((display.getText()+ " "));
    }
    
    @FXML
    private void clear(ActionEvent event) throws IOException {
       display.setText("");
    }
    
    @FXML
    private void newMat(ActionEvent event) throws IOException, InterruptedException {
             
        String[] commAndArg = new String[2] ;
        commAndArg[0] = "[";
        commAndArg[1] = " "+display.getText();
        comHandler.executeCommands(commAndArg);
        display.setText("");
        
        if (Matrix.getParseSucceed())
        {
            TimerTask clearLabel = new TimerTask() {
            @Override
            public void run() {
            SuccessLabel.setVisible(false);
                }
            };
            SuccessLabel.setVisible(true);
            timer.schedule(clearLabel, 1500);
        }
        else
        {
            TimerTask clearLabel = new TimerTask() {
            @Override
            public void run() {
            FailureLabel.setVisible(false);
                }
            };
            FailureLabel.setVisible(true);
            timer.schedule(clearLabel, 1500);
        }
    }
        
       
    @FXML
    private void back(ActionEvent event) throws IOException {
    
    Stage stage; 
    Parent root;
    stage=(Stage)backButton.getScene().getWindow();
     
    root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
       
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }    
}
