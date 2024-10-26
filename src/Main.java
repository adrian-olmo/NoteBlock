import ui.NoteBlock;

import javax.swing.*;

public class Main {
    public static void main (String[] args) {
        SwingUtilities.invokeLater(NoteBlock::new);
    }
}