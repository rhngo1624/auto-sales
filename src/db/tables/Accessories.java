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

    private final String ACCESSORY = "0";
    private final String EXT_COLOR = "1";
    private final String INT_COLOR = "2";
    private final String TIRES = "3";

    public ObservableList<Accessory> getAllExteriorColors(){
        String query = "SELECT * FROM Accessories WHERE AddOn = " + EXT_COLOR;
        ObservableList<Accessory> data = FXCollections.observableArrayList();

        try(
                Statement stmt = CONN.createStatement();
                ResultSet rs = stmt.executeQuery(query)
        ){

            while(rs.next()) {

                data.add(makeAccessory(rs));

            }

        }catch(SQLException e){

            System.err.println(e.getMessage());
        }

        return data;
    }

    public ObservableList<Accessory> getAllTires(){

        String query = "SELECT * FROM Accessories WHERE AddOn = " + TIRES;
        ObservableList<Accessory> data = FXCollections.observableArrayList();

        try(
                Statement stmt = CONN.createStatement();
                ResultSet rs = stmt.executeQuery(query)
        ){

            while(rs.next()) {

                data.add(makeAccessory(rs));

            }

        }catch(SQLException e){

            System.err.println(e.getMessage());
        }

        return data;

    }

    public ObservableList<Accessory> getAllInteriorColors(){
        String query = "SELECT * FROM Accessories WHERE AddOn = " + INT_COLOR;
        ObservableList<Accessory> data = FXCollections.observableArrayList();

        try(
                Statement stmt = CONN.createStatement();
                ResultSet rs = stmt.executeQuery(query)
        ){

            while(rs.next()) {

                data.add(makeAccessory(rs));

            }

        }catch(SQLException e){

            System.err.println(e.getMessage());
        }

        return data;
    }

    public ObservableList<Accessory> getAllAccessories(){
        String query = "SELECT * FROM Accessories WHERE AddOn = " + ACCESSORY;
        ObservableList<Accessory> data = FXCollections.observableArrayList();

        try(
                Statement stmt = CONN.createStatement();
                ResultSet rs = stmt.executeQuery(query)
        ){

            while(rs.next()) {

                data.add(makeAccessory(rs));

            }

        }catch(SQLException e){

            System.err.println(e.getMessage());
        }

        return data;
    }

    /**
     * Returns Accessory Objects
     * @return ObservableList<SQLModel>
     *
     */
    public ObservableList<Accessory> getAllRows(){

        String query = "SELECT * FROM Accessories";
        ObservableList<Accessory> data = FXCollections.observableArrayList();

        try(
                Statement stmt = CONN.createStatement();
                ResultSet rs = stmt.executeQuery(query)
        ){

            while(rs.next()) {

                data.add(makeAccessory(rs));

            }

        }catch(SQLException e){

            System.err.println(e.getMessage());
        }

        return data;

    }

    /**
     * Returns single Accessory object from database.
     * @param id ID of Accessory to get
     * @return SQLModel
     *
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

                return makeAccessory(rs);

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
     *
     */
    public boolean insert(Accessory model) {

        String query = "INSERT into Accessories (Name, Price, ImageLocation, Description, Rating, AddOn) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try(
                PreparedStatement stmt = CONN.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)

        ){

            setProperties(stmt, model);
            int affectedRows = stmt.executeUpdate();

            return affectedRows == 1;

        }catch (SQLException e){

            System.err.println(e.getMessage());
            return false;

        }

    }

    public boolean update(Accessory model){

        String query = "UPDATE Accessories SET Name = ?, Price = ?, ImageLocation = ?, " +
                "Description = ?, Rating = ?, AddOn = ? WHERE ID = ?";

        try(PreparedStatement stmt = CONN.prepareStatement(query)){

            setProperties(stmt, model);
            stmt.setInt(7, model.getID());

            int affectedRows = stmt.executeUpdate();

            return affectedRows == 1;

        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }

    }

    private void setProperties(PreparedStatement stmt, Accessory model){

        try{
            stmt.setString(1, model.getName());
            stmt.setDouble(2, model.getPrice());
            stmt.setString(3, model.getImageLocation());
            stmt.setString(4, model.getDescription());
            stmt.setInt(5, model.getRating());
            stmt.setInt(6, model.isAddOn());
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }

    }

    private Accessory makeAccessory(ResultSet rs){

        try{
            String name = rs.getString("Name");
            double price = rs.getDouble("Price");
            String imageLocation = rs.getString("ImageLocation");

            Accessory accessory = new Accessory(name, price, imageLocation);

            accessory.setID(rs.getInt("ID"));
            accessory.setDescription(rs.getString("Description"));
            accessory.setRating(rs.getInt("Rating"));
            accessory.setIsAddOn(rs.getInt("AddOn"));
            return accessory;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return null;
        }

    }


}
