package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

public class Utils {

    //Metodo para acessar o Stage onde o Controller que recebeu o evento esta
    public static Stage currentStage(ActionEvent event) {
        return (Stage) ((Node) event.getSource()).getScene().getWindow();

    }

    //Metodo para converter o valor da caixa de texto para Inteiro
    public static Integer tryParseToInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    //Formatar Table Column Date(Data)
    public static <T> void formatTableColumnDate(TableColumn<T, Date> tableColumn, String format) {
        tableColumn.setCellFactory(column -> {
            TableCell<T, Date> cell = new TableCell<T, Date>() {
                private SimpleDateFormat sdf = new SimpleDateFormat(format);

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(sdf.format(item));
                    }
                }
            };
            return cell;
        });
    }

    //Formatar Table Column Double(Ponto flutuante)
    public static <T> void formatTableColumnDouble(TableColumn<T, Double> tableColumn, int decimalPlaces) {
        tableColumn.setCellFactory(column -> {
            TableCell<T, Double> cell = new TableCell<T, Double>() {
                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        Locale.setDefault(Locale.US);//Locale formata teclado
                        setText(String.format("%." + decimalPlaces + "f", item));
                    }
                }
            };
            return cell;
        });
    }
}
