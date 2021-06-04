package de.oop.projekt.test;

import de.oop.projekt.helper.Date;
import de.oop.projekt.main.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;


class StudyTest {

    //    DoctorContainer doctorContainer = Study.getInstance().getDoctorContainer();
//    TestSubjectContainer testSubjectContainer = Study.getInstance().getTestSubjectContainer();
    TestSubject nico = new TestSubject("Nico", "Petersen", new Date(7, 12, 2000), Gender.MALE);
    TestSubject nico2 = new TestSubject("Nico", "Petersen", new Date(7, 12, 2000), Gender.MALE);
    TestSubject andrea = new TestSubject("Andrea", "Robitzsch", new Date(21, 10, 1986), Gender.FEMALE);
    //    TestSubject finn = new TestSubject("Finn", "Gruendel", new Date(16, 2, 1999), Gender.MALE);
    Doctor kielholz = new Doctor("Dr.", "Johann", "Kielholz", new Date(12, 7, 1956), "Allgemeinmedizin", Gender.MALE, new Date(01, 10, 1984));
    Doctor thorsen = new Doctor("Dr.", "Nana", "Thorsen", new Date(23, 5, 1970), "Allgemeinmedizin", Gender.FEMALE, new Date(15, 07, 2000));
    Doctor karev = new Doctor("Dr.", "Alex", "Karev", new Date(1, 5, 1980), "Pediatrician", Gender.MALE, new Date(01, 12, 2012));

    //
//
    @Test
    void testNewGenderSearch() {
        TestSubject nico = new TestSubject("Nico", "Petersen", new Date(7, 12, 2000), Gender.MALE);
        TestSubject nico2 = new TestSubject("Nico", "Petersen", new Date(7, 12, 2000), Gender.MALE);
        TestSubject andrea = new TestSubject("Andrea", "Robitzsch", new Date(21, 10, 1986), Gender.FEMALE);
        TestSubject finn = new TestSubject("Finn", "Gruendel", new Date(16, 2, 1999), Gender.MALE);
        Doctor kielholz = new Doctor("Dr.", "Johann", "Kielholz", new Date(12, 7, 1956), "Allgemeinmedizin", Gender.MALE, new Date(01, 10, 1984));
        Doctor thorsen = new Doctor("Dr.", "Nana", "Thorsen", new Date(23, 5, 1970), "Allgemeinmedizin", Gender.FEMALE, new Date(15, 07, 2000));
        Doctor karev = new Doctor("Dr.", "Alex", "Karev", new Date(1, 5, 1980), "Pediatrician", Gender.MALE, new Date(01, 12, 2012));
        Study.getInstance().addPerson(nico).addPerson(nico2).addPerson(andrea).addPerson(finn).addPerson(kielholz).addPerson(thorsen).addPerson(karev);
        List<TestSubject> lTS = Study.getInstance().searchTestSubject(Gender.MALE);
    }

//    @Test
//    void personenVornamen() {
//        assertEquals("Nico", nico.getFirstName());
//        assertEquals("Andrea", andrea.getFirstName());
//        assertEquals("Finn", finn.getFirstName());
//
//    }
//
//    @Test
//    void equalObjects() {
//        assertEquals(nico2, nico);
//    }
//
//    @Test
//    void personenNachnamen() {
//        assertEquals("Petersen", nico.getLastName());
//        assertEquals("Robitzsch", andrea.getLastName());
//        assertEquals("Gruendel", finn.getLastName());
//    }
//
//    @Test
//    void addPersonsToContainer() {
//        testSubjectContainer.addTestSubjectToList(nico);
//        testSubjectContainer.addTestSubjectToList(andrea);
//        testSubjectContainer.addTestSubjectToList(finn);
//    }
//
//    @Test
//    void addDoctorsToContainer() {
//
//    }
//
//    @Test
//    void getIndexesFromPersons() {
//        addPersonsToContainer();
//        //assertEquals(nico, testSubjectContainer.searchTestSubjectByLastName("Petersen"));
//        // assertEquals(andrea, TestSubjectContainer.searchForTestSubjectIndexInList(andrea));
//    }
//
//    @Test
//    void checkintolerance() {
//        nico.setIntolerances("Laktose, Fruktose");
//        testSubjectContainer.addTestSubjectToList(nico);
//
//        List<TestSubject> results = testSubjectContainer.searchTestSubjectIntolerance("Laktose");
//        assertTrue(results.get(0).equals(nico));
//        results = testSubjectContainer.searchTestSubjectIntolerance("Fruktose");
//        assertTrue(results.get(0).equals(nico));
//
//    }
//
//    @Test
//    void serializeStudy() {
//
////        addPersonsToContainer();
////        Serializer s = new Serializer("Serialization.study");
////        try {
////            s.writeToFile(Study.getInstance());
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//    }
//
//    @Test
//    void getStudyFromFile() {
////        Serializer s = new Serializer("Serialization.study");
////        try {
////            s.readFromFile();
////            checkInstances();
////            System.out.println(Study.getInstance().getTestSubjectContainer().getTestSubjectList().get(0));
////            System.out.println(Study.getInstance().getTestSubjectContainer().searchTestSubjectByLastName("Petersen"));
////            //assertEquals(0, Study.getInstance().getTestSubjectContainer().searchTestSubjectByLastName("Petersen"));
////		} catch (IOException | ClassNotFoundException e){
////			e.printStackTrace();
////		}
//    }
//
//    @Test
//    void testDurationOfEmployment() {
//        System.out.println(kielholz.durationOfEmployment());
//        System.out.println(thorsen.durationOfEmployment());
//        System.out.println(karev.durationOfEmployment());
//
//
//    }


}