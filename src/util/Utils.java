
package util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;


public class Utils {
    
    //Metodo para acessar o Stage onde o Controller que recebeu o evento esta
    public static Stage currentStage(ActionEvent event){
        return (Stage) ((Node) event.getSource()).getScene().getWindow();
        
    }  
}
