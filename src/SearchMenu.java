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
public class SearchMenu extends JPanel implements ActionListener {

    JButton CResButton;
    JButton HResButton;
    JButton typeButton;
    JButton availButton;
    JButton cityButton;
    JButton stateButton;
    JButton zipButton;
    JButton CResSubmit;
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

    public SearchMenu() {

        db = new HotelDatabase();
        //Create and set up the window.
        this.frame = new JFrame("SimpleTravel - Search Menu");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Display the window.
        frame.setPreferredSize(new Dimension(800, 500));

        //Display the window.
        frame.pack();
        frame.setVisible(true);

        CResButton = new JButton("Search Customer Reservation");
        CResButton.setVerticalTextPosition(AbstractButton.CENTER);
        CResButton.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        CResButton.setActionCommand("CRES");

        HResButton = new JButton("Search Hotel Reservations");
        HResButton.setVerticalTextPosition(AbstractButton.CENTER);
        HResButton.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        HResButton.setActionCommand("HRES");

        typeButton = new JButton("Search Type Availability");
        typeButton.setVerticalTextPosition(AbstractButton.CENTER);
        typeButton.setHorizontalTextPosition(AbstractButton.LEADING);
        typeButton.setActionCommand("TYPE");

        availButton = new JButton("Search All Availability");
        availButton.setVerticalTextPosition(AbstractButton.CENTER);
        availButton.setHorizontalTextPosition(AbstractButton.LEADING);
        availButton.setActionCommand("AVAILABILITY");

        cityButton = new JButton("Search City");
        cityButton.setVerticalTextPosition(AbstractButton.CENTER);
        cityButton.setHorizontalTextPosition(AbstractButton.LEADING);
        cityButton.setActionCommand("CITY");

        stateButton = new JButton("Search State");
        stateButton.setVerticalTextPosition(AbstractButton.CENTER);
        stateButton.setHorizontalTextPosition(AbstractButton.LEADING);
        stateButton.setActionCommand("STATE");

        zipButton = new JButton("Search Zipcode");
        zipButton.setVerticalTextPosition(AbstractButton.CENTER);
        zipButton.setHorizontalTextPosition(AbstractButton.LEADING);
        zipButton.setActionCommand("ZIP");


        CResButton.addActionListener(this);
        HResButton.addActionListener(this);
        typeButton.addActionListener(this);
        availButton.addActionListener(this);
        cityButton.addActionListener(this);
        stateButton.addActionListener(this);
        zipButton.addActionListener(this);

        //Add Components to this container, using the default FlowLayout.
        add(CResButton);
        add(HResButton);
        add(typeButton);
        add(availButton);
        add(cityButton);
        add(stateButton);
        add(zipButton);

        this.setOpaque(true); //content panes must be opaque
        frame.setContentPane(this);
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void addCustomerFields() {
        //Create everything.
        cIDField = new JTextField(20);
        cIDField.setActionCommand("CRES");
        cIDField.addActionListener(this);
        cIDLabel = new JLabel("Enter C_ID:");
        cIDLabel.setLabelFor(cIDField);

        //Lay out everything.
        JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));

        CResSubmit = new JButton("Submit Changes");
        CResSubmit.setActionCommand("CRESSUBMIT");
        CResSubmit.addActionListener(this);

        textPane.add(CResSubmit);
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
        capacityField = new JTextField(20);
        quantityField = new JTextField(20);

        typeField.setActionCommand("SUBMITROOM");
        capacityField.setActionCommand("SUBMITROOM");
        quantityField.setActionCommand("SUBMITROOM");

        typeField.addActionListener(this);
        capacityField.addActionListener(this);
        quantityField.addActionListener(this);

        typeLabel = new JLabel("Enter Room Type:");
        typeLabel.setLabelFor(typeField);
        capacityLabel = new JLabel("Enter Room Capacity:");
        capacityLabel.setLabelFor(capacityField);
        quantityLabel = new JLabel("Enter Room Quantity:");
        quantityLabel.setLabelFor(quantityField);

        //Lay out everything.
        JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));

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

        hotelAddressSubmit = new JButton("Search");
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
        if(stateButton != null) { stateButton.setVisible(false); }
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
        if(typeLabel != null) { typeLabel.setVisible(false); }
        if(typeField != null) { typeField.setVisible(false); }
        if(capacityLabel != null) { capacityLabel.setVisible(false); }
        if(capacityField != null) { capacityField.setVisible(false); }
        if(quantityLabel != null) { quantityLabel.setVisible(false); }
        if(quantityField != null) { quantityField.setVisible(false); }

    }

    // Finds the available reservation(s) for a customer given their CID:
    public void searchCustomerReservations(Connection connection) throws SQLException {

        ResultSet rs = null;
        String sql = "SELECT res_num FROM Reservation WHERE c_ID = ?";
        PreparedStatement pStmt = connection.prepareStatement(sql);
        pStmt.clearParameters();

        pStmt.setInt(1, Integer.parseInt(cIDField.getText()));

        try {

            rs = pStmt.executeQuery();
            this.queryResult = new JLabel("Results");
            String result = "<html><br/><br/>Results:<br/><br/>";
            if(!rs.next()) { result += "There are no rows in this table."; }

            while (rs.next()) {
                result += "Reservation number: " + rs.getInt(1) + "\n";
            }
            result += "</html>";
            queryResult.setText(result);
            frame.add(queryResult);
            frame.revalidate();
            frame.repaint();
        }
        catch (SQLException e) { e.printStackTrace(); }
        finally {
            pStmt.close();
            if(rs != null) { rs.close(); }
        }
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

        } else if("CRES".equals(e.getActionCommand())) {
            resetFields();
            addCustomerFields();

        } else if("RESERVATION".equals(e.getActionCommand())) {
            //addReservationFields();

        } else if("ADDRESSES".equals(e.getActionCommand())) {
            resetFields();
            addAddressFields();

        } else if("CRESSUBMIT".equals(e.getActionCommand())) {
            try {
                searchCustomerReservations(connection);
            } catch(SQLException s) {
                s.printStackTrace();
            }
        }
    }
}