package app.core;

import app.utils.ConnectionUtil;
import java.sql.Connection;
import java.sql.SQLException;
import app.core.SQLModel;
import javafx.collections.ObservableList;



public interface SQLTable {

    Connection CONN = ConnectionUtil.getInstance().getConnection();

    ObservableList<SQLModel> getAllRows() throws SQLException;
    SQLModel getModel(int id) throws SQLException;
    boolean insertModel(SQLModel model) throws Exception;
    boolean updateModel(SQLModel model) throws Exception;
    boolean deleteModel(int id) throws Exception;
    boolean modelExists(int id);

}
