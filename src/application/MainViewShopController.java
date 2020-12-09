package application;

import util.Alerts;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartmentService;

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

    //Metodo para tratar o evento Seller
    @FXML
    public void onMenuItemSellerAction() {
        System.out.println("onMenuItemSellerAction");
    }

    //Metodo para tratar os eventos, trantando Botao DEPARTMENT LIST
    @FXML
    public void onMenuItemDepartmentAction() {
        //Acao de inicializacao do controler
        loadView("DepartmentList.fxml", (DepartmentListController controller) -> {
                controller.setDepartmentService(new DepartmentService()); //DepartmentService tem metodo que recebe colecao de Department
                controller.updateTableView(); //Metodo updateView recebe 
        });
    }

    //Metodo para tratar os eventos, tratando botao ABOUT
    @FXML
    public void onMenuItemAboutAction() {
        loadView("About.fxml", x -> {});
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    //Criando um FXMLLoader para carregar a tela,e sincronizandod evitando as threads
    private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            VBox newVBox = loader.load();

            Scene mainScene = Main.getMainScene();
            VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent(); //GETROOT pega o primeiro elemento da View

            //Manipulacao da tela principal
            Node mainMenu = mainVBox.getChildren().get(0); //Creia um node recebe o Vbox
            mainVBox.getChildren().clear();//Limpa todos os filhos do Main VBox
            mainVBox.getChildren().add(mainMenu);//Pega os filhos do Main Nebu
            mainVBox.getChildren().addAll(newVBox.getChildren()); //Adicionando uma colecao < os filhos do Vbox
            
            //Executa a funcao
            T controller = loader.getController();
            initializingAction.accept(controller);
        } catch (Exception e) {
            //Caso ocorra a execao, o sistema ira msotrar uma Janela de alerta com o Erro
            Alerts.showAlert("IOException", "Error loading view", e.getMessage(), AlertType.ERROR);
        }

    }
}



































