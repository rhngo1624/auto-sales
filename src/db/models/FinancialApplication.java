package db.models;


public class FinancialApplication {

    private int ID;
    private int userID;
    private int carID;
    private int numberOfDependents;
    private String residentialStatus;
    private double monthlyHomePayment;
    private String landLordName;
    private String previousAddress;
    private String currentEmployerName;
    private String currentEmployerAddress;
    private double grossMonthlySalary;
    private String workPhone;
    private String jobTitle;
    private String jobLength;
    private double OtherMonthlyGrossIncome;
    private String otherIncomeSource;
    private String ref1;
    private String ref2;
    private String ref1Phone;
    private String ref2Phone;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public String getLandLordName() {
        return landLordName;
    }

    public void setLandLordName(String landLordName) {
        this.landLordName = landLordName;
    }

    public int getNumberOfDependents() {
        return numberOfDependents;
    }

    public void setNumberOfDependents(int numberOfDependents) {
        this.numberOfDependents = numberOfDependents;
    }

    public double getMonthlyHomePayment() {
        return monthlyHomePayment;
    }

    public void setMonthlyHomePayment(double monthlyHomePayment) {
        this.monthlyHomePayment = monthlyHomePayment;
    }

    public String getPreviousAddress() {
        return previousAddress;
    }

    public void setPreviousAddress(String previousAddress) {
        this.previousAddress = previousAddress;
    }

    public String getResidentialStatus() {
        return residentialStatus;
    }

    public void setResidentialStatus(String residentialStatus) {
        this.residentialStatus = residentialStatus;
    }

    public String getCurrentEmployerAddress() {
        return currentEmployerAddress;
    }

    public void setCurrentEmployerAddress(String currentEmployerAddress) {
        this.currentEmployerAddress = currentEmployerAddress;
    }

    public String getCurrentEmployerName() {
        return currentEmployerName;
    }

    public void setCurrentEmployerName(String currentEmployerName) {
        this.currentEmployerName = currentEmployerName;
    }

    public double getGrossMonthlySalary() {
        return grossMonthlySalary;
    }

    public void setGrossMonthlySalary(double grossMonthlySalary) {
        this.grossMonthlySalary = grossMonthlySalary;
    }

    public String getJobLength() {
        return jobLength;
    }

    public void setJobLength(String jobLength) {
        this.jobLength = jobLength;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getOtherIncomeSource() {
        return otherIncomeSource;
    }

    public void setOtherIncomeSource(String otherIncomeSource) {
        this.otherIncomeSource = otherIncomeSource;
    }

    public double getOtherMonthlyGrossIncome() {
        return OtherMonthlyGrossIncome;
    }

    public void setOtherMonthlyGrossIncome(double otherMonthlyGrossIncome) {
        OtherMonthlyGrossIncome = otherMonthlyGrossIncome;
    }

    public String getRef1() {
        return ref1;
    }

    public void setRef1(String ref1) {
        this.ref1 = ref1;
    }

    public String getRef1Phone() {
        return ref1Phone;
    }

    public void setRef1Phone(String ref1Phone) {
        this.ref1Phone = ref1Phone;
    }

    public String getRef2() {
        return ref2;
    }

    public void setRef2(String ref2) {
        this.ref2 = ref2;
    }

    public String getRef2Phone() {
        return ref2Phone;
    }

    public void setRef2Phone(String ref2Phone) {
        this.ref2Phone = ref2Phone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }
}
