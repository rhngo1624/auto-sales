package app.controllers;


import java.net.URL;
import java.util.ResourceBundle;

import app.core.Resource;
import app.core.StoreItem;
import app.ui.items.SelectedItemPane;
import app.ui.items.StoreItemPane;
import app.utils.ModalUtil;
import app.utils.Session;
import db.models.Review;
import db.tables.Reviews;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

// TODO: finish implementing logic, make sure to check car and accessory for a reviews field
public class ReviewsController implements Initializable {

    @FXML
    private VBox mainPane;

    private StoreItem item;

    public ReviewsController(){
        if(SelectedItemPane.get() != null){
            this.item = SelectedItemPane.get().getItem();
        }

    }

    @FXML
    public void initialize(URL location, ResourceBundle rb){

        Reviews table = new Reviews();

        if(table.getModels(item.getID()).isEmpty()){
            ModalUtil.showMessage("No reviews");
            mainPane.getScene().getWindow().hide();

        }else{

            for(Review r : new Reviews().getModels(item.getID())){
                makeView(r);
            }

        }

    }

    private void makeView(Review review){

        VBox reviewPane = new VBox();

        Label title = new Label("Title: " + review.getTitle());
        Label owner = new Label("Owner: " + review.getOwner());
        Label rating = new Label("Rating: " + getRating(review.getRating()));

        title.setPadding(new Insets(5,5,5,5));
        owner.setPadding(new Insets(5,5,5,5));
        rating.setPadding(new Insets(5,5,5,5));

        TextArea content = new TextArea(review.getContents());
        content.setWrapText(true);
        content.setEditable(false);
        content.setMinSize(120,120);

        VBox.setMargin(reviewPane, new Insets(10,10,10,10));
        reviewPane.setSpacing(10);

        reviewPane.getChildren().addAll(rating, title, owner, content);
        reviewPane.setStyle("-fx-border-color: black");

        mainPane.getChildren().add(reviewPane);

    }

    private String getRating(int val){

        StringBuilder rating = new StringBuilder();

        for(int i = 0; i < val; i++){
            rating.append("*");
        }

        return rating.toString();

    }

}
