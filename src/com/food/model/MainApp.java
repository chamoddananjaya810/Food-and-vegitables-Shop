import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Create a class for the custom JDialog
class MyDialog extends JDialog {
    private JTextField textField;
    private String inputText;

    public MyDialog(JFrame parent) {
        super(parent, "Input Dialog", true);
        setLayout(new FlowLayout());
        setSize(300, 150);

        // Create and add components
        JLabel label = new JLabel("Enter something:");
        textField = new JTextField(20);
        JButton okButton = new JButton("OK");

        add(label);
        add(textField);
        add(okButton);

        // Action listener for OK button
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputText = textField.getText(); // Retrieve the input text
                setVisible(false); // Hide the dialog
            }
        });
    }

    public String getInputText() {
        return inputText; // Return the input text
    }
}

// Create a class for the JPanel
class MyPanel extends JPanel {
    private JLabel label;

    public MyPanel() {
        setLayout(new FlowLayout());
        label = new JLabel("Input: ");
        add(label);
    }

    // Method to update the JLabel with the data from the dialog
    public void updateLabel(String text) {
        label.setText("Input: " + text);
    }
}

// Main application class
public class MainApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JDialog to JPanel Example");
        MyPanel panel = new MyPanel();

        // Button to trigger the dialog
        JButton showDialogButton = new JButton("Show Dialog");
        showDialogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyDialog dialog = new MyDialog(frame);
                dialog.setVisible(true); // Show the dialog
                String input = dialog.getInputText(); // Get the input text
                panel.updateLabel(input); // Update the panel with the input text
            }
        });

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.add(showDialogButton, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setVisible(true);
    }
}