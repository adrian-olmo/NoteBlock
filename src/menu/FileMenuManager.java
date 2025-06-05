package menu;

import javax.swing.*;

import lang.Messages;
import ui.NoteBlock;
import java.awt.event.KeyEvent;
import editor.EditorFileManager;

public class FileMenuManager {

    public JMenu createFileMenu(NoteBlock noteBlock) {
        JMenu fileMenu = new JMenu(Messages.FILE.getText());
        fileMenu.add(MenuItemCreator.createMenuItem(Messages.NEW.getText(), KeyEvent.VK_N, e -> new NoteBlock().setVisible(true)));
        fileMenu.add(MenuItemCreator.createMenuItem(Messages.OPEN.getText(), KeyEvent.VK_A, e -> new EditorFileManager().openFile(NoteBlock.textArea)));
        fileMenu.add(MenuItemCreator.createMenuItem(Messages.SAVE.getText(), KeyEvent.VK_G, e -> new EditorFileManager().saveFile(NoteBlock.textArea)));
        fileMenu.add(MenuItemCreator.createMenuItem(Messages.EXIT.getText(), KeyEvent.VK_P, e -> noteBlock.dispose()));
        return fileMenu;
    }


}
