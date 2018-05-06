import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;

/*
 * ButtonDemo.java requires the following files:
 *   images/right.gif
 *   images/middle.gif
 *   images/left.gif
 */
public class MainMenu extends JPanel implements ActionListener {

    JButton viewTablesButton;
    JButton addRecordsButton;
    JButton updateRecordsButton;
    JButton deleteRecordsButton;
    JButton searchRecordsButton;
    Connection connection;
    HotelDatabase db;

    public MainMenu() {

        db = new HotelDatabase();
        //Create and set up the window.
        JFrame frame = new JFrame("SimpleTravel - Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Display the window.
        frame.setPreferredSize(new Dimension(800, 500));

        //Display the window.
        frame.pack();
        frame.setVisible(true);

        viewTablesButton = new JButton("View Table");
        viewTablesButton.setVerticalTextPosition(AbstractButton.CENTER);
        viewTablesButton.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        //b1.setMnemonic(KeyEvent.VK_D);
        viewTablesButton.setActionCommand("VIEW");

        addRecordsButton = new JButton("Add Records");
        addRecordsButton.setVerticalTextPosition(AbstractButton.CENTER);
        addRecordsButton.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        //b1.setMnemonic(KeyEvent.VK_D);
        addRecordsButton.setActionCommand("ADD");

        updateRecordsButton = new JButton("Update Records");
        updateRecordsButton.setVerticalTextPosition(AbstractButton.CENTER);
        updateRecordsButton.setHorizontalTextPosition(AbstractButton.LEADING);
        updateRecordsButton.setActionCommand("UPDATE");

        deleteRecordsButton = new JButton("Delete Records");
        deleteRecordsButton.setVerticalTextPosition(AbstractButton.CENTER);
        deleteRecordsButton.setHorizontalTextPosition(AbstractButton.LEADING);
        deleteRecordsButton.setActionCommand("DELETE");

        searchRecordsButton = new JButton("Search Records");
        searchRecordsButton.setVerticalTextPosition(AbstractButton.CENTER);
        searchRecordsButton.setHorizontalTextPosition(AbstractButton.LEADING);
        searchRecordsButton.setActionCommand("SEARCH");

        //Listen for actions on buttons 1 and 3.
        viewTablesButton.addActionListener(this);
        addRecordsButton.addActionListener(this);
        updateRecordsButton.addActionListener(this);
        deleteRecordsButton.addActionListener(this);
        searchRecordsButton.addActionListener(this);


        //Add Components to this container, using the default FlowLayout.
        add(viewTablesButton);
        add(addRecordsButton);
        add(updateRecordsButton);
        add(deleteRecordsButton);
        add(searchRecordsButton);

        this.setOpaque(true); //content panes must be opaque
        frame.setContentPane(this);
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void actionPerformed(ActionEvent e) {
        if ("VIEW".equals(e.getActionCommand())) {
            ViewMenu vm = new ViewMenu();
            vm.setConnection(connection);
        } else if("ADD".equals(e.getActionCommand())){
            AddMenu am = new AddMenu();
            am.setConnection(connection);
        } else if("UPDATE".equals(e.getActionCommand())) {
            UpdateMenu um = new UpdateMenu();
            um.setConnection(connection);
        } else if("DELETE".equals(e.getActionCommand())) {
            DeleteMenu dm = new DeleteMenu();
            dm.setConnection(connection);
        } else if("SEARCH".equals(e.getActionCommand())) {
            SearchMenu sm = new SearchMenu();
            sm.setConnection(connection);
        }
    }


}