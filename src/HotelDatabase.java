// Packages:

// JDBC Libraries:
import java.sql.*;

// JDK Libraries
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.math.*;
import oracle.jdbc.driver.*;    // JDBC Driver

// Enter at command line when starting Java Application:
// -Djdbc.drivers=oracle/jdbc.driver

///////////////////////////////////////////////////////////////////////////////
// JDBC API Reference:
//
// 1. Creating a Statement Object
//
// String sql = "INSERT INTO [Table Name] VALUES (?, ?, ?, ?)";  -> '?' is a placeholder
// PreparedStatement pStmt = connection.prepareStatement(sql);
//
// pStmt.clearParameters();             -> Clears parameters
// pStmt.setString(1, "88877");         -> Sets parameter 1 to "88877"
// pStmt.setInt(2, 125000);             -> Sets parameter 2 to 125000
//
// 2. Executing a Query
//
// try {
//   int numRows = pStmt.executeUpdate(); -> executeUpdate() used if SQL stmt does
// } catch (SQLException sqle);              NOT return any records (e.g. UPDATE,
//                                           INSERT, ALTER, and DELETE commands).
//                                           Returns an integer indicated the number
//                                           of rows the SQL update modified.
//
// String sqlQuery = "SELECT name, salary FROM [Table Name] WHERE ID=?";
// PreparedStatement pStmt2 = connection.prepareStatement(sqlQuery);
//
// pStmt2.setString(1, ID);                -> Replaces '?'
//
// ResultSet rset = pStmt2.executeQuery(); -> executeQuery() used if SQL stmt DOES
//                                            return rows, MUST save in a ResultSet
//
// 3. Printing Query Results
//
// while (rset.next())
//    System.out.println(rset.getString(1) + " " + rset.getInt(2));
//
//     -> (1) in getString refers to 'name' value in SELECT query, and (2)
//        refers to 'salary' value in SELECT query
//
// 4. Closing the Result Set
//
// rset.close();
// pStmt.close();
// pStmt2.close();
//
// APPENDIX:
//   - API Commands:
//      clearParameters()            -> Clears parameters
//      setString(i, string)         -> Sets parameter number i to String string
//      setInt(i, x)                 -> Sets parameter number i to value in x
//      getString(i)                 -> Returns the ith attribute selected, when String
//      getString(string)            -> Returns the value of the designated column name in string
//      getInt(i)                    -> Returns the ith attribute selected, when Integer
//      executeUpdate()              -> used to execute when SQL stmt does not return records
//      executeQuery()              -> used to execute when SQL stmt returns records
//      next()                       -> read next row of results from Result Set
//      previous()                   -> read one row back from current row in Result Set
//      absolute(i)                  -> reads the row with the specified number i in Result Set
//      relative(i)                  -> moves forward or backward (if negative)
//      first()                      -> reads the first row in the Result Set
//      last()                       -> reads the last row in the Result Set
//
///////////////////////////////////////////////////////////////////////////////

public class HotelDatabase {

    // Fields:

    // DB Connection Properties:
    private String driver = "oracle.jdbc.driver.OracleDriver";
    private String jdbc_url = "jdbc:oracle:thin:@apollo.vse.gmu.edu:1521:ite10g";

    // DB User Properties:
    private String username;
    private String password;

    // Address Properties:
    private String city;
    private String state;
    private int    zip;

    // Hotel Properties:
    private String hotel_name;
    private String branch_ID;
    private String phone;          // do check length > 10

    // Room (Type) Properties:
    private String type;
    private int    capacity;
    private int    quantity;

    // Customer Properties:
    private int    c_ID;
    private String first_name;
    private String last_name;
    private int    age;            // do check age > 18
    private char   gender;         // F, M, or N

    // Reservation/Booking Properties:
    private int res_num;
    private int party_size;
    private int cost;
    private Date check_in;
    private Date check_out;

    // Default constructor:
    public HotelDatabase() {}

    // Runs the program from the command line:
    public static void main(String[] args) {

        HotelDatabase hotelDB = new HotelDatabase();

        // Get the connection:
        Connection connection = hotelDB.getConnection();

    }

