// Packages:

// JDBC Libraries:
import java.sql.*;
import oracle.jdbc.driver.*;    // JDBC Driver
import org.omg.PortableInterceptor.LOCATION_FORWARD;

// JDK Libraries
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.time.LocalDate;
import java.math.*;

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
//      executeQuery()               -> used to execute when SQL stmt returns records
//      next()                       -> read next row of results from Result Set
//      previous()                   -> read one row back from current row in Result Set
//      absolute(i)                  -> reads the row with the specified number i in Result Set
//      relative(i)                  -> moves forward or backward (if negative)
//      first()                      -> reads the first row in the Result Set
//      last()                       -> reads the last row in the Result Set
//
///////////////////////////////////////////////////////////////////////////////

public class HotelDatabase {

  ///////////////////////////////////////////////////////////////////////////////
  //                                  Fields                                   //
  ///////////////////////////////////////////////////////////////////////////////

  // DB Connection Properties:
  private String driver   = "oracle.jdbc.driver.OracleDriver";
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
  private int    branch_ID;
  private String phone;

  // Room (Type) Properties:
  private String type;
  private int    capacity;
  private int    quantity;

  // Customer Properties:
  private int    c_ID;
  private String first_name;
  private String last_name;
  private int    age;
  private char   gender;

  // Reservation/Booking Properties:
  private int  res_num;
  private int  party_size;
  private int  cost;
  private LocalDate check_in;
  private LocalDate check_out;

  // DB Columns:
  private final ArrayList<String> HOTEL;
  private final ArrayList<String> ROOM;
  private final ArrayList<String> CUSTOMER;
  private final ArrayList<String> RESERVATION;
  private final ArrayList<String> HOTEL_ADDRESS;
  private final ArrayList<String> HOTEL_ROOM;
  private final ArrayList<String> BOOKING;
  private final ArrayList<String> INFORMATION;
  private final ArrayList<String> NUMBERS;
  private final ArrayList<String> DATES;
  private final ArrayList<ArrayList<String>> ALL;

  ///////////////////////////////////////////////////////////////////////////////
  //                                  Methods                                  //
  ///////////////////////////////////////////////////////////////////////////////

  // Default constructor:
  public HotelDatabase() {

    this.HOTEL = new ArrayList<String>(Arrays.asList("HOTEL", "HOTEL_NAME", "BRANCH_ID", "PHONE"));
    this.ROOM = new ArrayList<String>(Arrays.asList("ROOM", "HOTEL_NAME", "BRANCH_ID", "TYPE", "CAPACITY"));
    this.CUSTOMER = new ArrayList<String>(Arrays.asList("CUSTOMER", "C_ID", "FIRST_NAME", "LAST_NAME", "AGE", "GENDER"));
    this.RESERVATION = new ArrayList<String>(Arrays.asList("RESERVATION", "C_ID", "RES_NUM", "PARTY_SIZE", "COST"));
    this.HOTEL_ADDRESS = new ArrayList<String>(Arrays.asList("HOTEL_ADDRESS", "HOTEL_NAME", "BRANCH_ID", "CITY", "STATE", "ZIP"));
    this.HOTEL_ROOM = new ArrayList<String>(Arrays.asList("HOTEL_ROOM", "HOTEL_NAME", "BRANCH_ID", "TYPE", "QUANTITY"));
    this.BOOKING = new ArrayList<String>(Arrays.asList("BOOKING", "C_ID", "RES_NUM", "CHECK_IN", "CHECK_OUT", "HOTEL_NAME", "BRANCH_ID", "TYPE"));
    this.INFORMATION = new ArrayList<String>(Arrays.asList("INFORMATION", "HOTEL_NAME", "BRANCH_ID", "TYPE", "DATE_FROM", "DATE_TO", "NUM_AVAILABLE", "PRICE"));
    this.NUMBERS = new ArrayList<String>(Arrays.asList("BRANCH_ID", "CAPACITY", "C_ID", "AGE", "RES_NUM", "PARTY_SIZE", "COST", "ZIP", "QUANTITY", "NUM_AVAILABLE", "PRICE"));
    this.DATES = new ArrayList<String>(Arrays.asList("CHECK_IN", "CHECK_OUT", "DATE_FROM", "DATE_TO"));

    // INDEXING:                                                0     1       2           3             4             5         6           7
    this.ALL = new ArrayList<ArrayList<String>>(Arrays.asList(HOTEL, ROOM, CUSTOMER, RESERVATION, HOTEL_ADDRESS, HOTEL_ROOM, BOOKING, INFORMATION));
  }

  // Runs the program from the command line:
  public static void main(String[] args) {

    // Initialize Database instance:
    HotelDatabase hotelDB = new HotelDatabase();
    Scanner scan = new Scanner(System.in);

    // Get the connection:
    hotelDB.username = "";
    hotelDB.password = "";
    Connection connection = hotelDB.getConnection(hotelDB.username, hotelDB.password);
    try {
        // Populate DateList and assign generic values to price:
        //hotelDB.populateDemo(connection, LocalDate.parse("2018-12-01"), LocalDate.parse("2018-12-31"));
        //hotelDB.searchArea(connection, "Long Beach", "CA", 94103);
        //hotelDB.showTable(connection);
        //hotelDB.createRoom(connection, scan);
        //hotelDB.searchCustomerReservations(connection, 2);
        //hotelDB.searchHotelReservations(connection, , int branchID, LocalDate checkIn, LocalDate checkOut)
        hotelDB.searchAvailabilityType(connection, "Westin Hotel", 1, "Presidential Suite", LocalDate.parse("2018-12-01"), LocalDate.parse("2018-12-01"));

    } catch (SQLException e) {
        e.printStackTrace();
    }
  }

