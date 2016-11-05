package db.models;

/**
 * Appointment class.
 * 
 * @author RN   11 / 5 / 2016
 *
 */
public class Appointment implements SQLModel {
  
  private int ID;
  private int appointmentID;
  private int appointmentType; // 0 = maintenance, 1 = test driving.
  // private varChar date;  Designate data type (Date?).
  // private varChar time;  Designate data type.
  private int carID;
  private int userID;
  
  // Maintenance only.
  private String notes; // Reason for maintenance.
  
  public Appointment(int appointmendID, int appointmentType,
                     int carID, int userID) {
    setAppointmentID(appointmentID);
    setAppointmentType(appointmentType);
    setCarID(carID);
    setUserID(userID);
    
    if (appointmentType == 0) { // If appointmentType is maintenance...
      // Acquire notes.
      // setNotes(notes);
    }
  }
  
  public void setID(int id) {
    ID = id;
  }
  
  public int getID() {
    return ID;
  }
  
  public void setAppointmentID(int appointmentID) {
    this.appointmentID = appointmentID;
  }
  
  public int getAppointmentID() {
    return appointmentID;
  }
  
  public void setAppointmentType(int appointmentType) {
    this.appointmentType = appointmentType;
  }
  
  public int getAppointmentType() {
    return appointmentType;
  }
  
  public void setCarID(int carID) {
    this.carID = carID;
  }
  
  public int getCarID() {
    return carID;
  }
  
  public void setUserID(int userID) {
    this.userID = userID;
  }
  
  public int getUserID() {
    return userID;
  }

  /*
   * public void setDate(// date) {
   *   this.date = date;
   * }
   * 
   * public // getDate() {
   *   return date;
   * }
   */
  
  /*
   * public void setTime(// time) {
   *   this.time = time;
   * }
   * 
   * public // getTime() {
   *   return time;
   * }
   */
  
  public void setNotes(String notes) {
    this.notes = notes;
  }
   
  public String getNotes() {
    return notes;
  }
   
}
