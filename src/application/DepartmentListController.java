
package application;

import util.Alerts;
import util.Utils;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

/**
 *
 * @author Jonas create 03/12/2020
 */
public class DepartmentListController implements Initializable{

    @FXML
    private DepartmentService service;
    
    @FXML
    private TableView<Department> tableViewDepartment;
    
    @FXML
    private TableColumn<Department, Integer> tableColumnId;
    
    @FXML
    private TableColumn<Department, String> tableColumnName;
    
    @FXML
    private Button btNew;
    
    private ObservableList<Department> obsList;
    
    //TRATANDO EVENTO DO BOTAO NEW
    @FXML
    public void onBtNewAction(ActionEvent event){
        Stage parenStage = Utils.currentStage(event);
        createDialogForm("DepartmentForm.fxml", parenStage);
    }
    
    //SET PARA CLASSE DEPARTMENT SERVICE
    public void setDepartmentService(DepartmentService service){
        this.service = service;
    }
    
    //Metodo que inicia ao iniciar a classe
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeNodes();
         
    }
    
    private void initializeNodes() {
        //Iniciar o comportamento das colunas
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        //AJUSTANDO A TABLE VIEW AO TAMANHO DA TELA
        Stage stage = (Stage) Main.getMainScene().getWindow();
        tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
    }
    
    //Metodo responsavel por carregar o servico  ejogar o Department
    //Na observableList
    public void updateTableView(){
        if(service == null){
            throw new IllegalStateException("Service was null");
        }
        List<Department> list = service.findyAll();
        obsList = FXCollections.observableArrayList(list);
        tableViewDepartment.setItems(obsList);
    }
    
    //Metodo da janela de dialogo
    private void createDialogForm(String absoluteName, Stage parentStage){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            Pane pane = loader.load();
            //instancia um novo stage , criando um novo stage
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Enter Department data");
            dialogStage.setScene(new Scene(pane));
            dialogStage.setResizable(false); //Resizable: Diz se janela pode ser redimencionada
            dialogStage.initOwner(parentStage);
            dialogStage.initModality(Modality.WINDOW_MODAL);//Trava a janela
            dialogStage.showAndWait();
        } catch (IOException e){
            Alerts.showAlert("IO Exception", "Error loading view ", e.getMessage(), AlertType.ERROR);
        }
    }
}
