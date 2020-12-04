package application;

import gui.util.Alerts;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

/**
 *
 * @author Jonas create 01/12/2020
 */
public class MainViewShopController implements Initializable {

    @FXML
    private MenuItem menuItemSeller;

    @FXML
    private MenuItem menuItemDepartment;

    @FXML
    private MenuItem menuItemAbout;

    //Metodo para tratar os eventos
    @FXML
    public void onMenuItemSellerAction() {
        System.out.println("onMenuItemSellerAction");
    }

    //Metodo para tratar os eventos, trantando Botao DEPARTMENT LIST
    @FXML
    public void onMenuItemDepartmentAction() {
        loadView("DepartmentList.fxml");
    }

    //Metodo para tratar os eventos, tratando botao ABOUT
    @FXML
    public void onMenuItemAboutAction() {
        loadView("About.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
   

    //Criando um FXMLLoader para carregar a tela,e sincronizandod evido as threads
    private synchronized void loadView(String absoluteName) { 
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            VBox newVBox = loader.load();
            
            Scene mainScene = Main.getMainScene();
            VBox mainVBox = (VBox)((ScrollPane)mainScene.getRoot()).getContent(); //GETROOT pega o primeiro elemento da View
            
            
            //Manipulacao da tela principalS
            Node mainMenu = mainVBox.getChildren().get(0); //Creia um node recebe o Vbox
            mainVBox.getChildren().clear();//Limpa todos os filhos do Main VBox
            mainVBox.getChildren().add(mainMenu);//Pega os filhos do Main Nebu
            mainVBox.getChildren().addAll(newVBox.getChildren()); //Adicionando uma colecao < os filhos do Vbox
        } catch (Exception e) {
            //Caso ocorra a execao, o sistema ira msotrar uma Janela de alerta com o Erro
            Alerts.showAlert("IOException", "Error loading view", e.getMessage(), AlertType.ERROR);
        }

    }


}












































