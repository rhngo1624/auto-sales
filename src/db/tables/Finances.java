package db.tables;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import app.core.SQLTable;
import db.models.FinancialApplication;
import javafx.collections.ObservableList;

public class Finances extends SQLTable<FinancialApplication> {

    public ObservableList<FinancialApplication> getAllRows(){
        return null;
    }
    public FinancialApplication get(int id){
        return null;
    }
    public ArrayList<FinancialApplication> getModels() {

        ArrayList<FinancialApplication> FinancialApplications = new ArrayList<>();
        String query = "SELECT * FROM Finances";
        ResultSet rs;

        try(
                PreparedStatement stmt = CONN.prepareStatement(query,
                        ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_READ_ONLY)){

            rs = stmt.executeQuery();

            while(rs.next()){

                FinancialApplication FinancialApplication = makeFinancialApplication(rs);

                FinancialApplications.add(FinancialApplication);

            }

        }catch(SQLException e){

            System.err.println(e.getMessage());
            return null;

        }

        return FinancialApplications;
    }
    public boolean insert(FinancialApplication model){
        String query = "INSERT into Finances (UserID, CarID, NumberOfDependents, ResidentialStatus, " +
                "MonthlyHomePayment, LandLordName, PreviousAddress, CurrentEmployerName, " +
                "CurrentEmployerAddress, GrossMonthlySalary, WorkPhone, JobTitle, JobLength, " +
                "OtherMonthlyGrossIncome, OtherIncomeSource, Reference1, Reference2, Ref1Phone, " +
                "Ref2Phone, DriversLicenseNo, SSN, Completed) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";


        try(
                PreparedStatement stmt = CONN.prepareStatement(query)

        ){

            setProperties(stmt, model);

            int affectedRows = stmt.executeUpdate();

            return affectedRows == 1;


        }catch (SQLException e){

            System.err.println(e.getMessage());
            return false;

        }
    }
    public boolean update(FinancialApplication model){

        String query = "UPDATE Finances SET UserID = ?, CarID = ?, NumberOfDependents = ?, " +
                "ResidentialStatus = ?, MonthlyHomePayment = ?, LandLordName = ?, " +
                "PreviousAddress = ?, CurrentEmployerName = ?, " +
                "CurrentEmployerAddress = ?, GrossMonthlySalary = ?, WorkPhone = ?, JobTitle = ?, " +
                "JobLength = ?, OtherMonthlyGrossIncome = ?, OtherIncomeSource = ?, Reference1 = ?," +
                " Reference2 = ?, Ref1Phone = ?, Ref2Phone = ?, DriversLicenseNo = ?, " +
                "SSN = ?, Completed = ? WHERE ID = ?";

        try(PreparedStatement stmt = CONN.prepareStatement(query)){
            setProperties(stmt, model);
            stmt.setInt(23, model.getID());
            int affectedRows = stmt.executeUpdate();
            return affectedRows == 1;

        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }

    }

    private FinancialApplication makeFinancialApplication(ResultSet rs){

        try{

            FinancialApplication financeApp = new FinancialApplication();

            financeApp.setID(rs.getInt("ID"));
            financeApp.setUserID(rs.getInt("UserID"));
            financeApp.setCarID(rs.getInt("CarID"));
            financeApp.setNumberOfDependents(rs.getInt("NumberOfDependents"));
            financeApp.setResidentialStatus(rs.getString("ResidentialStatus"));
            financeApp.setMonthlyHomePayment(rs.getDouble("MonthlyHomePayment"));
            financeApp.setLandLordName(rs.getString("LandLordName"));
            financeApp.setPreviousAddress(rs.getString("PreviousAddress"));
            financeApp.setCurrentEmployerName(rs.getString("CurrentEmployerName"));
            financeApp.setCurrentEmployerAddress(rs.getString("CurrentEmployerAddress"));
            financeApp.setGrossMonthlySalary(rs.getDouble("GrossMonthlySalary"));
            financeApp.setWorkPhone(rs.getString("WorkPhone"));
            financeApp.setJobTitle(rs.getString("JobTitle"));
            financeApp.setJobLength(rs.getString("JobLength"));
            financeApp.setOtherMonthlyGrossIncome(rs.getDouble("OtherMonthlyGrossIncome"));
            financeApp.setOtherIncomeSource(rs.getString("OtherIncomeSource"));
            financeApp.setRef1(rs.getString("Reference1"));
            financeApp.setRef2(rs.getString("Reference2"));
            financeApp.setRef1Phone(rs.getString("Ref1Phone"));
            financeApp.setRef2Phone(rs.getString("Ref2Phone"));
            financeApp.setDriversLicenseNo(rs.getString("DriversLicenseNo"));
            financeApp.setSSN(rs.getString("SSN"));
            financeApp.setCompleted(rs.getBoolean("Completed"));

            return financeApp;

        }catch(SQLException e){

            System.err.println(e.getMessage());
            return null;

        }

    }

    private void setProperties(PreparedStatement stmt, FinancialApplication model){
        try{

            stmt.setInt(1, model.getUserID());
            stmt.setInt(2, model.getCarID());
            stmt.setInt(3, model.getNumberOfDependents());
            stmt.setString(4, model.getResidentialStatus());
            stmt.setDouble(5, model.getMonthlyHomePayment());
            stmt.setString(6, model.getLandLordName());
            stmt.setString(7, model.getPreviousAddress());
            stmt.setString(8, model.getCurrentEmployerName());
            stmt.setString(9, model.getCurrentEmployerAddress());
            stmt.setDouble(10, model.getGrossMonthlySalary());
            stmt.setString(11, model.getWorkPhone());
            stmt.setString(12, model.getJobTitle());
            stmt.setString(13, model.getJobLength());
            stmt.setDouble(14, model.getOtherMonthlyGrossIncome());
            stmt.setString(15, model.getOtherIncomeSource());
            stmt.setString(16, model.getRef1());
            stmt.setString(17, model.getRef2());
            stmt.setString(18, model.getRef1Phone());
            stmt.setString(19, model.getRef2Phone());
            stmt.setString(20, model.getDriversLicenseNo());
            stmt.setString(21, model.getSSN());
            stmt.setBoolean(22, model.isCompleted());

        }catch(SQLException e){
            System.err.println(e.getErrorCode());
        }
    }
}
