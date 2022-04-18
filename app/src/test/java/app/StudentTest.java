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

public class StudentTest {
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
        service.deleteStudent("1");
    }

    @Test
    public void givenOutOfUpperRangeGroup_whenAddStudent_thenReturnZero() {
        int result = service.saveStudent("1", "Nume", 938);

        assertEquals(0, result);
    }

    @Test
    public void givenOutOfLowerRangeGroup_whenAddStudent_thenReturnZero() {
        int result = service.saveStudent("1", "Nume", 110);

        assertEquals(0, result);
    }

    @Test
    public void givenLowerRangeBorderGroup_whenAddStudent_thenReturnOne() {
        int result = service.saveStudent("1", "Nume", 111);

        assertEquals(1, result);
    }

    @Test
    public void givenInLowerRangeGroup_whenAddStudent_thenReturnOne() {
        int result = service.saveStudent("1", "Nume", 112);

        assertEquals(1, result);
    }

    @Test
    public void givenInUpperRangeGroup_whenAddStudent_thenReturnOne() {
        int result = service.saveStudent("1", "Nume", 936);

        assertEquals(1, result);
    }

    @Test
    public void addStudent_ValidInput_Success() {
        var result = service.saveStudent("1", "Nume", 937);

        assertEquals(1, result);
    }

    @Test
    public void addStudent_EmptyStringId_ValidationError() {
        var result = service.saveStudent("", "Nume", 937);

        assertEquals(0, result);
    }

    @Test
    public void addStudent_NullId_ValidationError() {
        var result = service.saveStudent(null, "Nume", 937);

        assertEquals(0, result);
    }

    @Test
    public void addStudent_EmptyStringNume_ValidationError() {
        var result = service.saveStudent("1", "", 937);

        assertEquals(0, result);
    }

    @Test
    public void addStudent_NullNume_ValidationError() {
        var result = service.saveStudent("1", null, 937);

        assertEquals(0, result);
    }
}
