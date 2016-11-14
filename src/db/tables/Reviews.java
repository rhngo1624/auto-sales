package db.tables;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import app.core.SQLTable;
import db.models.Car;
import db.models.Review;
import javafx.collections.ObservableList;

// TODO: adjust to fixed components in Reviews Table
// TODO: refactor getModels

public class Reviews extends SQLTable<Review> {

    public ObservableList<Review> getAllRows(){
        return null;
    }
    public Review get(int id){
        return null;
    }
    public ArrayList<Review> getModels(int id) {

        ArrayList<Review> reviews = new ArrayList<>();
        String query = "SELECT * FROM Reviews WHERE CarID = ?";
        ResultSet rs;

        try(
                PreparedStatement stmt = CONN.prepareStatement(query,
                        ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_READ_ONLY)){

            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while(rs.next()){

                Review review = makeReview(rs);

                reviews.add(review);

            }

        }catch(SQLException e){

            System.err.println(e.getMessage());
            return null;

        }

        return reviews;
    }
    public boolean insert(Review model){
        String query = "INSERT into Reviews (Content, Rating, Username, CarID, Title) " +
                "VALUES (?, ?, ?, ?, ?)";


        try(
                PreparedStatement stmt = CONN.prepareStatement(query)

        ){

            setProperties(stmt, model);

            int affectedRows = stmt.executeUpdate();

            return affectedRows == 1;


        }catch (SQLException e){

            System.err.println(e.getMessage());
            return false;

        }
    }
    public boolean update(Review model){
        return true;
    }

    private Review makeReview(ResultSet rs){

        try{

            Review review = new Review();

            review.setContents(rs.getString("Content"));
            review.setRating(rs.getInt("Rating"));
            review.setOwner(rs.getString("Username"));
            review.setID(rs.getInt("CarID"));
            review.setTitle(rs.getString("Title"));

            return review;

        }catch(SQLException e){

            System.err.println(e.getMessage());
            return null;

        }

    }

    private void setProperties(PreparedStatement stmt, Review model){
        try{


            stmt.setString(1, model.getContents());
            stmt.setInt(2, model.getRating());
            stmt.setString(3, model.getOwner());
            stmt.setInt(4, model.getID());
            stmt.setString(5, model.getTitle());

        }catch(SQLException e){
            System.err.println(e.getErrorCode());
        }
    }


}
