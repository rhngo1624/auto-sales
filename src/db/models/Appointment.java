package db.models;

import app.core.SQLModel;

/**
 * Appointment class.
 *
 * @author RN   11 / 5 / 2016
 */
public class Appointment implements SQLModel {

    public final String[] times = {
            "9:30AM", "10:00AM", "10:30AM", "11:00AM",
            "11:30AM", "12:00PM", "12:30PM", "1:00PM",
            "1:30PM", "2:00PM", "2:30PM", "3:00PM",
            "3:30PM", "4:00PM", "4:30PM", "5:00PM"
    };

    private int ID;
    final public int MAINTENANCE_T = 0;
    final public int TESTDRIVE_T = 1;
    private int appointmentType;
    private String date;
    private String time;
    private int carID;
    private int userID;

    // Maintenance only.
    private String notes; // Reason for maintenance.

    public Appointment(int appointmentType, int carID, int userID) {

        setAppointmentType(appointmentType);
        setCarID(carID);
        setUserID(userID);

    }

    public void setID(int id) {
        ID = id;
    }

    public int getID() {
        return ID;
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

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }

}

