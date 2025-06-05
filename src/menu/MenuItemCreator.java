package menu;

import javax.swing.*;
import interfaces.ActionHandler;
import java.awt.event.InputEvent;

public class MenuItemCreator {

    public static JMenuItem createMenuItem(String text, int mnemonic, ActionHandler actionHandler) {
        JMenuItem item = new JMenuItem(text);
        item.setMnemonic(mnemonic);
        item.setAccelerator(KeyStroke.getKeyStroke(mnemonic, InputEvent.CTRL_DOWN_MASK));
        item.addActionListener(actionHandler::handle);
        return item;
    }

    public static JMenuItem createMenuItem(String text, ActionHandler actionHandler) {
        JMenuItem item = new JMenuItem(text);
        item.addActionListener(actionHandler::handle);
        return item;
    }

    public static JMenuItem createMenuItem(String text) {
        return new JMenuItem(text);
    }
}
