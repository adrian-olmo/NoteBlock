package ui;

import editor.EditorFontManager;
import lang.Messages;
import menu.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.undo.UndoManager;

public class NoteBlock extends JFrame implements ItemListener {

    public static JTextArea textArea;
    public static JComboBox<Integer> fontSize;
    public static JComboBox<String> fontFamily;
    public static DefaultComboBoxModel<Integer> sizeModel;
    public static DefaultComboBoxModel<String> fontModel;
    FileMenuManager fileMenuManager;
    EditMenuManager editMenuManager;
    AboutMenuCreator aboutMenuCreator;
    LanguageMenuCreator languageMenuCreator;
    public static UndoManager undoManager = new UndoManager();

    public NoteBlock(){
        fileMenuManager = new FileMenuManager();
        editMenuManager = new EditMenuManager();
        aboutMenuCreator = new AboutMenuCreator();
        languageMenuCreator = new LanguageMenuCreator(this); // Pasamos this para poder actualizar
        initComponents();
        initGUI();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initGUI () {
        setTitle(Messages.TITLE.getText()); // Usamos Messages para el título
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

        JPanel fontPanel = new JPanel(new FlowLayout());
        setUpTopPanel(fontPanel);

        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        textPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        container.add(fontPanel, BorderLayout.NORTH);
        container.add(textPanel, BorderLayout.CENTER);
    }

    private void setupMenu () {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenuManager.createFileMenu(this));
        menuBar.add(editMenuManager.createEditMenu());
        menuBar.add(aboutMenuCreator.createAboutMenu());
        menuBar.add(languageMenuCreator.createToolMenu());
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
        topPanel.add(new JLabel(Messages.FONT_SIZE.getText() + ":"));
        topPanel.add(fontSize);

        fontModel = new DefaultComboBoxModel<>();
        new EditorFontManager().changeFontFamily();
        fontFamily.setModel(fontModel);
        topPanel.add(new JLabel(Messages.FONT_FAMILY.getText() + ":"));
        topPanel.add(fontFamily);
    }

    @Override
    public void itemStateChanged (ItemEvent e) {
        if ( e.getSource() == fontSize || e.getSource() == fontFamily ){
            new EditorFontManager().editFont();
        }
    }

    // Método para actualizar la UI cuando cambia el idioma
    public void updateUILanguage () {
        SwingUtilities.invokeLater(() -> {
            setTitle(Messages.TITLE.getText());

            // Actualizar labels del font panel
            Component[] components = ((JPanel)getContentPane().getComponent(0)).getComponents();
            for (Component comp : components) {
                if ( comp instanceof JLabel label ) {
                    if (label.getText().startsWith("Font size") || label.getText().startsWith("Tamaño")) {
                        label.setText(Messages.FONT_SIZE.getText() + ":");
                    } else if (label.getText().startsWith("Font family") || label.getText().startsWith("Familia")) {
                        label.setText(Messages.FONT_FAMILY.getText() + ":");
                    }
                }
            }
            // Reconstruir la barra de menú
            setupMenu();
        });
    }
}