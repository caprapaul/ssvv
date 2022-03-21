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
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TemaTest {
    private Service service;

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
        service.deleteTema("1");
    }

    @Test
    public void givenValidInput_whenAddAssignment_thenReturnOne() {
        int result = service.saveTema("1", "Descriere", 7, 2);

        assertEquals(1, result);
    }

    @Test
    public void givenOutOfUpperRangeDeadline_whenAddAssignment_thenReturnZero() {
        int result = service.saveTema("1", "Descriere", 15, 2);

        assertEquals(0, result);
    }
}
