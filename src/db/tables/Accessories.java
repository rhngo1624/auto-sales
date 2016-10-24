package db.tables;


// TODO: refactor SQLTable to be an abstract class and implement D.R.Y principle
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.models.Accessory;
import db.models.SQLModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Accessories implements SQLTable {

    /**
     * Returns Accessory Objects
     * @return ObservableList<SQLModel>
     * @throws SQLException
     */
    public ObservableList<SQLModel> getAllRows() throws SQLException{

        String query = "SELECT * FROM Accessories";
        ObservableList<SQLModel> data = FXCollections.observableArrayList();
        Accessory accessory;

        try(
                Statement stmt = CONN.createStatement();
                ResultSet rs = stmt.executeQuery(query);
        ){

            while(rs.next()) {

                String name = rs.getString("Name");
                double price = rs.getDouble("Price");
                String imageLocation = rs.getString("ImageLocation");

                accessory = new Accessory(name, price, imageLocation);

                accessory.setID(rs.getInt("ID"));

                data.add(accessory);

            }

        }

        return data;

    }

    /**
     * Retuns single Accessory object from database.
     * @param id ID of Accessory to get
     * @return SQLModel
     * @throws SQLException
     */
    public SQLModel getModel(int id) throws SQLException{

        String query = "SELECT * FROM Accessories WHERE ID = ?";
        ResultSet rs;

        try(
                PreparedStatement stmt = CONN.prepareStatement(query,
                        ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_READ_ONLY)){

            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next()){

                String name = rs.getString("Name");
                double price = rs.getDouble("Price");
                String imageLocation = rs.getString("ImageLocation");

                Accessory accessory = new Accessory(name, price, imageLocation);

                accessory.setID(rs.getInt("ID"));

                return accessory;

            }else{

                return null;

            }
        }catch(SQLException e){

            System.err.println(e.getMessage());
            return null;

        }


    }

    /**
     * Insert Accessory Object into database.
     *
     * @param model Accessory object to insert.
     * @return true if successful, false otherwise.
     * @throws Exception
     */
    public boolean insertModel(SQLModel model) throws Exception{

        String query = "INSERT into Accessories (Name, Price, ImageLocation) " +
                "VALUES (?, ?, ?)";

        ResultSet keys = null;

        try(
                PreparedStatement stmt = CONN.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS)

        ){

            stmt.setString(1, ((Accessory)model).getName());
            stmt.setDouble(2, ((Accessory)model).getPrice());
            stmt.setString(3, ((Accessory)model).getImageLocation());

            int affectedRows = stmt.executeUpdate();

            if(affectedRows == 1){

                keys = stmt.getGeneratedKeys();
                keys.next();

                int newKey = keys.getInt(1);

                model.setID(newKey);

            }else{

                System.err.println("No rows affected.");
                return false;

            }

        }catch (SQLException e){

            System.err.println(e.getMessage());
            return false;

        } finally {

            if(keys != null){
                keys.close();
            }

        }

        return true;

    }
    public boolean updateModel(SQLModel model) throws Exception{

        String query = "UPDATE Cars SET Name = ?, Price = ?, ImageLocation = ? WHERE ID = ?";

        try(PreparedStatement stmt = CONN.prepareStatement(query)){

            stmt.setString(1, ((Accessory)model).getName());
            stmt.setDouble(2, ((Accessory)model).getPrice());
            stmt.setString(3, ((Accessory)model).getImageLocation());
            stmt.setInt(4, model.getID());

            int affectedRows = stmt.executeUpdate();

            if(affectedRows == 1){
                return true;
            }else{
                return false;
            }

        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }

    }
    public boolean deleteModel(int id) throws Exception{

        String query = "DELETE FROM Accessories WHERE ID = ?";

        ResultSet keys = null;

        try(PreparedStatement stmt = CONN.prepareStatement(query)){

            stmt.setInt(1, id);
            stmt.execute();

        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        } finally {
            if(keys != null){
                keys.close();
            }
        }

        return true;

    }
    public boolean modelExists(int id){

        try{
            getModel(id);
            return true;
        } catch(Exception e){
            return false;
        }

    }


}
