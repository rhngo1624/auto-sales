package app.ui;

import app.utils.ModalUtil;
import app.utils.Resource;
import db.models.Car;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class CarPane extends VBox {

    private final Car car;
    private final String name;

    public CarPane(Car car){
        System.out.println("Making Pane");
        this.car = car;
        name = car.getMake() + " " + car.getModel() + " " + car.getYear();
        setAlignment(Pos.CENTER);
        setupPane();
    }

    public void setupPane(){

        Label name = new Label(this.name);
        Label price = new Label("$" + car.getPrice());
        ImageView image = new ImageView(new Image(car.getImageLocation()));
        image.setOnMouseClicked(new ViewClickListener());
        image.setFitHeight(150);
        image.setFitWidth(175);

        getChildren().add(name);
        getChildren().add(image);
        getChildren().add(price);

    }

    private class ViewClickListener implements EventHandler<MouseEvent> {

        public void handle(MouseEvent event){
            ModalUtil.setupAndShow(Resource.CARVIEW, name);

        }

    }

}
