package de.oop.projekt.main;

/**
 * Dieses {@link Enum} bietet die Auswahl der Geschlechter
 */
public enum Gender {
    MALE {
        /**
         * Diese Methode gibt das Geschlecht in Kurzform als String aus
         * @return String männlich
         */
        @Override
        public String toString() {
            return "männlich";
        }
    },
    FEMALE {
        /**
         * Diese Methode gibt das Geschlecht in Kurzform als String aus
         * @return String weiblich
         */
        @Override
        public String toString() {
            return "weiblich";
        }
    },
    DIVERS {
        /**
         * Diese Methode gibt das Geschlecht in Kurzform als String aus
         * @return String divers
         */
        @Override
        public String toString() {
            return "divers";
        }
    }

}
