package db.tables;

/**
 * PatientManager Class
 *
 * PatientManager Class handles all Database interactions with the Patient
 * Table.
 *
 * @author RayDeveloper
 */
import db.ConnectionUtil;
import db.beans.Patient;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PatientManager {

    //Establishes connection using ConnectionUtil Class
    private static final Connection CONN = ConnectionUtil.getInstance().getConnection();

    /**
     * Returns Patient objects in database as ObservableList
     *
     * @return ObservableList<Patient>
     * @throws SQLException
     */
    public static ObservableList<Patient> getAllRows() throws SQLException {

        String query = "SELECT * FROM Patients";
        ObservableList<Patient> data = FXCollections.observableArrayList();
        Patient patient;

        //try with resources: closes resources after using them.
        try (
                Statement stmt = CONN.createStatement();
                ResultSet rs = stmt.executeQuery(query);) {

            while (rs.next()) {
                //create new instance of Patient
                patient = new Patient();
                //set Patient fields
                patient.setID(rs.getInt("ID"));
                patient.setFirstName(rs.getString("FIRST_NAME"));
                patient.setLastName(rs.getString("LAST_NAME"));
                patient.setCellPhone(rs.getString("CELL"));
                patient.setColorCode(rs.getString("COLOR_CODE"));
                //add patient to ObservableList
                data.add(patient);

            }

        }

        return data;

    }

    /**
     * Returns single Patient object from database.
     *
     * @param id ID of Patient to get
     * @return Patient
     * @throws SQLException
     */
    public static Patient getPatient(int id) throws SQLException {

        String query = "SELECT * FROM Patients WHERE ID = ?";
        ResultSet rs = null;

        /**
         * try with resources: closes resources after using them.
         *
         * PreparedStatement parameters changed from default because HSQLDB is
         * being used.
         */
        try (
                PreparedStatement stmt = CONN.prepareStatement(query,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);) {
            //sets int for query
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {
                //creates patient instance
                Patient patient = new Patient();
                //sets patient fields
                patient.setID(rs.getInt("ID"));
                patient.setFirstName(rs.getString("FIRST_NAME"));
                patient.setLastName(rs.getString("LAST_NAME"));
                patient.setSS(rs.getString("SS_NUM"));
                patient.setBirthMonth(rs.getString("BIRTH_MONTH"));
                patient.setBirthDay(rs.getString("BIRTH_DAY"));
                patient.setBirthYear(rs.getString("BIRTH_YEAR"));
                patient.setAddress(rs.getString("ADDRESS"));
                patient.setCity(rs.getString("CITY"));
                patient.setState(rs.getString("STATE"));
                patient.setCellPhone(rs.getString("CELL"));
                patient.setCurrentMeds(rs.getString("CURRENT_MEDS"));
                patient.setColorCode(rs.getString("COLOR_CODE"));
                patient.setNotes(rs.getString("NOTES"));
                patient.setDateJoined(rs.getString("DATE_JOINED"));
                patient.setImgLocation(rs.getString("IMG_LOCATION"));
                //returns patient
                return patient;

            } else {

                return null;

            }
        } catch (SQLException e) {

            System.err.println(e);
            return null;

        } finally {
            //closes ResultSet
            if (rs != null) {

                rs.close();

            }
        }
    }

    /**
     * Inserts Patient Object into database.
     *
     * @param patient Patient to insert.
     * @return true if successful, false otherwise.
     * @throws Exception
     */
    public static boolean insert(Patient patient) throws Exception {

        String query = "INSERT into Patients (FIRST_NAME, LAST_NAME, SS_NUM, "
                + "BIRTH_MONTH, BIRTH_DAY, BIRTH_YEAR, ADDRESS, CITY, STATE, "
                + "CELL, COLOR_CODE, NOTES, CURRENT_MEDS, IMG_LOCATION) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        ResultSet keys = null;
        //try with resources: closes resources after using them.
        try (
                PreparedStatement stmt = CONN.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
            //sets query values
            stmt.setString(1, patient.getFirstName());
            stmt.setString(2, patient.getLastName());
            stmt.setString(3, patient.getSS());
            stmt.setString(4, patient.getBirthMonth());
            stmt.setString(5, patient.getBirthDay());
            stmt.setString(6, patient.getBirthYear());
            stmt.setString(7, patient.getAddress());
            stmt.setString(8, patient.getCity());
            stmt.setString(9, patient.getState());
            stmt.setString(10, patient.getCellPhone());
            stmt.setString(11, patient.getColorCode());
            stmt.setString(12, patient.getNotes());
            stmt.setString(13, patient.getCurrentMeds());
            stmt.setString(14, patient.getImgLocation());
            //integer initialized with value of affected rows
            int affectedRows = stmt.executeUpdate();
            //checks if 1 row was affected
            if (affectedRows == 1) {
                //gets keys
                keys = stmt.getGeneratedKeys();
                keys.next();
                //sets next available key and returns int value
                int newKey = keys.getInt(1);
                //patient ID is set with new unique ID
                patient.setID(newKey);

            } else {

                System.err.println("No rows affected.");
                return false;

            }

        } catch (SQLException e) {

            System.err.println(e);
            return false;

        } finally {
            //closes ResultSet
            if (keys != null) {
                keys.close();
            }
        }

        return true;
    }

    /**
     * Updates Patient object in database.
     *
     * @param patient Patient to update.
     * @return true if successful, false otherwise.
     * @throws Exception
     */
    public static boolean update(Patient patient) throws Exception {

        String query
                = "UPDATE Patients SET "
                + "FIRST_NAME = ?, LAST_NAME = ?, SS_NUM = ?, BIRTH_MONTH = ?, "
                + "BIRTH_DAY = ?, BIRTH_YEAR = ?, ADDRESS = ?, CITY = ?, STATE = ?, "
                + "CELL = ?, CURRENT_MEDS = ?, COLOR_CODE = ?, NOTES = ?, IMG_LOCATION = ? "
                + "WHERE ID = ?";

        try (
                PreparedStatement stmt = CONN.prepareStatement(query);) {
            //sets values for query
            stmt.setString(1, patient.getFirstName());
            stmt.setString(2, patient.getLastName());
            stmt.setString(3, patient.getSS());
            stmt.setString(4, patient.getBirthMonth());
            stmt.setString(5, patient.getBirthDay());
            stmt.setString(6, patient.getBirthYear());
            stmt.setString(7, patient.getAddress());
            stmt.setString(8, patient.getCity());
            stmt.setString(9, patient.getState());
            stmt.setString(10, patient.getCellPhone());
            stmt.setString(11, patient.getCurrentMeds());
            stmt.setString(12, patient.getColorCode());
            stmt.setString(13, patient.getNotes());
            stmt.setString(14, patient.getImgLocation());
            stmt.setInt(15, patient.getID());

            int affectedRows = stmt.executeUpdate();
            //returns true if anything was changed, false otherwise.
            if (affectedRows == 1) {

                return true;

            } else {

                return false;

            }

        } catch (SQLException e) {

            System.err.println(e);

            return false;

        }

    }

    /**
     * Deletes Patient object from database.
     *
     * @param id Patient to delete.
     * @return true if successful, false otherwise.
     * @throws Exception
     */
    public static boolean delete(int id) throws Exception {

        String query = "DELETE FROM Patients WHERE ID = ?";

        ResultSet keys = null;
        //try with resources: closes resources after using them.
        try (
                PreparedStatement stmt = CONN.prepareStatement(query);) {
            //sets query id value
            stmt.setInt(1, id);
            stmt.execute();

        } catch (SQLException e) {

            System.err.println(e);
            return false;

        } finally {
            //closes ResultSet
            if (keys != null) {
                keys.close();
            }
        }

        return true;
    }

    /**
     * Check if Patient exists in Database.
     *
     * @param id Patient ID
     * @return true if patient found, false otherwise.
     */
    public static boolean patientExists(int id) {

        try {
            PatientManager.getPatient(id);
            return true;

        } catch (Exception e) {
            return false;
        }

    }

}
