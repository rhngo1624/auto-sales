package db.tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import db.models.Car;
import db.models.Review;
import db.models.SQLModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Cars implements SQLTable {

    public ObservableList<SQLModel> getAllRows() throws SQLException{

        String query = "SELECT * FROM Cars";
        ObservableList<SQLModel> data = FXCollections.observableArrayList();
        Car car;

        try(
                Statement stmt = CONN.createStatement();
                ResultSet rs = stmt.executeQuery(query);
        ){

            while(rs.next()) {

                car = new Car();

                car.setID(rs.getInt("ID"));
                car.setMake(rs.getString("Make"));
                car.setModel(rs.getString("Model"));
                car.setYear(rs.getInt("Year"));
                car.setFuelEconomy(rs.getString("FuelEconomy"));
                car.setTransmission(rs.getString("Transmission"));
                car.setTotalSeating(rs.getInt("TotalSeating"));
                car.setDoorAmount(rs.getInt("DoorAmount"));
                car.setEngineType(rs.getString("EngineType"));
                car.setRating(rs.getInt("Rating"));
                car.setReviews(getReviews());
                car.setPrice(rs.getDouble("Price"));

                data.add(car);
            }

        }

        return data;

    }
    public SQLModel getModel(int id) throws SQLException{


    }
    public boolean insertModel(SQLModel model) throws Exception{


    }
    public boolean updateModel(SQLModel model) throws Exception{


    }
    public boolean deleteModel(int id) throws Exception{


    }
    public boolean modelExists(int id){


    }

    private String[] getReviews(){

        Reviews reviewsTable = new Reviews();
        String[] reviewContents = new String[20];
        int count = 0;
        try{

            for(SQLModel review : reviewsTable.getAllRows()){
                if(count >= 20){
                    break;
                }

                reviewContents[count] = ((Review)review).getContents();
                count++;

            }
        }catch(SQLException e){
            e.getMessage();
            return null;
        }

        return reviewContents;

    }

}
