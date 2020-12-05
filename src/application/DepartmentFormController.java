
package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import util.Constraints;

/**
 *
 * @author Jonas create 05/12/2020
 */
public class DepartmentFormController implements Initializable{

    //Declarando as variaves, os componentes
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
    
    //Metodo para tratar botao SAVE
    @FXML
    public void onBtSaveAction(){
        System.out.println("onBtSaveAction");
    }
    
    @FXML
    public void onBtCancelAction(){
        System.out.println("onBtCancelAction");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeNodes();
    }
    
    private  void initializeNodes(){
        Constraints.setTextFieldInteger(txtId); //So pode ser inteiro
        Constraints.setTextFieldMaxLength(txtName, 30); //Colocando o limite de caracteres no TXT
    }
    
}
