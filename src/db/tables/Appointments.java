package db.tables;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import db.models.Appointment;
import db.models.SQLModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Appointment class.
 * 
 * @author RN 11 / 6 / 2016
 *
 */
public class Appointments implements SQLTable {

  public ObservableList<SQLModel> getAllRows() throws SQLException {
    
    String query = "SELECT * FROM Appointments";
    ObservableList<SQLModel> data = FXCollections.observableArrayList();
    Appointment appointment;
    
    try (
        Statement stmt = CONN.createStatement();
        ResultSet rs = stmt.executeQuery(query);
    ) {
      
      while (rs.next()) {
        
        int appointmentID = rs.getInt("AppointmentID");
        int appointmentType = rs.getInt("AppointmentType");
        // // date = rs.get//("Date");
        // // time = rs.get//("Time");
        int carID = rs.getInt("CarID");
        int userID = rs.getInt("UserID");
        
        appointment = new Appointment(appointmentID, appointmentType,
                                      carID, userID);
        
        appointment.setID(rs.getInt("ID"));
        // appointment.setNotes(rs.getString("Notes")); (?)
        
        data.add(appointment);
      }
      
    }
    
    return data;
    
  }
  
  public SQLModel getModel(int id) throws SQLException {
    
    String query = "SELECT * FROM Appointments WHERE ID = ?";
    ResultSet rs;
    
    try (
        PreparedStatement stmt = CONN.prepareStatement(query,
                                                   ResultSet.TYPE_FORWARD_ONLY,
                                                   ResultSet.CONCUR_READ_ONLY)
    ) {
      
      stmt.setInt(1, id);
      
      rs = stmt.executeQuery();
      
      if (rs.next()) {
        
        int appointmentID = rs.getInt("AppointmentID");
        int appointmentType = rs.getInt("AppointmentType");
        // // date = rs.get//("Date");
        // // time = rs.get//("Time");
        int carID = rs.getInt("CarID");
        int userID = rs.getInt("UserID");
        
        SQLModel appointment = new Appointment(appointmentID, appointmentType,
                                               carID, userID);
        
        appointment.setID(rs.getInt("ID"));
        // ((Appointment)appointment).setNotes(rs.getString("Notes")); (?)
        
        return appointment;
        
      } else {
        
        return null;
        
      }
      
    } catch (SQLException ex) {
      
      System.err.println(ex.getMessage());
      return null;
      
    }
    
  }
  
  public boolean insertModel(SQLModel model) throws Exception {
    
    String query = "INSERT into Appointments (AppointmentID, " +
        "AppointmentType, Date, Time, CarID, UserID) " +
        "VALUES (?, ?, ?, ?, ?, ?)";
    
    ResultSet keys = null;
    
    try (
      PreparedStatement stmt = CONN.prepareStatement(query,
                                               Statement.RETURN_GENERATED_KEYS)
    ) {
      
      stmt.setInt(1, ((Appointment)model).getAppointmentID());
      stmt.setInt(2, ((Appointment)model).getAppointmentType());
      // stmt.set//(3, ((Appointment)model).getDate());
      // stmt.set//(4, ((Appointment)model).getTime());
      stmt.setInt(5, ((Appointment)model).getCarID());
      stmt.setInt(6, ((Appointment)model).getUserID());
      
      int affectedRows = stmt.executeUpdate();
      
      if (affectedRows == 1) {
        
        keys = stmt.getGeneratedKeys();
        keys.next();
        
        int newKey = keys.getInt(1);
        
        model.setID(newKey);
        
      } else {
        
        System.err.println("No rows affected.");
        return false;
        
      }
      
    } catch (SQLException ex) {
      
      System.err.println(ex.getMessage());
      return false;
      
    } finally {
      
      if (keys != null) {
        keys.close();
      }
      
    }
    
    return true;
    
  }
  
  public boolean updateModel(SQLModel model) throws Exception {
    
    String query = "UPDATE Appointments SET " +
        "AppointmentID = ?, AppointmentType = ?, Date = ?, Time = ?, " + 
        "CarID = ?, UserID = ? WHERE ID = ?";
    
    try (PreparedStatement stmt = CONN.prepareStatement(query)) {
        
        stmt.setInt(1, ((Appointment)model).getAppointmentID());
        stmt.setInt(2, ((Appointment)model).getAppointmentType());
        // stmt.set//(3, ((Appointment)model).getDate());
        // stmt.set//(4, ((Appointment)model).getTime());
        stmt.setInt(5, ((Appointment)model).getCarID());
        stmt.setInt(6, ((Appointment)model).getUserID());
        
        int affectedRows = stmt.executeUpdate();
        
        if (affectedRows == 1) {
          return true;
        } else {
          return false;
        }
        
      } catch (SQLException ex) {
        
        System.err.println(ex.getMessage());
        return false;
        
      }
    
  }
  
  public boolean deleteModel(int id) throws Exception {
    
    String query = "DELETE FROM Appointments WHERE ID = ?";
    
    ResultSet keys = null;
    
    try (PreparedStatement stmt = CONN.prepareStatement(query)) {
      
      stmt.setInt(1, id);
      stmt.execute();
      
    } catch (SQLException ex) {
      System.err.println(ex.getMessage());
      return false;
    } finally {
      if (keys != null) {
        keys.close();
      }
    }
    
    return true;
    
  }
  
  public boolean modelExists(int id) {
    
    try {
      getModel(id);
      return true;
    } catch (Exception ex) {
      return false;
    }
    
  }
  
}
