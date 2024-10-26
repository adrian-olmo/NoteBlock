package ui;

import menu.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.undo.UndoManager;

import editor.EditorFontManager;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class NoteBlock extends JFrame implements ItemListener {

    public static JTextArea textArea;
    public static JComboBox<Integer> fontSize;
    public static JComboBox<String> fontFamily;
    public static DefaultComboBoxModel<Integer> sizeModel;
    public static DefaultComboBoxModel<String> fontModel;
    FileMenuManager fileMenuManager;
    EditMenuManager editMenuManager;
    AboutMenuCreator aboutMenuCreator;
    public static UndoManager undoManager = new UndoManager();



    public NoteBlock(){
        fileMenuManager = new FileMenuManager();
        editMenuManager = new EditMenuManager();
        aboutMenuCreator = new AboutMenuCreator();
        initComponents();
        initGUI();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initGUI () {
        setTitle("Bloc de notas");
        setSize(new Dimension(900, 600));
        setLocationRelativeTo(null);
        setVisible(true);
        addComponents();
        setupMenu();
        setupListeners();
    }

    private void initComponents () {
        textArea = new JTextArea(10, 40);
        fontSize = new JComboBox<>();
        fontFamily = new JComboBox<>();
    }

    private void addComponents () {
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        setUpTopPanel(topPanel);

        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        textPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        container.add(topPanel, BorderLayout.NORTH);
        container.add(textPanel, BorderLayout.CENTER);
    }

    private void setupMenu () {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenuManager.createFileMenu(this)); // Pasa una referencia a NoteBlock
        menuBar.add(editMenuManager.createEditMenu());
        menuBar.add(aboutMenuCreator.createAboutMenu());
        setJMenuBar(menuBar);
    }

    private void setupListeners () {
        fontSize.addItemListener(this);
        fontFamily.addItemListener(this);
        textArea.getDocument().addUndoableEditListener(e -> undoManager.addEdit(e.getEdit()));
    }

    private void setUpTopPanel (JPanel topPanel) {
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
    public void itemStateChanged (ItemEvent e) {
        if ( e.getSource() == fontSize || e.getSource() == fontFamily ){
            new EditorFontManager().editFont();
        }
    }
}