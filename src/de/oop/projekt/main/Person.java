package de.oop.projekt.main;
import de.oop.projekt.helper.Date;

import java.io.Serializable;
import java.util.Objects;

/**
 * Die abstrakte Klasse <code>Person</code> implementiert das Interface {@link Serializable} und beherbergt verschiedene Attribute {@link #firstName}, {@link #lastName}, {@link #gender} und {@link #dateOfBirth}, die sich die erbenden Klassen teilen.
 */
public abstract class Person implements Serializable {

    String firstName;
    String lastName;
    Gender gender;
    Date dateOfBirth;

    /**
     * Konstruktor der Klasse Person
     * Dieser Konstruktor ist protected, damit die Sichtbarkeit nicht im ganzen Programm, sondern nur in den erbenden Klassen verwendet werden kann.
     *
     * @param firstName   Vorname
     * @param lastName    Nachname
     * @param dateOfBirth {@link Date Geburtsdatum}
     * @param gender      {@link Gender Geschlecht}
     */
    protected Person(String firstName, String lastName, Date dateOfBirth, Gender gender) {
        this.setFirstName(firstName).setLastName(lastName).setDateOfBirth(dateOfBirth).setGender(gender);
    }
//TODO passt das hier mit der veränderung        Nico: ¿Welche Änderung?

    /**
     * Abstrakte Methode: verpflichtet die erbenden Klassen dazu eine Methode zu implementieren, die
     * einen Visitor akzeptiert
     *
     * @param v {@link Visitor}
     * @return {@link Boolean}
     */
    public abstract Boolean acceptVisitor(Visitor v);

    /**
     * Getter Vorname
     *
     * @return Vorname als {@link String}
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter Vorname
     *
     * @param firstName Vorname
     * @return sich selbt als {@link Person}-Objekt
     */
    public Person setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * Getter Nachname
     *
     * @return Nachname als {@link String}
     */
    public String getLastName() {
        return lastName;
    }


    /**
     * Setter Nachname
     *
     * @param lastName Nachname
     * @return sich selbt als {@link Person}-Objekt
     */
    public Person setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }


    /**
     * Getter Geschlecht
     *
     * @return Geschlecht als {@link Gender}-Objekt
     */
    public Gender getGender() {
        return gender;
    }


    /**
     * Setter Geschlecht
     *
     * @param gender Geschlecht
     * @return sich selbt als {@link Person}-Objekt
     */
    public Person setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    /**
     * Getter Datum
     *
     * @return Datum als {@link Date}
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }


    /**
     * Setter Geburtsdatum
     *
     * @param dateOfBirth Geburtsdatum
     * @return sich selbst als {@link Person}-Objekt
     */
    public Person setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    /**
     * Vergleicht ein übergebenes Objekt und das aufgerufene Objekt auf Gleichheit
     *
     * @param o Zu vergleichendes Objekt
     * @return TRUE -> Datum ist gleich | FALSE -> Datum unterscheidet sich
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(dateOfBirth, person.dateOfBirth);
    }


    /**
     * Wandelt die Objektparameter in einen Hash-Wert um
     *
     * @return HASH-Wert des Objekts
     */
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, dateOfBirth);
    }

    /**
     * Gibt die Person als String aus. Standardfunktion auf die die erbenden Klassen {@link TestSubject} und {@link Doctor} zurückgreifen
     *
     * @return Doctor als {@link String}
     */
    @Override
    public String toString() {
        return firstName + " " + lastName + ",  " + getGender().toString() + "," + " * " + dateOfBirth;
    }


}
