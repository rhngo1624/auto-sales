package app.utils;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import app.core.Resource;
import app.core.SQLModel;
import app.core.SQLTable;
import db.models.Accessory;
import db.models.Appointment;
import db.models.Car;
import db.models.FinancialApplication;
import db.models.Transaction;
import db.models.User;
import db.tables.Users;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


public class ModalUtil {


    public static void setupAndShow(URL uiPath, String title, boolean undecorated){

        Stage stage;
        Region layout;

        try{

            System.out.println(uiPath);
            layout = FXMLLoader.load(uiPath);

        }catch(IOException e){

            System.out.println("Could not locate FXML File.");
            return;

        }

        stage = new Stage();
        if(undecorated){
            stage.initStyle(StageStyle.TRANSPARENT);
        }
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }

    public static void showWarning(String message){

        Image img = new Image("resources/imgs/icons/warning.png");
        createModal(img, message);

    }

    public static void showMessage(String message){

        Image img = new Image("resources/imgs/icons/envelope.png");
        createModal(img, message);

    }

    public static void showDeleteItemPrompt(SQLTable itemTable, TableView ref){

        SQLModel item = (SQLModel)ref.getSelectionModel().getSelectedItem();
        String itemInfo = "";
        String className = item.getClass().getSimpleName();
        Stage stage = new Stage();

        switch(className){

            case "Accessory":
                itemInfo = ((Accessory)item).getName();
                break;
            case "Car":
                itemInfo = ((Car)item).getName();
                break;
            case "Appointment":
                User u1 = new Users().get(((Appointment)item).getUserID());
                itemInfo = u1.getUsername() + "'s appointment";
                break;
            case "FinancialApplication":
                User u2 = new Users().get(((FinancialApplication)item).getUserID());
                itemInfo = u2.getUsername() + "'s application";
                break;
            case "Transaction":
                User u3 = ((Transaction)item).getUser();
                itemInfo = u3.getUsername() + "'s transaction";
                break;
            case "User":
                itemInfo = ((User)item).getUsername();
                break;
            default:
                ModalUtil.showWarning("Issue acquiring item...");
                stage.close();
                break;

        }

        Label infoDisplay = new Label("Are you sure you want to delete " + itemInfo + "?");
        infoDisplay.setStyle("-fx-text-fill: white;");
        VBox.setMargin(infoDisplay, new Insets(10, 10, 10, 10));

        ImageView imageView = new ImageView("resources/imgs/icons/warning.png");
        StackPane.setMargin(imageView, new Insets(8, 0, 10, 0));

        JFXButton OK = new JFXButton("OK");
        Font futura = new Font("Futura Bold", 13);
        OK.setFont(futura);
        OK.setStyle("-fx-text-fill: white; -fx-background-color: #3c4656;");
        OK.setAlignment(Pos.CENTER);
        OK.setMinWidth(80);
        OK.setOnAction((e) -> {

            if(itemTable.delete(item.getID())){
                ModalUtil.showMessage("Item deleted!");
                stage.close();
                ref.setItems(ref.getItems());
            }else{
                ModalUtil.showWarning("Issue deleting item.");
            }

        });

        JFXButton cancel = new JFXButton("Cancel");
        cancel.setFont(futura);
        cancel.setStyle("-fx-text-fill: white; -fx-background-color: #3c4656;");
        cancel.setAlignment(Pos.CENTER);
        cancel.setMinWidth(80);
        cancel.setOnAction((e) -> {

            Timeline timeline = new Timeline();

            KeyFrame key = new KeyFrame(Duration.millis(125),
                    new KeyValue(stage.getScene().getRoot().opacityProperty(), 0));

            timeline.getKeyFrames().add(key);
            timeline.setOnFinished((ae) -> stage.close());
            timeline.play();


        });

        StackPane infoContainer = new StackPane();
        StackPane.setAlignment(infoDisplay, Pos.CENTER);
        infoContainer.getChildren().add(infoDisplay);

        StackPane imgContainer = new StackPane();
        imgContainer.setPrefSize(200, 100);
        imgContainer.setAlignment(Pos.TOP_CENTER);
        VBox.setMargin(imgContainer, new Insets(5, 0, 0, 0));
        imgContainer.getChildren().add(imageView);
        StackPane.setAlignment(imageView, Pos.CENTER);

        HBox btnContainer = new HBox();
        btnContainer.setPrefSize(200, 100);
        btnContainer.getChildren().addAll(OK, cancel);
        btnContainer.setSpacing(5);
        btnContainer.setAlignment(Pos.CENTER);

        VBox root = new VBox();
        root.setPrefSize(388, 220);
        root.setStyle("-fx-background-color: #2D373B;");
        root.getChildren().addAll(imgContainer, infoContainer, btnContainer);

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    private static void createModal(Image img, String message){

        Stage stage = new Stage();

        Label infoDisplay = new Label(message);
        infoDisplay.setStyle("-fx-text-fill: white;");
        VBox.setMargin(infoDisplay, new Insets(10, 10, 10, 10));

        ImageView imageView = new ImageView(img);
        StackPane.setMargin(imageView, new Insets(8, 0, 10, 0));

        JFXButton button = new JFXButton("OK");
        StackPane.setMargin(button, new Insets(10, 0, 2, 0));
        Font futura = new Font("Futura Bold", 13);
        button.setFont(futura);
        button.setStyle("-fx-text-fill: white; -fx-background-color: #3c4656;");
        button.setAlignment(Pos.CENTER);
        button.setMinWidth(80);
        button.setOnAction((e) -> {

            Timeline timeline = new Timeline();

            KeyFrame key = new KeyFrame(Duration.millis(125),
                    new KeyValue(stage.getScene().getRoot().opacityProperty(), 0));

            timeline.getKeyFrames().add(key);
            timeline.setOnFinished((ae) -> stage.close());
            timeline.play();


        });

        StackPane infoContainer = new StackPane();
        StackPane.setAlignment(infoDisplay, Pos.CENTER);
        infoContainer.getChildren().add(infoDisplay);

        StackPane imgContainer = new StackPane();
        imgContainer.setPrefSize(200, 100);
        imgContainer.setAlignment(Pos.TOP_CENTER);
        VBox.setMargin(imgContainer, new Insets(5, 0, 0, 0));
        imgContainer.getChildren().add(imageView);
        StackPane.setAlignment(imageView, Pos.CENTER);

        StackPane btnContainer = new StackPane();
        btnContainer.setPrefSize(200, 100);
        btnContainer.getChildren().add(button);
        StackPane.setAlignment(button, Pos.CENTER);

        VBox root = new VBox();
        root.setPrefSize(388, 220);
        root.setStyle("-fx-background-color: #2D373B;");
        root.getChildren().addAll(imgContainer, infoContainer, btnContainer);

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();

    }

}
