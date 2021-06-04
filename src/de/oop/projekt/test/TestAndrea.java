package de.oop.projekt.test;

import de.oop.projekt.helper.Date;
import de.oop.projekt.main.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestAndrea {

    Doctor doc1 = new Doctor("Dr.", "Alex", "Robitzsch", new Date(1, 4, 1979),
            "Onkologie", Gender.MALE, new Date(15, 8, 1990));
    Doctor doc2 = new Doctor("Dr.", "Nico", "Petersen", new Date(1, 1, 1996),
            "Zahnarzt", Gender.MALE, new Date(15, 10, 2020));
    TestSubject tS1 = new TestSubject("Johann", "Mueller", new Date(3, 10, 1960), Gender.DIVERS);
    TestSubject tS2 = new TestSubject("Maria", "Log", new Date(6, 11, 2004), Gender.MALE);
    TestSubject tS3 = new TestSubject("Paula", "Wolz", new Date(24, 12, 1998), Gender.FEMALE);
    TestSubject tS4 = new TestSubject("Manja", "Scholz", new Date(2, 11, 1988), Gender.FEMALE);
    // Tests schreiben zu diversen Methoden

    @Test
    void create() {

        Study.getInstance().addPerson(doc1);
        Study.getInstance().addPerson(doc2);
        Study.getInstance().addPerson(tS1);
        Study.getInstance().addPerson(tS2);
        Study.getInstance().addPerson(tS3);
        Study.getInstance().addPerson(tS4);
        assertEquals(6, Study.getInstance().getPersonList().size());


    }

    @Test
    void checkSearch() {
        //suchmethode nach Mueller und gucke ob dieselbe Instanz mit assertEquals

        Study.getInstance().addPerson(doc1);
        Study.getInstance().addPerson(doc2);
        Study.getInstance().addPerson(tS1);
        Study.getInstance().addPerson(tS2);
        Study.getInstance().addPerson(tS3);
        Study.getInstance().addPerson(tS4);

        assertEquals("Mueller", Study.getInstance().searchTestSubject("Mueller").get(0).getLastName());

        assertEquals(2, Study.getInstance().searchTestSubject(Gender.FEMALE).size());
        Date date1 = new Date(6, 11, 2004);
        assertEquals(new Date(6, 11, 2004), Study.getInstance().searchTestSubject(date1).get(0).getDateOfBirth());

    }

    @Test
    void update() {

    }

}
