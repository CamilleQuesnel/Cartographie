package fr.cq.cartographievue.bo;

public enum TypeAttribut {
    STRING(".+"), // tout texte non vide
    INTEGER("^-?\\d+$"),
    FLOAT("^-?\\d*\\.?\\d+$"),
    BOOLEAN("^(?i:true|false|0|1|oui|non)$"),
    DATE("^\\d{4}-\\d{2}-\\d{2}$");

    private final String regex;

    TypeAttribut(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }

    /** Vérifie si une valeur correspond au type **/
    public boolean matches(String valeur) {
        if (valeur == null)
            return false;
        return valeur.trim().matches(this.regex);
    }
    // TODO : possibilité d'intégrer directement un parseur qui converti
    // automatiquement la valeur
}
