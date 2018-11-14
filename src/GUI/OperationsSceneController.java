/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Commands.CommandHandler;
import Matrix.MatrixOperations;
import MuttLab.MuttLab;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author paul
 */
public class OperationsSceneController implements Initializable {
    
    @FXML
    private Button back;
        
    @FXML
    TextField scaler;
    
    CommandHandler comHandler = new CommandHandler();
    
    @FXML
    private Label SuccessLabel;
    
    @FXML
    private Label FailureLabel;
    
    Timer timer = new Timer(true);
         
    @FXML
    private ListView<String> matrices = new ListView<>();
              
    @FXML
    private void add(ActionEvent event) throws IOException {
        executeCommand("+");
    }
    
    @FXML
    private void scale()
    {   
        executeCommandAndArg("*",scaler.getText());
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
        checkOperationSuccess(comm);
    }
    
    private void executeCommandAndArg(String comm, String arg)
    {
        String[] commAndArg = new String[2] ;
        commAndArg[0] = comm;
        commAndArg[1] = arg;
        comHandler.executeCommands(commAndArg);
        checkOperationSuccess(comm);
    }
    
    private void checkOperationSuccess(String comm)
    {
        if (comm.matches("dup") && MuttLab.getMatrices().size()>0)
        {
            matrices.getItems().add((MuttLab.getMatrices().get(MuttLab.getMatrices().size()-1)));
           
            TimerTask clearLabel = new TimerTask() {
            @Override
            public void run() {
            SuccessLabel.setVisible(false);
                }
            };
            SuccessLabel.setVisible(true);
            timer.schedule(clearLabel, 1500);
        }
        else if (MatrixOperations.getOperationSuccess())
        {
            matrices.getItems().add((MuttLab.getMatrices().get(MuttLab.getMatrices().size()-1)));
           
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
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        matrices.setItems(MuttLab.getMatrices());
    } 
}
