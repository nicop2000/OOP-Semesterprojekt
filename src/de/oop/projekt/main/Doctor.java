package de.oop.projekt.main;

//import java.time.LocalDate;

import de.oop.projekt.helper.Date;

import java.util.*;
import java.time.*;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Die Klasse <code>Doctor</code> erweitert die Klasse {@link Person} und fügt verschiedene Attribute {@link #preTitle}, {@link #specialty}, {@link #dateOfEntry} und {@link #employeeID} hinzu.
 */
public class Doctor extends Person {

    private String preTitle = "";
    private String specialty = "";
    private Date dateOfEntry;
    private UUID employeeID = UUID.randomUUID();

    /**
     * Kontruktor für das Objekt Doctor. Stützt sich auf den Konstruktor der Superklasse.
     *
     * @param preTitle    Titel
     * @param firstname   Vorname
     * @param lastname    Nachname
     * @param dateOfBirth Geburtsdatum
     * @param specialty   Fachgebiet
     * @param gender      Geschlecht
     * @param dateOfEntry Erhaltsdatum
     */
    public Doctor(String preTitle, String firstname, String lastname, Date dateOfBirth, String specialty, Gender gender, Date dateOfEntry) {
        super(firstname, lastname, dateOfBirth, gender);
        this.setPreTitle(preTitle).setSpecialty(specialty).setDateOfEntry(dateOfEntry);
    }

    public Boolean acceptVisitor(Visitor v) {
        return v.visit(this);
    }

    /**
     * Update Methode für ein bereits instantiiertes Objekt der Klasse Doctor
     *
     * @param preTitle    Titel
     * @param firstname   Vorname
     * @param lastname    Nachname
     * @param dateOfBirth Geburtsdatum
     * @param gender      Geschlecht
     * @param specialty   Fachgebiet
     * @param dateOfEntry Eintrittsdatum
     * @return this
     */

    public Doctor update(String preTitle, String firstname, String lastname, Date dateOfBirth, Gender gender, String specialty, Date dateOfEntry) {
        this.setPreTitle(preTitle).setSpecialty(specialty).setDateOfEntry(dateOfEntry).setFirstName(firstname).setLastName(lastname).setDateOfBirth(dateOfBirth).setGender(gender);
        return this;
    }

    /**
     * Getter Fachgebiet
     *
     * @return Fachgebiet
     */
    public String getSpecialty() {
        return specialty;
    }


    /**
     * Setter Fachgebiet
     *
     * @param specialty Fachgebiet
     * @return sich selbt als {@link Doctor}-Objekt
     */
    public Doctor setSpecialty(String specialty) {
        this.specialty = specialty;
        return this;
    }

    /**
     * Getter UUID
     *
     * @return UUID
     */
    public UUID getEmployeeID() {
        return employeeID;
    }


    /**
     * Getter Titel
     *
     * @return Titel
     */
    public String getPreTitle() {
        return preTitle;
    }

    /**
     * Setter Titel
     *
     * @param preTitle Titel
     * @return sich selbt als {@link Doctor}-Objekt
     */
    public Doctor setPreTitle(String preTitle) {
        this.preTitle = preTitle;
        return this;
    }

    /**
     * Getter Eintrittsdatum
     *
     * @return Eintrittsdatum
     */
    public Date getDateOfEntry() {
        return this.dateOfEntry;
    }

    /**
     * Setter Eintrittsdatum
     *
     * @param dateOfEntry Eintrittsdatum
     * @return sich selbt als {@link Doctor}-Objekt
     */
    public Doctor setDateOfEntry(Date dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
        return this;
    }

    /**
     * Gibt die Dauer der Anstellung eines Doktors in Tagen zurück.
     *
     * @return long Dauer der Anstellung
     */
    public long durationOfEmployment() {

        LocalDate startDate = LocalDate.of(this.getDateOfEntry().getYear(), this.getDateOfEntry().getMonth(), this.getDateOfEntry().getDay());
        LocalDate currentDate = LocalDate.now();
        long duration = DAYS.between(startDate, currentDate);

        return duration;
    }


// to do

    /**
     * Gibt das Doctor-Objekt als String aus. Greift dabei unter Anderem auf die {@link Person#toString() Standardimplementierung} der abstrakten Superklasse {@link Person} zurück.
     *
     * @return Doctor als {@link String}
     */
    @Override
    public String toString() {
        return "Arzt: " + getPreTitle() + " " + super.toString() + ", " + " (Mitarbeiter-ID: " + getEmployeeID() + "), Eintrittsdatum: " + getDateOfEntry() + ")";
    }
}
