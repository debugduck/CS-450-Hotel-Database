import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/*
 * ButtonDemo.java requires the following files:
 *   images/right.gif
 *   images/middle.gif
 *   images/left.gif
 */
public class UpdateMenu extends JPanel implements ActionListener {

    JButton updateNameButton;
    JButton updateLastNameButton;
    JButton updateAgeButton;
    JButton updateGenderButton;
    JButton updateHotelPhoneButton;
    JButton firstSubmit;
    JButton lastSubmit;
    JButton ageSubmit;
    JButton genderSubmit;
    JButton phoneSubmit;


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

    public UpdateMenu() {

        db = new HotelDatabase();
        //Create and set up the window.
        this.frame = new JFrame("SimpleTravel - Update Records");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Display the window.
        frame.setPreferredSize(new Dimension(800, 500));

        //Display the window.
        frame.pack();
        frame.setVisible(true);

        updateNameButton = new JButton("Update Customer Name");
        updateNameButton.setVerticalTextPosition(AbstractButton.CENTER);
        updateNameButton.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        updateNameButton.setActionCommand("NAME");

        updateAgeButton = new JButton("Update Customer Age");
        updateAgeButton.setVerticalTextPosition(AbstractButton.CENTER);
        updateAgeButton.setHorizontalTextPosition(AbstractButton.LEADING);
        updateAgeButton.setActionCommand("AGE");

        updateGenderButton = new JButton("Update Customer Gender");
        updateGenderButton.setVerticalTextPosition(AbstractButton.CENTER);
        updateGenderButton.setHorizontalTextPosition(AbstractButton.LEADING);
        updateGenderButton.setActionCommand("GENDER");

        updateHotelPhoneButton = new JButton("Update Hotel Phone");
        updateHotelPhoneButton.setVerticalTextPosition(AbstractButton.CENTER);
        updateHotelPhoneButton.setHorizontalTextPosition(AbstractButton.LEADING);
        updateHotelPhoneButton.setActionCommand("PHONE");


        updateNameButton.addActionListener(this);
        updateAgeButton.addActionListener(this);
        updateGenderButton.addActionListener(this);
        updateHotelPhoneButton.addActionListener(this);

        //Add Components to this container, using the default FlowLayout.
        add(updateNameButton);
        add(updateAgeButton);
        add(updateGenderButton);
        add(updateHotelPhoneButton);

        this.setOpaque(true); //content panes must be opaque
        frame.setContentPane(this);
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void addNameFields() {
        //Create everything.
        cIDField = new JTextField(20);
        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);

        cIDField.setActionCommand("SUBMITNAME");
        firstNameField.setActionCommand("SUBMITNAME");
        lastNameField.setActionCommand("SUBMITNAME");

        cIDField.addActionListener(this);
        firstNameField.addActionListener(this);
        lastNameField.addActionListener(this);

        cIDLabel = new JLabel("Enter C_ID:");
        cIDLabel.setLabelFor(cIDField);
        firstNameLabel = new JLabel("New First Name:");
        firstNameLabel.setLabelFor(firstNameField);
        lastNameLabel = new JLabel("New Last Name:");
        lastNameLabel.setLabelFor(lastNameField);

        //Lay out everything.
        JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));

        firstSubmit = new JButton("Submit Changes");
        firstSubmit.setActionCommand("SUBMITNAME");
        firstSubmit.addActionListener(this);

        textPane.add(firstSubmit);
        textPane.add(cIDLabel);
        textPane.add(cIDField);
        textPane.add(firstNameLabel);
        textPane.add(firstNameField);
        textPane.add(lastNameLabel);
        textPane.add(lastNameField);

        add(textPane);
        frame.revalidate();
        frame.repaint();
    }

    public void addGenderFields() {
        //Create everything.
        cIDField = new JTextField(20);
        genderField = new JTextField(20);

        cIDField.setActionCommand("SUBMITGENDER");
        genderField.setActionCommand("SUBMITGENDER");

        cIDField.addActionListener(this);
        genderField.addActionListener(this);

        cIDLabel = new JLabel("Enter C_ID:");
        cIDLabel.setLabelFor(cIDField);
        genderLabel = new JLabel("New Gender:");
        genderLabel.setLabelFor(genderField);

        //Lay out everything.
        JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));

        genderSubmit = new JButton("Submit Changes");
        genderSubmit.setActionCommand("SUBMITGENDER");
        genderSubmit.addActionListener(this);

        textPane.add(genderSubmit);
        textPane.add(cIDLabel);
        textPane.add(cIDField);
        textPane.add(genderLabel);
        textPane.add(genderField);

        add(textPane);
        frame.revalidate();
        frame.repaint();
    }

    public void addAgeFields() {
        //Create everything.
        cIDField = new JTextField(20);
        ageField = new JTextField(20);

        cIDField.setActionCommand("SUBMITAGE");
        ageField.setActionCommand("SUBMITAGE");

        cIDField.addActionListener(this);
        ageField.addActionListener(this);

        cIDLabel = new JLabel("Enter C_ID:");
        cIDLabel.setLabelFor(cIDField);
        ageLabel = new JLabel("New Age:");
        ageLabel.setLabelFor(ageField);

        //Lay out everything.
        JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));

        ageSubmit = new JButton("Submit Changes");
        ageSubmit.setActionCommand("SUBMITAGE");
        ageSubmit.addActionListener(this);

        textPane.add(ageSubmit);
        textPane.add(cIDLabel);
        textPane.add(cIDField);
        textPane.add(ageLabel);
        textPane.add(ageField);

        add(textPane);
        frame.revalidate();
        frame.repaint();
    }

    public void addPhoneFields() {
        //Create everything.
        hotelNameField = new JTextField(20);
        branchIDField = new JTextField(20);
        phoneField = new JTextField(20);

        hotelNameField.setActionCommand("SUBMITPHONE");
        branchIDField.setActionCommand("SUBMITPHONE");
        phoneField.setActionCommand("SUBMITPHONE");

        hotelNameField.addActionListener(this);
        branchIDField.addActionListener(this);
        phoneField.addActionListener(this);

        hotelNameLabel = new JLabel("Enter Hotel Name:");
        hotelNameLabel.setLabelFor(hotelNameField);
        branchIDLabel = new JLabel("Enter Hotel Branch ID:");
        branchIDLabel.setLabelFor(branchIDField);
        phoneLabel = new JLabel("New Phone:");
        phoneLabel.setLabelFor(phoneField);

        //Lay out everything.
        JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));

        phoneSubmit = new JButton("Submit Changes");
        phoneSubmit.setActionCommand("SUBMITPHONE");
        phoneSubmit.addActionListener(this);

        textPane.add(phoneSubmit);
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

    public void resetFields() {
        if(firstSubmit != null) { firstSubmit.setVisible(false); }
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
        if(ageSubmit != null) { ageSubmit.setVisible(false); }
        if(hotelNameLabel != null) { hotelNameLabel.setVisible(false); }
        if(hotelNameField != null) { hotelNameField.setVisible(false); }
        if(branchIDLabel != null) { branchIDLabel.setVisible(false); }
        if(branchIDField != null) { branchIDField.setVisible(false); }
        if(phoneLabel != null) { phoneLabel.setVisible(false); }
        if(phoneField != null) { phoneField.setVisible(false); }
        if(lastSubmit != null) { lastSubmit.setVisible(false); }
        if(typeLabel != null) { typeLabel.setVisible(false); }
        if(typeField != null) { typeField.setVisible(false); }
        if(capacityLabel != null) { capacityLabel.setVisible(false); }
        if(capacityField != null) { capacityField.setVisible(false); }
        if(quantityLabel != null) { quantityLabel.setVisible(false); }
        if(quantityField != null) { quantityField.setVisible(false); }

    }

    // Updates Customer first name attribute given CID:
    public void updateCustomerFirstName(Connection connection) throws SQLException {

        DatabaseMetaData dmd = connection.getMetaData();
        ResultSet rs = dmd.getTables(null, null, "CUSTOMER", null);

        if (rs.next()){

            String sql = "UPDATE Customer SET first_name = ? WHERE c_ID = ?";
            PreparedStatement pStmt = connection.prepareStatement(sql);
            pStmt.clearParameters();

            pStmt.setString(1, firstNameField.getText());
            pStmt.setInt(2, Integer.parseInt(cIDField.getText()));

            try {
                pStmt.executeUpdate();
                JOptionPane.showMessageDialog(frame,
                        "The customer name has been successfully updated.",
                        "Customer Name Changed",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            catch (SQLException e) {
                JOptionPane.showMessageDialog(frame,
                        "The customer name was unable to be changed.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
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
    public void updateCustomerLastName(Connection connection) throws SQLException {

        DatabaseMetaData dmd = connection.getMetaData();
        ResultSet rs = dmd.getTables(null, null, "CUSTOMER", null);

        if (rs.next()){

            String sql = "UPDATE Customer SET last_name = ? WHERE c_ID = ?";
            PreparedStatement pStmt = connection.prepareStatement(sql);
            pStmt.clearParameters();

            pStmt.setString(1, lastNameField.getText());
            pStmt.setInt(2, Integer.parseInt(cIDField.getText()));

            try { pStmt.executeUpdate(); }
            catch (SQLException e) {
                JOptionPane.showMessageDialog(frame,
                        "The customer name was unable to be changed.\n",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
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
    public void updateCustomerGender(Connection connection) throws SQLException {

        DatabaseMetaData dmd = connection.getMetaData();
        ResultSet rs = dmd.getTables(null, null, "CUSTOMER", null);

        if (rs.next()){

            String sql = "UPDATE Customer SET gender = ? WHERE c_ID = ?";
            PreparedStatement pStmt = connection.prepareStatement(sql);
            pStmt.clearParameters();

            pStmt.setString(1, genderField.getText());
            pStmt.setInt(2, Integer.parseInt(cIDField.getText()));

            try { pStmt.executeUpdate(); }
            catch (SQLException e) {
                JOptionPane.showMessageDialog(frame,
                        "The customer name was unable to be changed.\n",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
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
    public void updateCustomerAge(Connection connection) throws SQLException {

        DatabaseMetaData dmd = connection.getMetaData();
        ResultSet rs = dmd.getTables(null, null, "CUSTOMER", null);

        if (rs.next()){

            String sql = "UPDATE Customer SET age = ? WHERE c_ID = ?";
            PreparedStatement pStmt = connection.prepareStatement(sql);
            pStmt.clearParameters();

            pStmt.setString(1, ageField.getText());
            pStmt.setInt(2, Integer.parseInt(cIDField.getText()));

            try { pStmt.executeUpdate(); }
            catch (SQLException e) {
                JOptionPane.showMessageDialog(frame,
                        "The customer age was unable to be changed.\n",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            finally {
                pStmt.close();
                rs.close();
            }
        }
        else {
            System.out.println("ERROR: Error loading CUSTOMER Table.");
        }
    }

    // Updates Hotel phone number attribute given hotel name and branch ID:
    public void updateHotelPhone(Connection connection) throws SQLException {

        DatabaseMetaData dmd = connection.getMetaData();
        ResultSet rs = dmd.getTables(null, null, "HOTEL", null);

        if (rs.next()){

            String sql = "UPDATE Hotel SET phone = ? WHERE hotel_name = ? AND branch_ID = ?";
            PreparedStatement pStmt = connection.prepareStatement(sql);
            pStmt.clearParameters();

            pStmt.setString(1, phoneField.getText());
            pStmt.setString(2, hotelNameField.getText());
            pStmt.setInt(3, Integer.parseInt(branchIDField.getText()));

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

    public void actionPerformed(ActionEvent e) {

        if(queryResult != null) {
            queryResult.setText("");
            frame.add(queryResult);
            frame.revalidate();
            frame.repaint();
        }
        if ("NAME".equals(e.getActionCommand())) {
            resetFields();
            addNameFields();

        } else if("AGE".equals(e.getActionCommand())) {
            resetFields();
            addAgeFields();

        } else if("GENDER".equals(e.getActionCommand())) {
            resetFields();
            addGenderFields();

        } else if("PHONE".equals(e.getActionCommand())) {
            resetFields();
            addPhoneFields();

        }  else if("SUBMITNAME".equals(e.getActionCommand())) {
            try {
                updateCustomerFirstName(connection);
                updateCustomerLastName(connection);
            } catch(SQLException s) {
                s.printStackTrace();
            }
        } else if("SUBMITAGE".equals(e.getActionCommand())) {
            try {
                updateCustomerAge(connection);
                JOptionPane.showMessageDialog(frame,
                        "The customer age has been successfully updated.",
                        "Customer Age Changed",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch(SQLException s) {
                JOptionPane.showMessageDialog(frame,
                        "The customer age was unable to be updated.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if("SUBMITGENDER".equals(e.getActionCommand())) {
            try {
                updateCustomerGender(connection);
                JOptionPane.showMessageDialog(frame,
                        "The customer gender has been successfully updated.",
                        "Customer Gender Changed",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch(SQLException s) {
                JOptionPane.showMessageDialog(frame,
                        "The customer gender was unable to be updated.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if("SUBMITPHONE".equals(e.getActionCommand())) {
            try {
                updateHotelPhone(connection);
                JOptionPane.showMessageDialog(frame,
                        "The hotel phone has been successfully updated.",
                        "Hotel Phone Changed",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch(SQLException s) {
                JOptionPane.showMessageDialog(frame,
                        "The hotel phone was unable to be updated.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}