  // Creates a list of entries for dates from a start and end date:
  public void populateDemo(Connection connection, LocalDate start, LocalDate end) throws SQLException {

    DatabaseMetaData dmd = connection.getMetaData();
    ResultSet rs = dmd.getTables(null, null, "DateList", null);

    if (rs != null){

      while (!start.isAfter(end)){

        String sql = "INSERT INTO DateList VALUES (to_date(?, 'YYYY-MM-DD'), to_date(?, 'YYYY-MM-DD'))";

        String sql1 = "INSERT INTO Information VALUES('Westin Hotel', 1, 'Single Suite', to_date(?, 'YYYY-MM-DD'), to_date(?, 'YYYY-MM-DD'), 5, 200)";
        String sql2 = "INSERT INTO Information VALUES('Westin Hotel', 1, 'Traditional Suite', to_date(?, 'YYYY-MM-DD'), to_date(?, 'YYYY-MM-DD'), 10, 300)";
        String sql3 = "INSERT INTO Information VALUES('Westin Hotel', 1, 'Presidential Suite', to_date(?, 'YYYY-MM-DD'), to_date(?, 'YYYY-MM-DD'), 1, 1000)";
        String sql4 = "INSERT INTO Information VALUES('Westin Hotel', 2, 'Single Suite', to_date(?, 'YYYY-MM-DD'), to_date(?, 'YYYY-MM-DD'), 5, 200)";
        String sql5 = "INSERT INTO Information VALUES('Westin Hotel', 2, 'Traditional Suite', to_date(?, 'YYYY-MM-DD'), to_date(?, 'YYYY-MM-DD'), 15, 400)";
        String sql6 = "INSERT INTO Information VALUES('Westin Hotel', 2, 'Presidential Suite', to_date(?, 'YYYY-MM-DD'), to_date(?, 'YYYY-MM-DD'), 1, 1000)";
        String sql7 = "INSERT INTO Information VALUES('Ritz Carlton Hotel', 1, 'Traditional Suite', to_date(?, 'YYYY-MM-DD'), to_date(?, 'YYYY-MM-DD'), 20, 800)";
        String sql8 = "INSERT INTO Information VALUES('Ritz Carlton Hotel', 1, 'Presidential Suite', to_date(?, 'YYYY-MM-DD'), to_date(?, 'YYYY-MM-DD'), 1, 2000)";
        String sql9 = "INSERT INTO Information VALUES('Ritz Carlton Hotel', 2, 'Traditional Suite', to_date(?, 'YYYY-MM-DD'), to_date(?, 'YYYY-MM-DD'), 25, 1000)";
        String sql10 = "INSERT INTO Information VALUES('Ritz Carlton Hotel', 2, 'Presidential Suite', to_date(?, 'YYYY-MM-DD'), to_date(?, 'YYYY-MM-DD'), 1, 2000)";
        String sql11 = "INSERT INTO Information VALUES('Four Seasons Hotel', 1, 'Single Suite', to_date(?, 'YYYY-MM-DD'), to_date(?, 'YYYY-MM-DD'), 10, 350)";
        String sql12 = "INSERT INTO Information VALUES('Four Seasons Hotel', 1, 'Twin Suite', to_date(?, 'YYYY-MM-DD'), to_date(?, 'YYYY-MM-DD'), 10, 250)";

        PreparedStatement pStmt = connection.prepareStatement(sql);
        PreparedStatement pStmt1 = connection.prepareStatement(sql1);
        PreparedStatement pStmt2 = connection.prepareStatement(sql2);
        PreparedStatement pStmt3 = connection.prepareStatement(sql3);
        PreparedStatement pStmt4 = connection.prepareStatement(sql4);
        PreparedStatement pStmt5 = connection.prepareStatement(sql5);
        PreparedStatement pStmt6 = connection.prepareStatement(sql6);
        PreparedStatement pStmt7 = connection.prepareStatement(sql7);
        PreparedStatement pStmt8 = connection.prepareStatement(sql8);
        PreparedStatement pStmt9 = connection.prepareStatement(sql9);
        PreparedStatement pStmt10 = connection.prepareStatement(sql10);
        PreparedStatement pStmt11 = connection.prepareStatement(sql11);
        PreparedStatement pStmt12 = connection.prepareStatement(sql12);

        pStmt.clearParameters();
        pStmt1.clearParameters();
        pStmt2.clearParameters();
        pStmt3.clearParameters();
        pStmt4.clearParameters();
        pStmt5.clearParameters();
        pStmt6.clearParameters();
        pStmt7.clearParameters();
        pStmt8.clearParameters();
        pStmt9.clearParameters();
        pStmt10.clearParameters();
        pStmt11.clearParameters();
        pStmt12.clearParameters();

        pStmt.setString(1, start.toString());
        pStmt1.setString(1, start.toString());
        pStmt2.setString(1, start.toString());
        pStmt3.setString(1, start.toString());
        pStmt4.setString(1, start.toString());
        pStmt5.setString(1, start.toString());
        pStmt6.setString(1, start.toString());
        pStmt7.setString(1, start.toString());
        pStmt8.setString(1, start.toString());
        pStmt9.setString(1, start.toString());
        pStmt10.setString(1, start.toString());
        pStmt11.setString(1, start.toString());
        pStmt12.setString(1, start.toString());

        start = start.plusDays(1);

        pStmt.setString(2, start.toString());
        pStmt1.setString(2, start.toString());
        pStmt2.setString(2, start.toString());
        pStmt3.setString(2, start.toString());
        pStmt4.setString(2, start.toString());
        pStmt5.setString(2, start.toString());
        pStmt6.setString(2, start.toString());
        pStmt7.setString(2, start.toString());
        pStmt8.setString(2, start.toString());
        pStmt9.setString(2, start.toString());
        pStmt10.setString(2, start.toString());
        pStmt11.setString(2, start.toString());
        pStmt12.setString(2, start.toString());

        try {
          pStmt.executeUpdate();
          pStmt1.executeUpdate();
          pStmt2.executeUpdate();
          pStmt3.executeUpdate();
          pStmt4.executeUpdate();
          pStmt5.executeUpdate();
          pStmt6.executeUpdate();
          pStmt7.executeUpdate();
          pStmt8.executeUpdate();
          pStmt9.executeUpdate();
          pStmt10.executeUpdate();
          pStmt11.executeUpdate();
          pStmt12.executeUpdate();
         }
        catch (SQLException e) { throw e; }
        finally {
          pStmt.close();
          pStmt1.close();
          pStmt2.close();
          pStmt3.close();
          pStmt4.close();
          pStmt5.close();
          pStmt6.close();
          pStmt7.close();
          pStmt8.close();
          pStmt9.close();
          pStmt10.close();
          pStmt11.close();
          pStmt12.close();
        }
      }
    }
    else {
      System.out.println("ERROR: Error loading CUSTOMER Table.");
    }
  }

