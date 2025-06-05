package menu;

import javax.swing.*;
import config.ConfigManager;
import editor.EditorAboutManager;
import lang.Messages;
import ui.NoteBlock;

public class AboutMenuCreator {

    private final ConfigManager configManager = new ConfigManager("src/url.properties");

    public JMenu createAboutMenu () {
        JMenu aboutMenu = new JMenu(Messages.ABOUT.getText());
        String githubURL = configManager.getProperty("url_github");
        String linkedInURL = configManager.getProperty("url_linkedin");

        aboutMenu.add(MenuItemCreator.createMenuItem("GitHub", e -> EditorAboutManager.openPage(githubURL)));
        aboutMenu.add(MenuItemCreator.createMenuItem("LinkedIn", e -> EditorAboutManager.openPage(linkedInURL)));
        return aboutMenu;
    }
}
