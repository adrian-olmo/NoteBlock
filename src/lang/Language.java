package lang;

// Enum auxiliar para control de idiomas (ahora con nombres traducibles)
public enum Language {
    ENGLISH(Messages.ENGLISH),
    SPANISH(Messages.SPANISH);

    private final Messages displayName;

    Language(Messages displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName.getText();
    }
}
