package menu;

import javax.swing.*;

import config.ConfigManager;
import editor.EditorAboutManager;
import editor.EditorFileManager;
import editor.EditorTextManager;
import interfaces.ActionHandler;
import interfaces.NoteCloser;
import interfaces.NoteOpener;
import ui.NoteBlock;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MenuManager implements NoteOpener, NoteCloser {

    private NoteBlock noteBlock;
    private ConfigManager configManager;

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

    public JMenu createFileMenu(){
        JMenu fileMenu = new JMenu("Archivo");
        /*fileMenu.add(createMenuItem("Nuevo", KeyEvent.VK_N, KeyEvent.VK_N, e -> openNewNoteWindow()));*/
        //fileMenu.add(createMenuItem("Salir", KeyEvent.VK_P, KeyEvent.VK_P, e -> closeNoteWindow()));
        fileMenu.add(createMenuItem("Abrir", KeyEvent.VK_A, KeyEvent.VK_A, e -> new EditorFileManager().openFile(NoteBlock.textArea)));
        fileMenu.add(createMenuItem("Guardar", KeyEvent.VK_G, KeyEvent.VK_S, e -> new EditorFileManager().saveFile(NoteBlock.textArea)));
        return fileMenu;
    }

    public JMenu createEditMenu() {
        JMenu editMenu = new JMenu("Edición");
        editMenu.add(createMenuItem("Cortar", KeyEvent.VK_X, KeyEvent.VK_X,
                e -> new EditorTextManager().cutText()));
        editMenu.add(createMenuItem("Copiar", KeyEvent.VK_C, KeyEvent.VK_C,
                e -> new EditorTextManager().copyText()));
        editMenu.add(createMenuItem("Pegar", KeyEvent.VK_V, KeyEvent.VK_V,
                e -> new EditorTextManager().pasteText()));
        /*editMenu.add(createMenuItem("Deshacer", KeyEvent.VK_Z, KeyEvent.VK_Z,
                e -> new EditorTextManager().undo()));
        editMenu.add(createMenuItem("Rehacer", KeyEvent.VK_Y, KeyEvent.VK_Y,
                e -> new EditorTextManager().redo()));*/
        return editMenu;
    }

    public JMenu createAboutMenu () {
        JMenu aboutMenu = new JMenu("Acerca De");
        String githubURL = configManager.getProperty("url_github");
        String linkedInURL = configManager.getProperty("url_linkedin");

        aboutMenu.add(createMenuItem("GitHub", e -> EditorAboutManager.openPage()));
        return aboutMenu;
    }

    @Override
    public void closeNoteWindow () {
        noteBlock.dispose();
    }

    @Override
    public void openNewNoteWindow () {
        new NoteBlock().setVisible(true);
    }


}
