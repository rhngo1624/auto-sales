package db.tables;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.models.Transaction;
import app.core.SQLTable;
import db.models.Transaction;
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


public class Transactions extends SQLTable<Transaction> {

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
	public ObservableList<Transaction> getAllRows(){

		String query = "SELECT * FROM Transactions";
		ObservableList<Transaction> data = FXCollections.observableArrayList();

		// Try with resources: closes resources after using them.
		try (Statement stmt = CONN.createStatement();
			 ResultSet rs = stmt.executeQuery(query);) {

			while (rs.next()) {
				data.add(makeTransaction(rs));
			}

		}catch(SQLException e){
			System.err.println(e.getMessage());
		}

		return data;

	}

	/**
	 * Returns a single Transactions object from database.
	 *
	 * @return Transaction
	 *
	 */
	public Transaction get(int id){

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
				ResultSet.CONCUR_READ_ONLY)) {
			// Set int for query

			rs = stmt.executeQuery();

			if (rs.next()) {
				return makeTransaction(rs);

			} else {
				return null;
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			return null;
		}

	}

	/**
	 * Inserts Transactions object into database.
	 *
	 * @param model Transactions to insert.
	 * @return true if successful, false otherwise.
	 *
	 */
	public boolean insert(Transaction model){

		String query = "INSERT into Transactions () VALUES ()";

		// Try with resources: closes resources after using them.
		try (PreparedStatement stmt = CONN.prepareStatement(query,
			Statement.RETURN_GENERATED_KEYS);) {
			// Sets query values.
			setProperties(stmt, model);
			// Integer initialized with value of affected rows.
			int affectedRows = stmt.executeUpdate();

			return affectedRows == 1;

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			return false;
		}
			
	}
	/**
	 * Updates Transactions object in database.
	 *
	 * @param model Transactions to update.
	 * @return true if successful, false otherwise.
	 *
	 */
    	public boolean update(Transaction model){
		String query = "UPDATE Transactions SET ID = ?";

		try ( PreparedStatement stmt = CONN.prepareStatement(query)) {
			// Sets values for query.
			setProperties(stmt, model);
			stmt.setInt(5, model.getID());
			int affectedRows = stmt.executeUpdate();
			// Returns true if anything was changed, false otherwise.
			return affectedRows == 1;
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			return false;
		}
			
	}

	private void setProperties(PreparedStatement stmt, Transaction model){
		// TODO: finish method
	}

	private Transaction makeTransaction(ResultSet rs){
		// TODO: finish method
		return null;
	}



}
