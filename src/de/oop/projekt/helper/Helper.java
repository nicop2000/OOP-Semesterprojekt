package de.oop.projekt.helper;


import java.util.Arrays;

/**
 * Diese Klasse bietet bestimmte Funktionen zur Überprüfung von Eingaben und Konvertierung in Objekte.
 */
public class Helper {


    /**
     * Überprüft mithilfe einer <code>Regular Expression</code>, ob ein Datumstring korrekt ist.
     *
     * @param text Eingabestring
     * @return TRUE -> Korrekter String | FALSE -> String entspricht nicht den Anfroderungen
     * @see <a href="https://regexr.com/">RegEx - Webseite</a>
     */
    public static Boolean dateFieldValid(String text) {
        return text.matches("([0-9]{1,2})\\.([0-9]{1,2})\\.([0-9]{4})");
    }


    /**
     * Konvertiert einen Datumstring in ein <code>{@link Date}</code>-Objekt
     * Wenn der Datum-String nicht valide ist, wird das Standarddatum 01.01.1970 zurückgegeben.
     *
     * @param dateAsString Datumstring
     * @return Datumobjekt
     */
    public static Date stringToDate(String dateAsString) {
        if (!Helper.dateFieldValid(dateAsString)) {
            return new Date(1, 1, 1970);
        }
        String[] dateAsArray = dateAsString.split("\\.");
        System.out.println(Arrays.toString(dateAsArray));
        int day = Integer.parseInt(dateAsArray[0]);
        int month = Integer.parseInt(dateAsArray[1]);
        int year = Integer.parseInt(dateAsArray[2]);
        return new Date(day, month, year);
    }
}



