package db.tables;

import java.sql.SQLException;

import db.models.SQLModel;
import javafx.collections.ObservableList;

public class Reviews implements SQLTable {

    public ObservableList<SQLModel> getAllRows() throws SQLException{
        return null;
    }
    public SQLModel getModel(int id) throws SQLException{
        return null;
    }
    public boolean insertModel(SQLModel model) throws Exception{
        return true;
    }
    public boolean updateModel(SQLModel model) throws Exception{
        return true;
    }
    public boolean deleteModel(int id) throws Exception{
        return true;
    }
    public boolean modelExists(int id){
        return true;
    }
}
