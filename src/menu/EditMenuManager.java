package menu;

import javax.swing.*;
import java.awt.event.KeyEvent;
import editor.EditorTextManager;
import lang.Messages;
import ui.NoteBlock;

public class EditMenuManager {

    public JMenu createEditMenu() {
        JMenu editMenu = new JMenu(Messages.EDIT.getText());
        editMenu.add(MenuItemCreator.createMenuItem(Messages.CUT.getText(), KeyEvent.VK_X, e -> new EditorTextManager().cutText()));
        editMenu.add(MenuItemCreator.createMenuItem(Messages.COPY.getText(), KeyEvent.VK_C, e -> new EditorTextManager().copyText()));
        editMenu.add(MenuItemCreator.createMenuItem(Messages.PASTE.getText(), KeyEvent.VK_V, e -> new EditorTextManager().pasteText()));
        editMenu.add(MenuItemCreator.createMenuItem(Messages.UNDO.getText(), KeyEvent.VK_Z, e -> new EditorTextManager().undo()));
        editMenu.add(MenuItemCreator.createMenuItem(Messages.REDO.getText(), KeyEvent.VK_Y, e -> new EditorTextManager().redo()));
        return editMenu;
    }
}
