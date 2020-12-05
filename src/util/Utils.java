
package util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;


public class Utils {
    
    //Metodo para acessar o Stage onde o Controller que recebeu o evento esta
    public static Stage currentStage(ActionEvent event){
        return (Stage) ((Node) event.getSource()).getScene().getWindow();
        
    }  
    
    //Metodo para converter o valor da caixa de texto para Inteiro
    public static Integer tryParseToInt(String str){
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