  // Opens a connection to the DB:
  public Connection getConnection(String username, String password) {

    Scanner scan = new Scanner(System.in);

    //System.out.print("Username: ");
    //this.username = scan.nextLine();
    //System.out.print("Password: ");
    //this.password = scan.nextLine();

    // Registering the JDBC Driver
    try { Class.forName(driver); }
    catch (ClassNotFoundException e) { e.printStackTrace(); }

    // Opening a connection to the database:
    Connection connection = null;
    try { connection = DriverManager.getConnection (jdbc_url, username, password); }
    catch (SQLException e) { e.printStackTrace(); }

    return connection;
  }

  public void showTable(Connection connection) throws SQLException {

    Scanner scan = new Scanner(System.in);
    int tableIndex = -1;

    while (tableIndex == -1){

      printViewTables();

      // Narrow which table the user wants to see:
      System.out.print("Enter a table to see: ");
      switch (String.valueOf(scan.next()).toUpperCase()){
        case "H":
          tableIndex = 0;
          break;
        case "R":
          tableIndex = 1;
          break;
        case "C":
          tableIndex = 2;
          break;
        case "V":
          tableIndex = 3;
          break;
        case "HA":
          tableIndex = 4;
          break;
        case "HR":
          tableIndex = 5;
          break;
        case "BK":
          tableIndex = 6;
          break;
        case "IN":
          tableIndex = 7;
          break;
        default:
          System.out.println("ERROR: Invalid Table. Please choose from the tables below: ");
      }
    }

    // Retreive all the rows from that table:
    ResultSet rs = null;
    String sql = "SELECT * FROM " + ALL.get(tableIndex).get(0);
    PreparedStatement pStmt = connection.prepareStatement(sql);

    try {

      rs = pStmt.executeQuery();

      while (rs.next()) {

        for (int i = 1; i < ALL.get(tableIndex).size(); i++){

          // Need to make distinction between values that are Strings, Ints, or Dates:
          if (NUMBERS.contains(ALL.get(tableIndex).get(i))){
            System.out.print(rs.getInt(ALL.get(tableIndex).get(i)));
          }
          else if (DATES.contains(ALL.get(tableIndex).get(i))){
            System.out.print(rs.getDate(ALL.get(tableIndex).get(i)));
          }
          else {
            System.out.print(rs.getString(ALL.get(tableIndex).get(i)));
          }
          System.out.print(" ");
        }
        System.out.println();
      }
    }
    catch (SQLException e) { e.printStackTrace(); }
    finally {
      pStmt.close();
      if(rs != null) { rs.close(); }
    }
  }

  // Closes a DB Connection:
  public void close(Connection connection) throws SQLException {

    try { connection.close(); }
    catch (SQLException e) { throw e; }
  }

  // Inserts a new customer into the CUSTOMER table:
  public void createCustomer(Connection connection) throws SQLException {

    Scanner scan = new Scanner(System.in);

    DatabaseMetaData dmd = connection.getMetaData();
    ResultSet rs = dmd.getTables(null, null, "CUSTOMER", null);

    if (rs.next()) {

      String sql = "INSERT INTO Customer VALUES (?, ?, ?, ?, ?)";
      PreparedStatement pStmt = connection.prepareStatement(sql);
      pStmt.clearParameters();

      System.out.print("Please provide a unique Customer ID: ");
      setCID(Integer.parseInt(scan.nextLine()));
      pStmt.setInt(1, getCID());

      System.out.print("Please provide a first name: ");
      setFirstName(scan.nextLine());
      pStmt.setString(2, getFirstName());

      System.out.print("Please provide a last name: ");
      setLastName(scan.nextLine());
      pStmt.setString(3, getLastName());

      System.out.print("Please provide an age: ");
      setAge(Integer.parseInt(scan.nextLine()));
      pStmt.setInt(4, getAge());

      System.out.print("Please provide a gender: ");
      setGender(scan.next().charAt(0));
      pStmt.setString(5, String.valueOf(getGender()));

      try { pStmt.executeUpdate(); }
      catch (SQLException e) { throw e; }
      finally {
        pStmt.close();
        rs.close();
      }
    }
    else {
      System.out.println("ERROR: Error loading CUSTOMER Table.");
    }
  }

