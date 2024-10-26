package menu;

import javax.swing.*;
import java.awt.event.KeyEvent;
import editor.EditorTextManager;

public class EditMenuManager {

    public JMenu createEditMenu() {
        JMenu editMenu = new JMenu("Edición");
        editMenu.add(MenuItemCreator.createMenuItem("Cortar", KeyEvent.VK_X, e -> new EditorTextManager().cutText()));
        editMenu.add(MenuItemCreator.createMenuItem("Copiar", KeyEvent.VK_C, e -> new EditorTextManager().copyText()));
        editMenu.add(MenuItemCreator.createMenuItem("Pegar", KeyEvent.VK_V, e -> new EditorTextManager().pasteText()));
        editMenu.add(MenuItemCreator.createMenuItem("Deshacer", KeyEvent.VK_Z, e -> new EditorTextManager().undo()));
        editMenu.add(MenuItemCreator.createMenuItem("Rehacer", KeyEvent.VK_Y, e -> new EditorTextManager().redo()));
        return editMenu;
    }
}
