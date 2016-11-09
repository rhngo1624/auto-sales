package app.core;

import app.utils.ConnectionUtil;
import java.sql.Connection;
import java.sql.SQLException;
import app.core.SQLModel;
import javafx.collections.ObservableList;



public interface SQLTable <T> {

    Connection CONN = ConnectionUtil.getInstance().getConnection();

    ObservableList<T> getAllRows() throws SQLException;
    SQLModel getModel(int id) throws SQLException;
    boolean insertModel(T model) throws Exception;
    boolean updateModel(T model) throws Exception;
    boolean deleteModel(int id) throws Exception;
    boolean modelExists(int id);

}