  // Updates Customer first name attribute given CID:
  public void updateCustomerFirstName(Connection connection, int CID) throws SQLException {

    Scanner scan = new Scanner(System.in);

    DatabaseMetaData dmd = connection.getMetaData();
    ResultSet rs = dmd.getTables(null, null, "CUSTOMER", null);

    if (rs.next()){

      String sql = "UPDATE Customer SET first_name = ? WHERE c_ID = ?";
      PreparedStatement pStmt = connection.prepareStatement(sql);
      pStmt.clearParameters();

      System.out.print("Please provide a new first name: ");
      setFirstName(scan.nextLine());
      pStmt.setString(1, getFirstName());

      setCID(CID);
      pStmt.setInt(2, getCID());

      try { pStmt.executeUpdate(); }
      catch (SQLException e) { throw e; }
      finally {
        pStmt.close();
        rs.close();
      }
    }
    else {
      System.out.println("ERROR: Error loading CUSTOMER Table.");
    }
  }

  // Updates Customer last name attribute given CID:
  public void updateCustomerLastName(Connection connection, int CID) throws SQLException {

    Scanner scan = new Scanner(System.in);

    DatabaseMetaData dmd = connection.getMetaData();
    ResultSet rs = dmd.getTables(null, null, "CUSTOMER", null);

    if (rs.next()){

      String sql = "UPDATE Customer SET last_name = ? WHERE c_ID = ?";
      PreparedStatement pStmt = connection.prepareStatement(sql);
      pStmt.clearParameters();

      System.out.print("Please provide a new last name: ");
      setLastName(scan.nextLine());
      pStmt.setString(1, getLastName());

      setCID(CID);
      pStmt.setInt(2, getCID());

      try { pStmt.executeUpdate(); }
      catch (SQLException e) { throw e; }
      finally {
        pStmt.close();
        rs.close();
      }
    }
    else {
      System.out.println("ERROR: Error loading CUSTOMER Table.");
    }
  }

  // Updates Customer gender attribute given CID:
  public void updateCustomerGender(Connection connection, int CID) throws SQLException {

    Scanner scan = new Scanner(System.in);

    DatabaseMetaData dmd = connection.getMetaData();
    ResultSet rs = dmd.getTables(null, null, "CUSTOMER", null);

    if (rs.next()){

      String sql = "UPDATE Customer SET gender = ? WHERE c_ID = ?";
      PreparedStatement pStmt = connection.prepareStatement(sql);
      pStmt.clearParameters();

      System.out.print("Please provide a new gender: ");
      setGender(scan.next().charAt(0));
      pStmt.setString(1, String.valueOf(getGender()));

      setCID(CID);
      pStmt.setInt(2, getCID());

      try { pStmt.executeUpdate(); }
      catch (SQLException e) { throw e; }
      finally {
        pStmt.close();
        rs.close();
      }
    }
    else {
      System.out.println("ERROR: Error loading CUSTOMER Table.");
    }
  }

  // Updates Customer age attribute given CID:
  public void updateCustomerAge(Connection connection, int CID) throws SQLException {

    Scanner scan = new Scanner(System.in);

    DatabaseMetaData dmd = connection.getMetaData();
    ResultSet rs = dmd.getTables(null, null, "CUSTOMER", null);

    if (rs.next()){

      String sql = "UPDATE Customer SET age = ? WHERE c_ID = ?";
      PreparedStatement pStmt = connection.prepareStatement(sql);
      pStmt.clearParameters();

      System.out.print("Please provide a new age: ");
      setAge(Integer.parseInt(scan.nextLine()));
      pStmt.setInt(1, getAge());

      setCID(CID);
      pStmt.setInt(2, getCID());

      try { pStmt.executeUpdate(); }
      catch (SQLException e) { throw e; }
      finally {
        pStmt.close();
        rs.close();
      }
    }
    else {
      System.out.println("ERROR: Error loading CUSTOMER Table.");
    }
  }

  // Deletes a Customer given CID from the CUSTOMER Table:
  public void deleteCustomer(Connection connection, int CID) throws SQLException {

    Scanner scan = new Scanner(System.in);

    DatabaseMetaData dmd = connection.getMetaData();
    ResultSet rs = dmd.getTables(null, null, "CUSTOMER", null);

    if (rs.next()){

      String sql = "DELETE FROM Customer WHERE c_ID = ?";
      PreparedStatement pStmt = connection.prepareStatement(sql);
      pStmt.clearParameters();

      setCID(CID);
      pStmt.setInt(1, getCID());

      try { pStmt.executeUpdate(); }
      catch (SQLException e) { throw e; }
      finally {
        pStmt.close();
        rs.close();
      }
    }
    else {
      System.out.println("ERROR: Error loading CUSTOMER Table.");
    }
  }

  // Inserts a new address into the ADDRESS Table:
  public void createAddress(Connection connection, Scanner scan) throws SQLException {

    DatabaseMetaData dmd = connection.getMetaData();
    ResultSet rs = dmd.getTables(null, null, "ADDRESS", null);

    if (rs.next()){

      String sql = "INSERT INTO Address VALUES (?, ?, ?)";
      PreparedStatement pStmt = connection.prepareStatement(sql);
      pStmt.clearParameters();

      System.out.print("Please provide hotel address city: ");
      setCity(scan.nextLine());
      pStmt.setString(1, getCity());

      System.out.print("Please provide hotel address state: ");
      setState(scan.nextLine());
      pStmt.setString(2, getState());

      System.out.print("Please provide hotel address zip: ");
      setZip(Integer.parseInt(scan.nextLine()));
      pStmt.setInt(3, getZip());

      try { pStmt.executeUpdate(); }
      catch (SQLException e) { throw e; }
      finally {
        pStmt.close();
        rs.close();
      }
    }
    else {
      System.out.println("ERROR: Error loading ADDRESS Table.");
    }
  }

