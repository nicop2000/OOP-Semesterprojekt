package de.oop.projekt.main;

import javax.print.Doc;

public interface Visitor {

    Boolean visit(TestSubject testSubject);

    Boolean visit(Doctor doctor);
}
