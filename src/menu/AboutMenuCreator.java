package menu;

import javax.swing.*;
import config.ConfigManager;
import editor.EditorAboutManager;

public class AboutMenuCreator {

    private final ConfigManager configManager = new ConfigManager("src/url.properties");

    public JMenu createAboutMenu () {
        JMenu aboutMenu = new JMenu("Acerca De");
        String githubURL = configManager.getProperty("url_github");
        String linkedInURL = configManager.getProperty("url_linkedin");

        aboutMenu.add(MenuItemCreator.createMenuItem("GitHub", e -> EditorAboutManager.openPage(githubURL)));
        aboutMenu.add(MenuItemCreator.createMenuItem("LinkedIn", e -> EditorAboutManager.openPage(linkedInURL)));
        return aboutMenu;
    }
}
