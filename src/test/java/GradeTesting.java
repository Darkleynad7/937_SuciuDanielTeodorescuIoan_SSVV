import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GradeTesting {
    private StudentXMLRepo studentXMLRepo;
    private NotaXMLRepo notaXMLRepo;
    private TemaXMLRepo temaXMLRepo;
    private StudentValidator studentValidator;
    private TemaValidator temaValidator;
    private NotaValidator notaValidator;
    private Service service;

    @Before
    public void setUp() throws IOException {
        File file = new File("src\\test\\files\\StudentTest.xml");
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><inbox></inbox>");
        fileWriter.flush();
        studentXMLRepo = new StudentXMLRepo("src\\test\\files\\StudentTest.xml");

        file = new File("src\\test\\files\\TemaTest.xml");
        file.createNewFile();
        fileWriter = new FileWriter(file);
        fileWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><inbox></inbox>");
        fileWriter.flush();
        temaXMLRepo = new TemaXMLRepo("src\\test\\files\\TemaTest.xml");

        file = new File("src\\test\\files\\NotaTest.xml");
        file.createNewFile();
        fileWriter = new FileWriter(file);
        fileWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><inbox></inbox>");
        fileWriter.flush();
        notaXMLRepo = new NotaXMLRepo("src\\test\\files\\NotaTest.xml");

        studentValidator = new StudentValidator();
        temaValidator = new TemaValidator();
        notaValidator = new NotaValidator(studentXMLRepo, temaXMLRepo);

        service = new Service(studentXMLRepo, studentValidator, temaXMLRepo, temaValidator, notaXMLRepo, notaValidator);
    }

    @After
    public void tearDown(){
        File file = new File("src\\test\\files\\StudentTest.xml");
        file.delete();

        file = new File("src\\test\\files\\TemaTest.xml");
        file.delete();

        file = new File("src\\test\\files\\NotaTest.xml");
        file.delete();
    }

    @Test
    @Order(1)
    public void testAddStudentIdStringNull(){
        Student student1 = new Student(null, "a", 2, "a");
        try{
            service.addStudent(student1);
            assert(true);
        }
        catch (ValidationException v){
            assert(v.getMessage().equals("Id null!"));
        }
        System.out.println("BBT Test 1 completed!");
    }

    @Test
    @Order(2)
    public void testAddAssignmentIdEmpty(){
        Tema tema = new Tema("", "a", 1, 1);
        try {
            service.addTema(tema);
        }
        catch (ValidationException v){
            assert (v.getMessage().equals("Numar tema invalid!"));
        }
        System.out.println("BBT Test 2 completed!");
    }

    @Test
    @Order(3)
    public void testAddGradeStudentNotExistent(){
        Nota nota = new Nota("a", "a", "a", 10F, LocalDate.now());
        try{
            service.addNota(nota, "Feedback");
        }
        catch (ValidationException v){
            assert (v.getMessage().equals("Studentul nu exista!"));
        }
        System.out.println("BBT Test 3 completed");
    }

    @Test
    @Order(4)
    public void testAddGradeValid(){
        Student student = new Student("a", "a", 1, "a");
        Tema tema = new Tema("a", "a", 3, 1);
        Nota nota = new Nota("a", "a", "a", 10F, LocalDate.now());
        service.addStudent(student);
        service.addTema(tema);
        assert(service.addNota(nota, "Very very good!") == 10F);
        System.out.println("BBT Test 4 completed");
    }
}
