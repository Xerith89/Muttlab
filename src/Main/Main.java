/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import MuttLab.MuttLab;

/**
 * Things to do:
 * improve add scene so buttons work with enter - also maybe get the matrix result in the box
 * Layout of load scene needs work
 * Go over the code to try to clean up and optimise - reduce dupe code, optimise saving etc
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXML/MainScene.fxml"));
        
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
