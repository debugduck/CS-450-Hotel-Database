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
public class DeleteMenu extends JPanel implements ActionListener {

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

    public DeleteMenu() {

        db = new HotelDatabase();
        //Create and set up the window.
        this.frame = new JFrame("SimpleTravel - Delete Records");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Display the window.
        frame.setPreferredSize(new Dimension(800, 500));

        //Display the window.
        frame.pack();
        frame.setVisible(true);

        customerButton = new JButton("Delete Customer");
        customerButton.setVerticalTextPosition(AbstractButton.CENTER);
        customerButton.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        customerButton.setActionCommand("CUSTOMER");

        roomButton = new JButton("Delete Room");
        roomButton.setVerticalTextPosition(AbstractButton.CENTER);
        roomButton.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        roomButton.setActionCommand("ROOM");

        hotelButton = new JButton("Delete Hotel");
        hotelButton.setVerticalTextPosition(AbstractButton.CENTER);
        hotelButton.setHorizontalTextPosition(AbstractButton.LEADING);
        hotelButton.setActionCommand("HOTEL");

        reservationButton = new JButton("Delete Reservation");
        reservationButton.setVerticalTextPosition(AbstractButton.CENTER);
        reservationButton.setHorizontalTextPosition(AbstractButton.LEADING);
        reservationButton.setActionCommand("RESERVATION");

        hotelAddressButton = new JButton("Delete Address");
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
        cIDField.setActionCommand("SUBMITCUSTOMER");
        cIDField.addActionListener(this);
        cIDLabel = new JLabel("Enter C_ID of Customer to delete:");
        cIDLabel.setLabelFor(cIDField);

        //Lay out everything.
        JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));

        customerSubmit = new JButton("Submit Changes");
        customerSubmit.setActionCommand("SUBMITCUSTOMER");
        customerSubmit.addActionListener(this);

        textPane.add(customerSubmit);
        textPane.add(cIDLabel);
        textPane.add(cIDField);

        add(textPane);
        frame.revalidate();
        frame.repaint();
    }

    public void addReservationFields() {
        //Create everything.
        resNumField = new JTextField(20);
        resNumField.setActionCommand("SUBMITRESERVATION");
        resNumField.addActionListener(this);
        cIDField = new JTextField(20);
        cIDField.setActionCommand("SUBMITRESERVATION");
        cIDField.addActionListener(this);

        resNumLabel = new JLabel("Enter Reservation Number to delete:");
        resNumLabel.setLabelFor(resNumField);
        cIDLabel = new JLabel("Enter Customer ID associated with this reservation number:");
        cIDLabel.setLabelFor(cIDField);


        //Lay out everything.
        JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));

        reservationSubmit = new JButton("Submit Changes");
        reservationSubmit.setActionCommand("SUBMITRESERVATION");
        reservationSubmit.addActionListener(this);

        textPane.add(reservationSubmit);
        textPane.add(resNumLabel);
        textPane.add(resNumField);
        textPane.add(cIDLabel);
        textPane.add(cIDField);

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

        add(textPane);
        frame.revalidate();
        frame.repaint();
    }

    public void addRoomFields() {
        typeField = new JTextField(20);
        typeField.setActionCommand("SUBMITROOM");
        typeField.addActionListener(this);
        typeLabel = new JLabel("Enter Room Type:");
        typeLabel.setLabelFor(typeField);

        //Lay out everything.
        JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));

        roomSubmit = new JButton("Submit Changes");
        roomSubmit.setActionCommand("SUBMITROOM");
        roomSubmit.addActionListener(this);

        textPane.add(roomSubmit);
        textPane.add(typeLabel);
        textPane.add(typeField);

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

    }

    // Deletes a Customer given CID from the CUSTOMER Table:
    public void deleteCustomer(Connection connection) throws SQLException {

        DatabaseMetaData dmd = connection.getMetaData();
        ResultSet rs = dmd.getTables(null, null, "CUSTOMER", null);

        if (rs.next()){

            String sql = "DELETE FROM Customer WHERE c_ID = ?";
            PreparedStatement pStmt = connection.prepareStatement(sql);
            pStmt.clearParameters();

            pStmt.setInt(1, Integer.parseInt(cIDField.getText()));

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
    public void deleteReservation(Connection connection) throws SQLException {

        DatabaseMetaData dmd = connection.getMetaData();
        ResultSet rs = dmd.getTables(null, null, "CUSTOMER", null);

        if (rs.next()){

            String sql = "DELETE FROM Reservation WHERE res_num = ? AND c_ID = ?";
            PreparedStatement pStmt = connection.prepareStatement(sql);
            pStmt.clearParameters();

            pStmt.setInt(1, Integer.parseInt(resNumField.getText()));
            pStmt.setInt(2, Integer.parseInt(cIDField.getText()));

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
    public void deleteHotel(Connection connection) throws SQLException {

        DatabaseMetaData dmd = connection.getMetaData();
        ResultSet rs = dmd.getTables(null, null, "CUSTOMER", null);

        if (rs.next()){

            String sql = "DELETE FROM Hotel WHERE hotel_name = ? AND branch_ID = ? AND phone = ?";
            PreparedStatement pStmt = connection.prepareStatement(sql);
            pStmt.clearParameters();

            pStmt.setString(1, hotelNameField.getText());
            pStmt.setInt(2, Integer.parseInt(branchIDField.getText()));
            pStmt.setString(3, phoneField.getText());

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
    public void deleteRoom(Connection connection) throws SQLException {

        DatabaseMetaData dmd = connection.getMetaData();
        ResultSet rs = dmd.getTables(null, null, "CUSTOMER", null);

        if (rs.next()){

            String sql = "DELETE FROM Room WHERE type = ?";
            PreparedStatement pStmt = connection.prepareStatement(sql);
            pStmt.clearParameters();

            pStmt.setString(1, typeField.getText());

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
    public void deleteAddress(Connection connection) throws SQLException {

        DatabaseMetaData dmd = connection.getMetaData();
        ResultSet rs = dmd.getTables(null, null, "CUSTOMER", null);

        if (rs.next()){

            String sql = "DELETE FROM Address WHERE city = ? AND state = ? AND zip = ?";
            PreparedStatement pStmt = connection.prepareStatement(sql);
            pStmt.clearParameters();

            pStmt.setString(1, cityField.getText());
            pStmt.setString(2, stateField.getText());
            pStmt.setInt(3, Integer.parseInt(zipField.getText()));

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

    public void actionPerformed(ActionEvent e) {

        if (queryResult != null) {
            queryResult.setText("");
            frame.add(queryResult);
            frame.revalidate();
            frame.repaint();
        }
        if ("HOTEL".equals(e.getActionCommand())) {
            resetFields();
            addHotelFields();

        } else if ("ROOM".equals(e.getActionCommand())) {
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
                deleteCustomer(connection);
                JOptionPane.showMessageDialog(frame,
                        "The customer has been successfully deleted from the table.",
                        "Customer Deleted",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch(SQLException s) {
                JOptionPane.showMessageDialog(frame,
                        "The customer was unable to be deleted.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if("SUBMITHOTEL".equals(e.getActionCommand())) {
            try {
                deleteHotel(connection);
                JOptionPane.showMessageDialog(frame,
                        "The hotel has been successfully deleted from the table.",
                        "Hotel Deleted",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch(SQLException s) {
                JOptionPane.showMessageDialog(frame,
                        "The hotel was unable to be deleted.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if("SUBMITROOM".equals(e.getActionCommand())) {
            try {
                deleteRoom(connection);
                JOptionPane.showMessageDialog(frame,
                        "The room has been successfully deleted from the table.",
                        "Room Deleted",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch(SQLException s) {
                JOptionPane.showMessageDialog(frame,
                        "The room was unable to be deleted.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if("SUBMITADDRESS".equals(e.getActionCommand())) {
            try {
                deleteAddress(connection);
                JOptionPane.showMessageDialog(frame,
                        "The address has been successfully deleted from the table.",
                        "Address Deleted",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch(SQLException s) {
                JOptionPane.showMessageDialog(frame,
                        "The address was unable to be deleted.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if("SUBMITRESERVATION".equals(e.getActionCommand())) {
            try {
                deleteReservation(connection);
                JOptionPane.showMessageDialog(frame,
                    "The reservation has been successfully deleted from the table.",
                    "Reservation Deleted",
                    JOptionPane.INFORMATION_MESSAGE);
            } catch(SQLException s) {
                JOptionPane.showMessageDialog(frame,
                    "The reservation was unable to be deleted from the table.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}