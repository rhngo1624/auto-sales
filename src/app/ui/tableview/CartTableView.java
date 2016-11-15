package app.ui.tableview;

import app.controllers.CheckoutController;
import app.core.StoreItem;
import app.utils.Session;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;


public class CartTableView extends TableView<StoreItem> {

    public CartTableView(){
        setupColumns();
        setItems(Session.getInstance().getUser().dumpCart());
    }

    private void setupColumns(){

        TableColumn <StoreItem, String> Remove = new TableColumn<>("Remove");
        Remove.setStyle("-fx-alignment: CENTER");
        TableColumn<StoreItem, String> Name = new TableColumn<>("Name");
        Name.setStyle("-fx-alignment: CENTER-LEFT");
        TableColumn<StoreItem, String> Type = new TableColumn<>("Type");
        Type.setStyle("-fx-alignment: CENTER");
        TableColumn<StoreItem, String> Price = new TableColumn<>("Price");

        Remove.setCellValueFactory(new PropertyValueFactory<>("Placeholder"));

        Callback<TableColumn<StoreItem, String>, TableCell<StoreItem, String>> cellFactory = //
                new Callback<TableColumn<StoreItem, String>, TableCell<StoreItem, String>>()
                {
                    @Override
                    public TableCell call(final TableColumn<StoreItem, String> param )
                    {
                        final TableCell<StoreItem, String> cell = new TableCell<StoreItem, String>()
                        {

                            final Button btn = new Button("X");

                            @Override
                            public void updateItem(String item, boolean empty){
                                super.updateItem(item, empty);
                                if (empty){
                                    setGraphic(null);
                                    setText(null);
                                }
                                else{

                                    btn.setPadding(Insets.EMPTY);
                                    btn.setMinWidth(20);

                                    btn.setOnAction((ActionEvent event) ->{

                                        StoreItem StoreItem = getTableView().getItems().get(getIndex());
                                        Session.getInstance().getUser().delCartItem(StoreItem);
                                        setItems(Session.getInstance().getUser().dumpCart());


                                    });

                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        Remove.setCellFactory(cellFactory);
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Type.setCellValueFactory(new PropertyValueFactory<>("type"));
        Price.setCellValueFactory(new PropertyValueFactory<>("dollarAmount"));

        Remove.prefWidthProperty().bind(this.widthProperty().divide(4));
        Name.prefWidthProperty().bind(this.widthProperty().divide(4));
        Type.prefWidthProperty().bind(this.widthProperty().divide(4));
        Price.prefWidthProperty().bind(this.widthProperty().divide(4));

        getColumns().add(0, Remove);
        getColumns().add(1, Name);
        getColumns().add(2, Type);
        getColumns().add(3, Price);

    }
}
