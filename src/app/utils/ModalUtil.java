package app.utils;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ModalUtil {

    public static void setupAndShow(URL uiPath, String title){

        Stage stage;
        Pane layout;

        try{

            System.out.println(uiPath);
            layout = FXMLLoader.load(uiPath);

        }catch(IOException e){

            System.out.println("Could not locate FXML File.");
            return;

        }

        stage = new Stage();
        Scene loginScene = new Scene(layout);
        stage.setScene(loginScene);
        stage.setTitle(title);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }

    public static void showWarning(String message){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Something has gone wrong.");
        alert.setContentText(message);

        alert.showAndWait();
    }

    public static void showMessage(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setContentText(message);

        alert.showAndWait();
    }
}
