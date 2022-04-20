package GUI;

import javax.swing.*;

public class JFrameWindow extends JFrame {

    public JFrameWindow(String windowTitle, int windowWidth, int windowHeight) {
        this.setTitle(windowTitle);
        this.setSize(windowWidth, windowHeight);
        // Set the program to stop running when the window's 'X' is clicked
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Center the window, should be triggered after setting the size
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
