package menu;

import javax.swing.*;

import editor.EditorTextManager;
import interfaces.ActionHandler;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class CreateMenuItem {
    public JMenuItem createMenuItem(String text, int mnemonic, int keyCode, ActionHandler actionHandler) {
        JMenuItem item = new JMenuItem(text);
        item.setMnemonic(mnemonic);
        item.setAccelerator(KeyStroke.getKeyStroke(keyCode, InputEvent.CTRL_DOWN_MASK));
        item.addActionListener(actionHandler::handle);
        return item;
    }

    public JMenuItem createMenuItem(String text, ActionHandler actionHandler) {
        JMenuItem item = new JMenuItem(text);
        item.addActionListener(actionHandler::handle);
        return item;
    }

    public JMenuItem createPopupMenuItem(String text, int mnemonic, ActionHandler actionHandler) {
        JMenuItem item = new JMenuItem(text);
        item.setMnemonic(mnemonic);
        item.addActionListener(actionHandler::handle);
        return item;
    }

    public JMenu createEditMenu() {
        JMenu editMenu = new JMenu("Edición");
        editMenu.add(createMenuItem("Cortar", KeyEvent.VK_X, KeyEvent.VK_X,
                e -> new EditorTextManager().cutText()));
        editMenu.add(createMenuItem("Copiar", KeyEvent.VK_C, KeyEvent.VK_C,
                e -> new EditorTextManager().copyText()));
        editMenu.add(createMenuItem("Pegar", KeyEvent.VK_V, KeyEvent.VK_V,
                e -> new EditorTextManager().pasteText()));
        editMenu.add(createMenuItem("Deshacer", KeyEvent.VK_Z, KeyEvent.VK_Z,
                e -> new EditorTextManager().undo()));
        editMenu.add(createMenuItem("Rehacer", KeyEvent.VK_Y, KeyEvent.VK_Y,
                e -> new EditorTextManager().redo()));
        return editMenu;
    }
}
