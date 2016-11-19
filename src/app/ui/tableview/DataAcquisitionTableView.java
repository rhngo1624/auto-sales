package app.ui.tableview;


import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class DataAcquisitionTableView<T> extends TableView<T> {

    private ObservableList<T> data;

    public void setData(ObservableList<T> data){
        setItems(data);
        this.data = data;
    }

    public ObservableList<T> getData(){
        return data;
    }

}
