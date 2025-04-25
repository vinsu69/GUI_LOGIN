package GUI_LOGIN;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

public class GUI extends JFrame {

    // UI Components
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;

    // Constructor
    public GUI() {
        initComponents();
        setTitle("Login Form");
        setBounds(600, 300, 400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Initialize UI Components
    private void initComponents() {
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        cancelButton = new JButton("Cancel");

        loginButton.addActionListener(evt -> handleLogin());
        cancelButton.addActionListener(evt -> clearFields());

        setLayout(new java.awt.GridLayout(3, 2, 10, 10));
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(cancelButton);
        pack();
    }

    // Handle login button click
    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username and password must not be empty.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (saveCredentials(username, password)) {
            JOptionPane.showMessageDialog(this, "Account saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Error saving account.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Save credentials to file
    private boolean saveCredentials(String username, String password) {
        File file = new File("C:\\DATA\\USER.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(username + ", " + password);
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace(); // Optional: Log the error for debugging
            return false;
        }
    }

    // Clear text fields
    private void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
    }

    // Main method
    public static void main(String[] args) {
        try {
            // Set Nimbus look and feel
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(GUI::new);
    }
}
