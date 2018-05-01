import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

/* PasswordDemo.java requires no other files. */

public class SignIn extends JPanel implements ActionListener {
    private static String SUBMIT = "submit";
    private static String HELP = "help";
    private static String ADD = "add";
    private static String VIEW = "view";
    private static String SEARCH = "search";
    private static String DELETE = "delete";
    private static String UPDATE = "update";

    private JFrame controllingFrame; //needed for dialogs
    private JTextField usernameField;
    private JPasswordField passwordField;
    private HotelDatabase db;
    private Connection connection;


    public SignIn(JFrame f) {
        //Use the default FlowLayout.
        controllingFrame = f;

        //Create everything.
        usernameField = new JTextField(20);
        usernameField.setActionCommand(SUBMIT);
        usernameField.addActionListener(this);
        passwordField = new JPasswordField(10);
        passwordField.setActionCommand(SUBMIT);
        passwordField.addActionListener(this);
        db = new HotelDatabase();

        JLabel passwordLabel = new JLabel("Enter the password: ");
        passwordLabel.setLabelFor(passwordField);

        JLabel usernameLabel = new JLabel("Enter the username: ");
        usernameLabel.setLabelFor(usernameField);

        JComponent buttonPane = createButtonPanel();

        //Lay out everything.
        JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));

        textPane.add(usernameLabel);
        textPane.add(usernameField);

        textPane.add(passwordLabel);
        textPane.add(passwordField);

        add(textPane);
        add(buttonPane);
    }

    protected JComponent createButtonPanel() {
        JPanel p = new JPanel(new GridLayout(0,1));
        JButton submitButton = new JButton("Sign In");
        JButton helpButton = new JButton("Help");

        submitButton.setActionCommand(SUBMIT);
        helpButton.setActionCommand(HELP);
        submitButton.addActionListener(this);
        helpButton.addActionListener(this);

        p.add(submitButton);
        p.add(helpButton);

        return p;
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (SUBMIT.equals(cmd)) { //Process the password.
            String username = usernameField.getText();
            String password = passwordField.getText();
            connection = db.getConnection(username, password);
            if (connection != null) {
                MainMenu mm = new MainMenu(controllingFrame);
                mm.setConnection(connection);
            } else {
                JOptionPane.showMessageDialog(controllingFrame,
                        "Invalid password. Try again.",
                        "Error Message",
                        JOptionPane.ERROR_MESSAGE);
            }

            passwordField.selectAll();
            resetFocus();
        } else { //The user has asked for help.
            JOptionPane.showMessageDialog(controllingFrame,
                    "You can get the password by searching this example's\n"
                            + "source code for the string \"correctPassword\".\n"
                            + "Or look at the section How to Use Password Fields in\n"
                            + "the components section of The Java Tutorial.");
        }
    }

    protected void showMenu(Connection connection) {
        //Create everything.
        //usernameField.setActionCommand(SUBMIT);
        //controllingFrame = new JFrame();
        //JLabel passwordLabel = new JLabel("Enter the password: ");
        //passwordLabel.setLabelFor(passwordField);

        //JLabel usernameLabel = new JLabel("Enter the username: ");
        //usernameLabel.setLabelFor(usernameField);

        JComponent buttonPane = createMainMenuButtons();

        add(buttonPane);

    }

    protected JComponent createMainMenuButtons() {
        JPanel p = new JPanel(new GridLayout(0,1));
        JButton viewTablesButton = new JButton("View Tables");
        JButton addRecordsButton = new JButton("Add Records");
        JButton updateRecordsButton = new JButton("Update Records");
        JButton deleteRecordsButton = new JButton("Delete Records");
        JButton searchRecordsButton = new JButton("Search Records");

        viewTablesButton.setActionCommand(VIEW);
        addRecordsButton.setActionCommand(ADD);
        updateRecordsButton.setActionCommand(UPDATE);
        deleteRecordsButton.setActionCommand(DELETE);
        searchRecordsButton.setActionCommand(SEARCH);
        viewTablesButton.addActionListener(this);
        addRecordsButton.addActionListener(this);
        updateRecordsButton.addActionListener(this);
        deleteRecordsButton.addActionListener(this);
        searchRecordsButton.addActionListener(this);

        p.add(viewTablesButton);
        p.add(addRecordsButton);
        p.add(updateRecordsButton);
        p.add(deleteRecordsButton);
        p.add(searchRecordsButton);

        return p;
    }

    //Must be called from the event dispatch thread.
    protected void resetFocus() {
        passwordField.requestFocusInWindow();
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("SimpleTravel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        final SignIn newContentPane = new SignIn(frame);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Make sure the focus goes to the right component
        //whenever the frame is initially given the focus.
        frame.addWindowListener(new WindowAdapter() {
            public void windowActivated(WindowEvent e) {
                newContentPane.resetFocus();
            }
        });

        //Display the window.
        frame.setPreferredSize(new Dimension(800, 500));
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }
}