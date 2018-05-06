import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.Scanner;

/*
 * ButtonDemo.java requires the following files:
 *   images/right.gif
 *   images/middle.gif
 *   images/left.gif
 */
public class AddMenu extends JPanel implements ActionListener {

    JButton customerButton;
    JButton roomButton;
    JButton hotelButton;
    JButton reservationButton;
    JButton hotelAddressButton;
    JButton customerSubmit;
    JButton roomSubmit;
    JButton hotelSubmit;
    JButton reservationSubmit;
    JButton hotelAddressSubmit;
    JButton bookingSubmit;

    // Create Customer
    JTextField cIDField;
    JLabel cIDLabel;
    JTextField firstNameField;
    JLabel firstNameLabel;
    JTextField lastNameField;
    JLabel lastNameLabel;
    JTextField ageField;
    JLabel ageLabel;
    JTextField genderField;
    JLabel genderLabel;

    // Create hotel
    JTextField hotelNameField;
    JLabel hotelNameLabel;
    JTextField branchIDField;
    JLabel branchIDLabel;
    JTextField phoneField;
    JLabel phoneLabel;

    // Create room
    JTextField typeField;
    JLabel typeLabel;
    JTextField capacityField;
    JLabel capacityLabel;
    JTextField quantityField;
    JLabel quantityLabel;

    // Create address
    JTextField cityField;
    JLabel cityLabel;
    JTextField stateField;
    JLabel stateLabel;
    JTextField zipField;
    JLabel zipLabel;

    // Create Reservation
    JTextField resNumField;
    JLabel resNumLabel;
    JTextField partySizeField;
    JLabel partySizeLabel;
    JTextField costField;
    JLabel costLabel;
    JTextField checkInField;
    JLabel checkInLabel;
    JTextField checkOutField;
    JLabel checkOutLabel;

    JLabel queryResult;
    JFrame frame;

    Connection connection;
    HotelDatabase db;

    public AddMenu() {

        db = new HotelDatabase();
        //Create and set up the window.
        this.frame = new JFrame("SimpleTravel - Add Records");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Display the window.
        frame.setPreferredSize(new Dimension(800, 500));

        //Display the window.
        frame.pack();
        frame.setVisible(true);

        customerButton = new JButton("Add Customer");
        customerButton.setVerticalTextPosition(AbstractButton.CENTER);
        customerButton.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        customerButton.setActionCommand("CUSTOMER");

        roomButton = new JButton("Add Room");
        roomButton.setVerticalTextPosition(AbstractButton.CENTER);
        roomButton.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        roomButton.setActionCommand("ROOM");

        hotelButton = new JButton("Add Hotel");
        hotelButton.setVerticalTextPosition(AbstractButton.CENTER);
        hotelButton.setHorizontalTextPosition(AbstractButton.LEADING);
        hotelButton.setActionCommand("HOTEL");

        reservationButton = new JButton("Add Reservation");
        reservationButton.setVerticalTextPosition(AbstractButton.CENTER);
        reservationButton.setHorizontalTextPosition(AbstractButton.LEADING);
        reservationButton.setActionCommand("RESERVATION");

        hotelAddressButton = new JButton("Add Address");
        hotelAddressButton.setVerticalTextPosition(AbstractButton.CENTER);
        hotelAddressButton.setHorizontalTextPosition(AbstractButton.LEADING);
        hotelAddressButton.setActionCommand("ADDRESSES");


        customerButton.addActionListener(this);
        roomButton.addActionListener(this);
        hotelButton.addActionListener(this);
        reservationButton.addActionListener(this);
        hotelAddressButton.addActionListener(this);

        //Add Components to this container, using the default FlowLayout.
        add(customerButton);
        add(roomButton);
        add(hotelButton);
        add(reservationButton);
        add(hotelAddressButton);

        this.setOpaque(true); //content panes must be opaque
        frame.setContentPane(this);
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void addCustomerFields() {
        //Create everything.
        cIDField = new JTextField(20);
        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        ageField = new JTextField(20);
        genderField = new JTextField(20);

        cIDField.setActionCommand("SUBMITCUSTOMER");
        firstNameField.setActionCommand("SUBMITCUSTOMER");
        lastNameField.setActionCommand("SUBMITCUSTOMER");
        ageField.setActionCommand("SUBMITCUSTOMER");
        genderField.setActionCommand("SUBMITCUSTOMER");

        cIDField.addActionListener(this);
        firstNameField.addActionListener(this);
        lastNameField.addActionListener(this);
        ageField.addActionListener(this);
        genderField.addActionListener(this);


        cIDLabel = new JLabel("Enter C_ID:");
        cIDLabel.setLabelFor(cIDField);
        firstNameLabel = new JLabel("Enter First Name:");
        firstNameLabel.setLabelFor(firstNameField);
        lastNameLabel = new JLabel("Enter Last Name:");
        lastNameLabel.setLabelFor(lastNameField);
        ageLabel = new JLabel("Enter Age:");
        ageLabel.setLabelFor(ageField);
        genderLabel = new JLabel("Enter Gender:");
        genderLabel.setLabelFor(genderField);

        //Lay out everything.
        JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));

