package application;

import db.DbException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import listeners.DataChangeListener;
import model.entities.Department;
import model.entities.Seller;
import model.exceptions.ValidationException;
import model.services.DepartmentService;
import model.services.SellerService;
import util.Alerts;
import util.Constraints;
import util.Utils;

/**
 *
 * @author Jonas create 05/12/2020
 */
public class SellerFormController implements Initializable {

    //Declarando as variaves, os componentes
    private Seller entity;

    private SellerService service;

    private DepartmentService departmentService; //Colocando uma dependencia do department Service

    private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtEmail;

    @FXML
    private DatePicker dpBirthDate; //Para utilizar a Data precisa um DatePicker

    @FXML
    private TextField txtBaseSalary;

    @FXML
    private ComboBox<Department> comboBoxDepartment;

    @FXML
    private Label labelErrorName;

    @FXML
    private Label labelErrorEmail;

    @FXML
    private Label labelErrorBirthdate;

    @FXML
    private Label labelErrorBaseSalary;

    @FXML
    private Button btSalve;

    @FXML
    private Button btCancel;

    @FXML
    private ObservableList<Department> obsList;

    //Implementando metodo set do entity 
    public void setSeller(Seller entity) {
        this.entity = entity;
    }

    //Metodo que adiciona um novo obejto na lista, para isso a classe que recebe o evento deve implementar a interface dataChageListener
    public void subscribeDataChangeListener(DataChangeListener listener) {
        dataChangeListeners.add(listener);
    }

    //SET para injetar dos services: O Seller SERVICE eo Department Service
    public void setServices(SellerService service, DepartmentService departmentService) {
        this.service = service;
        this.departmentService = departmentService;
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
        } catch (ValidationException e) { //ValidationException lanca uma excecao se o TextFiled estiver vazioe  voce tentar salvar
            setErrorMessages(e.getErrors());
        } catch (DbException e) {
            Alerts.showAlert("Error saving object", null, e.getMessage(), AlertType.ERROR);
        }
    }

    //Metodo notify, vai emitir o evento dataChange para todos os listeners
    private void notifyDataChangeListeners() {//Para cada objeto dataChange
        for (DataChangeListener listener : dataChangeListeners) {
            listener.onDataChanged();
        }
    }

    //Metodo que verifica se o textFiled esta vazio
    private Seller getFormData() {
        Seller obj = new Seller();

        ValidationException exception = new ValidationException("Validaation errors");

        obj.setId(Utils.tryParseToInt(txtId.getText()));

        //If para verificar se o TextFild Nome esta vazio
        if (txtName.getText() == null || txtName.getText().trim().equals("")) {
            exception.addError("name", "Field can't be empty");
        }
        obj.setName(txtName.getText());

        //Caso ocorra algum erro , lance a excessao
        if (exception.getErrors().size() > 0) {
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
        Constraints.setTextFieldMaxLength(txtName, 70); //Colocando o limite de caracteres no TXT
        Constraints.setTextFieldDouble(txtBaseSalary);
        Constraints.setTextFieldMaxLength(txtEmail, 60);//Definindo Tamanho maximo para o Email
        Utils.formatDatePicker(dpBirthDate, "dd/MM/yyyy");//Formatando a data com Dateìcker

        initializeComboBoxDepartment();//Inicializar a observable list no comboBox
    }

    //Metodo responsavel para pegar os dados do Seller e popular a caixa de texto do formulario
    public void updateFormData() {
        //Testa se Entily esta nulo, o meu seller estiver nulo 
        if (entity == null) {
            throw new IllegalStateException("Entity was null");
        }
        txtId.setText(String.valueOf(entity.getId())); //Pega o ID digitado
        txtName.setText(entity.getName());//Pega o ID digitado
        txtEmail.setText(entity.getEmail());//Pega o Email
        Locale.setDefault(Locale.US);//Locale utilizado para garantir que ele vai colocar o ponto e nao a virgula
        txtBaseSalary.setText(String.format("%.2f", entity.getBaseSalary()));//Precisa converter Double para String
        if (entity.getBirthDate() != null) {
            dpBirthDate.setValue(LocalDate.ofInstant(entity.getBirthDate().toInstant(), ZoneId.systemDefault()));
        }
        if (entity.getDepartment() == null){
            comboBoxDepartment.getSelectionModel().selectFirst();
        } else {
            comboBoxDepartment.setValue(entity.getDepartment());//O department que estiver vinculado ao vendedor vai ao comboBox

        }

    }

    //Carrega os objetos associados
    public void loadAssociatedObjects() {
        if (departmentService == null) {
            throw new IllegalStateException("DepartmentService was null");
        }
        List<Department> list = departmentService.findyAll();
        obsList = FXCollections.observableArrayList(list);//Joga a lista de Department no observableList
        comboBoxDepartment.setItems(obsList);
    }

    //Metodo para prencher a mensagen do erro na textLabel
    private void setErrorMessages(Map<String, String> errors) {
        Set<String> fields = errors.keySet();

        if (fields.contains("name")) {
            labelErrorName.setText(errors.get("name"));
        }
    }

    private void initializeComboBoxDepartment() {
        Callback<ListView<Department>, ListCell<Department>> factory = lv -> new ListCell<Department>() {
            @Override
            protected void updateItem(Department item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }
        };
        comboBoxDepartment.setCellFactory(factory);
        comboBoxDepartment.setButtonCell(factory.call(null));
    }
}
