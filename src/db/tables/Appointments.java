package db.tables;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

  public ArrayList<Appointment> getAllMaintenanceAppointments(){
      String query = "SELECT * FROM Appointments WHERE Type = 0";
      ArrayList<Appointment> maintenanceAppointments = new ArrayList<>();

      try(
              Statement stmt = CONN.createStatement();
              ResultSet rs = stmt.executeQuery(query)
      ) {
          while(rs.next()){
              maintenanceAppointments.add(makeAppointment(rs));
          }
      }catch(SQLException e){
          System.err.println(e.getMessage());
      }

      return maintenanceAppointments;
  }

  public ArrayList<Appointment> getAllTestDriveAppointments(){
      String query = "SELECT * FROM Appointments WHERE Type = 1";
      ArrayList<Appointment> testDriveAppointments = new ArrayList<>();

      try(
              Statement stmt = CONN.createStatement();
              ResultSet rs = stmt.executeQuery(query);
      ){
          while(rs.next()){
              testDriveAppointments.add(makeAppointment(rs));
          }


      }catch(SQLException e){
          System.err.println(e.getMessage());
      }

      return testDriveAppointments;
  }

  public ObservableList<Appointment> getAllRows() {
    
    String query = "SELECT * FROM Appointments";
    ObservableList<Appointment> data = FXCollections.observableArrayList();
    
    try (
        Statement stmt = CONN.createStatement();
        ResultSet rs = stmt.executeQuery(query)
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
    
    String query = "INSERT into Appointments (" +
        "Type, Date, Time, CarID, UserID, Notes) " +
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
        "Type = ?, Date = ?, Time = ?, " +
        "CarID = ?, UserID = ?, Notes = ? WHERE ID = ?";
    
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
          stmt.setInt(1, model.getAppointmentType());
          stmt.setString(2, model.getDate());
          stmt.setString(3, model.getTime());
          stmt.setInt(4, model.getCarID());
          stmt.setInt(5, model.getUserID());
          stmt.setString(6, model.getNotes());
      }catch(SQLException e){
          System.err.println(e.getMessage());
      }

  }

  private Appointment makeAppointment(ResultSet rs){
      try{
          int appointmentID = rs.getInt("ID");
          int appointmentType = rs.getInt("Type");
          String date = rs.getString("Date");
          String time = rs.getString("Time");
          String notes = rs.getString("Notes");
          int carID = rs.getInt("CarID");
          int userID = rs.getInt("UserID");

          Appointment appointment = new Appointment(appointmentType,
                  carID, userID);
          appointment.setID(appointmentID);
          appointment.setDate(date);
          appointment.setTime(time);
          appointment.setNotes(notes);
          return appointment;
      }catch(SQLException e){
          System.err.println(e.getMessage());
          return null;
      }
  }
  
}
