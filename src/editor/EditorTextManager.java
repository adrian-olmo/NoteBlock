package editor;

import java.awt.*;
import ui.NoteBlock;
import java.awt.datatransfer.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

public class EditorTextManager extends Component{

    // Obtener el portapapeles del sistema
    final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();


    /** CORTAR TEXTO SELECCIONADO
    * @apiNote
    * - Obtiene el texto seleccionado en el área de texto
    * - Crea una selección de texto y la establece en el portapapeles
    * - Elimina el texto seleccionado del área de texto
    */
    public void cutText() {
        String textoCortar = NoteBlock.textArea.getSelectedText();
        StringSelection selection = new StringSelection(textoCortar);
        clipboard.setContents(selection, selection);
        NoteBlock.textArea.replaceRange("", NoteBlock.textArea.getSelectionStart(), NoteBlock.textArea.getSelectionEnd());
    }

    /** COPIAR TEXTO SELECCIONADO
    * @apiNote
    * - Obtiene el texto seleccionado en el área de texto
    * - Crea una selección de texto y la establece en el portapapeles
    */
    public void copyText() {
        String textoCopiar = NoteBlock.textArea.getSelectedText();
        StringSelection selection = new StringSelection(textoCopiar);
        clipboard.setContents(selection, selection);
    }

    /** PEGAR TEXTO DESDE EL PORTAPAPELES
     * @apiNote
    * - Obtiene el contenido del portapapeles
    * - Verifica si el contenido es de tipo String
    * - Obtiene el texto del portapapeles y lo inserta en el área de texto
    */
    public void pasteText() {
        Transferable transferable = clipboard.getContents(this);
        try {
            if (transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                String s = (String) transferable.getTransferData(DataFlavor.stringFlavor);
                NoteBlock.textArea.replaceSelection(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void undo(){
          try {
              if (NoteBlock.doManager != null && NoteBlock.doManager.canUndo()){
                  NoteBlock.doManager.undo();
              }
          } catch (CannotUndoException exception){
              exception.printStackTrace();
          }
    }

    public void redo() {
        try {
            if (NoteBlock.doManager != null && NoteBlock.doManager.canRedo()){
                NoteBlock.doManager.redo();
            }
        } catch (CannotRedoException exception){
            exception.printStackTrace();
        }
    }

}