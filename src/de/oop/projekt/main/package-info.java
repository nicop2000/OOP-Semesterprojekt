/**
 * Alle funktionalen Klassen + Projektbeschreibung
 * <p></p>
 * <h1>OOP - Semesterprojekt Sommersemester 2021</h1>
 * Dieses Projekt beinhaltet verschiedene Klassen für eine selbsterstellte Studie.
 * <p>
 * Alle Klassen implementieren das Interface <code>Serializable</code>, dies bietet die Möglichkeit, eine Instanz dieser Klasse zu serialisieren.
 * Das Serialisieren bietet die Möglichkeit Objektstrukturen und Variablenbelegungen über die Laufzeit eines Programms/ einer Applikation hinaus zu speichern und diese beispielsweise auch in Netzwerken verfügbar zu machen.
 * Die Identität der serialisierten Objekte bleibt auch nach dem Serialisieren erhalten, so zeigt beispielsweise eine Referenz auf ein Objekt auch nach der Wiederherstellung noch auf das selbe Objekt.
 * </p>
 * <b>Ablauf: </b>Es werden entweder Testpersonen oder Ärzte erstellt, diese werden zum Zwecke der Suche in einer Liste von Personen gebündelt. Diese Container werden dann in der Klasse {@link de.oop.projekt.main.Study} instanziiert, serialisiert und in eine Datei geschrieben. Die Beschreibung der einzelnen Klassen und Methoden befindet sich immer direkt an den Methoden oder Klassen.
 *
 * @author Nico Petersen, Andrea Robitzsch, Finn Gründel
 * @version 1.0
 */
package de.oop.projekt.main;