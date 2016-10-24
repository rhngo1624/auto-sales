package db.tables;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import db.models.Car;
import db.models.Review;
import db.models.SQLModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Cars implements SQLTable {


    /**
     * Returns Car Objects
     * @return ObservableList<SQLModel>
     * @throws SQLException
     */
    public ObservableList<SQLModel> getAllRows() throws SQLException{

        String query = "SELECT * FROM Cars";
        ObservableList<SQLModel> data = FXCollections.observableArrayList();
        Car car;

        try(
                Statement stmt = CONN.createStatement();
                ResultSet rs = stmt.executeQuery(query);
        ){

            while(rs.next()) {

                String make = rs.getString("Make");
                String model = rs.getString("Model");
                int year = rs.getInt("Year");

                car = new Car(make, model, year);

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


                data.add(car);
            }

        }

        return data;

    }

    /**
     * Retuns single Car object from database.
     * @param id ID of Car to get
     * @return SQLModel
     * @throws SQLException
     */
    public SQLModel getModel(int id) throws SQLException{

        String query = "SELECT * FROM Cars WHERE ID = ?";
        ResultSet rs;

        try(
                PreparedStatement stmt = CONN.prepareStatement(query,
                        ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_READ_ONLY)){

            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next()){

                String make = rs.getString("Make");
                String model = rs.getString("Model");
                int year = rs.getInt("Year");

                SQLModel car = new Car(make, model, year);

                car.setID(rs.getInt("ID"));
                ((Car)car).setFuelEconomy(rs.getString("FuelEconomy"));
                ((Car)car).setTransmission(rs.getString("Transmission"));
                ((Car)car).setTotalSeating(rs.getInt("TotalSeating"));
                ((Car)car).setDoorAmount(rs.getInt("DoorAmount"));
                ((Car)car).setEngineType(rs.getString("EngineType"));
                ((Car)car).setRating(rs.getInt("Rating"));
                ((Car)car).setReviews(getReviews(car.getID()));
                ((Car)car).setPrice(rs.getDouble("Price"));
                ((Car)car).setImageLocation(rs.getString("ImageLocation"));

                return car;

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
    public boolean insertModel(SQLModel model) throws Exception{

        String query = "INSERT into Cars (Make, Model, Year, FuelEconomy, Transmission, " +
                "TotalSeating, DoorAmount, EngineType, Rating, ReviewID, Price, ImageLocation) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        ResultSet keys = null;

        try(
                PreparedStatement stmt = CONN.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS)

                ){

            stmt.setString(1, ((Car)model).getMake());
            stmt.setString(2, ((Car)model).getModel());
            stmt.setInt(3, ((Car)model).getYear());
            stmt.setString(4, ((Car)model).getFuelEconomy());
            stmt.setString(5, ((Car)model).getTransmission());
            stmt.setInt(6, ((Car)model).getTotalSeating());
            stmt.setInt(7, ((Car)model).getDoorAmount());
            stmt.setString(8, ((Car)model).getEngineType());
            stmt.setInt(9, ((Car)model).getRating());
            stmt.setInt(10, ((Car)model).getReviewID());
            stmt.setDouble(11, ((Car)model).getPrice());
            stmt.setString(12, ((Car)model).getImageLocation());

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

        String query = "UPDATE Cars SET " +
                "Make = ?, Model = ?, Year = ?, FuelEconomy = ?, Transmission = ?, " +
                "TotalSeating = ?, DoorAmount = ?, EngineType = ?, Rating = ?, ReviewID = ?, " +
                "Price = ?, ImageLocation = ? WHERE ID = ?";

        try(PreparedStatement stmt = CONN.prepareStatement(query)){

            stmt.setString(1, ((Car)model).getMake());
            stmt.setString(2, ((Car)model).getModel());
            stmt.setInt(3, ((Car)model).getYear());
            stmt.setString(4, ((Car)model).getFuelEconomy());
            stmt.setString(5, ((Car)model).getTransmission());
            stmt.setInt(6, ((Car)model).getTotalSeating());
            stmt.setInt(7, ((Car)model).getDoorAmount());
            stmt.setString(8, ((Car)model).getEngineType());
            stmt.setInt(9, ((Car)model).getRating());
            stmt.setInt(10, ((Car)model).getReviewID());
            stmt.setDouble(11, ((Car)model).getPrice());
            stmt.setString(12, ((Car)model).getImageLocation());

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

        String query = "DELETE FROM Cars WHERE ID = ?";

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

}
