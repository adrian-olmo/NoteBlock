package editor;

import java.awt.*;
import ui.NoteBlock;

public class EditorFontManager {

    public void changeFontSize() {
        for (int i = 20; i <= 50; i++) {
            NoteBlock.sizeModel.addElement(i);
        }
    }

    public void changeFontFamily() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fonts = ge.getAvailableFontFamilyNames();
        for (String font : fonts) {
            NoteBlock.fontModel.addElement(font);
        }
    }

    public void editFont() {
        Font font = new Font((String) NoteBlock.fontFamily.getSelectedItem(),
                Font.PLAIN, (int)
                NoteBlock.fontSize.getSelectedItem());
        NoteBlock.textArea.setFont(font);
    }
}