  // Inserts a new hotel into the HOTEL Table, updating ADDRESS and HOTEL_ADDRESS appropriately:
  public void createHotel(Connection connection) throws SQLException {

    Scanner scan = new Scanner(System.in);

    DatabaseMetaData dmd = connection.getMetaData();
    ResultSet rs1 = dmd.getTables(null, null, "HOTEL", null);
    ResultSet rs2 = dmd.getTables(null, null, "HOTEL_ADDRESS", null);

    // Must successfully locate HOTEL and HOTEL_ADDRESS before proceeding:
    if (rs1.next() && rs2.next()){

      createAddress(connection, scan);      // Creates an address to link HOTEL with ADDRESS

      String sql = "INSERT INTO Hotel VALUES (?, ?, ?)";
      PreparedStatement pStmt = connection.prepareStatement(sql);
      pStmt.clearParameters();

      System.out.print("Please provide a hotel name: ");
      setHotelName(scan.nextLine());
      pStmt.setString(1, getHotelName());

      System.out.print("Please provide a branch ID: ");
      setBranchID(Integer.parseInt(scan.nextLine()));
      pStmt.setInt(2, getBranchID());


      System.out.print("Please provide a phone number: ");
      setPhone(scan.nextLine());
      pStmt.setString(3, getPhone());

      try { pStmt.executeUpdate(); }
      catch (SQLException e) { throw e; }
      finally {
        pStmt.close();
        rs1.close();
        rs2.close();
      }

      linkHotelAddress(connection);  // Links HOTEL with ADDRESS entities in HOTEL_ADDRESS relation

      do {
        createRoom(connection, scan);
        System.out.println("Would you like to add another room type to this hotel (Y, N): ");
      } while ((String.valueOf(scan.next())).toUpperCase().equals("Y"));
    }
    else {
      System.out.println("ERROR: Error loading HOTEL or HOTEL_ADDRESS Table.");
    }
  }

  // Updates Hotel phone number attribute given hotel name and branch ID:
  public void updateHotelPhone(Connection connection, String hotel_name, int branch_ID) throws SQLException {

    Scanner scan = new Scanner(System.in);

    DatabaseMetaData dmd = connection.getMetaData();
    ResultSet rs = dmd.getTables(null, null, "HOTEL", null);

    if (rs.next()){

      String sql = "UPDATE Hotel SET phone = ? WHERE hotel_name = ? AND branch_ID = ?";
      PreparedStatement pStmt = connection.prepareStatement(sql);
      pStmt.clearParameters();

      System.out.print("Please provide a new phone number: ");
      setPhone(scan.nextLine());
      pStmt.setString(1, getPhone());

      setHotelName(hotel_name);
      pStmt.setString(2, getHotelName());
      setBranchID(branch_ID);
      pStmt.setInt(3, getBranchID());

      try { pStmt.executeUpdate(); }
      catch (SQLException e) { throw e; }
      finally {
        pStmt.close();
        rs.close();
      }
    }
    else {
      System.out.println("ERROR: Error loading HOTEL Table.");
    }
  }

  // Inserts a new room into the ROOM Table, linking the weak entity with the HOTEL strong entity:
  // ************************* CALL giveHotelRooms() FIRST *****************************
  private void createRoom(Connection connection, Scanner scan) throws SQLException {

    DatabaseMetaData dmd = connection.getMetaData();
    ResultSet rs1 = dmd.getTables(null, null, "ROOM", null);
    ResultSet rs2 = dmd.getTables(null, null, "HOTEL_ROOMS", null);

    if (rs1.next() && rs2.next()){

      String sql = "INSERT INTO Room VALUES (?, ?, ?, ?)";
      PreparedStatement pStmt = connection.prepareStatement(sql);
      pStmt.clearParameters();

      pStmt.setString(1, getHotelName());
      pStmt.setInt(2, getBranchID());

      System.out.print("Please provide the room type a name: ");
      setType(scan.nextLine());
      pStmt.setString(3, getType());

      System.out.print("Please provide a guest capacity for this type: "); // branch id, room type, capacity
      setCapacity(Integer.parseInt(scan.nextLine()));
      pStmt.setInt(4, getCapacity());

      try { pStmt.executeUpdate(); }
      catch (SQLException e) { e.printStackTrace(); }
      finally {
        pStmt.close();
        rs1.close();
        rs2.close();
      }

      linkHotelRoom(connection, scan);
    }
    else {
      System.out.println("ERROR: Error loading ROOM or HOTEL_ROOMS Table.");
    }
  }

  // Prompts user to provide existing hotel and branch_ID to add as many room types as necessary:
  public void giveHotelRooms(Connection connection) throws SQLException {

    Scanner scan = new Scanner(System.in);
    Scanner choice = new Scanner(System.in);

    DatabaseMetaData dmd = connection.getMetaData();
    ResultSet rs = dmd.getTables(null, null, "HOTEL", null);

    // Must successfully locate HOTEL and HOTEL_ADDRESS before proceeding:
    if (rs.next()){

      System.out.print("Please provide an existing hotel name: ");
      setHotelName(scan.nextLine());

      System.out.print("Please provide the existing hotel branch ID: ");
      setBranchID(Integer.parseInt(scan.nextLine()));

      // Loop to create room types and link to Hotel:
      do {
        createRoom(connection, new Scanner(System.in));
        System.out.println("Would you like to add another room type to this hotel (Y, N): ");
      } while (choice.next().toUpperCase().equals("Y"));
    }
    else {
      System.out.println("ERROR: Error loading HOTEL Table.");
    }
  }

  // Links the current HOTEL and ADDRESS attributes values into the HOTEL_ADDRESS relation:
  private void linkHotelAddress(Connection connection) throws SQLException {

    String sql = "INSERT INTO Hotel_Address VALUES (?, ?, ?, ?, ?)";
    PreparedStatement pStmt = connection.prepareStatement(sql);
    pStmt.clearParameters();

    pStmt.setString(1, getHotelName());
    pStmt.setInt(2, getBranchID());
    pStmt.setString(3, getCity());
    pStmt.setString(4, getState());
    pStmt.setInt(5, getZip());

    try { pStmt.executeUpdate(); }
    catch (SQLException e) { throw e; }
    finally { pStmt.close(); }
  }

