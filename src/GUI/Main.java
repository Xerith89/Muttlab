/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import MuttLab.MuttLab;

/**
 * Things to do:
 * fix the matrix list view (remove brackets)
 * error messages for operations with no matrices added
 * tabbing/Enter fire buttons
 * Make the list view update when doing an operation
 * Design and implement the load scene with streams
 * Put things in packages and get it to work
 * Go over the code to try and optimise - such as getting button text 
 * 
 * wishlist:
 * new line when using ; button on new matrix
 * 
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("MuttLab");
        stage.setScene(scene);
        stage.show();
        
        Thread muttlabth = new Thread(() -> {
            MuttLab ml = MuttLab.getInstance();
            ml.exec();
        });
        muttlabth.setDaemon(true);
        muttlabth.start();
    }
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
        
    }
    
}
