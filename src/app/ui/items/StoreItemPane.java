package app.ui.items;

import app.utils.ModalUtil;
import app.core.Resource;
import app.utils.Session;
import app.core.StoreItem;
import db.models.User;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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
import javafx.util.Duration;

public class StoreItemPane extends VBox {

    private final StoreItem item;
    private final StoreItemPane paneRef;
    private Label imageLabel;


    public StoreItemPane(StoreItem item){

        this.paneRef = this;
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

        ImageView imageView = new ImageView(new Image(item.getImageLocation()));
        imageView.setFitHeight(150);
        imageView.setFitWidth(230);

        imageLabel = new Label();
        imageLabel.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        imageLabel.setGraphic(imageView);
        imageLabel.setOnMouseClicked(new StoreItemPane.ViewClickListener());

        imageLabel.setTooltip(new Tooltip("Right-Click to Store in Cart"));

        getChildren().add(name);
        getChildren().add(imageLabel);
        getChildren().add(price);

    }

    public StoreItem getItem(){
        if(item != null){
            return item;
        }else{
            return null;
        }
    }

    public Label getClickableImageRef(){
        return imageLabel;
    }

    private class ViewClickListener implements EventHandler<MouseEvent> {

        private Session session = Session.getInstance();

        public void handle(MouseEvent event){

            if (event.getButton() == MouseButton.SECONDARY) {

                if (session.isActive()) {

                    User user = session.getUser();
                    user.getCart().addItem(item);
                    ModalUtil.showMessage(item.getName() + " was added to " + user.getUsername() + "'s cart!");

                }

            } else {

                SelectedItemPane.set(paneRef);
                ModalUtil.setupAndShow(Resource.ITEMVIEW, item.getName(), false);

            }

        }

    }


}
