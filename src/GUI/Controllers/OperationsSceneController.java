package GUI.Controllers;

import Commands.CommandHandler;
import Matrix.MatrixOperations;
import MuttLab.MuttLab;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Operations Controller class
 * Handles our operation scene
 * and its methods.
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
    private Button deleteButton;
           
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
        //Intialise list view with oberservable list.                      
        matrices.setItems(MuttLab.mats);  
        //space is the default fire button so this sets it to enter for the buttons.
        addButton.defaultButtonProperty().bind(addButton.focusedProperty());
        subButton.defaultButtonProperty().bind(subButton.focusedProperty());
        backButton.defaultButtonProperty().bind(backButton.focusedProperty());
        dupeButton.defaultButtonProperty().bind(dupeButton.focusedProperty());
        scaleButton.defaultButtonProperty().bind(scaleButton.focusedProperty());
        multiplyButton.defaultButtonProperty().bind(multiplyButton.focusedProperty());
        multiplyPointButton.defaultButtonProperty().bind(multiplyPointButton.focusedProperty());
        deleteButton.defaultButtonProperty().bind(deleteButton.focusedProperty());
    } 
       
    /**
    * Sums two compatible matrices pointwise.
    */
    @FXML
    private void add(ActionEvent event) throws IOException {
        executeCommand("+");
    }
    
    /**
    * Opens a dialog box to get a scaler
    * from the user and then multiples each
    * element by this number.
    */
    @FXML
    private void scale()
    {   
        Dialog<String> inputDialog = new Dialog<>();
        inputDialog.setTitle("Scale a Matrix");
        inputDialog.setHeaderText("Enter a Number to Scale by");
        DialogPane dialogPane = inputDialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        TextField input = new TextField("1");
        dialogPane.setContent(new VBox(8, input));
        Platform.runLater(input::requestFocus);
        
        inputDialog.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                executeCommandAndArg("*",input.getText());
            }
            return null;
        });
       inputDialog.showAndWait();        
    }
      
    /**
    * Multiplies two compatible matrices pointwise.
    */
    @FXML
    private void multiplyPoint()
    {   
        executeCommand(".*");
    }
    
    /**
    * Subtracts two compatible matrices.
    */
    @FXML
    private void subt(ActionEvent event) throws IOException {
        executeCommand("-");
    }
    
    /**
    * Multiplies two compatible matrices.
    */
    @FXML
    private void multiply(ActionEvent event) throws IOException {
        executeCommand("*");
    }
    
    /**
    * Duplicates the last matrix created.
    */
    @FXML
    private void dupe(ActionEvent event) throws IOException {
        executeCommand("dup");
    }
    
    /**
    * Removes the last character from the input string 
    * if it is not empty.
    */
    @FXML
    private void delete(ActionEvent event) throws IOException {
        if (!MuttLab.matrixList.isEmpty())
        {
        MuttLab.matrixList.remove(MuttLab.matrixList.size()-1);
        MuttLab.mats.remove(MuttLab.mats.size()-1);
        }
    }
    
    /**
    * Returns to the previous scene.
    */
    @FXML
    private void back(ActionEvent event) throws IOException {
    
    Stage stage; 
    Parent root;
    stage=(Stage)backButton.getScene().getWindow();
     
    root = FXMLLoader.load(getClass().getResource("/GUI/FXML/MainScene.fxml"));
       
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }
    
    /**
    * Passes the command with to the
    * command handler then calls the function to
    * check if the operation succeeds.
    */
    private void executeCommand(String comm)
    {
        String[] commAndArg = new String[1] ;
        commAndArg[0] = comm;
        comHandler.executeCommands(commAndArg);
        checkOperationSuccess(comm);
    }
    
    /**
    * Passes the command with its argument to the
    * command handler then calls the function to
    * check if the operation succeeds.
    */
    private void executeCommandAndArg(String comm, String arg)
    {
        String[] commAndArg = new String[2] ;
        commAndArg[0] = comm;
        commAndArg[1] = arg;
        comHandler.executeCommands(commAndArg);
        checkOperationSuccess(comm);
    }
    
    /**
    * Checks the operation type and then
    * determines if it succeeded
    * If so, it will print a message for a few seconds
    * If not, it will also print a message for a few seconds.
    */
    private void checkOperationSuccess(String comm)
    {
        if (comm.matches("dup") && MuttLab.mats.size() >0)
        {
            matrices.getItems().add((MuttLab.mats.get(MuttLab.mats.size()-1)));
           
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
           MuttLab.mats.add(MuttLab.matrixList.get(MuttLab.matrixList.size()-1).getString());
                       
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
