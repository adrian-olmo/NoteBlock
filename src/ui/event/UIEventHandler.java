package ui.event;

import editor.EditorFontManager;
import ui.NoteBlock;


public class UIEventHandler  {
    private final NoteBlock noteBlock;
    private final EditorFontManager editorFontManager;

    public UIEventHandler(NoteBlock noteBlock, EditorFontManager editorFontManager) {
        this.noteBlock = noteBlock;
        this.editorFontManager = editorFontManager;
    }

    public void handleFontSizeChange(){
        editorFontManager.editFont();
    }

    public void handleFontFamilyChange(){
        editorFontManager.editFont();
    }
}
