package app.ui.items;


import app.controllers.CheckoutController;
import app.utils.Session;
import app.core.StoreItem;
import db.models.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

// TODO: refactor to make checkout items presented as a grid pane instead of listview
public class CartItem extends GridPane {

    private StoreItem item;
    private CheckoutController control;
    private int type;

    public CartItem(StoreItem item, CheckoutController control) {

        this.item = item;
        this.control = control;
        if (item.getClass().getSimpleName().equals("Car")) {
            type = 0;
        } else {
            type = 1;
        }

        setup();
    }

    private void setup() {

        Button deleteButton = new Button("X");
        deleteButton.setPadding(Insets.EMPTY);
        deleteButton.setOnAction(new ButtonListener());
        deleteButton.setPrefSize(20, 15);
        deleteButton.setStyle("-fx-background-color: #3c4656; " +
                "-fx-text-fill: white; -fx-font-size: 8pt");
        add(deleteButton, 0, 1);

        Label name = new Label(item.getName());
        name.setStyle("-fx-text-fill: white");
        add(name, 1, 1);

        Label type = new Label(getType());
        type.setStyle("-fx-text-fill: white");
        add(type, 2, 1);

        Label price = new Label(item.getDollarAmount());
        price.setStyle("-fx-text-fill: white");
        add(price, 3, 1);

        setHgap(10);
        setVgap(10);

    }

    public String getType() {
        if (type == 0) {
            return "Car";
        } else {
            return "Accessory";
        }
    }


    private class ButtonListener implements EventHandler<ActionEvent> {

        public void handle(ActionEvent e) {

            if (Session.getInstance().isActive()) {
                User u = Session.getInstance().getUser();
                u.delCartItem(item);
                control.loadItems();

            } else {
                System.out.println("Session not active.");
            }

        }


    }

}
