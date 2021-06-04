package de.oop.projekt.main;

import de.oop.projekt.gui.GUI;
import de.oop.projekt.helper.Date;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Die Klasse <code>Study</code> bietet die Möglichkeit die gesamte Studie zu serialisieren. Die Klasse verfügt über ein <a href="https://de.wikibooks.org/wiki/Muster:_Java:_Singleton">Singleton-Pattern</a>
 * <p>
 * In dieser Klasse werden alle Personen verwaltet und mithilfe der {@link #writeToFile()} serialisiert und in die Datei <code>{@link #filename}</code> (standardmäßig: 'Serialization.study') geschrieben.
 * <p>
 * Mithilfe der {@link #readFromFile(String)} - Methode wird die Datei wieder eingelesen und in die {@link #instance} - Instanz geschrieben. Es kann aufgrund des <a href="https://de.wikibooks.org/wiki/Muster:_Java:_Singleton">Singleton-Patterns</a> und der daraus resultierenden {@link #getInstance()}-Methode immer nur eine Instanz der Study-Klasse zur Laufzeit geben.
 */
public class Study implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private static Study instance;
    private static String filename = "Serialization.study";
    private final List<Person> personList = new ArrayList<>();
    private Boolean changesNotSaved = false;
    private String title = "Medizinische Studie";

    /**
     * Konstruktor der Klasse Study
     * private, damit keine neuen Instanzen der Klasse von außen erzeugt werden können. Siehe auch {@link #getInstance()} und <a href="https://de.wikibooks.org/wiki/Muster:_Java:_Singleton">Singleton-Pattern</a>
     */
    private Study() {
        super();
    }


    /**
     * Zugriffsmehtode für die Study-Instanz. Gibt die einzig existierende Instanz zur Laufzeit zurück.
     *
     * @return instance der {@link Study}-Klasse
     */
    public synchronized static Study getInstance() {
        if (instance == null) {
            instance = new Study();
        }
        return instance;
    }

    /**
     * Getter Dateiname
     *
     * @return Dateiname als {@link String}
     */
    public static String getFilename() {
        return filename;
    }

    /**
     * Setter Dateiname
     *
     * @param filename Dateiname
     */
    public static void setFilename(String filename) {
        Study.filename = filename;
    }

    /**
     * Schreibt mithilfe der {@link ObjectOutputStream}-Klasse und {@link FileOutputStream}-Klasse die Instanz der Study in eine Datei mit dem Dateinamen {@link #filename}
     * Setzt die 'nicht gespeichert'-Erinnerung zurück
     *
     * @throws IOException Fehlermeldung
     * @see ObjectOutputStream
     * @see FileOutputStream
     */
    public static void writeToFile() throws IOException {

        ObjectOutputStream objOutStream = new ObjectOutputStream(new FileOutputStream(System.getProperty("user.home") + "/" + getFilename()));
        objOutStream.writeObject(getInstance());
        objOutStream.close();
        System.out.println(objOutStream);
        System.out.println(System.getProperty("user.home"));
        Study.getInstance().setChangesNotSaved(false);
    }


    /**
     * Liest mithilfe der {@link ObjectInputStream}-Klasse und der {@link FileInputStream}-Klasse eine Instanz der Study-Klasse aus einer Datei aus.
     * <p>
     * Der Name der Datei {@link #filename} wird übergeben. Die Methode ist <code>static</code>, damit kein Objekt der Klasse erzeugt werden muss
     *
     * @param filename Dateiname als {@link String}
     * @throws IOException Fehlermeldung
     * @see ObjectInputStream
     * @see FileInputStream
     */
    public static void readFromFile(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream(System.getProperty("user.home") + "/" + filename));
        instance = (Study) objInStream.readObject();
        objInStream.close();
    }

    /**
     * Löscht alle bisher getätigten Eingaben und setzt die Laufzeitumgebung auf Initialwerte zurück
     *
     * @return sich selbst als {@link Study}
     */
    public Study reset() {
        setTitle("Medizinische Studie");
        setChangesNotSaved(false);
        setFilename("Serialization.study");
        personList.clear();
        return this;
    }

    /**
     * Überprüft, ob alle Änderungen gesichert worden sind. Wird beim Schließen des Programms mit <code>exitSession()</code> aufgerufen.
     *
     * @return TRUE -> Alle Änderungen gespeichert | FALSE -> Offene Änderungen
     */
    public Boolean isChangesNotSaved() {
        return changesNotSaved;
    }


    /**
     * Setter Speichererinnerung
     *
     * @param changesNotSaved Speichererinnerung
     * @return sich selbt als {@link Study}-Objekt
     */
    public Study setChangesNotSaved(Boolean changesNotSaved) {
        this.changesNotSaved = changesNotSaved;
        return this;
    }

    /**
     * Getter Titel der Studie
     *
     * @return Titel der Studie als {@link String}
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter Titel der Studie
     *
     * @param title Titel der Studie
     * @return sich selbt als {@link Study}-Objekt
     */
    public Study setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Fügt Instanzen der Klassen {@link TestSubject} oder {@link Doctor} zu der Liste aller Personen ({@link #personList}) hinzu
     *
     * @param p hinzuzufügenes/r {@link TestSubject} oder {@link Doctor} als Person
     * @return sich selbst als {@link Study}
     */
    public Study addPerson(Person p) {
        personList.add(p);
        setChangesNotSaved(true);
        return this;
    }


    /**
     * Entfernt Instanzen der Klassen {@link TestSubject} oder {@link Doctor} aus der Liste aller Personen ({@link #personList})
     * Vergleicht den {@param p} per PredicateFilter mit dem aktuellen Listenelement n. Es wird auf Identität geprüft.
     *
     * @param p zu Löschendes/r {@link TestSubject} oder {@link Doctor} als Person
     * @return sich selbst als {@link Study}
     */
    public Study removePerson(Person p) {
        personList.removeIf(n -> (n == p));
        setChangesNotSaved(true);
        return this;
    }

    /**
     * Bietet die Möglichkeit nach einem {@link TestSubject} per Nachname zu suchen.
     * <code>for ({@link Person} p : {@link #personList})</code> iteriert über jedes Element in der Liste. Es wird eine Liste mit passenden Objekten zurückgegeben.
     * Mit Hilfe des {@link VisitorIsTestSubject}-Visitors wird überprüft, ob es sich bei der Person um eine TestSubject-Instanz oder um eine Doctor-Instanz handelt. {@link VisitorIsTestSubject} gibt für {@link TestSubject} true, für {@link Doctor} false zurück.
     *
     * @param lastName Nachname für die Suche.
     * @return Liste der gefundenen Objekte.
     * @see TestSubject#getLastName()
     * @see java.lang.String#equalsIgnoreCase(String)
     */
    public List<TestSubject> searchTestSubject(String lastName) {
        List<TestSubject> matchingSubjects = new ArrayList<>();
        for (Person p : personList) {
            if (p.acceptVisitor(new VisitorIsTestSubject()) && p.getLastName().equalsIgnoreCase(lastName)) {
                matchingSubjects.add((TestSubject) p);
            }
        }
        return matchingSubjects;
    }

    /**
     * Bietet die Möglichkeit nach einem {@link TestSubject} per ID zu suchen.
     * <code>for ({@link Person} p : {@link #personList})</code> iteriert über jedes Element in der Liste. Es wird eine Liste mit passenden Objekten zurückgegeben.
     * Mit Hilfe des {@link VisitorIsTestSubject}-Visitors wird überprüft, ob es sich bei der Person um eine TestSubject-Instanz oder um eine Doctor-Instanz handelt. {@link VisitorIsTestSubject} gibt für {@link TestSubject} true, für {@link Doctor} false zurück.
     *
     * @param id ID für die Suche.
     * @return Liste der gefundenen Objekte.
     * @see TestSubject#getTestSubjectID()
     * @see java.util.UUID#equals(Object)
     * @see java.util.List#add(Object)
     */
    public List<TestSubject> searchTestSubject(UUID id) {
        List<TestSubject> matchingSubjects = new ArrayList<>();
        for (Person p : personList) {
            if (p.acceptVisitor(new VisitorIsTestSubject()) && ((TestSubject) p).getTestSubjectID().equals(id)) {
                matchingSubjects.add((TestSubject) p);
            }
        }
        return matchingSubjects;
    }

    /**
     * Bietet die Möglichkeit nach einem {@link TestSubject} per Geburtsdatum zu suchen.
     * <code>for ({@link Person} p : {@link #personList})</code> iteriert über jedes Element in der Liste. Es wird eine Liste mit passenden Objekten zurückgegeben.
     * Mit Hilfe des {@link VisitorIsTestSubject}-Visitors wird überprüft, ob es sich bei der Person um eine TestSubject-Instanz oder um eine Doctor-Instanz handelt. {@link VisitorIsTestSubject} gibt für {@link TestSubject} true, für {@link Doctor} false zurück.
     *
     * @param d Geburtsdatum für die Suche.
     * @return Liste der gefundenen Objekte.
     * @see TestSubject#getDateOfBirth()
     * @see Date#equals(Object)
     * @see java.util.List#add(Object)
     */
    public List<TestSubject> searchTestSubject(Date d) {
        List<TestSubject> matchingSubjects = new ArrayList<>();
        for (Person p : personList) {
            if (p.acceptVisitor(new VisitorIsTestSubject()) && p.getDateOfBirth().equals(d)) {
                matchingSubjects.add((TestSubject) p);
            }
        }
        return matchingSubjects;
    }

    /**
     * Bietet die Möglichkeit nach einem {@link TestSubject} per Geschlecht zu suchen.
     * <code>for ({@link Person} p : {@link #personList})</code> iteriert über jedes Element in der Liste. Es wird eine Liste mit passenden Objekten zurückgegeben.
     * Mit Hilfe des {@link VisitorIsTestSubject}-Visitors wird überprüft, ob es sich bei der Person um eine TestSubject-Instanz oder um eine Doctor-Instanz handelt. {@link VisitorIsTestSubject} gibt für {@link TestSubject} true, für {@link Doctor} false zurück.
     *
     * @param g Geschlecht für die Suche.
     * @return Liste der gefundenen Objekte.
     * @see TestSubject#getGender()
     * @see java.lang.Enum#equals(Object)
     * @see java.util.List#add(Object)
     */
    public List<TestSubject> searchTestSubject(Gender g) {
        List<TestSubject> matchingSubjects = new ArrayList<>();
        for (Person p : personList) {
            if (p.acceptVisitor(new VisitorIsTestSubject()) && p.getGender().equals(g)) {
                matchingSubjects.add((TestSubject) p);
            }
        }
        return matchingSubjects;
    }


    public List<Person> getPersonList() {
        return personList;
    }

    /**
     * Bietet die Möglichkeit nach einem {@link Doctor} per Nachname zu suchen.
     * <code>for ({@link Person} p : {@link #personList})</code> iteriert über jedes Element in der Liste. Es wird eine Liste mit passenden Objekten zurückgegeben.
     * Mit Hilfe des {@link VisitorIsTestSubject}-Visitors wird überprüft, ob es sich bei der Person um eine TestSubject-Instanz oder um eine Doctor-Instanz handelt. {@link VisitorIsTestSubject} gibt für {@link TestSubject} true, für {@link Doctor} false zurück.
     *
     * @param lastName Nachname für die Suche.
     * @return Liste der gefundenen Objekte.
     * @see Doctor#getLastName()
     * @see java.lang.String#equalsIgnoreCase(String)
     */
    public List<Doctor> searchDoctor(String lastName) {
        List<Doctor> matchingSubjects = new ArrayList<>();
        for (Person p : personList) {
            if (!p.acceptVisitor(new VisitorIsTestSubject()) && p.getLastName().equalsIgnoreCase(lastName)) {
                matchingSubjects.add((Doctor) p);
            }
        }
        return matchingSubjects;

    }

    /**
     * Bietet die Möglichkeit nach einem {@link Doctor} per Nachname zu suchen.
     * <code>for ({@link Person} p : {@link #personList})</code> iteriert über jedes Element in der Liste. Es wird eine Liste mit passenden Objekten zurückgegeben.
     * Mit Hilfe des {@link VisitorIsTestSubject}-Visitors wird überprüft, ob es sich bei der Person um eine TestSubject-Instanz oder um eine Doctor-Instanz handelt. {@link VisitorIsTestSubject} gibt für {@link TestSubject} true, für {@link Doctor}
     *
     * @param d Geburtsdatum für die Suche.
     * @return Liste der gefundenen Objekte.
     * @see Doctor#getDateOfBirth()
     * @see Date#equals(Object)
     * @see java.util.List#add(Object)
     */
    public List<Doctor> searchDoctor(Date d) {
        List<Doctor> matchingSubjects = new ArrayList<>();
        for (Person p : personList) {
            if (!p.acceptVisitor(new VisitorIsTestSubject()) && p.getDateOfBirth().equals(d)) {
                matchingSubjects.add((Doctor) p);
            }
        }
        return matchingSubjects;

    }


    /**
     * Bietet die Möglichkeit nach einem {@link Doctor} per Nachname zu suchen.
     * <code>for ({@link Person} p : {@link #personList})</code> iteriert über jedes Element in der Liste. Es wird eine Liste mit passenden Objekten zurückgegeben.
     * Mit Hilfe des {@link VisitorIsTestSubject}-Visitors wird überprüft, ob es sich bei der Person um eine TestSubject-Instanz oder um eine Doctor-Instanz handelt. {@link VisitorIsTestSubject} gibt für {@link TestSubject} true, für {@link Doctor}
     *
     * @param employeeID Mitarbeiter-ID für die Suche.
     * @return Liste der gefundenen Objekte.
     * @see Doctor#getEmployeeID()
     * @see java.util.UUID#equals(Object)
     * @see java.util.List#add(Object)
     */
    public List<Doctor> searchDoctor(UUID employeeID) {
        List<Doctor> matchingSubjects = new ArrayList<>();
        for (Person p : personList) {
            if (!p.acceptVisitor(new VisitorIsTestSubject()) && ((Doctor) p).getEmployeeID().equals(employeeID)) {
                matchingSubjects.add((Doctor) p);
            }
        }
        return matchingSubjects;
    }

    /**
     * Bietet die Möglichkeit nach einem {@link Doctor} per Nachname zu suchen.
     * <code>for ({@link Person} p : {@link #personList})</code> iteriert über jedes Element in der Liste. Es wird eine Liste mit passenden Objekten zurückgegeben.
     * Mit Hilfe des {@link VisitorIsTestSubject}-Visitors wird überprüft, ob es sich bei der Person um eine TestSubject-Instanz oder um eine Doctor-Instanz handelt. {@link VisitorIsTestSubject} gibt für {@link TestSubject} true, für {@link Doctor}
     *
     * @param g Geschlecht für die Suche.
     * @return Liste der gefundenen Objekte.
     * @see Doctor#getGender()
     * @see java.lang.Enum#equals(Object)
     * @see java.util.List#add(Object)
     */
    public List<Doctor> searchDoctor(Gender g) {
        List<Doctor> matchingSubjects = new ArrayList<>();
        for (Person p : personList) {
            if (!p.acceptVisitor(new VisitorIsTestSubject()) && p.getGender().equals(g)) {
                matchingSubjects.add((Doctor) p);
            }
        }
        return matchingSubjects;
    }
}


