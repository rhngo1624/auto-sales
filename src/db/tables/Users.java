package db.tables;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;

import app.core.SQLTable;
import db.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Users extends SQLTable<User> {

    private static final String adminCode = "autoadmin11";
    
    public User validate(String user, String pass) throws SQLException{

        //get all users that match user/password combo
        String query = "SELECT * FROM Users WHERE USERNAME = ? AND PASSWORD = ?";
        ResultSet rs;
        int id;

        //try with resources
        try (
                PreparedStatement stmt = CONN.prepareStatement(query,
                        ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_READ_ONLY)) {
            
            stmt.setString(1, user);
            stmt.setString(2, pass); 
            
            rs = stmt.executeQuery(); 

            if(!rs.isBeforeFirst()){
                
                return null;
                
            }else{
                
                id = rs.getInt("ID");
                return get(id);
            }

        }

    }

    /**
     * Returns User Objects
     * @return ObservableList<User>
     *
     */
    public ObservableList<User> getAllRows(){

        String query = "SELECT * FROM Users";
        ObservableList<User> data = FXCollections.observableArrayList();

        try(
                Statement stmt = CONN.createStatement();
                ResultSet rs = stmt.executeQuery(query);
        ){

            while(rs.next()) {

                data.add(makeUser(rs));

            }

        }catch(SQLException e){
            System.err.println(e.getMessage());
        }

        return data;

    }

    /**
     * Retuns single User object from database.
     * @param id ID of User to get
     * @return User
     *
     */
    public User get(int id){

        String query = "SELECT * FROM Users WHERE ID = ?";
        ResultSet rs;

        try(
                PreparedStatement stmt = CONN.prepareStatement(query,
                        ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_READ_ONLY)){

            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next()){

                return makeUser(rs);

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
     *
     */
    public boolean insert(User model){

        String query = "INSERT into Users (Username, Password, Admin, FirstName, LastName, " +
                "Address, City, State, Zipcode, Phone, BirthDay, BirthMonth, BirthYear) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


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
    public boolean update(User model){

        String query = "UPDATE Users SET Username = ?, Password = ?, Admin = ?, FirstName = ?, " +
                "LastName = ?, Address = ?, City = ?, State = ?, Zipcode = ?, Phone = ?, " +
                "BirthDay = ?, BirthMonth = ?, BirthYear = ? WHERE ID = ?";

        try(PreparedStatement stmt = CONN.prepareStatement(query)){

            setProperties(stmt, model);
            stmt.setInt(14, model.getID());

            int affectedRows = stmt.executeUpdate();

            return affectedRows == 1;

        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }

    }

    public String getAdminCode(){
        return adminCode;
    }

    public boolean usernameExists(String username){

        ObservableList<User> users = getAllRows();

        for(User user : users){

            if(user.getUsername().equals(username)){
                return true;
            }

        }


        return false;

    }

    private void setProperties(PreparedStatement stmt, User model){
        try{
            stmt.setString(1, model.getUsername());
            stmt.setString(2, model.getPassword());
            stmt.setBoolean(3, model.isAdmin());
            stmt.setString(4, model.getFirstname());
            stmt.setString(5, model.getLastname());
            stmt.setString(6, model.getAddress());
            stmt.setString(7, model.getCity());
            stmt.setString(8, model.getState());
            stmt.setString(9, model.getZipcode());
            stmt.setString(10, model.getPhone());
            stmt.setInt(11, model.getBirthDay());
            stmt.setInt(12, model.getBirthMonth());
            stmt.setInt(13, model.getBirthYear());
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

    private User makeUser(ResultSet rs){
        try{
            String username = rs.getString("Username");
            boolean isAdmin = rs.getBoolean("Admin");

            User user = new User(username);

            user.setID(rs.getInt("ID"));
            user.setAdminPriveleges(isAdmin);
            user.setFirstname(rs.getString("Firstname"));
            user.setLastname(rs.getString("Lastname"));
            user.setAddress(rs.getString("Address"));
            user.setCity(rs.getString("City"));
            user.setState(rs.getString("State"));
            user.setZipcode(rs.getString("Zipcode"));
            user.setPhone(rs.getString("Phone"));
            user.setBirthDay(rs.getInt("BirthDay"));
            user.setBirthMonth(rs.getInt("BirthMonth"));
            user.setBirthYear(rs.getInt("BirthYear"));

            return user;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }


}
