package db.tables;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.core.SQLTable;
import db.models.Car;
import db.models.Review;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Cars extends SQLTable<Car> {

    /**
     * Returns Car Objects
     * @return ObservableList<Car>
     * @throws SQLException
     */
    public ObservableList<Car> getAllRows(){

        String query = "SELECT * FROM Cars";
        ObservableList<Car> data = FXCollections.observableArrayList();

        try(
                Statement stmt = CONN.createStatement();
                ResultSet rs = stmt.executeQuery(query);
        ){

            while(rs.next()) {

                data.add(makeCar(rs));
            }

        }catch(SQLException e){
            System.err.println(e.getMessage());
        }

        return data;

    }

    /**
     * Retuns single Car object from database.
     * @param id ID of Car to get
     * @return Car
     * @throws SQLException
     */
    public Car get(int id){

        String query = "SELECT * FROM Cars WHERE ID = ?";
        ResultSet rs;

        try(
                PreparedStatement stmt = CONN.prepareStatement(query,
                        ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_READ_ONLY)){

            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next()){

                return makeCar(rs);

            }else{

                return null;

            }
        }catch(SQLException e){

            System.err.println(e.getMessage());
            return null;

        }


    }

    /**
     * Insert Car Object into database.
     *
     * @param model Car object to insert.
     * @return true if successful, false otherwise.
     * @throws Exception
     */
    public boolean insert(Car model){

        String query = "INSERT into Cars (Make, Model, Year, FuelEconomy, Transmission, " +
                "TotalSeating, DoorAmount, EngineType, Rating, ReviewID, Price, ImageLocation) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


        try(
                PreparedStatement stmt = CONN.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS)

                ){

            setProperties(stmt, model);

            int affectedRows = stmt.executeUpdate();

            return affectedRows == 1;

        }catch (SQLException e){

            System.err.println(e.getMessage());
            return false;

        }

    }
    public boolean update(Car model){

        String query = "UPDATE Cars SET " +
                "Make = ?, Model = ?, Year = ?, FuelEconomy = ?, Transmission = ?, " +
                "TotalSeating = ?, DoorAmount = ?, EngineType = ?, Rating = ?, ReviewID = ?, " +
                "Price = ?, ImageLocation = ? WHERE ID = ?";

        try(PreparedStatement stmt = CONN.prepareStatement(query)){

            setProperties(stmt, model);
            stmt.setInt(13, model.getID());

            int affectedRows = stmt.executeUpdate();

            return affectedRows == 1;

        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }

    }

    private String[] getReviews(int id){

        Reviews reviewsTable = new Reviews();
        String[] reviewContents = new String[20];
        int count = 0;
        try{

            for(Review review : reviewsTable.getModels(id)){
                if(count >= 20){
                    break;
                }

                reviewContents[count] = review.getContents();
                count++;

            }
        }catch(SQLException e){
            e.getMessage();
            return null;
        }

        return reviewContents;

    }

    private void setProperties(PreparedStatement stmt, Car model){
        try{

            stmt.setString(1, model.getMake());
            stmt.setString(2, model.getModel());
            stmt.setInt(3, model.getYear());
            stmt.setString(4, model.getFuelEconomy());
            stmt.setString(5, model.getTransmission());
            stmt.setInt(6, model.getTotalSeating());
            stmt.setInt(7, model.getDoorAmount());
            stmt.setString(8, model.getEngineType());
            stmt.setInt(9, model.getRating());
            stmt.setInt(10, model.getReviewID());
            stmt.setDouble(11, model.getPrice());
            stmt.setString(12, model.getImageLocation());

        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

    private Car makeCar(ResultSet rs){

        try{

            String make = rs.getString("Make");
            String model = rs.getString("Model");
            int year = rs.getInt("Year");

            Car car = new Car(make, model, year);

            car.setID(rs.getInt("ID"));
            car.setFuelEconomy(rs.getString("FuelEconomy"));
            car.setTransmission(rs.getString("Transmission"));
            car.setTotalSeating(rs.getInt("TotalSeating"));
            car.setDoorAmount(rs.getInt("DoorAmount"));
            car.setEngineType(rs.getString("EngineType"));
            car.setRating(rs.getInt("Rating"));
            car.setReviews(getReviews(car.getID()));
            car.setPrice(rs.getDouble("Price"));
            car.setImageLocation(rs.getString("ImageLocation"));

            return car;

        }catch(SQLException e){
            System.err.println(e.getMessage());
            return null;
        }

    }

}
