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
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private Button backButton;
    
    @FXML
    private Button addButton;
    @FXML
    private Button subButton;
    @FXML
    private Button dupeButton;
    @FXML
    private Button scaleButton;
    @FXML
    private Button multiplyButton;
    @FXML
    private Button multiplyPointButton;
        
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
    
     /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        matrices.setItems(MuttLab.getMatrices());
        
        addButton.defaultButtonProperty().bind(addButton.focusedProperty());
        subButton.defaultButtonProperty().bind(subButton.focusedProperty());
        backButton.defaultButtonProperty().bind(backButton.focusedProperty());
        dupeButton.defaultButtonProperty().bind(dupeButton.focusedProperty());
        scaleButton.defaultButtonProperty().bind(scaleButton.focusedProperty());
        multiplyButton.defaultButtonProperty().bind(multiplyButton.focusedProperty());
        multiplyPointButton.defaultButtonProperty().bind(multiplyPointButton.focusedProperty());
        
        addButton.setOnAction(e -> {
            try {add(e);} catch (IOException ex) {
                Logger.getLogger(NewMatrixSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        subButton.setOnAction(e -> {
            try {subt(e);} catch (IOException ex) {
                Logger.getLogger(NewMatrixSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        backButton.setOnAction(e -> {
            try {back(e);} catch (IOException ex) {
                Logger.getLogger(NewMatrixSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        dupeButton.setOnAction(e -> {
            try {dupe(e);} catch (IOException ex) {
                Logger.getLogger(NewMatrixSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        scaleButton.setOnAction(e -> {
            scale();
        });
        multiplyButton.setOnAction(e -> {
            try {multiply(e);} catch (IOException ex) {
                Logger.getLogger(NewMatrixSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        multiplyPointButton.setOnAction(e -> {
             multiplyPoint();
        });
        
    } 
    
              
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
    stage=(Stage)backButton.getScene().getWindow();
     
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
    
   
}
