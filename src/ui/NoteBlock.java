package ui;

import menu.*;
import config.*;
import editor.*;
import java.awt.*;
import intefaces.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.undo.UndoManager;

public class NoteBlock extends JFrame implements ItemListener, NoteOpener, NoteCloser {
    public static JTextArea textArea;
    public static JComboBox<Integer> fontSize;
    public static JComboBox<String> fontFamily;
    public static DefaultComboBoxModel<Integer> sizeModel;
    public static DefaultComboBoxModel<String> fontModel;
    public static UndoManager doManager = new UndoManager();
    CreateMenuItem createMenuItem;
    ConfigManager configManager;

    public NoteBlock() {
        createMenuItem = new CreateMenuItem();
        configManager = new ConfigManager("src/url.properties");
        initializeComponents();
        setupUI();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initializeComponents() {
        textArea = new JTextArea(10, 40);
        fontSize = new JComboBox<>();
        fontFamily = new JComboBox<>();
    }

    private void setupUI() {
        setTitle("Bloc de notas");
        setSize(new Dimension(900, 600));
        setLocationRelativeTo(null);
        setVisible(true);

        setupListeners();
        setupMenu();
        addComponents();
    }

    private void setupListeners() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        fontSize.addItemListener(this);
        fontFamily.addItemListener(this);

        textArea.getDocument().addUndoableEditListener(e -> doManager.addEdit(e.getEdit()));
    }

    private void setupMenu() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        menuBar.add(createMenuItem.createEditMenu());
        menuBar.add(createAboutMenu());
        setJMenuBar(menuBar);
    }

    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("Archivo");
        fileMenu.add(createMenuItem.createMenuItem("Nuevo", KeyEvent.VK_N, KeyEvent.VK_N, e -> openNewNoteWindow()));
        fileMenu.add(createMenuItem.createMenuItem("Abrir", KeyEvent.VK_A, KeyEvent.VK_A, e -> new EditorFileManager().openFile(textArea)));
        fileMenu.add(createMenuItem.createMenuItem("Guardar", KeyEvent.VK_G, KeyEvent.VK_S, e -> new EditorFileManager().saveFile(textArea)));
        fileMenu.add(createMenuItem.createMenuItem("Salir", KeyEvent.VK_P, KeyEvent.VK_P, e -> closeNoteWindow()));
        return fileMenu;
    }

    private JMenu createAboutMenu() {
        JMenu aboutMenu = new JMenu("Acerca De");
        String githubURL = configManager.getProperty("url_github");
        String linkedinURL = configManager.getProperty("url_linkedin");

        aboutMenu.add(createMenuItem.createMenuItem("Github",
                e -> EditorAboutManager.openGithubPage(githubURL)));
        aboutMenu.add(createMenuItem.createMenuItem("LinkedIn",
                e -> EditorAboutManager.openGithubPage(linkedinURL)));
        return aboutMenu;
    }

    private void createPopUpMenu() {
        JPopupMenu popupMenu = new JPopupMenu();

        popupMenu.add(
                createMenuItem.createPopupMenuItem("Cortar", KeyEvent.VK_X, e -> new EditorTextManager().cutText()));
        popupMenu.add(
                createMenuItem.createPopupMenuItem("Copiar", KeyEvent.VK_C, e -> new EditorTextManager().copyText()));
        popupMenu.add(
                createMenuItem.createPopupMenuItem("Pegar", KeyEvent.VK_V, e -> new EditorTextManager().pasteText()));
        popupMenu.add(
                createMenuItem.createPopupMenuItem("Deshacer", KeyEvent.VK_V, e -> new EditorTextManager().undo()));
        popupMenu.add(
                createMenuItem.createPopupMenuItem("Guardar", KeyEvent.VK_G, e -> new EditorFileManager().saveFile(textArea)));

        textArea.setComponentPopupMenu(popupMenu);
    }

    private void addComponents() {
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        setupTopPanel(topPanel);

        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        textPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        container.add(topPanel, BorderLayout.NORTH);
        container.add(textPanel, BorderLayout.CENTER);

        createPopUpMenu();
    }

    private void setupTopPanel(JPanel topPanel) {
        sizeModel = new DefaultComboBoxModel<>();
        new EditorFontManager().changeFontSize();
        fontSize.setModel(sizeModel);
        topPanel.add(fontSize);

        fontModel = new DefaultComboBoxModel<>();
        new EditorFontManager().changeFontFamily();
        fontFamily.setModel(fontModel);
        topPanel.add(fontFamily);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == fontSize || e.getSource() == fontFamily) {
            new EditorFontManager().editFont();
        }
    }

    @Override
    public void openNewNoteWindow() {
        new NoteBlock().setVisible(true);
    }

    @Override
    public void closeNoteWindow() {
        dispose();
    }
}