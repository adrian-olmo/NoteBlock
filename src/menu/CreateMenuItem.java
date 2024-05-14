
package menu;

import intefaces.ActionHandler;

import javax.swing.*;
import java.awt.event.InputEvent;

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
    // TODO: REVISAR RUTAS IMAGES
    /*private JMenuItem createPopupMenuItem(String text, int mnemonic, String iconPath, ActionHandler actionHandler) {
        JMenuItem item = new JMenuItem(text);
        item.setMnemonic(mnemonic);
        item.addActionListener(actionHandler::handle);
        item.setIcon(new ImageIcon(getClass().getResource(iconPath))); // Cargar el icono desde el recurso
        return item;
    }*/

}