    // Opens a connection to the DB:
    public Connection getConnection(){

        Scanner scan = new Scanner(System.in);

        System.out.print("Username: ");
        this.username = scan.next();
        System.out.print("Password: ");
        this.password = scan.next();

        // Registering the JDBC Driver
        try { Class.forName(driver); }
        catch (ClassNotFoundException e) { e.printStackTrace(); }

        // Opening a connection to the database:
        Connection connection = null;
        try { connection = DriverManager.getConnection (jdbc_url, username, password); }
        catch (SQLException e) { e.printStackTrace(); }

        return connection;
    }

    // Closes a DB Connection:
    public void close(Connection connection) throws SQLException {

        try { connection.close(); }
        catch (SQLException e) { throw e; }
    }

    // Inserts a new customer
    public void createCustomer(Connection connection) throws SQLException {

        Scanner scan = new Scanner(System.in);

        DatabaseMetaData dmd = connection.getMetaData();
        ResultSet rs = dmd.getTables(null, null, "CUSTOMER", null);

        if (rs.next()) {

            String sql = "INSERT INTO Customer VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pStmt = connection.prepareStatement(sql);
            pStmt.clearParameters();

            System.out.print("Please provide a unique Customer ID: ");
            setCID(scan.nextInt());
            pStmt.setInt(1, getCID());

            System.out.print("Please provide a first name: ");
            setFirstName(scan.next());
            pStmt.setString(2, getFirstName());

            System.out.print("Please provide a last name: ");
            setLastName(scan.next());
            pStmt.setString(3, getLastName());

            System.out.print("Please provide an age: ");
            setAge(scan.nextInt());
            pStmt.setInt(4, getAge());

            System.out.print("Please provide a gender: ");
            setGender(scan.next().charAt(0));
            pStmt.setString(5, String.valueOf(getGender()));

            try {
                pStmt.executeUpdate();
            } catch (SQLException e) {
                throw e;
            } finally {
                pStmt.close();
            }
        } else {
            System.out.println("ERROR: Error loading CUSTOMER Table.");
        }
    }


        ///////////////////////////////////////////////////////////////////////////////
        //                            Getter and Setter Methods                      //
        ///////////////////////////////////////////////////////////////////////////////

    public String getCity() {
        return this.city;
    }

    public void setCity(String newCity) {
        this.city = newCity;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String newState) {
        this.city = newState;
    }

    public int getZip() {
        return this.zip;
    }

    public void setZip(int newZip) {
        this.zip = newZip;
    }

    public String getHotelName() {
        return this.hotel_name;
    }

    public void setHotelName(String newHotelName) {
        this.hotel_name = newHotelName;
    }

    public String getBranchID() {
        return this.branch_ID;
    }

    public void setBranchID(String newBranchID) {
        this.branch_ID = newBranchID;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String newPhone) {
        this.phone = newPhone;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String newType) {
        this.type = newType;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int newCapacity) {
        this.capacity = newCapacity;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }

    public int getCID(){
        return this.c_ID;
    }

    public void setCID(int c_ID){
        this.c_ID = c_ID;
    }

    public String getFirstName(){
        return this.first_name;
    }

    public void setFirstName(String first_name){
        this.first_name = first_name;
    }

    public String getLastName(){
        return this.last_name;
    }

    public void setLastName(String last_name){
        this.last_name = last_name;
    }

    public int getAge(){
        return this.age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public char getGender(){
        return this.gender;
    }

    public void setGender(char gender){
        this.gender = gender;
    }

    public int getResNum(){
        return this.res_num;
    }

    public void setResNum(int res_num){
        this.res_num = res_num;
    }

    public int getPartySize(){
        return this.party_size;
    }

    public void setPartySize(int party_size){
        this.party_size = party_size;
    }

    public int getCost(){
        return this.cost;
    }

    public void setCost(int cost){
        this.cost = cost;
    }

    public Date getCheckIn(){
        return this.check_in;
    }

    public void setCheckIn(Date check_in){
        this.check_in = check_in;
    }

    public Date getCheckOut(){
        return this.check_out;
    }

    public void setCheckOut(Date check_out){
        this.check_out = check_out;
    }
}