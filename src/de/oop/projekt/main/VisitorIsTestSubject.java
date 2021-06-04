package de.oop.projekt.main;

/**
 * Die Klasse <code>VisitorIsTestSubject</code> bietet die Möglichkeit mit Hilfe von Bool'schen Rückgabewerten zu überprüfen, ob es sich bei einer Personeninstanz um ein {@link TestSubject} oder einen {@link Doctor} handelt.
 */

public class VisitorIsTestSubject implements Visitor {

    /**
     * Wird aufgerufen, wenn die an den Visitor {@link VisitorIsTestSubject} übergebene Instanz ein {@link TestSubject} ist
     *
     * @param testSubject eine Personen-Instanz die ein TestSubject ist
     * @return true
     */
    public Boolean visit(TestSubject testSubject) {
        return true;
    }

    /**
     * Wird aufgerufen, wenn die an den Visitor {@link VisitorIsTestSubject} übergebene Instanz ein {@link Doctor} ist
     *
     * @param doctor eine Personen-Instanz die ein Doktor ist
     * @return false
     */
    public Boolean visit(Doctor doctor) {
        return false;
    }
}