  // Links the current HOTEL and ROOM attributes values into the HOTEL_ROOMS relation:
  private void linkHotelRoom(Connection connection, Scanner scan) throws SQLException {

    String sql = "INSERT INTO Hotel_Rooms VALUES (?, ?, ?, ?)";
    PreparedStatement pStmt = connection.prepareStatement(sql);
    pStmt.clearParameters();

    System.out.print("Please enter the number of " + getType() + "s the hotel has: ");
    setQuantity(scan.nextInt());

    pStmt.setString(1, getHotelName());
    pStmt.setInt(2, getBranchID());
    pStmt.setString(3, getType());
    pStmt.setInt(4, getQuantity());

    try { pStmt.executeUpdate(); }
    catch (SQLException e) { throw e; }
    finally { pStmt.close(); }
  }

  // Finds the available reservation(s) for a customer given their CID:
  public void searchCustomerReservations(Connection connection, int CID) throws SQLException {

    ResultSet rs = null;
    String sql = "SELECT res_num FROM Reservation WHERE c_ID = ?";
    PreparedStatement pStmt = connection.prepareStatement(sql);
    pStmt.clearParameters();

    setCID(CID);
    pStmt.setInt(1, getCID());

    try {

      System.out.printf("  Reservations for C_ID (%d):\n", getCID());
      System.out.println("+------------------------------------------------------------------------------+");

      rs = pStmt.executeQuery();

      while (rs.next()) {
        System.out.println("Reservation number: " + rs.getInt(1));
      }
    }
    catch (SQLException e) { e.printStackTrace(); }
    finally {
      pStmt.close();
      if(rs != null) { rs.close(); }
    }
  }

  // Finds all available reservation(s) for a hotel given its name and branch ID with a date filter:
  public void searchHotelReservations(Connection connection, String hotel_name, int branchID, LocalDate checkIn, LocalDate checkOut) throws SQLException {

    ResultSet rs = null;
    String sql = "SELECT c_id, res_num, check_in, check_out FROM Booking WHERE hotel_name = ? AND branch_ID = ? AND check_in >= to_date(?, 'YYYY-MM-DD') AND check_out <= to_date(?, 'YYYY-MM-DD')";
    PreparedStatement pStmt = connection.prepareStatement(sql);
    pStmt.clearParameters();

    setHotelName(hotel_name);
    pStmt.setString(1, getHotelName());
    setBranchID(branchID);
    pStmt.setInt(2, getBranchID());

    if (checkIn == null && checkOut == null){
      pStmt.setString(3, LocalDate.parse("2000-01-01").toString());
      pStmt.setString(4, LocalDate.parse("3000-01-01").toString());
    }
    else if (checkIn == null){
      pStmt.setString(3, checkIn.toString());
      pStmt.setString(4, LocalDate.parse("3000-01-01").toString());
    }
    else if (checkOut == null){
      pStmt.setString(3, LocalDate.parse("2000-01-01").toString());
      pStmt.setString(4, checkOut.toString());
    }
    else {
      pStmt.setString(3, checkIn.toString());
      pStmt.setString(4, checkOut.toString());
    }

    try {

      System.out.printf("  Reservations for %S, branch ID (%d): \n", getHotelName(), getBranchID());
      System.out.println("+------------------------------------------------------------------------------+");

      rs = pStmt.executeQuery();

      while (rs.next()) {
        System.out.println(" " + rs.getString(1) + " " + rs.getInt(2));
        System.out.println("    Reservation DATES: " + rs.getDate(3) + " TO " + rs.getDate(4));
      }
    }
    catch (SQLException e) { throw e; }
    finally {
      pStmt.close();
      rs.close();
    }
  }

  // Finds all availabilities and pricelistings for a particular type of room at a specified hotel within a date range:
  public void searchAvailabilityType(Connection connection, String hotel_name, int branchID, String type, LocalDate from, LocalDate to) throws SQLException {

    ResultSet rs = null;
    String sql = "SELECT num_avail, price FROM Information WHERE hotel_name = ? AND branch_ID = ? AND type = ? AND date_from >= to_date(?, 'YYYY-MM-DD') AND date_to <= to_date(?, 'YYYY-MM-DD')";

    PreparedStatement pStmt = connection.prepareStatement(sql);
    pStmt.clearParameters();

    setHotelName(hotel_name);
    pStmt.setString(1, getHotelName());
    setBranchID(branchID);
    pStmt.setInt(2, getBranchID());
    setType(type);
    pStmt.setString(3, getType());
    pStmt.setString(4, from.toString());
    pStmt.setString(5, to.toString());
    System.out.println(pStmt.toString());

    try {

      System.out.printf("  Availiabilities for %S type at %S, branch ID (%d): \n", getType(), getHotelName(), getBranchID());
      System.out.printf("  Date Listing: " + String.valueOf(from) + " TO " + String.valueOf(to) + "\n");
      System.out.println("+------------------------------------------------------------------------------+");

      rs = pStmt.executeQuery();

      while(rs.first()) {
        System.out.println(rs.getInt(1) + " " + rs.getInt(2));
      }
    }
    catch (SQLException e) { throw e; }
    finally {
      pStmt.close();
      if(rs != null) { rs.close(); }
    }
  }

