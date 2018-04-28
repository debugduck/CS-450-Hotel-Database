// Packages:

// JDBC Libraries:
import java.sql.*;
import oracle.jdbc.driver.*;    // JDBC Driver

// JDK Libraries
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
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
  private Date check_in;
  private Date check_out;

  ///////////////////////////////////////////////////////////////////////////////
  //                                  Methods                                  //
  ///////////////////////////////////////////////////////////////////////////////

  // Default constructor:
  public HotelDatabase() {}

  // Runs the program from the command line:
  public static void main(String[] args) {

    // Initialize Database instance:
    HotelDatabase hotelDB = new HotelDatabase();

    // Get the connection:
    Connection connection = hotelDB.getConnection();
    try {
        hotelDB.createCustomer(connection);
        hotelDB.updateCustomerFirstName(connection, 12345);
        hotelDB.updateCustomerLastName(connection, 12345);
        hotelDB.updateCustomerAge(connection, 12345);
        hotelDB.updateCustomerGender(connection, 12345);
        hotelDB.createHotel(connection);
    } catch (SQLException e) {
        e.printStackTrace();
    }
  }

  // Opens a connection to the DB:
  public Connection getConnection() {

    Scanner scan = new Scanner(System.in);

    System.out.print("Username: ");
    this.username = scan.nextLine();
    System.out.print("Password: ");
    this.password = scan.nextLine();

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
      finally { pStmt.close(); }
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
      finally { pStmt.close(); }
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
      finally { pStmt.close(); }
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
      finally { pStmt.close(); }
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
      finally { pStmt.close(); }
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
      finally { pStmt.close(); }
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
      finally { pStmt.close(); }
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
      finally { pStmt.close(); }

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
      finally { pStmt.close(); }
    }
    else {
      System.out.println("ERROR: Error loading HOTEL Table.");
    }
  }

  // Inserts a new room into the ROOM Table, linking the weak entity with the HOTEL strong entity:
  private void createRoom(Connection connection, Scanner scan) throws SQLException {

    DatabaseMetaData dmd = connection.getMetaData();
    ResultSet rs1 = dmd.getTables(null, null, "ROOM", null);
    ResultSet rs2 = dmd.getTables(null, null, "HOTEL_ROOMS", null);

    if (rs1.next() && rs2.next()){

      String sql = "INSERT INTO Room VALUES (?, ?, ?, ?)";
      PreparedStatement pStmt = connection.prepareStatement(sql);
      pStmt.clearParameters();

      System.out.print("Please provide the room type a name: ");
      setType(scan.nextLine());
      pStmt.setString(1, getType());

      System.out.print("Please provide a guest capacity for this type: ");
      setCapacity(scan.nextInt());
      pStmt.setInt(2, getCapacity());

      try { pStmt.executeUpdate(); }
      catch (SQLException e) { throw e; }
      finally { pStmt.close(); }

      linkHotelRoom(connection, scan);
    }
    else {
      System.out.println("ERROR: Error loading ROOM or HOTEL_ROOMS Table.");
    }
  }

  // Prompts user to provide existing hotel and branch_ID to add as many room types as necessary:
  public void giveHotelRooms(Connection connection) throws SQLException {

    Scanner scan = new Scanner(System.in);

    DatabaseMetaData dmd = connection.getMetaData();
    ResultSet rs = dmd.getTables(null, null, "HOTEL", null);

    // Must successfully locate HOTEL and HOTEL_ADDRESS before proceeding:
    if (rs.next()){

      System.out.print("Please provide an existing hotel name: ");
      setHotelName(scan.nextLine());
      pStmt.setString(1, getHotelName());

      System.out.print("Please provide the existing hotel branch ID: ");
      setBranchID(Integer.parseInt(scan.nextLine()));
      pStmt.setInt(2, getBranchID());

      // Loop to create room types and link to Hotel:
      do {
        createRoom(connection, scan);
        System.out.println("Would you like to add another room type to this hotel (Y, N): ");
      } while ((String.valueOf(scan.next())).toUpperCase().equals("Y"));
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

    System.out.print("Please enter the number of rooms the hotel contains of type %s: ", getType());
    setQuantity(scan.nextInt());

    pStmt.setString(1, getHotelName());
    pStmt.setInt(2, getBranchID());
    pStmt.setString(3, getType());
    pStmt.setInt(4, getQuantity());

    try { pStmt.executionUpdate(); }
    catch (SQLException e) { throw e; }
    finally { pStmt.close(); }
  }

  ///////////////////////////////////////////////////////////////////////////////
  //                                Command Line GUI                           //
  ///////////////////////////////////////////////////////////////////////////////

  // Prints the main menu interfac for user display:
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

  // Prints the interface for tables available for user display:
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
