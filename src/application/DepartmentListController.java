
package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entilies.Department;

/**
 *
 * @author Jonas create 03/12/2020
 */
public class DepartmentListController implements Initializable{

    @FXML
    private TableView<Department> tableViewDepartment;
    
    @FXML
    private TableColumn<Department, Integer> tableColumnId;
    
    @FXML
    private TableColumn<Department, String> tableColumnName;
    
    @FXML
    private Button btNew;
    
    //TRATANDO EVENTO DO BOTAO NEW
    @FXML
    public void onBtNewAction(){
        System.out.println("onBtNewAction");
    }
    
    //Metodo que inicia ao iniciar a classe
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeNodes();
         
    }
    
    private void initializeNodes() {
        //Iniciar o comportamento das colunas
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        //AJUSTANDO A TABLE VIEW AO TAMANHO DA TELA
        Stage stage = (Stage) Main.getMainScene().getWindow();
        tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
    }
    
}
