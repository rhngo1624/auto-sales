package db.tables;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.core.SQLTable;
import db.models.Accessory;
import app.core.SQLModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Accessories extends SQLTable<Accessory> {

    /**
     * Returns Accessory Objects
     * @return ObservableList<SQLModel>
     * @throws SQLException
     */
    public ObservableList<Accessory> getAllRows(){

        String query = "SELECT * FROM Accessories";
        ObservableList<Accessory> data = FXCollections.observableArrayList();
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
                accessory.setDescription(rs.getString("Description"));

                data.add(accessory);

            }

        }catch(SQLException e){

            System.err.println(e.getMessage());
        }

        return data;

    }

    /**
     * Retuns single Accessory object from database.
     * @param id ID of Accessory to get
     * @return SQLModel
     * @throws SQLException
     */
    public Accessory get(int id){

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
                accessory.setDescription(rs.getString("Description"));

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
    public boolean insert(Accessory model) {

        String query = "INSERT into Accessories (Name, Price, ImageLocation, Description) " +
                "VALUES (?, ?, ?, ?)";

        ResultSet keys = null;

        try(
                PreparedStatement stmt = CONN.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS)

        ){

            stmt.setString(1, model.getName());
            stmt.setDouble(2, model.getPrice());
            stmt.setString(3, model.getImageLocation());
            stmt.setString(4, model.getDescription());

            int affectedRows = stmt.executeUpdate();

            if(affectedRows == 1){

                keys = stmt.getGeneratedKeys();
                assignNextID(keys, model);

            }else{

                System.err.println("No rows affected.");
                return false;

            }

        }catch (SQLException e){

            System.err.println(e.getMessage());
            return false;

        } finally {

            SQLTable.closeKeys(keys);

        }

        return true;

    }
    public boolean update(Accessory model){

        String query = "UPDATE Accessories SET Name = ?, Price = ?, ImageLocation = ?, " +
                "Description = ? WHERE ID = ?";

        try(PreparedStatement stmt = CONN.prepareStatement(query)){

            stmt.setString(1, model.getName());
            stmt.setDouble(2, model.getPrice());
            stmt.setString(3, model.getImageLocation());
            stmt.setString(4, model.getDescription());
            stmt.setInt(5, model.getID());

            int affectedRows = stmt.executeUpdate();

            return affectedRows == 1;

        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }

    }


}
