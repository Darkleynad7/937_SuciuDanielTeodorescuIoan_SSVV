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
import java.time.LocalDate;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IncrementalIntegration {
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
    public void testIncIntegrationAddStudentIdStringEmpty(){
        Student student1 = new Student("", "a", 2, "a");
        try{
            service.addStudent(student1);
            assert(true);
        }
        catch (ValidationException v){
            assert(v.getMessage().equals("Id empty!"));
        }
        System.out.println("IncI test 1 completed!");
    }

    @Test
    @Order(2)
    public void testIncIntegrationAddStudentValidAddAssignmentIdEmpty(){
        Student student1 = new Student("a", "a", 2, "a");
        Tema tema = new Tema("", "a", 1, 1);

        try {
            service.addStudent(student1);
            service.addTema(tema);
        }
        catch (ValidationException v){
            assert (v.getMessage().equals("Numar tema invalid!"));
        }
        service.deleteStudent("a");
        System.out.println("IncI Test 2 completed!");
    }

    @Test
    @Order(3)
    public void testIncIntegrationAddStudentValidAddAssignmentValidAddGradeNotaLowerThan0(){
        Student student1 = new Student("a", "a", 2, "a");
        Tema tema = new Tema("a", "a", 1, 1);
        Nota nota = new Nota("a", "a", "a", -1D, LocalDate.now());

        try {
            service.addStudent(student1);
            service.addTema(tema);
            service.addNota(nota, "Very Good!\n");
        }
        catch (ValidationException v){
            assert (v.getMessage().equals("Valoarea notei nu este corecta!"));
        }
        service.deleteStudent("a");
        service.deleteTema("a");
        System.out.println("IncI Test 2 completed!");
    }
}
