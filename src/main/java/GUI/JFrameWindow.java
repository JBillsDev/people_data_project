package GUI;

import Utility.Config;
import org.tinylog.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

final public class JFrameWindow extends JFrame  {

    public JFrameWindow(String windowTitle, Config configReference) {
        final var config = configReference;

        final Dimension dimensionWindowMinimumSize = new Dimension(480, 335);

        this.setTitle(windowTitle);
        this.setMinimumSize(dimensionWindowMinimumSize);
        this.setBounds(config.getWindowRect());
        // Set the program to stop running when the window's 'X' is clicked
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Update config file when window is resized
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent event) {
                super.componentResized(event);

                config.updateWindowDimensions(event.getComponent().getWidth(),
                                                       event.getComponent().getHeight());
                config.updateWindowPosition(event.getComponent().getX(),
                                                     event.getComponent().getY());
                config.writeConfig();
            }
        });

        // Update config file when window is moved
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent event) {
                super.componentMoved(event);

                config.updateWindowDimensions(event.getComponent().getWidth(),
                        event.getComponent().getHeight());
                config.updateWindowPosition(event.getComponent().getX(),
                        event.getComponent().getY());
                config.writeConfig();
            }
        });

        final var jPanelPersonEditor = new JPanelPersonEditor();
        this.add(jPanelPersonEditor.getPanelRoot());

        this.setVisible(true);
        Logger.info("JFrameWindow (" + windowTitle + ") initialized");
    }
}
