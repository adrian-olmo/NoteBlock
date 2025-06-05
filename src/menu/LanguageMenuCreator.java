package menu;

import lang.Language;
import lang.Messages;
import ui.NoteBlock;

import javax.swing.*;

public class LanguageMenuCreator {
    private final NoteBlock noteBlock;

    public LanguageMenuCreator(NoteBlock noteBlock) {
        this.noteBlock = noteBlock;
    }

    public JMenu createToolMenu() {
        JMenu toolMenu = new JMenu(Messages.LANGUAGE.getText());

        JMenuItem spanishItem = new JMenuItem(Messages.SPANISH.getText());
        spanishItem.addActionListener(e -> {
            Messages.setLanguage(Language.SPANISH);
            noteBlock.updateUILanguage();
        });

        JMenuItem englishItem = new JMenuItem(Messages.ENGLISH.getText());
        englishItem.addActionListener(e -> {
            Messages.setLanguage(Language.ENGLISH);
            noteBlock.updateUILanguage();
        });

        toolMenu.add(spanishItem);
        toolMenu.add(englishItem);

        return toolMenu;
    }
}
