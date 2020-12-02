
package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

/**
 *
 * @author Jonas create 01/12/2020
 */

public class MainViewShopController implements Initializable{

    @FXML
    private MenuItem menuItemSeller;
    
    @FXML
    private MenuItem menuItemDepartment;
    
    @FXML
    private MenuItem menuItemAbout;
    
    //Metodo para tratar os eventos
    @FXML
    public void onMenuItemSellerAction(){
        System.out.println("onMenuItemSellerAction");
    }
    
    //Metodo para tratar os eventos
    @FXML
    public void onMenuItemDepartmentAction(){
        System.out.println("onMenuItemDepartmentAction");
    }
    
    //Metodo para tratar os eventos
    @FXML
    public void onMenuItemAboutAction(){
        System.out.println("onMenuItemAboutAction");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
}
