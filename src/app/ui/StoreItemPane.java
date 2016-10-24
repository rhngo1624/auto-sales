package app.ui;

import app.utils.ModalUtil;
import app.utils.Resource;
import db.models.StoreItem;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class StoreItemPane extends VBox {

    private final StoreItem item;

    public StoreItemPane(StoreItem item){

        this.item = item;
        setAlignment(Pos.CENTER);
        setPadding(new Insets(10, 10, 10, 10));
        setupPane();

    }

    private void setupPane(){

        Label name = new Label(item.getName());
        name.setStyle("-fx-text-fill: white;");

        Label price = new Label(item.getDollarAmount());
        price.setStyle("-fx-text-fill: white");

        ImageView image = new ImageView(new Image(item.getImageLocation()));
        image.setOnMouseClicked(new StoreItemPane.ViewClickListener());

        image.setFitHeight(150);
        image.setFitWidth(230);

        Label imageLabel = new Label();
        imageLabel.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        imageLabel.setGraphic(image);

        imageLabel.setTooltip(new Tooltip("Right-Click to Store in Cart"));

        getChildren().add(name);
        getChildren().add(imageLabel);
        getChildren().add(price);

    }

    private class ViewClickListener implements EventHandler<MouseEvent> {

        public void handle(MouseEvent event){

            if(event.getButton() == MouseButton.SECONDARY){

                // TODO: Display tooltip on mouse dragged over to say "Right click to add to cart"

            }else{

                ModalUtil.setupAndShow(Resource.ITEMVIEW, item.getName());

            }

        }

    }


}