        customerSubmit = new JButton("Submit Changes");
        customerSubmit.setActionCommand("SUBMITCUSTOMER");
        customerSubmit.addActionListener(this);

        textPane.add(customerSubmit);
        textPane.add(cIDLabel);
        textPane.add(cIDField);
        textPane.add(firstNameLabel);
        textPane.add(firstNameField);
        textPane.add(lastNameLabel);
        textPane.add(lastNameField);
        textPane.add(ageLabel);
        textPane.add(ageField);
        textPane.add(genderLabel);
        textPane.add(genderField);


        add(textPane);
        frame.revalidate();
        frame.repaint();
    }

    public void addReservationFields() {
        //Create everything.
        // c_ID        INTEGER,
        // res_num     INTEGER NOT NULL,
        // party_size  INTEGER NOT NULL,
        // cost        INTEGER,
        cIDField = new JTextField(20);
        resNumField = new JTextField(20);
        partySizeField = new JTextField(20);

        cIDField.setActionCommand("SUBMITRESERVATION");
        resNumField.setActionCommand("SUBMITRESERVATION");
        partySizeField.setActionCommand("SUBMITRESERVATION");

        cIDField.addActionListener(this);
        resNumField.addActionListener(this);
        partySizeField.addActionListener(this);

        cIDLabel = new JLabel("Enter C_ID:");
        cIDLabel.setLabelFor(cIDField);
        resNumLabel = new JLabel("Reservation Number:");
        resNumLabel.setLabelFor(resNumField);
        partySizeLabel = new JLabel("Enter Party Size:");
        partySizeLabel.setLabelFor(partySizeField);


        //Lay out everything.
        JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));

        reservationSubmit = new JButton("Submit Changes");
        reservationSubmit.setActionCommand("SUBMITRESERVATION");
        reservationSubmit.addActionListener(this);

        textPane.add(reservationSubmit);
        textPane.add(cIDLabel);
        textPane.add(cIDField);
        textPane.add(resNumLabel);
        textPane.add(resNumField);
        textPane.add(partySizeLabel);
        textPane.add(partySizeField);



        add(textPane);
        frame.revalidate();
        frame.repaint();
    }

    public void addHotelFields() {
        hotelNameField = new JTextField(20);
        branchIDField = new JTextField(20);
        phoneField = new JTextField(20);

        hotelNameField.setActionCommand("SUBMITHOTEL");
        branchIDField.setActionCommand("SUBMITHOTEL");
        phoneField.setActionCommand("SUBMITHOTEL");

        hotelNameField.addActionListener(this);
        branchIDField.addActionListener(this);
        phoneField.addActionListener(this);

        hotelNameLabel = new JLabel("Enter Hotel Name:");
        hotelNameLabel.setLabelFor(hotelNameField);
        branchIDLabel = new JLabel("Enter Branch ID:");
        branchIDLabel.setLabelFor(branchIDField);
        phoneLabel = new JLabel("Enter Phone Number:");
        phoneLabel.setLabelFor(phoneField);

        cityField = new JTextField(20);
        stateField = new JTextField(20);
        zipField = new JTextField(20);

        cityField.setActionCommand("SUBMITHOTEL");
        stateField.setActionCommand("SUBMITHOTEL");
        zipField.setActionCommand("SUBMITHOTEL");

        cityField.addActionListener(this);
        stateField.addActionListener(this);
        zipField.addActionListener(this);

        cityLabel = new JLabel("Enter City:");
        cityLabel.setLabelFor(cityField);
        stateLabel = new JLabel("Enter State:");
        stateLabel.setLabelFor(stateField);
        zipLabel = new JLabel("Enter Zipcode:");
        zipLabel.setLabelFor(zipField);

        //Lay out everything.
        JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));

        hotelSubmit = new JButton("Submit Changes");
        hotelSubmit.setActionCommand("SUBMITHOTEL");
        hotelSubmit.addActionListener(this);

        textPane.add(hotelSubmit);
        textPane.add(hotelNameLabel);
        textPane.add(hotelNameField);
        textPane.add(branchIDLabel);
        textPane.add(branchIDField);
        textPane.add(phoneLabel);
        textPane.add(phoneField);
        textPane.add(cityLabel);
        textPane.add(cityField);
        textPane.add(stateLabel);
        textPane.add(stateField);
        textPane.add(zipLabel);
        textPane.add(zipField);

        add(textPane);
        frame.revalidate();
        frame.repaint();
    }

    public void addRoomFields() {
        hotelNameField = new JTextField(20);
        branchIDField = new JTextField(20);
        typeField = new JTextField(20);
        capacityField = new JTextField(20);
        quantityField = new JTextField(20);

        hotelNameField.setActionCommand("SUBMITROOM");
        branchIDField.setActionCommand("SUBMITROOM");
        typeField.setActionCommand("SUBMITROOM");
        capacityField.setActionCommand("SUBMITROOM");
        quantityField.setActionCommand("SUBMITROOM");

        hotelNameField.addActionListener(this);
        branchIDField.addActionListener(this);
        typeField.addActionListener(this);
        capacityField.addActionListener(this);
        quantityField.addActionListener(this);

        hotelNameLabel = new JLabel("Enter the name of the Hotel:");
        hotelNameLabel.setLabelFor(hotelNameField);
        branchIDLabel = new JLabel("Enter the hotel branch ID:");
        branchIDLabel.setLabelFor(branchIDField);
        typeLabel = new JLabel("Enter Room Type:");
        typeLabel.setLabelFor(typeField);
        capacityLabel = new JLabel("Enter Room Capacity:");
        capacityLabel.setLabelFor(capacityField);
        quantityLabel = new JLabel("Enter Room Quantity:");
        quantityLabel.setLabelFor(quantityField);

        //Lay out everything.
        JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));

        roomSubmit = new JButton("Submit Changes");
        roomSubmit.setActionCommand("SUBMITROOM");
        roomSubmit.addActionListener(this);

        textPane.add(roomSubmit);
        textPane.add(hotelNameLabel);
        textPane.add(hotelNameField);
        textPane.add(branchIDLabel);
        textPane.add(branchIDField);
        textPane.add(typeLabel);
        textPane.add(typeField);
        textPane.add(capacityLabel);
        textPane.add(capacityField);
        textPane.add(quantityLabel);
        textPane.add(quantityField);

        add(textPane);
        frame.revalidate();
        frame.repaint();
    }

    public void addBookingFields() {
        checkInField = new JTextField(20);
        checkOutField = new JTextField(20);
        typeField = new JTextField(20);
        hotelNameField = new JTextField(20);
        branchIDField = new JTextField(20);

        hotelNameField.setActionCommand("SUBMITBOOKING");
        branchIDField.setActionCommand("SUBMITBOOKING");
        typeField.setActionCommand("SUBMITBOOKING");
        checkInField.setActionCommand("SUBMITBOOKING");
        checkOutField.setActionCommand("SUBMITBOOKING");

        hotelNameField.addActionListener(this);
        branchIDField.addActionListener(this);
        typeField.addActionListener(this);
        checkInField.addActionListener(this);
        checkOutField.addActionListener(this);

        hotelNameLabel = new JLabel("Enter the name of the Hotel:");
        hotelNameLabel.setLabelFor(hotelNameField);
        branchIDLabel = new JLabel("Enter the hotel branch ID:");
        branchIDLabel.setLabelFor(branchIDField);
        typeLabel = new JLabel("Enter Room Type:");
        typeLabel.setLabelFor(typeField);
        checkInLabel = new JLabel("Enter Check In Date:");
        checkInLabel.setLabelFor(checkInField);
        checkOutLabel = new JLabel("Enter Check Out Date:");
        checkOutLabel.setLabelFor(checkOutLabel);

        //Lay out everything.
        JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));

        bookingSubmit = new JButton("Submit Changes");
        bookingSubmit.setActionCommand("SUBMITBOOKING");
        bookingSubmit.addActionListener(this);

        textPane.add(bookingSubmit);
        textPane.add(hotelNameLabel);
        textPane.add(hotelNameField);
        textPane.add(branchIDLabel);
        textPane.add(branchIDField);
        textPane.add(typeLabel);
        textPane.add(typeField);
        textPane.add(checkInLabel);
        textPane.add(checkInField);
        textPane.add(checkOutLabel);
        textPane.add(checkOutField);

        add(textPane);
        frame.revalidate();
        frame.repaint();
    }

    public void addAddressFields() {
        cityField = new JTextField(20);
        stateField = new JTextField(20);
        zipField = new JTextField(20);

        cityField.setActionCommand("SUBMITADDRESS");
        stateField.setActionCommand("SUBMITADDRESS");
        zipField.setActionCommand("SUBMITADDRESS");

        cityField.addActionListener(this);
        stateField.addActionListener(this);
        zipField.addActionListener(this);

        cityLabel = new JLabel("Enter City:");
        cityLabel.setLabelFor(cityField);
        stateLabel = new JLabel("Enter State:");
        stateLabel.setLabelFor(stateField);
        zipLabel = new JLabel("Enter Zipcode:");
        zipLabel.setLabelFor(zipField);

        //Lay out everything.
        JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));

        hotelAddressSubmit = new JButton("Submit Changes");
        hotelAddressSubmit.setActionCommand("SUBMITADDRESS");
        hotelAddressSubmit.addActionListener(this);

        textPane.add(hotelAddressSubmit);
        textPane.add(cityLabel);
        textPane.add(cityField);
        textPane.add(stateLabel);
        textPane.add(stateField);
        textPane.add(zipLabel);
        textPane.add(zipField);

        add(textPane);
        frame.revalidate();
        frame.repaint();
    }

    public void resetFields() {
        if(customerSubmit != null) { customerSubmit.setVisible(false); }
        if(cIDLabel !=  null) { cIDLabel.setVisible(false); }
        if(cIDField != null) { cIDField.setVisible(false); }
        if(firstNameLabel != null) { firstNameLabel.setVisible(false); }
        if(firstNameField != null) { firstNameField.setVisible(false); }
        if(lastNameLabel != null) { lastNameLabel.setVisible(false); }
        if(lastNameField != null) { lastNameField.setVisible(false); }
        if(ageLabel != null) { ageLabel.setVisible(false); }
        if(ageField != null) { ageField.setVisible(false); }
        if(genderLabel != null) { genderLabel.setVisible(false); }
        if(genderField != null) { genderField.setVisible(false); }
        if(hotelSubmit != null) { hotelSubmit.setVisible(false); }
        if(hotelNameLabel != null) { hotelNameLabel.setVisible(false); }
        if(hotelNameField != null) { hotelNameField.setVisible(false); }
        if(branchIDLabel != null) { branchIDLabel.setVisible(false); }
        if(branchIDField != null) { branchIDField.setVisible(false); }
        if(phoneLabel != null) { phoneLabel.setVisible(false); }
        if(phoneField != null) { phoneField.setVisible(false); }
        if(roomSubmit != null) { roomSubmit.setVisible(false); }
        if(typeLabel != null) { typeLabel.setVisible(false); }
        if(typeField != null) { typeField.setVisible(false); }
        if(capacityLabel != null) { capacityLabel.setVisible(false); }
        if(capacityField != null) { capacityField.setVisible(false); }
        if(quantityLabel != null) { quantityLabel.setVisible(false); }
        if(quantityField != null) { quantityField.setVisible(false); }
        if(checkInField != null) { checkInField.setVisible(false); }
        if(checkInLabel != null) { checkInLabel.setVisible(false); }
        if(checkOutLabel != null) { checkOutLabel.setVisible(false); }
        if(checkOutField != null) { checkOutField.setVisible(false); }
        if(costLabel != null) { costLabel.setVisible(false); }
        if(costField != null) { costField.setVisible(false); }
        if(reservationSubmit != null) { reservationSubmit.setVisible(false); }
        if(bookingSubmit != null) { bookingSubmit.setVisible(false); }
        if(resNumField != null) { resNumField.setVisible(false); }
        if(resNumLabel != null) { resNumLabel.setVisible(false); }
        if(partySizeField != null) { partySizeField.setVisible(false); }
        if(partySizeLabel != null) { partySizeLabel.setVisible(false); }
        if(cityField != null) { cityField.setVisible(false); }
        if(cityLabel != null) { cityLabel.setVisible(false); }
        if(stateField != null) { stateField.setVisible(false); }
        if(stateLabel != null) { stateLabel.setVisible(false); }
        if(zipField != null) { zipField.setVisible(false); }
        if(zipLabel != null) { zipLabel.setVisible(false); }

    }

    public void addCustomer(Connection connection) throws SQLException {

        DatabaseMetaData dmd = connection.getMetaData();
        ResultSet rs = dmd.getTables(null, null, "CUSTOMER", null);

        if (rs.next()) {

            String sql = "INSERT INTO Customer VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pStmt = connection.prepareStatement(sql);
            pStmt.clearParameters();

            // set customer id
            pStmt.setInt(1, Integer.parseInt(cIDField.getText()));

            // set first name
            pStmt.setString(2, firstNameField.getText());

            // set last name
            pStmt.setString(3, lastNameField.getText());

            // set age
            pStmt.setInt(4, Integer.parseInt(ageField.getText()));

            // set gender
            pStmt.setString(5, genderField.getText());

            try {
                pStmt.executeUpdate();
                JOptionPane.showMessageDialog(frame,
                        "The customer has been successfully added to the table.",
                        "Customer Added",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            catch (SQLException s) {
                JOptionPane.showMessageDialog(frame,
                        "The customer was unable to be added:\n -Customer already exists\n -Customer ID is not valid\n -Gender is not valid\n",
                        "Customer Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            finally {
                pStmt.close();
                rs.close();
            }
        }
    }

    public void addReservation(Connection connection) throws SQLException {

        DatabaseMetaData dmd = connection.getMetaData();
        ResultSet rs = dmd.getTables(null, null, "RESERVATION", null);

        if (rs.next()) {

            String sql = "INSERT INTO Reservation VALUES (?, ?, ?, ?)";
            PreparedStatement pStmt = connection.prepareStatement(sql);
            pStmt.clearParameters();

            // set customer id
            pStmt.setInt(1, Integer.parseInt(cIDField.getText()));

            // set reservation number
            pStmt.setInt(2, Integer.parseInt(resNumField.getText()));

            // set party size
            pStmt.setInt(3, Integer.parseInt(partySizeField.getText()));

            // set cost: initially 0 until added to booking and trigger will adjust cost
            pStmt.setInt(4, 0);


            try {
                pStmt.executeUpdate();
                addBookingFields();
                JOptionPane.showMessageDialog(frame,
                        "The reservation has been successfully added to the table.",
                        "Reservation Added",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            catch (SQLException e) {
                JOptionPane.showMessageDialog(frame,
                        "Reservation unable to be added for one of the following reasons:\n -Customer does not exist\n -Reservation number already exists\n",
                        "Reservation Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            finally {
                pStmt.close();
                rs.close();
            }
        }
    }

    public void addBooking(Connection connection) throws SQLException {

        DatabaseMetaData dmd = connection.getMetaData();
        ResultSet rs = dmd.getTables(null, null, "BOOKING", null);

        if (rs.next()) {

            String sql = "INSERT INTO Booking VALUES (?, ?, to_date(?, 'YYYY-MM-DD'), to_date(?, 'YYYY-MM-DD'), ?, ?, ?)";
            PreparedStatement pStmt = connection.prepareStatement(sql);
            pStmt.clearParameters();

            // set customer id
            pStmt.setInt(1, Integer.parseInt(cIDField.getText()));

            // set reservation number
            pStmt.setInt(2, Integer.parseInt(resNumField.getText()));

            // set check in/check out
            pStmt.setString(3, checkInField.getText());
            pStmt.setString(4, checkOutField.getText());

            // set cost: initially 0 until added to booking and trigger will adjust cost
            pStmt.setString(5, hotelNameField.getText());

            // set hotel branch ID
            pStmt.setInt(6, Integer.parseInt(branchIDField.getText()));

            // set room type
            pStmt.setString(7, typeField.getText());


            try {
                pStmt.executeUpdate();
                JOptionPane.showMessageDialog(frame,
                        "The booking has been successfully added to the table.",
                        "Booking Added",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            catch (SQLException e) {
                JOptionPane.showMessageDialog(frame,
                        "Booking unable to be added for one or more of the following reasons:\n -Party size too large for specified room\n -Hotel does not exist\n -No rooms available for specified room type\n -Reservation number already exists\n",
                        "Booking Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            finally {
                pStmt.close();
                rs.close();
            }
        }
    }

    public void addRoom(Connection connection) throws SQLException {

        DatabaseMetaData dmd = connection.getMetaData();
        ResultSet rs1 = dmd.getTables(null, null, "ROOM", null);
        ResultSet rs2 = dmd.getTables(null, null, "HOTEL_ROOMS", null);

        if (rs1.next() && rs2.next()){

            String sql = "INSERT INTO Room VALUES (?, ?, ?, ?)";
            PreparedStatement pStmt = connection.prepareStatement(sql);
            pStmt.clearParameters();

            pStmt.setString(1, hotelNameField.getText());
            pStmt.setInt(2, Integer.parseInt(branchIDField.getText()));
            pStmt.setString(3, typeField.getText());
            pStmt.setInt(4, Integer.parseInt(capacityField.getText()));

            try {
                pStmt.executeUpdate();
                JOptionPane.showMessageDialog(frame,
                        "The room has been successfully added to the table.",
                        "Room Added",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            catch (SQLException e) {
                JOptionPane.showMessageDialog(frame,
                        "The room was unable to be added.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            finally {
                pStmt.close();
                rs1.close();
                rs2.close();
            }

            linkHotelRoom(connection);
        }
        else {
            System.out.println("ERROR: Error loading ROOM or HOTEL_ROOMS Table.");
        }

    }

    public void addHotel(Connection connection) throws SQLException {

        DatabaseMetaData dmd = connection.getMetaData();
        ResultSet rs1 = dmd.getTables(null, null, "HOTEL", null);
        ResultSet rs2 = dmd.getTables(null, null, "HOTEL_ADDRESS", null);

        // Must successfully locate HOTEL and HOTEL_ADDRESS before proceeding:
        if (rs1.next() && rs2.next()){

            String sql = "INSERT INTO Hotel VALUES (?, ?, ?)";
            PreparedStatement pStmt = connection.prepareStatement(sql);
            pStmt.clearParameters();

            pStmt.setString(1, hotelNameField.getText());
            pStmt.setInt(2, Integer.parseInt(branchIDField.getText()));
            pStmt.setString(3, phoneField.getText());

            try {
                pStmt.executeUpdate();
                JOptionPane.showMessageDialog(frame,
                        "The hotel has been successfully added to the table.",
                        "Hotel Added",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            catch (SQLException e) {
                JOptionPane.showMessageDialog(frame,
                        "The hotel was unable to be added:\n -Hotel already exists\n",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            finally {
                pStmt.close();
                rs1.close();
                rs2.close();
            }
        }
        else {
            System.out.println("ERROR: Error loading HOTEL or HOTEL_ADDRESS Table.");
        }

        linkHotelAddress(connection);
    }

    public void addAddress(Connection connection) throws SQLException {
        DatabaseMetaData dmd = connection.getMetaData();
        ResultSet rs = dmd.getTables(null, null, "ADDRESS", null);

        if (rs.next()){

            String sql = "INSERT INTO Address VALUES (?, ?, ?)";
            PreparedStatement pStmt = connection.prepareStatement(sql);
            pStmt.clearParameters();

            pStmt.setString(1, cityField.getText());

            pStmt.setString(2, stateField.getText());

            pStmt.setInt(3, Integer.parseInt(zipField.getText()));

            try {
                pStmt.executeUpdate();
                JOptionPane.showMessageDialog(frame,
                        "The address has been successfully added to the table.",
                        "Hotel Address Added",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            catch (SQLException e) {
                JOptionPane.showMessageDialog(frame,
                        "There was an error while adding the hotel address.",
                        "Error Message",
                        JOptionPane.ERROR_MESSAGE);
            }
            finally {
                pStmt.close();
                rs.close();
            }
        }
    }

    // Links the current HOTEL and ADDRESS attributes values into the HOTEL_ADDRESS relation:
    private void linkHotelAddress(Connection connection) throws SQLException {

        String sql = "INSERT INTO Hotel_Address VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pStmt = connection.prepareStatement(sql);
        pStmt.clearParameters();

        pStmt.setString(1, hotelNameField.getText());
        pStmt.setInt(2, Integer.parseInt(branchIDField.getText()));
        pStmt.setString(3, cityField.getText());
        pStmt.setString(4,stateField.getText());
        pStmt.setInt(5, Integer.parseInt(zipField.getText()));

        try { pStmt.executeUpdate(); }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(frame,
                    "There was an error while adding the hotel address.",
                    "Error Message",
                    JOptionPane.ERROR_MESSAGE);
        }
        finally { pStmt.close(); }
    }

    // Links the current HOTEL and ROOM attributes values into the HOTEL_ROOMS relation:
    private void linkHotelRoom(Connection connection) throws SQLException {

        String sql = "INSERT INTO Hotel_Rooms VALUES (?, ?, ?, ?)";
        PreparedStatement pStmt = connection.prepareStatement(sql);
        pStmt.clearParameters();

        pStmt.setString(1, hotelNameField.getText());
        pStmt.setInt(2, Integer.parseInt(branchIDField.getText()));
        pStmt.setString(3, typeField.getText());
        pStmt.setInt(4, Integer.parseInt(quantityField.getText()));

        try { pStmt.executeUpdate(); }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(frame,
                    "There was an error while adding the hotel address.",
                    "Error Message",
                    JOptionPane.ERROR_MESSAGE);
        }
        finally { pStmt.close(); }
    }

    public void actionPerformed(ActionEvent e) {

        if(queryResult != null) {
            queryResult.setText("");
            frame.add(queryResult);
            frame.revalidate();
            frame.repaint();
        }
        if ("HOTEL".equals(e.getActionCommand())) {
            resetFields();
            addHotelFields();

        } else if("ROOM".equals(e.getActionCommand())) {
            resetFields();
            addRoomFields();

        } else if("CUSTOMER".equals(e.getActionCommand())) {
            resetFields();
            addCustomerFields();

        } else if("RESERVATION".equals(e.getActionCommand())) {
            resetFields();
            addReservationFields();

        } else if("ADDRESSES".equals(e.getActionCommand())) {
            resetFields();
            addAddressFields();

        } else if("SUBMITCUSTOMER".equals(e.getActionCommand())) {
            try {
                addCustomer(connection);
            } catch(SQLException s) {
                s.printStackTrace();
            }
        } else if("SUBMITHOTEL".equals(e.getActionCommand())) {
            try {
                addHotel(connection);
            } catch(SQLException s) {
                s.printStackTrace();
            }
        } else if("SUBMITROOM".equals(e.getActionCommand())) {
            try {
                addRoom(connection);
            } catch(SQLException s) {
                s.printStackTrace();
            }
        } else if("SUBMITADDRESS".equals(e.getActionCommand())) {
            try {
                addAddress(connection);
            } catch(SQLException s) {
                s.printStackTrace();
            }
        } else if("SUBMITRESERVATION".equals(e.getActionCommand())) {
            try {
                addReservation(connection);
            } catch(SQLException s) {
                s.printStackTrace();
            }
        } else if("SUBMITBOOKING".equals(e.getActionCommand())) {
            try {
                addBooking(connection);
                resetFields();
            } catch(SQLException s) {
                s.printStackTrace();
            }
        }
    }
}