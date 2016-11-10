package app.core;

import app.utils.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import app.core.SQLModel;
import javafx.collections.ObservableList;



public abstract class SQLTable <T> {

    protected Connection CONN = ConnectionUtil.getInstance().getConnection();

    public abstract ObservableList<T> getAllRows();
    public abstract T get(int id);
    public abstract boolean insert(T model);
    public abstract boolean update(T model);
    boolean delete(int id){

        String className = this.getClass().getSimpleName();

        String query = "DELETE FROM " + className + " WHERE ID = ?";

        try(PreparedStatement stmt = CONN.prepareStatement(query)){

            stmt.setInt(1, id);
            stmt.execute();

        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }

        return true;

    }

    protected static void assignNextID(ResultSet genKeys, SQLModel model){

        if(genKeys != null){

            try{

                genKeys.next();
                int newKey = genKeys.getInt(1);
                model.setID(newKey);

            }catch(SQLException e){
                System.err.println(e.getMessage());

            }
        }
    }

    protected static void closeKeys(ResultSet keys){
        try{
            if(keys != null){
                keys.close();
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

}
