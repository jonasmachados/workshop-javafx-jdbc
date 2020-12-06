package application;

import db.DbException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.text.StyleConstants;
import listeners.DataChangeListener;
import model.entities.Department;
import model.services.DepartmentService;
import util.Alerts;
import util.Constraints;
import util.Utils;

/**
 *
 * @author Jonas create 05/12/2020
 */
public class DepartmentFormController implements Initializable {

    //Declarando as variaves, os componentes
    private Department entity;

    private DepartmentService service;

    private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private Label labelErrorName;

    @FXML
    private Button btSalve;

    @FXML
    private Button btCancel;

    //Implementando metodo set do entity 
    public void setDepartment(Department entity) {
        this.entity = entity;
    }

    //Metodo que adiciona um novo obejto na lista
    public void subscribeDataChangeListener(DataChangeListener listener) {
        dataChangeListeners.add(listener);
    }

    //SET PARA O DEPARTMENT SERVICE
    public void setDepartmentService(DepartmentService service) {
        this.service = service;
    }

    //Metodo para tratar botao SAVE
    @FXML
    public void onBtSaveAction(ActionEvent event) {
        if (entity == null) {
            throw new IllegalStateException("Entity was null");
        }
        if (service == null) {
            throw new IllegalStateException("Service was null");
        }
        try {
            entity = getFormData();
            service.saveOrUpdate(entity); //salvandos os dados
            notifyDataChangeListeners();
            Utils.currentStage(event).close();//Fechando a tela
        } catch (DbException e) {
            Alerts.showAlert("Error saving object", null, e.getMessage(), AlertType.ERROR);
        }
    }

    //Metodo notify / vai emitir o evento dataChange para todos os listeners
    private void notifyDataChangeListeners() {
        for (DataChangeListener listener : dataChangeListeners) {
            listener.onDataChanged();
        }
    }

    private Department getFormData() {
        Department obj = new Department();

        obj.setId(Utils.tryParseToInt(txtId.getText()));
        obj.setName(txtName.getText());

        return obj;
    }

    //Metodo para tratar o botao cancel
    @FXML
    public void onBtCancelAction(ActionEvent event) {
        Utils.currentStage(event).close();//Fechando a tela
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeNodes();
    }

    private void initializeNodes() {
        Constraints.setTextFieldInteger(txtId); //So pode ser inteiro
        Constraints.setTextFieldMaxLength(txtName, 30); //Colocando o limite de caracteres no TXT
    }

    //Metodo responsavel para pegar os dados do Department e popualr a caixa de texto do formulario
    public void updateFormData() {
        //Testa se Entily esta nulo, o meu departamento estiver nulo 
        if (entity == null) {
            throw new IllegalStateException("Entity was null");
        }
        txtId.setText(String.valueOf(entity.getId())); //Pega o ID digitado
        txtName.setText(entity.getName());//Pega o ID digitado
    }

}
