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
    JButton generalButton;
    JButton availButton;
    JButton cityButton;
    JButton stateButton;
    JButton zipButton;
    JButton CResSubmit;
    JButton generalSubmit;
    JButton reservationSubmit;

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

        generalButton = new JButton("Search General Availability");
        generalButton.setVerticalTextPosition(AbstractButton.CENTER);
        generalButton.setHorizontalTextPosition(AbstractButton.LEADING);
        generalButton.setActionCommand("GENERAL");

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
        generalButton.addActionListener(this);
        availButton.addActionListener(this);
        cityButton.addActionListener(this);
        stateButton.addActionListener(this);
        zipButton.addActionListener(this);

        //Add Components to this container, using the default FlowLayout.
        add(CResButton);
        add(HResButton);
        add(generalButton);
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

        CResSubmit = new JButton("Search");
        CResSubmit.setActionCommand("CRESSUBMIT");
        CResSubmit.addActionListener(this);

        textPane.add(cIDLabel);
        textPane.add(cIDField);
        textPane.add(CResSubmit);

        add(textPane);
        frame.revalidate();
        frame.repaint();
    }

    public void addGeneralSearchFields() {
        cityField = new JTextField(20);
        partySizeField = new JTextField(20);
        checkInField = new JTextField(20);
        checkOutField = new JTextField(20);

        cityField.setActionCommand("GENERALSUBMIT");
        partySizeField.setActionCommand("GENERALSUBMIT");
        checkInField.setActionCommand("GENERALSUBMIT");
        checkOutField.setActionCommand("GENERALSUBMIT");

        cityField.addActionListener(this);
        partySizeField.addActionListener(this);
        checkInField.addActionListener(this);
        checkOutField.addActionListener(this);

        cityLabel = new JLabel("Enter City:");
        cityLabel.setLabelFor(cityField);
        partySizeLabel = new JLabel("Enter Party Size:");
        partySizeLabel.setLabelFor(partySizeField);
        checkInLabel = new JLabel("Enter Check In Date:");
        checkInLabel.setLabelFor(checkInField);
        checkOutLabel = new JLabel("Enter Check Out Date:");
        checkOutLabel.setLabelFor(checkOutField);

        //Lay out everything.
        JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));

        generalSubmit = new JButton("Search");
        generalSubmit.setActionCommand("GENERALSUBMIT");
        generalSubmit.addActionListener(this);

        textPane.add(cityLabel);
        textPane.add(cityField);
        textPane.add(partySizeLabel);
        textPane.add(partySizeField);
        textPane.add(checkInLabel);
        textPane.add(checkInField);
        textPane.add(checkOutLabel);
        textPane.add(checkOutField);
        textPane.add(generalSubmit);

        add(textPane);
        frame.revalidate();
        frame.repaint();
    }

    public void resetFields() {
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
        if(generalSubmit != null) { generalSubmit.setVisible(false); }
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
        if(checkInField != null) { checkInField.setVisible(false); }
        if(checkInLabel != null) { checkInLabel.setVisible(false); }
        if(checkOutLabel != null) { checkOutLabel.setVisible(false); }
        if(checkOutField != null) { checkOutField.setVisible(false); }
        if(costLabel != null) { costLabel.setVisible(false); }
        if(costField != null) { costField.setVisible(false); }
        if(reservationSubmit != null) { reservationSubmit.setVisible(false); }
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
        if(CResSubmit != null) { CResSubmit.setVisible(false); }
        if(queryResult != null) { queryResult.setText(""); }

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

    // Finds the availability of hotels in a certain area (city):
    public void generalSearch(Connection connection) throws SQLException {

        ResultSet rs = null;
        String sql = "SELECT DISTINCT I1.hotel_name, I1.branch_ID, I1.type, R.capacity "
                     + "FROM Information I1, Room R, Hotel_Address HA "
                     + "WHERE I1.hotel_name = R.hotel_name AND I1.hotel_name = HA.hotel_name "
                     + "AND I1.branch_ID = R.branch_ID AND I1.branch_ID = HA.branch_ID "
                     + "AND I1.type = R.type "
                     + "AND HA.city = ? "
                     + "AND R.capacity >= ? "

                     + "AND NOT EXISTS ( "
                     + "SELECT I2.type "
                     + "FROM Information I2 "
                     + "WHERE I1.type = I2.type "
                     + "AND I2.date_from >= to_date(?, 'YYYY-MM-DD') "
                     + "AND I2.date_to <= to_date(?, 'YYYY-MM-DD') "
                     + "AND I2.num_avail = 0)";

        PreparedStatement pStmt = connection.prepareStatement(sql);
        pStmt.clearParameters();

        pStmt.setString(1, cityField.getText());
        pStmt.setInt(2, Integer.parseInt(partySizeField.getText()));
        pStmt.setString(3, checkInField.getText());
        pStmt.setString(4, checkOutField.getText());

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
        if ("GENERAL".equals(e.getActionCommand())) {
            resetFields();
            addGeneralSearchFields();

        } else if("CRES".equals(e.getActionCommand())) {
            resetFields();
            addCustomerFields();

        } else if("CRESSUBMIT".equals(e.getActionCommand())) {
            try {
                searchCustomerReservations(connection);
            } catch(SQLException s) {
                s.printStackTrace();
            }
        } else if("GENERALSUBMIT".equals(e.getActionCommand())) {
            try {
                generalSearch(connection);
            } catch(SQLException s) {
                s.printStackTrace();
            }
        }
    }
}
