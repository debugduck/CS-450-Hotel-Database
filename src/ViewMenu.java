import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * ButtonDemo.java requires the following files:
 *   images/right.gif
 *   images/middle.gif
 *   images/left.gif
 */
public class ViewMenu extends JPanel implements ActionListener {

    JButton customerButton;
    JButton roomButton;
    JButton hotelButton;
    JButton reservationButton;
    JButton hotelAddressButton;
    JButton hotelRoomButton;
    JButton bookingButton;
    JButton informationButton;
    JLabel queryResult;
    JFrame frame;

    Connection connection;
    HotelDatabase db;

    public ViewMenu() {

        db = new HotelDatabase();
        //Create and set up the window.
        this.frame = new JFrame("SimpleTravel - View Tables");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Display the window.
        frame.setPreferredSize(new Dimension(800, 500));

        //Display the window.
        frame.pack();
        frame.setVisible(true);

        customerButton = new JButton("Customers");
        customerButton.setVerticalTextPosition(AbstractButton.CENTER);
        customerButton.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        customerButton.setActionCommand("CUSTOMERS");

        roomButton = new JButton("Rooms");
        roomButton.setVerticalTextPosition(AbstractButton.CENTER);
        roomButton.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        roomButton.setActionCommand("ROOMS");

        hotelButton = new JButton("Hotels");
        hotelButton.setVerticalTextPosition(AbstractButton.CENTER);
        hotelButton.setHorizontalTextPosition(AbstractButton.LEADING);
        hotelButton.setActionCommand("HOTELS");

        reservationButton = new JButton("Reservations");
        reservationButton.setVerticalTextPosition(AbstractButton.CENTER);
        reservationButton.setHorizontalTextPosition(AbstractButton.LEADING);
        reservationButton.setActionCommand("RESERVATIONS");

        hotelAddressButton = new JButton("Hotel Addresses");
        hotelAddressButton.setVerticalTextPosition(AbstractButton.CENTER);
        hotelAddressButton.setHorizontalTextPosition(AbstractButton.LEADING);
        hotelAddressButton.setActionCommand("ADDRESSES");

        hotelRoomButton = new JButton("Hotel Rooms");
        hotelRoomButton.setVerticalTextPosition(AbstractButton.CENTER);
        hotelRoomButton.setHorizontalTextPosition(AbstractButton.LEADING);
        hotelRoomButton.setActionCommand("HOTELROOMS");

        bookingButton = new JButton("Bookings");
        bookingButton.setVerticalTextPosition(AbstractButton.CENTER);
        bookingButton.setHorizontalTextPosition(AbstractButton.LEADING);
        bookingButton.setActionCommand("BOOKINGS");

        informationButton = new JButton("Information");
        informationButton.setVerticalTextPosition(AbstractButton.CENTER);
        informationButton.setHorizontalTextPosition(AbstractButton.LEADING);
        informationButton.setActionCommand("INFO");


        //Listen for actions on buttons 1 and 3.
        customerButton.addActionListener(this);
        roomButton.addActionListener(this);
        hotelButton.addActionListener(this);
        reservationButton.addActionListener(this);
        hotelAddressButton.addActionListener(this);
        hotelRoomButton.addActionListener(this);
        bookingButton.addActionListener(this);
        informationButton.addActionListener(this);

        //Add Components to this container, using the default FlowLayout.
        add(customerButton);
        add(roomButton);
        add(hotelButton);
        add(reservationButton);
        add(hotelAddressButton);
        add(hotelRoomButton);
        add(bookingButton);
        add(informationButton);

        this.setOpaque(true); //content panes must be opaque
        frame.setContentPane(this);
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void actionPerformed(ActionEvent e) {

        if(queryResult != null) {
            queryResult.setText("");
            frame.add(queryResult);
            frame.revalidate();
            frame.repaint();
        }
        if ("HOTELS".equals(e.getActionCommand())) {
            try {
                printTable(0);
            } catch (SQLException s) {
                s.printStackTrace();
            }
        } else if("ROOMS".equals(e.getActionCommand())) {
            try {
                printTable(1);
            } catch (SQLException s) {
                s.printStackTrace();
            }
        } else if("CUSTOMERS".equals(e.getActionCommand())) {
            try {
                printTable(2);
            } catch (SQLException s) {
                s.printStackTrace();
            }
        } else if("RESERVATIONS".equals(e.getActionCommand())) {
            try {
                printTable(3);
            } catch (SQLException s) {
                s.printStackTrace();
            }
        } else if("ADDRESSES".equals(e.getActionCommand())) {
            try {
                printTable(4);
            } catch (SQLException s) {
                s.printStackTrace();
            }
        } else if("HOTELROOMS".equals(e.getActionCommand())) {
            try {
                printTable(5);
            } catch (SQLException s) {
                s.printStackTrace();
            }
        } else if("BOOKINGS".equals(e.getActionCommand())) {
            try {
                printTable(6);
            } catch (SQLException s) {
                s.printStackTrace();
            }
        } else if("INFO".equals(e.getActionCommand())) {
            try {
                printTable(7);
            } catch (SQLException s) {
                s.printStackTrace();
            }
        }
    }

    public void printTable(int tableIndex) throws SQLException {
        // Retrieve all the rows from that table:
        ResultSet rs = null;
        String sql = "SELECT * FROM " + db.ALL.get(tableIndex).get(0);
        PreparedStatement pStmt = connection.prepareStatement(sql);
        this.queryResult = new JLabel("Results");

        try {

            rs = pStmt.executeQuery();

            String result = "<html><br/><br/>Results:<br/><br/>";
            if(rs == null) { result += "There are no rows in this table."; }
            while ( rs != null && rs.next()) {

                for (int i = 1; i < db.ALL.get(tableIndex).size(); i++){

                    // Need to make distinction between values that are Strings, Ints, or Dates:
                    if (db.NUMBERS.contains(db.ALL.get(tableIndex).get(i))){
                        result += db.ALL.get(tableIndex).get(i);
                        result += ": ";
                        result += String.valueOf((rs.getInt(db.ALL.get(tableIndex).get(i))));

                    }
                    else if (db.DATES.contains(db.ALL.get(tableIndex).get(i))){
                        result += db.ALL.get(tableIndex).get(i);
                        result += ": ";
                        result += String.valueOf((rs.getDate(db.ALL.get(tableIndex).get(i))));
                    }
                    else {
                        result += db.ALL.get(tableIndex).get(i);
                        result += ": ";
                        result += String.valueOf((rs.getString(db.ALL.get(tableIndex).get(i))));
                    }
                    result += " <br/>";
                }
                result += "------------------------------<br/>";
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

}