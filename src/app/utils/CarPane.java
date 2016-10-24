package app.utils;

import db.models.Car;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class CarPane extends VBox {

    private final Car car;
    private final String name;

    public CarPane(Car car){
        this.car = car;
        name = car.getMake() + " " + car.getModel() + " " + car.getYear();
        setupPane();
    }

    public void setupPane(){

        Label name = new Label(this.name);
        Label price = new Label("$" + car.getPrice());
        ImageView image = new ImageView(new Image(car.getImageLocation()));
        Button viewButton = new Button("View");
        viewButton.setOnAction(new ViewButtonListener());

        getChildren().add(name);
        getChildren().add(image);
        getChildren().add(price);
        getChildren().add(viewButton);

    }

    private class ViewButtonListener implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event){

            ModalUtil.setupAndShow(Resource.CARVIEW, name);

        }

    }

}