  // Finds all availabilities and pricelistings for ALL types of rooms at a specified hotel within a date range:
  public void searchAvailability(Connection connection, String hotel_name, int branchID, java.sql.Date from, java.sql.Date to) throws SQLException {

    ResultSet rs = null;
    String sql = "SELECT type, num_avail, price FROM Information WHERE hotel_name = ? AND branch_ID = ? AND date_from >= Convert(datetime, ?) AND date_to <= Convert(datetime, ?)";

    PreparedStatement pStmt = connection.prepareStatement(sql);
    pStmt.clearParameters();

    setHotelName(hotel_name);
    pStmt.setString(1, getHotelName());
    setBranchID(branchID);
    pStmt.setInt(2, getBranchID());
    pStmt.setDate(3, from);
    pStmt.setDate(4, to);

    try {

      System.out.printf("  Availiabilities at %S, branch ID (%d): \n", getHotelName(), getBranchID());
      System.out.printf("  Date Listing: " + String.valueOf(from) + " TO " + String.valueOf(to) + "\n");
      System.out.println("+------------------------------------------------------------------------------+");

      rs = pStmt.executeQuery();

      while (rs.next()) {
        System.out.println(rs.getString(1) + " " + rs.getInt(2) + " " + rs.getInt(3));
      }
    }
    catch (SQLException e) { throw e; }
    finally {
      pStmt.close();
      if(rs != null) { rs.close(); }
    }
  }

  // Finds all hotels within a given city:
  public void searchAreaCity(Connection connection, String city) throws SQLException {

    ResultSet rs = null;
    String sql = "SELECT hotel_name, branch_id FROM Hotel_Address WHERE city = ?";
    PreparedStatement pStmt = connection.prepareStatement(sql);
    pStmt.clearParameters();

    setCity(city);
    pStmt.setString(1, getCity());

    try {

      System.out.printf("  Hotels in %s:\n", getCity());
      System.out.println("+------------------------------------------------------------------------------+");

      rs = pStmt.executeQuery();

      while (rs.next()) {
        System.out.println(rs.getString(1) + " " + rs.getInt(2));
      }
    }
    catch (SQLException e) { e.printStackTrace(); }
    finally {
      pStmt.close();
      if(rs != null) { rs.close(); }
    }
  }

  // Finds all hotels within a given state:
  public void searchAreaState(Connection connection, String state) throws SQLException {

    ResultSet rs = null;
    String sql = "SELECT hotel_name, branch_id FROM Hotel_Address WHERE state = ?";
    PreparedStatement pStmt = connection.prepareStatement(sql);
    pStmt.clearParameters();

    setState(state);
    pStmt.setString(1, getState());

    try {

      System.out.printf("  Hotels in %s:\n", getState());
      System.out.println("+------------------------------------------------------------------------------+");

      rs = pStmt.executeQuery();

      while (rs.next()) {
        System.out.println(rs.getString(1) + " " + rs.getInt(2));
      }
    }
    catch (SQLException e) { throw e; }
    finally {
      pStmt.close();
      rs.close();
    }
  }

  // Finds all hotels within a given zip:
  public void searchAreaCity(Connection connection, int zip) throws SQLException {

    ResultSet rs = null;
    String sql = "SELECT hotel_name, branch_id FROM Hotel_Address WHERE zip = ?";
    PreparedStatement pStmt = connection.prepareStatement(sql);
    pStmt.clearParameters();

    setZip(zip);
    pStmt.setInt(1, getZip());

    try {

      System.out.printf("  Hotels in %d:\n", getZip());
      System.out.println("+------------------------------------------------------------------------------+");

      rs = pStmt.executeQuery();

      while (rs.next()) {
        System.out.println(rs.getString(1) + " " + rs.getInt(2));
      }
    }
    catch (SQLException e) { throw e; }
    finally {
      pStmt.close();
      if(rs != null) { rs.close(); }
    }
  }

  // Finds all hotels within a given area; used for combinations for two:
  public void searchArea(Connection connection, String city, String state, int zip) throws SQLException {

    ResultSet rs = null;
    PreparedStatement pStmt = null;
    String sql1 = "SELECT hotel_name, branch_id FROM Hotel_Address WHERE city = ?";
    String sql2 = "SELECT hotel_name, branch_id FROM Hotel_Address WHERE state = ?";
    String sql3 = "SELECT hotel_name, branch_id FROM Hotel_Address WHERE zip = ?";

    // City and State combination:
    if (city != null && state != null){

      pStmt = connection.prepareStatement(sql1 + " INTERSECT " + sql2);
      pStmt.clearParameters();

      setCity(city);
      pStmt.setString(1, getCity());
      setState(state);
      pStmt.setString(2, getState());
    }

    // City and ZIP combination:
    else if (city != null){

      pStmt = connection.prepareStatement(sql1 + " INTERSECT " + sql3);
      pStmt.clearParameters();

      setCity(city);
      pStmt.setString(1, getCity());
      setZip(zip);
      pStmt.setInt(2, getZip());
    }

    // State and ZIP combination:
    else {

      pStmt = connection.prepareStatement(sql2 + " INTERSECT " + sql3);
      pStmt.clearParameters();

      setState(state);
      pStmt.setString(1, getState());
      setZip(zip);
      pStmt.setInt(2, getZip());
    }

    try {

      System.out.println("  Hotels in area:");
      System.out.println("+------------------------------------------------------------------------------+");

      rs = pStmt.executeQuery();
      String result;

      while (rs.next()) {

        System.out.println(rs.getString(1) + " " + rs.getInt(2));
      }
    }
    catch (SQLException e) { throw e; }
    finally {
      pStmt.close();
      if(rs != null) { rs.close(); }
    }
  }

  // Finds all hotels within the full address:
  public void searchAreaFull(Connection connection, String city, String state, int zip) throws SQLException{

    ResultSet rs = null;
    String sql = "SELECT hotel_name, branch_id FROM Hotel_Address WHERE city = ? AND state = ? AND zip = ?";
    PreparedStatement pStmt = connection.prepareStatement(sql);
    pStmt.clearParameters();

    setCity(city);
    pStmt.setString(1, getCity());
    setState(state);
    pStmt.setString(2, getState());
    setZip(zip);
    pStmt.setInt(3, getZip());

    try {

      System.out.println("  Hotels in area:");
      System.out.println("+------------------------------------------------------------------------------+");

      rs = pStmt.executeQuery();

      while (rs.next()) {
        System.out.println(rs.getString(1) + " " + rs.getInt(2));
      }
    }
    catch (SQLException e) { throw e; }
    finally {
      pStmt.close();
      if(rs != null) { rs.close(); }
    }
  }

