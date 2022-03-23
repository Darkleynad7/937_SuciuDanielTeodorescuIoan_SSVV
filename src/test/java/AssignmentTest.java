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

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AssignmentTest {
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
    public void tearDown() {
        File file = new File("src\\test\\files\\StudentTest.xml");
        file.delete();

        file = new File("src\\test\\files\\TemaTest.xml");
        file.delete();

        file = new File("src\\test\\files\\NotaTest.xml");
        file.delete();
    }

    @Test
    @Order(1)
    public void testAddAssignmentIdValid(){
        Tema tema = new Tema("a", "a", 2, 1);
        assert (service.addTema(tema) == null);
        temaXMLRepo.delete("a");
        System.out.println("Test nr 1 completed!");
    }

    @Test
    @Order(2)
    public void testAddAssignmentIdNull(){
        Tema tema = new Tema(null, "a", 2, 1);
        try {
            service.addTema(tema);
        }
        catch (ValidationException v){
            assert (v.getMessage().equals("Numar tema null!"));
        }
        System.out.println("Test nr 2 completed!");
    }
}
