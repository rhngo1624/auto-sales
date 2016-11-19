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
