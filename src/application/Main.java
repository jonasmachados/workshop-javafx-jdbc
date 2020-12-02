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
import javafx.scene.control.ScrollPane;
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
            ScrollPane scrollPane = loader.load();
            
            //Ajusta janela  do ScrollPane
            scrollPane.setFitToHeight(true); //Ajusta Altura
            scrollPane.setFitToWidth(true);  //Ajustar Largura
            
            Scene mainScene = new Scene(scrollPane);
            primaryStage.setScene(mainScene);
            primaryStage.setTitle("Sample JavaFX application"); //Titulo para o palco
            primaryStage.show(); //mostrando o palco
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
















