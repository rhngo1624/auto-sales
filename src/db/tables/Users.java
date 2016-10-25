package db.tables;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;

import app.utils.ConnectionUtil;
import db.models.Accessory;
import db.models.SQLModel;
import db.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Users implements SQLTable{
    
    public User validate(String user, String pass) throws SQLException{

        //get all users that match user/password combo
        String query = "SELECT * FROM Users WHERE USERNAME = ? AND PASSWORD = ?";
        ResultSet rs = null;
        int id;

        //try with resources
        try (
                PreparedStatement stmt = CONN.prepareStatement(query,
                        ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_READ_ONLY);) {
            
            stmt.setString(1, user);
            stmt.setString(2, pass); 
            
            rs = stmt.executeQuery(); 

            if(!rs.isBeforeFirst()){
                
                return null;
                
            }else{
                
                id = rs.getInt("ID");
                return (User)this.getModel(id);
            }

        }

    }

    /**
     * Returns User Objects
     * @return ObservableList<SQLModel>
     * @throws SQLException
     */
    public ObservableList<SQLModel> getAllRows() throws SQLException{

        String query = "SELECT * FROM Users";
        ObservableList<SQLModel> data = FXCollections.observableArrayList();
        User user;

        try(
                Statement stmt = CONN.createStatement();
                ResultSet rs = stmt.executeQuery(query);
        ){

            while(rs.next()) {

                String username = rs.getString("Username");
                boolean isAdmin = rs.getBoolean("Admin");

                user = new User(username);
                user.setAdminPriveleges(isAdmin);
                user.setID(rs.getInt("ID"));

                data.add(user);

            }

        }

        return data;

    }

    /**
     * Retuns single User object from database.
     * @param id ID of User to get
     * @return SQLModel
     * @throws SQLException
     */
    public SQLModel getModel(int id) throws SQLException{

        String query = "SELECT * FROM Users WHERE ID = ?";
        ResultSet rs;

        try(
                PreparedStatement stmt = CONN.prepareStatement(query,
                        ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_READ_ONLY)){

            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next()){

                String username = rs.getString("Username");
                boolean isAdmin = rs.getBoolean("Admin");

                User user = new User(username);

                user.setID(rs.getInt("ID"));
                user.setAdminPriveleges(isAdmin);

                return user;

            }else{

                return null;

            }
        }catch(SQLException e){

            System.err.println(e.getMessage());
            return null;

        }


    }

    /**
     * Insert User Object into database.
     *
     * @param model User object to insert.
     * @return true if successful, false otherwise.
     * @throws Exception
     */
    public boolean insertModel(SQLModel model) throws Exception{

        String query = "INSERT into Users (Username, Password, Admin) " +
                "VALUES (?, ?, ?)";

        ResultSet keys = null;

        try(
                PreparedStatement stmt = CONN.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS)

        ){

            stmt.setString(1, ((User)model).getUsername());
            stmt.setString(2, ((User)model).getPassword());
            stmt.setBoolean(3, ((User)model).isAdmin());

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

        String query = "UPDATE Users SET Username = ?, Password = ?, Admin = ? WHERE ID = ?";

        try(PreparedStatement stmt = CONN.prepareStatement(query)){

            stmt.setString(1, ((User)model).getUsername());
            stmt.setString(2,((User)model).getPassword());
            stmt.setBoolean(3, ((User)model).isAdmin());

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

       String query = "DELETE FROM Users WHERE ID = ?";

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
