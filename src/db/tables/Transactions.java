package db.tables;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.models.SQLModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Transactions Class
 *
 * Transactions Class handles all database interactions with the Transactions table.
 *
 * RN 10/28/2016 11:10am
 */
// Import statements


public class Transactions implements SQLTable {

	/*variables
	String saleDate;
	boolean isGift;
	*/
	// A transaction also has information from vehicles, buyers, and sellers. 

	/**
	 * Returns Transactions objects in database as ObservableList.
	 *
	 * @return ObservableList<Transactions>
	 * @throws SQLException
	 */
	public ObservableList<SQLModel> getAllRows() throws SQLException {

		String query = "SELECT * FROM Transactions";
		ObservableList<SQLModel> data = FXCollections.observableArrayList();
		Transactions transactions;

		// Try with resources: closes resources after using them.
		try (Statement stmt = CONN.createStatement();
			 ResultSet rs = stmt.executeQuery(query);) {

			while (rs.next()) {
				// Create a new instance of Transactions.
				transactions = new Transactions();
				// Set Transactions fields.

				// Add transactions to ObservableList.
				data.add((SQLModel)transactions);
			}

		}

		return data;

	}

	/**
	 * Returns a single Transactions object from database.
	 *
	 * @return SQLModel
	 * @throws SQLException
	 */
	public SQLModel getModel(int id) throws SQLException {

		String query = "SELECT * FROM Transactions";

		ResultSet rs = null;

		/**
		 * Try with resources: closes resources after using them.
		 *
		 * PreparedStatement parameters changed from default because HSQLDB is
		 * being used.
		 */
		try (PreparedStatement stmt = CONN.prepareStatement(query,
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);) {
			// Set int for query

			rs = stmt.executeQuery();

			if (rs.next()) {
				// Creates transactions instance.
				Transactions transactions = new Transactions();
				// Sets transactions fields.

				// Returns transactions.
				return (SQLModel)transactions;
			} else {
				return null;
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			return null;
		} finally {
			// Closes ResultSet.
			if (rs != null) {
				rs.close();
			}
		}
	}

	/**
	 * Inserts Transactions object into database.
	 *
	 * @param model Transactions to insert.
	 * @return true if successful, false otherwise.
	 * @throws Exception
	 */
	public boolean insertModel(SQLModel model) throws Exception {

		String query = "INSERT into Transactions (ID) VALUES (?)";
		ResultSet keys = null;
		// Try with resources: closes resources after using them.
		try (PreparedStatement stmt = CONN.prepareStatement(query,
			Statement.RETURN_GENERATED_KEYS);) {
			// Sets query values.
			
			// Integer initialized with value of affected rows.
			int affectedRows = stmt.executeUpdate();
			// Checks if 1 row was affected.
			if (affectedRows == 1) {
				// Gets keys.
				keys = stmt.getGeneratedKeys();
				keys.next();
				// Sets next available key and returns int value.
				int newKey = keys.getInt(1);
				// 
			} else {
				System.err.println("No rows affected.");
				return false;
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			return false;
		} finally {
			// Closes ResultSet.
			if (keys != null) {
				keys.close();
			}
		}
		return true;
			
	}
	/**
	 * Updates Transactions object in database.
	 *
	 * @param transactions Transactions to update.
	 * @return true if successful, false otherwise.
	 * @throws Exception
	 */
    	public boolean updateModel(SQLModel model) throws Exception {
		String query = "UPDATE Transactions SET ID = ?";

		try ( PreparedStatement stmt = CONN.prepareStatement(query);) {
			// Sets values for query.

			int affectedRows = stmt.executeUpdate();
			// Returns true if anything was changed, false otherwise.
			if (affectedRows == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException ex) {
			System.err.println(ex);
			return false;
		}
			
	}

	/**
	 * Deletes Transactions object from database.
	 *
	 * @return true if successful, false otherwise.
	 * @throws Exception
	 */
    	public boolean deleteModel(int id) throws Exception {

		String query = "DELETE FROM Transactions WHERE ID = ?";

		ResultSet keys = null;

		// Try with resources: closes resources after using them.
		try (PreparedStatement stmt = CONN.prepareStatement(query);) {
			stmt.setInt(1, id);
			stmt.execute();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			return false;
		} finally {
			// Closes ResultSet
			if (keys != null) {
				keys.close();
			}
		}

		return true;

	}

	/**
	 * Check if Transactions exists in database.
	 *
	 * @return true if transactions found, false otherwise.
	 */
    	public boolean modelExists(int id) {

		try {
			getModel(id);
			return true;
		} catch (Exception ex) {
			return false;
		}

	}

}
