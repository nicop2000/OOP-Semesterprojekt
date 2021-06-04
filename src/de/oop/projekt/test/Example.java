package de.oop.projekt.test;


import de.oop.projekt.helper.Date;
import de.oop.projekt.main.*;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Example {

    TestSubject person1 = new TestSubject("Nico", "Petersen", new Date(7, 12, 2000), Gender.MALE);
    TestSubject person2 = new TestSubject("Andrea", "Robitzsch", new Date(5, 4, 1990), Gender.FEMALE, "Caspar, WA");
    TestSubject person3 = new TestSubject("Finn", "Gründel", new Date(2, 5, 1999), Gender.DIVERS);
    Doctor doctor1 = new Doctor("Dr.", "Andy", "Arbeit", new Date(12, 4, 1965), "Internist", Gender.MALE, new Date(4, 4, 2012));
    Doctor doctor2 = new Doctor("Dr.", "Franziska", "Meier", new Date(4, 10, 1975), "Hautarzt", Gender.FEMALE, new Date(2, 8, 2002));
    Doctor doctor3 = new Doctor("Dr.", "Conchita", "Wurst", new Date(9, 7, 1944), "Orthopäde", Gender.DIVERS, new Date(8, 5, 2021));


    @Test
    void deleteAll() {
        Study.getInstance().getPersonList().clear();
    }

    @Test
    void init() {
        deleteAll();
        Study.getInstance().addPerson(person1);
        Study.getInstance().addPerson(person2);
        Study.getInstance().addPerson(person3);
        Study.getInstance().addPerson(doctor1);
        Study.getInstance().addPerson(doctor2);
        Study.getInstance().addPerson(doctor3);
    }

    @Test
    void checkCreate() {
        init();
        checkPersons();
    }

    void checkPersons() {
        assertEquals(Study.getInstance().getPersonList().get(0), person1);
        assertEquals(Study.getInstance().getPersonList().get(1), person2);
        assertEquals(Study.getInstance().getPersonList().get(2), person3);
        assertEquals(Study.getInstance().getPersonList().get(3), doctor1);
        assertEquals(Study.getInstance().getPersonList().get(4), doctor2);
        assertEquals(Study.getInstance().getPersonList().get(5), doctor3);
    }

    @Test
    void read() {
        init();
        System.out.println(person2);
        System.out.println(doctor3);
        assertEquals("Nico", person1.getFirstName());
        assertEquals(Gender.FEMALE, doctor2.getGender());
        assertEquals(new Date(7, 12, 2000), person1.getDateOfBirth());
        assertEquals("Caspar, WA", person2.getIntolerance());
    }

    @Test
    void update() {
        init();
        TestSubject person1Updated = new TestSubject("Eric", "Petersen", new Date(7, 12, 2000), Gender.MALE);
        Doctor doctor1Updated = new Doctor("Prof. Dr.", "Andy", "Arbeit", new Date(12, 4, 1965), "Internist", Gender.DIVERS, new Date(4, 4, 2012));
        person1.update("Eric", person1.getLastName(), person1.getIntolerance(), person1.getDateOfBirth(), person1.getGender());
        doctor1.update("Prof. Dr.", doctor1.getFirstName(), doctor1.getLastName(), doctor1.getDateOfBirth(), doctor1.getGender(), doctor1.getSpecialty(), doctor1.getDateOfEntry());
        assertEquals(person1Updated, person1);
        assertEquals(doctor1Updated, doctor1);
    }

    @Test
    void delete() {
        init();
        List<TestSubject> results = Study.getInstance().searchTestSubject(new Date(7, 12, 2000));
        assertEquals(1, results.size());
        Study.getInstance().removePerson(person1);
        List<TestSubject> resultsAfterRemove = Study.getInstance().searchTestSubject(new Date(7, 12, 2000));
        assertEquals(0, resultsAfterRemove.size());
    }

    @Test
    void importExport() {
        init();
        try {
            Study.writeToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Study.getInstance().reset();
        assertEquals(0, Study.getInstance().getPersonList().size());
        assertEquals("Medizinische Studie", Study.getInstance().getTitle());
        assertEquals("Serialization.study", Study.getFilename());
        try {
            Study.readFromFile("Serialization.study");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals(1, Study.getInstance().searchTestSubject(new Date(7, 12, 2000)).size());
        checkPersons();
    }


}
