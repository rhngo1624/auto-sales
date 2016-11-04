package db.tables;

import app.utils.ConnectionUtil;
import java.sql.Connection;
import java.sql.SQLException;
import db.models.SQLModel;
import javafx.collections.ObservableList;

// convert to generics

public interface SQLTable {

    Connection CONN = ConnectionUtil.getInstance().getConnection();

    ObservableList<SQLModel> getAllRows() throws SQLException;
    SQLModel getModel(int id) throws SQLException;
    boolean insertModel(SQLModel model) throws Exception;
    boolean updateModel(SQLModel model) throws Exception;
    boolean deleteModel(int id) throws Exception;
    boolean modelExists(int id);

}
