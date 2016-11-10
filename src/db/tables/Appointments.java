package db.tables;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.core.SQLTable;
import db.models.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Appointment class.
 * 
 * @author RN 11 / 6 / 2016
 *
 */
public class Appointments extends SQLTable<Appointment> {

  public ObservableList<Appointment> getAllRows() {
    
    String query = "SELECT * FROM Appointments";
    ObservableList<Appointment> data = FXCollections.observableArrayList();
    
    try (
        Statement stmt = CONN.createStatement();
        ResultSet rs = stmt.executeQuery(query);
    ) {
      
      while (rs.next()) {

          data.add(makeAppointment(rs));

      }
      
    }catch(SQLException e){
      System.err.println(e.getMessage());
    }
    
    return data;
    
  }
  
  public Appointment get(int id) {
    
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
        
          return makeAppointment(rs);
        
      } else {
        
        return null;
        
      }
      
    } catch (SQLException ex) {
      
      System.err.println(ex.getMessage());
      return null;
      
    }
    
  }
  
  public boolean insert(Appointment model) {
    
    String query = "INSERT into Appointments (AppointmentID, " +
        "AppointmentType, Date, Time, CarID, UserID) " +
        "VALUES (?, ?, ?, ?, ?, ?)";

    
    try (
      PreparedStatement stmt = CONN.prepareStatement(query,
                                               Statement.RETURN_GENERATED_KEYS)
    ) {
      
      setProperties(stmt, model);
      
      int affectedRows = stmt.executeUpdate();
      
      return affectedRows == 1;
      
    } catch (SQLException ex) {
      
      System.err.println(ex.getMessage());
      return false;
      
    }
    
  }
  
  public boolean update(Appointment model){
    
    String query = "UPDATE Appointments SET " +
        "AppointmentID = ?, AppointmentType = ?, Date = ?, Time = ?, " + 
        "CarID = ?, UserID = ? WHERE ID = ?";
    
    try (PreparedStatement stmt = CONN.prepareStatement(query)) {
        
        setProperties(stmt, model);
        stmt.setInt(7, model.getID());
        
        int affectedRows = stmt.executeUpdate();
        
        return affectedRows == 1;
        
      } catch (SQLException ex) {
        
        System.err.println(ex.getMessage());
        return false;
        
      }
    
  }

  private void setProperties(PreparedStatement stmt, Appointment model){

      try{
          stmt.setInt(1, model.getAppointmentID());
          stmt.setInt(2, model.getAppointmentType());
          // stmt.set//(3, model.getDate());
          // stmt.set//(4, model.getTime());
          stmt.setInt(5, model.getCarID());
          stmt.setInt(6, model.getUserID());
      }catch(SQLException e){
          System.err.println(e.getMessage());
      }

  }

  private Appointment makeAppointment(ResultSet rs){
      try{
          int appointmentID = rs.getInt("AppointmentID");
          int appointmentType = rs.getInt("AppointmentType");
          // // date = rs.get//("Date");
          // // time = rs.get//("Time");
          int carID = rs.getInt("CarID");
          int userID = rs.getInt("UserID");

          Appointment appointment = new Appointment(appointmentID, appointmentType,
                  carID, userID);

          appointment.setID(rs.getInt("ID"));
          // ((Appointment)appointment).setNotes(rs.getString("Notes")); (?)
          return appointment;
      }catch(SQLException e){
          System.err.println(e.getMessage());
          return null;
      }
  }
  
}
