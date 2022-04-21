package GUI;

import Utility.Config;
import org.tinylog.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

final public class JFrameWindow extends JFrame  {

    Config configReference;

    JPanelPersonEditor jPanelPersonEditor;

    Dimension dimensionWindowMinimumSize = new Dimension(480, 480);

    public JFrameWindow(String windowTitle, Config configReference) {
        this.configReference = configReference;

        this.setTitle(windowTitle);
        this.setMinimumSize(this.dimensionWindowMinimumSize);
        this.setBounds(this.configReference.getWindowRect());
        // Set the program to stop running when the window's 'X' is clicked
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Update config file when window is resized
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent event) {
                super.componentResized(event);

                configReference.updateWindowDimensions(event.getComponent().getWidth(),
                                                       event.getComponent().getHeight());
                configReference.updateWindowPosition(event.getComponent().getX(),
                                                     event.getComponent().getY());
                configReference.writeConfig();
            }
        });

        // Update config file when window is moved
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent event) {
                super.componentMoved(event);

                configReference.updateWindowDimensions(event.getComponent().getWidth(),
                        event.getComponent().getHeight());
                configReference.updateWindowPosition(event.getComponent().getX(),
                        event.getComponent().getY());
                configReference.writeConfig();
            }
        });

        this.jPanelPersonEditor = new JPanelPersonEditor();
        this.add(this.jPanelPersonEditor.getPanelRoot());

        this.setVisible(true);
        Logger.info("JFrameWindow (" + windowTitle + ") initialized");
    }
}
