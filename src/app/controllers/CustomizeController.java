package app.controllers;


import com.apple.laf.AquaButtonBorder;
import com.jfoenix.controls.JFXButton;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.ui.display.CarDisplay;
import app.ui.items.StoreItemPane;
import app.utils.ModalUtil;
import app.utils.Session;
import db.models.Car;
import db.models.User;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *  Controller Class for Customize Page
 */
public class CustomizeController implements Initializable {

    @FXML
    private FlowPane flowPane;
    @FXML
    private JFXButton returnButton;
    @FXML
    private ToolBar toolbar;
    private User user = Session.getInstance().getUser();
    private Car carToCustomize = null;
    private JFXButton tiresButton;
    private JFXButton interiorColorButton;
    private JFXButton exteriorColorButton;

    @FXML
    public void initialize(URL location, ResourceBundle rb) {
        flowPane.getChildren().clear();
        CarDisplay display = new CarDisplay();
        display.setColumns(2);
        display.createElementsWithPanes(getPaneFactory());
        flowPane.getChildren().add(display.getDisplay());
    }

    public void close() {
        ((Stage) flowPane.getScene().getWindow()).close();
    }

    public ArrayList<StoreItemPane> getPaneFactory() {
        ArrayList<StoreItemPane> panes = new ArrayList<>();
        for (Car car : user.getCart().getCars()) {
            StoreItemPane pane = new StoreItemPane(car);
            pane.getClickableImageRef().setOnMouseClicked((e) -> {
                carToCustomize = car;
                ModalUtil.showMessage(carToCustomize.getName() + " was chosen!");
                Timeline timeline = new Timeline();

                KeyValue appear = new KeyValue(flowPane.getScene()
                        .getRoot().opacityProperty(), 1);

                KeyValue disappear = new KeyValue(flowPane.getScene().getRoot().opacityProperty(), 0);

                KeyFrame fade = new KeyFrame(Duration.millis(150), disappear, appear);

                timeline.getKeyFrames().addAll(fade);
                timeline.setOnFinished((ae) -> initNextScreen());
                timeline.play();

            });

            panes.add(pane);
        }

        return panes;
    }

    private void initNextScreen(){
        toolbar.getItems().clear();

        tiresButton = new JFXButton();
        exteriorColorButton = new JFXButton();
        interiorColorButton = new JFXButton();

        ImageView view = new ImageView(new Image("resources/imgs/icons/tire.png"));
        styleButton(tiresButton, view);
        ImageView view2 = new ImageView(new Image("resources/imgs/icons/paint-palette.png"));
        styleButton(exteriorColorButton, view2);
        ImageView view3 = new ImageView(new Image("resources/imgs/icons/design_wheel.png"));
        styleButton(interiorColorButton, view3);

        toolbar.getItems().addAll(exteriorColorButton, interiorColorButton,
                tiresButton, returnButton);



    }

    private void styleButton(JFXButton button, ImageView image){
        image.setFitWidth(40);
        image.setFitHeight(35);
        button.setGraphic(image);
    }


}
