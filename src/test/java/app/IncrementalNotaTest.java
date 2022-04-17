package app;

import app.domain.Nota;
import app.domain.Student;
import app.domain.Tema;
import app.repository.NotaXMLRepository;
import app.repository.StudentXMLRepository;
import app.repository.TemaXMLRepository;
import app.service.Service;
import app.validation.NotaValidator;
import app.validation.StudentValidator;
import app.validation.TemaValidator;
import app.validation.Validator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IncrementalNotaTest {
    private static Service service;

    @Before
    public void init() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "test-data/" + "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "test-data/" + "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "test-data/" + "note.xml");

        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @After
    public void clean() {
        service.deleteStudent("2");
        service.deleteTema("2");
    }

    @Test
    public void givenValidInput_whenAddStudent_thenReturnOne() {
        var result = service.saveStudent("2", "Nume", 937);

        assertEquals(1, result);
    }

    @Test
    public void givenValidInput_whenAddAssignment_thenReturnOne() {
        service.saveStudent("2", "Nume", 937);
        int result = service.saveTema("2", "Descriere", 7, 2);

        assertEquals(1, result);
    }

    @Test
    public void givenValidInput_whenAddGrade_thenReturnOne() {
        service.saveStudent("2", "Nume", 937);
        service.saveTema("2", "Descriere", 7, 2);
        int result = service.saveNota("2", "2", 9.5, 12, "good");

        assertEquals(1, result);
    }
}
