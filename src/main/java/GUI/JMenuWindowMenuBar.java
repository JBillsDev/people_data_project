package GUI;

import Data.LiveData;

import javax.swing.*;

final public class JMenuWindowMenuBar extends JMenuBar {

    public JMenuWindowMenuBar(JPanelPersonEditor jPanelPersonEditorReference) {
        final String TEXT_MENU_FILE = "File";
        final String TEXT_MENU_FILE_EXIT = "Exit";
        final String TEXT_MENU_FILE_LOAD = "Load";
        final String TEXT_MENU_FILE_NEW = "New";
        final String TEXT_MENU_FILE_SAVE = "Save";

        final var menuFile = new JMenu(TEXT_MENU_FILE);
        final var menuFileNew = new JMenuItem(TEXT_MENU_FILE_NEW);
        menuFileNew.addActionListener(event -> {
            LiveData.clearData();
            jPanelPersonEditorReference.clearAll();
        });
        menuFile.add(menuFileNew);

        menuFile.addSeparator();

        final var menuFileSave = new JMenuItem(TEXT_MENU_FILE_SAVE);
        menuFile.add(menuFileSave);

        final var menuFileLoad = new JMenuItem(TEXT_MENU_FILE_LOAD);
        menuFile.add(menuFileLoad);

        menuFile.addSeparator();

        final var menuFileExit = new JMenuItem(TEXT_MENU_FILE_EXIT);
        menuFileExit.addActionListener(event -> System.exit(0));
        menuFile.add(menuFileExit);

        this.add(menuFile);
    }

}
