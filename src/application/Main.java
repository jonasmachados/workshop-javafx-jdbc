/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Jonas
 */
public class Main extends Application {

   
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
            Parent parent = loader.load();
            Scene mainScene = new Scene(parent);
            primaryStage.setScene(mainScene);
            primaryStage.setTitle("Sample JavaFX application"); //Titulo para o palco
            primaryStage.show(); //mostrando o palco
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
