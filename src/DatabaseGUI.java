import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

/* PasswordDemo.java requires no other files. */

public class DatabaseGUI extends JPanel implements ActionListener {
    private static String SUBMIT = "submit";
    private static String HELP = "help";

    private JFrame controllingFrame; //needed for dialogs
    private JTextField usernameField;
    private JPasswordField passwordField;


    public DatabaseGUI(JFrame f) {
        //Use the default FlowLayout.
        controllingFrame = f;

        //Create everything.
        usernameField = new JTextField(20);
        usernameField.setActionCommand(SUBMIT);
        usernameField.addActionListener(this);
        passwordField = new JPasswordField(10);
        passwordField.setActionCommand(SUBMIT);
        passwordField.addActionListener(this);

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
            char[] input = passwordField.getPassword();
            if (isPasswordCorrect(input)) {
                JOptionPane.showMessageDialog(controllingFrame,
                        "Success! You typed the right password.");
            } else {
                JOptionPane.showMessageDialog(controllingFrame,
                        "Invalid password. Try again.",
                        "Error Message",
                        JOptionPane.ERROR_MESSAGE);
            }

            //Zero out the possible password, for security.
            Arrays.fill(input, '0');

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

    /**
     * Checks the passed-in array against the correct password.
     * After this method returns, you should invoke eraseArray
     * on the passed-in array.
     */
    private static boolean isPasswordCorrect(char[] input) {
        boolean isCorrect = true;
        char[] correctPassword = { 'b', 'u', 'g', 'a', 'b', 'o', 'o' };

        if (input.length != correctPassword.length) {
            isCorrect = false;
        } else {
            isCorrect = Arrays.equals (input, correctPassword);
        }

        //Zero out the password.
        Arrays.fill(correctPassword,'0');

        return isCorrect;
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
        final DatabaseGUI newContentPane = new DatabaseGUI(frame);
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