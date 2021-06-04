package de.oop.projekt.helper;

import java.io.Serializable;
import java.util.Objects;

/**
 * Die Klasse <code>Date</code> bietet die Möglichkeit ein Datum zu erstellen.
 * <p>
 * Dieses Datum beinhaltet einen Tag, ein Jahr und einen Monat.
 * Es gibt noch die Möglichkeit abzufragen, ob ein Tag der letzte Tag des Monats ist, ob es ein langer oder ein kurzer Monat ist und ob das Jahr ein Schltjahr ist.
 */
public class Date implements Serializable {

    private int day;
    private int month;
    private int year;

    /**
     * Konstruktor der <code>Date</code> - Klasse
     *
     * @param day   Tag
     * @param month Monat
     * @param year  Jahr
     */
    public Date(int day, int month, int year) {
        setYear(year);
        setMonth(month);
        setDay(day);
    }

    /**
     * Diese Methode gibt das {@link Date}-Objekt als String aus
     *
     * @return Datum als {@link String}
     */
    @Override
    public String toString() {
        return getDay() + "." + getMonth() + "." + getYear();
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
        if (!(o instanceof Date)) return false;
        Date date = (Date) o;
        return getDay() == date.getDay() && getMonth() == date.getMonth() && getYear() == date.getYear();
    }


    /**
     * Wandelt die Objektparameter in einen Hash-Wert um
     *
     * @return HASH-Wert des Objekts
     */
    @Override
    public int hashCode() {
        return Objects.hash(getDay(), getMonth(), getYear());
    }

    /**
     * Getter Tag
     *
     * @return Tag
     */
    public int getDay() {
        return day;
    }

    /**
     * Setter Tag
     * Bietet die Möglichkeit einen Tag zu setzen (Setter für day). Ueberprueft, ob der Tag in den Monat passt und passt entsprechend an.
     * Tag zu klein -> Tag = 1 | Tag zu gross -> Tag = 28/29/30/31 (je nach Monat)
     *
     * @param day Tag, der gesetzt werden soll
     * @return sich selbt als {@link Date}-Objekt
     */
    public Date setDay(int day) {
        if (day < 1) {
            this.day = 1;
        } else if (day > 31) {
            if (isLongMonth()) {
                this.day = 31;
            } else if (this.getMonth() != 2) {
                this.day = 30;
            } else {
                if (this.isLeapYear()) {
                    this.day = 29;
                } else {
                    this.day = 28;
                }
            }
        } else if (day > 28 && this.getMonth() == 2 && !isLeapYear()) {
            this.day = 28;
        } else if (day > 29 && this.getMonth() == 2 && isLeapYear()) {
            this.day = 29;
        } else {
            this.day = day;
        }
        return this;
    }

    /**
     * Getter Monat
     *
     * @return Monat
     */
    public int getMonth() {
        return month;
    }

    /**
     * Setter Monat
     * Bietet die Möglichkeit einen Monat zu setzen, Ueberprueft, ob der Monat in ein Jahr passt und passt entsprechend an.
     * Monat zu klein -> Monat = 1 | Monat zu gross -> Monat = 12
     *
     * @param month Monat, der gesetzt werden soll
     * @return sich selbt als {@link Date}-Objekt
     */
    public Date setMonth(int month) {
        if (month < 1) {
            this.month = 1;
        } else if (month > 12) {
            this.month = 12;
        } else {
            this.month = month;
        }
        return this;
    }

    /**
     * Getter Jahr
     *
     * @return Jahr
     */
    public int getYear() {
        return year;
    }

    /**
     * Setter Jahr
     *
     * @param year Jahr, das gesetzt werden soll
     * @return sich selbt als {@link Date}-Objekt
     */
    public Date setYear(int year) {
        this.year = year;
        return this;
    }

    /**
     * Üerprüft, ob ein Jahr ein Schaltjahr ist
     * Wird in {@link #isUltimo()} verwendet
     *
     * @return TRUE -> Schaltjahr | FALSE -> Kein Schaltjahr
     * @see <a href="https://de.wikipedia.org/wiki/Schaltjahr">Wikipedia</a>
     */
    public Boolean isLeapYear() {

        if (this.getYear() % 4 == 0) {
            if (this.getYear() % 100 == 0) {
                if (this.getYear() % 400 == 0) {
                    return true;
                }
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Überprüft mithilfe von {@link #isLongMonth()} und {@link #isLeapYear()}, ob ein Tag in einem Monat der höchste Tag ist.
     *
     * @return TRUE -> Tag ist höchster Tag im Monat | FALSE -> Tag ist nicht höchster Tag im Monat.
     * @see #isLongMonth()
     * @see #isLeapYear()
     */
    public Boolean isUltimo() {
        if (this.getMonth() == 2) {
            if (isLeapYear()) {
                if (this.getDay() == 29) {
                    return true;
                }
            } else {
                if (this.getDay() == 28) {
                    return true;
                }
            }
        }

        if (isLongMonth()) {
            if (this.getDay() == 31)
                return true;
        } else {
            return this.getDay() == 30;
        }

        return false;

    }

    /**
     * Überprüft, ein Monat lang oder kurz ist
     *
     * @return TRUE -> Monat ist lang | FALSE -> Monat ist kurz
     */
    public Boolean isLongMonth() {
        if (this.getMonth() <= 7) {
            return this.getMonth() % 2 == 1;
        } else {
            return this.getMonth() % 2 == 0;
        }
    }


}
