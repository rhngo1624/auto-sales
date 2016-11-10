package db.tables;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import app.core.SQLTable;
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
    public ArrayList<Review> getModels(int id) throws SQLException{
        ArrayList<Review> reviews = new ArrayList<>();
        String query = "SELECT * FROM Reviews WHERE ID = ?";
        ResultSet rs;

        try(
                PreparedStatement stmt = CONN.prepareStatement(query,
                        ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_READ_ONLY)){

            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next()){

                Review review = new Review();

                review.setID(id);
                review.setContents(rs.getString("Content"));
                review.setRating(rs.getInt("Rating"));
                reviews.add(review);

            }

        }catch(SQLException e){

            System.err.println(e.getMessage());
            return null;

        }

        return reviews;
    }
    public boolean insert(Review model){
        return true;
    }
    public boolean update(Review model){
        return true;
    }

}
