package ui;

import editor.EditorFontManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class NoteBlock extends JFrame implements ItemListener {

    public static JTextArea textArea;
    public static JComboBox<Integer> fontSize;
    public static JComboBox<String> fontFamily;
    public static DefaultComboBoxModel<Integer> sizeModel;
    public static DefaultComboBoxModel<String> fontModel;

    public NoteBlock(){
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

    private void setupListeners () {
        fontSize.addItemListener(this);
        fontFamily.addItemListener(this);
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