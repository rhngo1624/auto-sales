package app.ui;

import app.utils.ModalUtil;
import app.utils.Resource;
import db.models.Car;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class CarPane extends VBox {

    private final Car car;
    private final String name;

    public CarPane(Car car){
        this.car = car;
        name = car.getMake() + " " + car.getModel() + " " + car.getYear();
        setAlignment(Pos.CENTER);
        setPadding(new Insets(10, 10, 10, 10));
        setupPane();
    }

    public void setupPane(){

        Label name = new Label(this.name);
        name.setStyle("-fx-text-fill: white;");

        Label price = new Label(car.getDollarAmount());
        price.setStyle("-fx-text-fill: white");

        ImageView image = new ImageView(new Image(car.getImageLocation()));
        image.setOnMouseClicked(new ViewClickListener());

        image.setFitHeight(150);
        image.setFitWidth(230);

        getChildren().add(name);
        getChildren().add(image);
        getChildren().add(price);

    }

    private class ViewClickListener implements EventHandler<MouseEvent> {

        public void handle(MouseEvent event){

            if(event.getButton() == MouseButton.SECONDARY){

                // TODO: Display tooltip on mouse dragged over to say "Right click to add to cart"

            }else{

                ModalUtil.setupAndShow(Resource.CARVIEW, name);

            }

        }

    }

}
