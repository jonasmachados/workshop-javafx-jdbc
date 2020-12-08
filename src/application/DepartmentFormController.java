package application;

import db.DbException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
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
import model.exceptions.ValidationException;
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

    //Metodo que adiciona um novo obejto na lista, para isso a classe que recebe o evento deve implementar a interface dataChageListener
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
        //Try pois ira tenta salvar no banco de dados
        try {
            entity = getFormData();
            service.saveOrUpdate(entity); //salvandos os dados
            notifyDataChangeListeners();//Notificando os lsiteners, 
            Utils.currentStage(event).close();//Fechando a tela
        }catch(ValidationException e){
            setErrorMessages(e.getErrors());
        } 
        catch (DbException e) {
            Alerts.showAlert("Error saving object", null, e.getMessage(), AlertType.ERROR);
        }
    }

    //Metodo notify, vai emitir o evento dataChange para todos os listeners
    private void notifyDataChangeListeners() {//Para cada objeto dataChange
        for (DataChangeListener listener : dataChangeListeners) {
            listener.onDataChanged();
        }
    }

    
    private Department getFormData() {
        Department obj = new Department();

        ValidationException exception = new ValidationException("Validaation errors");
        
        obj.setId(Utils.tryParseToInt(txtId.getText()));
        
        //If para verificar se o TextFild Nome esta vazio
        if (txtName.getText() == null || txtName.getText().trim().equals("")){
            exception.addError("name", "Field can't be empty");
        }
        obj.setName(txtName.getText());
        
        //Caso ocorra algum erro , lance a excessao
        if(exception.getErrors().size() > 0){
        throw exception;
    }
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

    //Metodo para prencher a mensagen do erro na textLabel
    private void setErrorMessages(Map<String, String> errors){
        Set<String> fields = errors.keySet();
        
        if(fields.contains("name")){
            labelErrorName.setText(errors.get("name"));
        } 
    }
}
