package menu;

import javax.swing.*;
import ui.NoteBlock;
import java.awt.event.KeyEvent;
import editor.EditorFileManager;

public class FileMenuManager {

    public JMenu createFileMenu(NoteBlock noteBlock) {
        JMenu fileMenu = new JMenu("Archivo");
        fileMenu.add(MenuItemCreator.createMenuItem("Nuevo", KeyEvent.VK_N, e -> new NoteBlock().setVisible(true)));
        fileMenu.add(MenuItemCreator.createMenuItem("Abrir", KeyEvent.VK_A, e -> new EditorFileManager().openFile(NoteBlock.textArea)));
        fileMenu.add(MenuItemCreator.createMenuItem("Guardar", KeyEvent.VK_G, e -> new EditorFileManager().saveFile(NoteBlock.textArea)));
        fileMenu.add(MenuItemCreator.createMenuItem("Salir", KeyEvent.VK_P, e -> noteBlock.dispose()));
        return fileMenu;
    }


}