  // Finds all the room types available at a specified hotel:
  public void searchHotelRoomTypes(Connection connection, String hotel_name, int branch_ID) throws SQLException {

    ResultSet rs = null;
    String sql = "SELECT type, quantity FROM Hotel_Rooms WHERE hotel_name = ? AND branch_ID = ?";
    PreparedStatement pStmt = connection.prepareStatement(sql);
    pStmt.clearParameters();

    setHotelName(hotel_name);
    pStmt.setString(1, getHotelName());
    setBranchID(branch_ID);
    pStmt.setInt(2, getBranchID());

    try {

      System.out.printf("  Room types available at %S, branch ID (%d)", getHotelName(), getBranchID());
      System.out.println("+------------------------------------------------------------------------------+");

      rs = pStmt.executeQuery();

      while (rs.next()) {
        System.out.println(rs.getString(1) + " " + rs.getInt(2));
      }
    }
    catch (SQLException e) { throw e; }
    finally {
      pStmt.close();
      if(rs != null) { rs.close(); }
    }
  }

  ///////////////////////////////////////////////////////////////////////////////
  //                              Command Line GUI                             //
  ///////////////////////////////////////////////////////////////////////////////

  // Prints the main menu interface for user display:
  public void mainMenu() {

    System.out.println("\n                            HOTEL RESERVATION SYSTEM");
    System.out.println("+------------------------------------------------------------------------------+");
    System.out.println(" COMMANDS:");
    System.out.println(" - View Tables\t\t(1)");
    System.out.println(" - Add Records\t\t(2)");
    System.out.println(" - Update Records\t(3)");
    System.out.println(" - Delete Records\t(4)");
    System.out.println(" - Search Records\t(5)");
    System.out.println("+------------------------------------------------------------------------------+");
    System.out.print(" > ");
  }

  public void printViewTables() {

    System.out.println("\n                                  VIEW TABLES");
    System.out.println("+------------------------------------------------------------------------------+");
    //System.out.println(" - Addresses\t\t(A) - Display all addresses where hotels are located");
    System.out.println(" - Hotels\t\t(H)  - Display all hotels and their branch IDs");
    System.out.println(" - Rooms\t\t(R)  - Display all room types offered by each hotel");
    System.out.println(" - Customers\t\t(C)  - Display all customer accounts");
    System.out.println(" - Reservations\t\t(R)  - Display all reservations made by customers");
    //System.out.println(" - Date Listings \t\t (D) - Display all date listings ");
    System.out.println("+------------------------------------------------------------------------------+");
    System.out.println(" - Hotel Addresses\t(HA) - Display all hotels and their addresses");
    System.out.println(" - Hotel Rooms\t\t(HR) - Display all hotels and room type quantities");
    //System.out.println(" - Reserved (RR) - Display all reservation numbers of each customer");
    System.out.println(" - Bookings\t\t(BK) - Display all customer booking information");
    System.out.println(" - Information\t\t(IN) - Display all availabilities and pricelistings");
    System.out.println("+------------------------------------------------------------------------------+");
    System.out.print(" > ");
  }

  public void printAddRecords() {

    System.out.println("\n                                  ADD RECORDS");
    System.out.println("+------------------------------------------------------------------------------+");
    System.out.println(" - Add Customer\t\t(1)");
    System.out.println(" - Add Hotel\t\t(2)");
    System.out.println(" - Add Hotel Room(s)\t\t(3)");
    System.out.println(" - Add Reservation\t\t(4)");
    System.out.println("+------------------------------------------------------------------------------+");
  }

  public void printUpdateRecords() {

    System.out.println("\n                                UPDATE RECORDS");
    System.out.println("+------------------------------------------------------------------------------+");
    System.out.println(" - Update Customer [NAME]\t\t(C1)");
    System.out.println(" - Update Customer [AGE]\t\t(C2)");
    System.out.println(" - Update Customer [GENDER]\t\t(C3)");
    System.out.println("+------------------------------------------------------------------------------+");
    System.out.println(" - Update Hotel [PHONE]\t\t(H1)");
    System.out.println("+------------------------------------------------------------------------------+");
  }

  public void printDeleteRecords() {

    System.out.println("\n                                DELETE RECORDS");
    System.out.println("+------------------------------------------------------------------------------+");
    System.out.println("+------------------------------------------------------------------------------+");
  }

  public void printSearchRecords() {

    System.out.println("\n                                SEARCH RECORDS");
    System.out.println("+------------------------------------------------------------------------------+");
    System.out.println("+------------------------------------------------------------------------------+");
  }

  ///////////////////////////////////////////////////////////////////////////////
  //                          Getter and Setter Methods                        //
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
    this.state = newState;
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

  public int getBranchID() {
    return this.branch_ID;
  }

  public void setBranchID(int newBranchID) {
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

  public LocalDate getCheckIn(){
    return this.check_in;
  }

  public void setCheckIn(LocalDate check_in){
    this.check_in = check_in;
  }

  public LocalDate getCheckOut(){
    return this.check_out;
  }

  public void setCheckOut(LocalDate check_out){
    this.check_out = check_out;
  }

  public String getUsername() {
      return this.username;
  }

  public void setUsername(String username) {
      this.username = username;
  }

  public String getPassword() {
      return this.password;
  }

  public void setPassword(String password) {
      this.password = password;
  }
}
