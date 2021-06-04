package de.oop.projekt.main;

import de.oop.projekt.helper.Date;

import java.util.ArrayList;
import java.util.List;
import java.util.*;


/**
 * Die Klasse <code>TestSubject</code> erweitert die Klasse {@link Person} und dient der Erstellung von verschiedenen Testpersonen, die an der Studie teilgenommen haben.
 */
public class TestSubject extends Person {

    private UUID testSubjectID = UUID.randomUUID();

    private List<String> intolerances = new ArrayList<>();

    /**
     * Konstruktor für eine Testperson ohne Unverträglichkeiten
     *
     * @param vorname      Vorname
     * @param nachname     Nachname
     * @param geburtsdatum Geburtsdatum
     * @param gender       Geschlecht
     */
    public TestSubject(String vorname, String nachname, Date geburtsdatum, Gender gender) {
        super(vorname, nachname, geburtsdatum, gender);
    }

    /**
     * Konstruktor für eine Testperson mit Unverträglichkeiten
     *
     * @param vorname      Vorname
     * @param nachname     Nachname
     * @param geburtsdatum Geburtsdatum
     * @param gender       Geschlecht
     * @param intolerances Unverträglichkeiten
     */
    public TestSubject(String vorname, String nachname, Date geburtsdatum, Gender gender, String intolerances) {
        super(vorname, nachname, geburtsdatum, gender);
        setIntolerances(intolerances);
    }

    public Boolean acceptVisitor(Visitor v) {
        return v.visit(this);
    }

    public TestSubject update(String firstname, String lastname, String intolerances, Date dateOfBirth, Gender gender) {
        this.setIntolerances(intolerances).setFirstName(firstname).setLastName(lastname).setDateOfBirth(dateOfBirth).setGender(gender);
        return this;
    }

    /**
     * Getter ID des TestSubjects
     *
     * @return ID des TestSubjects als {@link UUID}
     */
    public UUID getTestSubjectID() {
        return this.testSubjectID;
    }

    /**
     * Getter Unverträglichkeiten
     *
     * @return Unverträglichkeiten als {@link ArrayList}
     */
    public List<String> getIntolerancesAsList() {
        return intolerances;
    }


    /**
     * Setter Unverträglichkeiten
     * Bietet die Möglichkeit Unverträglichkeiten hinzuzufügen. Löscht alle bisherigen Unverträglichkeiten
     * Trennt den String anhand der Kommata auf und fügt es in eine ArrayList hinzu.
     *
     * @param intolerances Unverträglichkeiten, die hinzugefügt werden sollen als {@link String}
     * @return {@link ArrayList} mit den Unverträglichkeiten als einzelen Einträge
     */
    public TestSubject setIntolerances(final String intolerances) {
        String[] temp = intolerances.split(",");
        getIntolerancesAsList().clear();
        for (int i = 0; i < temp.length; i++) {
            this.intolerances.add(temp[i].trim());
        }
        return this;
    }

    /**
     * Bietet die Möglichkeit Unverträglichkeiten zu entfernen
     * <p>
     * Momentan nicht verwendet, aber der Vollständigkeit halber für Weiterentwicklung des Programms implementiert
     * Vergleicht den Parameter {@param intolerance} per PredicateFilter mit dem aktuellen Listenelement n. Es wird auf Gleicheit geprüft. Die Groß-/Kleinschreibung wird dabei mit der {@link java.lang.String#equalsIgnoreCase(String)} ignoriert. Bei Übereinstimmung wird das Element aus der Liste entfernt.
     *
     * @param intolerance Unverträglichkeit, die entfernt werden soll als {@link String}
     * @return sich selbst als {@link TestSubject}
     * @see java.lang.String#equalsIgnoreCase(String)
     */
    public TestSubject removeIntolerance(String intolerance) {
        this.intolerances.removeIf(n -> (n.equalsIgnoreCase(intolerance)));
        return this;

    }

    /**
     * Bietet die Möglichkeit Unverträglichkeiten einzeln hinzuzufügen
     * <p>
     * Momentan nicht verwendet, aber der Vollständigkeit halber für Weiterentwicklung des Programms implementiert
     *
     * @param intolerance Unverträglichkeit, die hinzugefügt werden soll als {@link String}
     * @return sich selbst als {@link TestSubject}
     */
    public TestSubject addIntolerance(String intolerance) {
        this.getIntolerancesAsList().add(intolerance);
        return this;

    }

    /**
     * Gibt die Unverträglichkeiten als {@link String} zurück. Alle Einträge werden mit einem Komma getrennt und einem {@link StringBuilder} hinzugefügt.
     *
     * @return Unverträglichkeiten als {@link String}
     */
    public String getIntolerance() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < intolerances.size(); i++) {
            sb.append(intolerances.get(i));
            if ((i < (intolerances.size() - 1))) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    /**
     * Gibt das TestSubject-Objekt als String aus. Greift dabei unter Anderem auf die {@link Person#toString() Standardimplementierung} der abstrakten Superklasse {@link Person} zurück.
     *
     * @return Doctor als {@link String}
     */
    @Override
    public String toString() {
        return "Testperson: " + super.toString() + " (Tester-ID: " + testSubjectID + ")" + ", Unverträglichkeiten: " + getIntolerance();
    }


}
