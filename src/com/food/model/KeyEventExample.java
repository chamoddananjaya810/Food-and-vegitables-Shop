import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyEventExample {

    public static void main(String[] args) {
        // Create a new JFrame (window)
        JFrame frame = new JFrame("KeyEvent Example");
        
        // Create a JTextField where user can type
        JTextField textField = new JTextField(20);
        
        // Create a JLabel to display messages
        JLabel label = new JLabel("Press a key...");

        // Add a KeyListener to the text field using KeyAdapter
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Get the KeyCode and perform actions
                int keyCode = e.getKeyCode();
                
                switch (keyCode) {
                    case KeyEvent.VK_ENTER:
                        label.setText("Enter key pressed!");
                        break;
                    case KeyEvent.VK_ESCAPE:
                        label.setText("Escape key pressed!");
                        break;
                    case KeyEvent.VK_A:
                        label.setText("The 'A' key was pressed!");
                        break;
                    case KeyEvent.VK_UP:
                        label.setText("Up arrow key pressed!");
                        break;
                    case KeyEvent.VK_DOWN:
                        label.setText("Down arrow key pressed!");
                        break;
                    case KeyEvent.VK_LEFT:
                        label.setText("Left arrow key pressed!");
                        break;
                    case KeyEvent.VK_RIGHT:
                        label.setText("Right arrow key pressed!");
                        break;
                    case KeyEvent.VK_F1:
                        label.setText("F1 key pressed!");
                        break;
                    default:
                        label.setText("Some other key pressed...");
                        break;
                }
            }
        });

        // Create a JPanel to hold the text field and label
        JPanel panel = new JPanel();
        panel.add(textField);
        panel.add(label);

        // Add the panel to the frame
        frame.add(panel);

        // Set frame properties
        frame.setSize(400, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
