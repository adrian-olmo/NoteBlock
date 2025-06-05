package lang;

import java.util.LinkedHashSet;
import java.util.Set;

public enum Messages {
    // Textos de la aplicación
    FILE("File", "Archivo"),
    NEW("New", "Nuevo"),
    OPEN("Open", "Abrir"),
    SAVE("Save", "Guardar"),
    SAVE_FILE("Save file", "Guardar archivo"),
    EXIT("Exit", "Salir"),
    EDIT("Edit", "Editar"),
    UNDO("Undo", "Deshacer"),
    REDO("Redo", "Rehacer"),
    CUT("Cut", "Cortar"),
    COPY("Copy", "Copiar"),
    PASTE("Paste", "Pegar"),
    ABOUT("About", "Acerca de"),
    FONT_SIZE("Font size", "Tamaño de fuente"),
    FONT_FAMILY("Font family", "Tipo de fuente"),
    TITLE("Notepad", "Bloc de notas"),
    LANGUAGE("Language", "Idioma"),
    ENGLISH("English", "Inglés"),
    SPANISH("Spanish", "Español");

    private final String englishText;
    private final String spanishText;

    private static Language currentLanguage = Language.ENGLISH;
    private static final Set<LanguageChangeListener> listeners = new LinkedHashSet<>();

    Messages(String englishText, String spanishText) {
        this.englishText = englishText;
        this.spanishText = spanishText;
    }

    public String getText() {
        return currentLanguage == Language.ENGLISH ? englishText : spanishText;
    }

    public static void setLanguage(Language lang) {
        currentLanguage = lang;
        notifyLanguageChanged();
    }

    private static void notifyLanguageChanged() {
        for (LanguageChangeListener listener : listeners) {
            listener.onLanguageChanged();
        }
    }

    public interface LanguageChangeListener {
        void onLanguageChanged();
    }
}



