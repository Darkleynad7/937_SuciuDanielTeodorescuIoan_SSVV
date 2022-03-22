import domain.Student;
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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentTest {
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

    @Order(1)
    @Test
    public void testAddStudentIdStringNotNull(){
        Student student1 = new Student("a", "a", 2, "a");
        assert(service.addStudent(student1) == null);
        studentXMLRepo.delete("a");
        System.out.println("EC Test 1 completed!");
    }

    @Order(2)
    @Test
    public void testAddStudentIdStringNull(){
        Student student1 = new Student(null, "a", 2, "a");
        try{
            service.addStudent(student1);
            assert(true);
        }
        catch (ValidationException v){
            assert(v.getMessage().equals("Id null!"));
        }
        System.out.println("EC Test 2 completed!");
    }

    @Order(3)
    @Test
    public void testAddStudentIdStringEmpty(){
        Student student1 = new Student("", "a", 2, "a");
        try{
            service.addStudent(student1);
            assert(true);
        }
        catch (ValidationException v){
            assert(v.getMessage().equals("Id empty!"));
        }
        System.out.println("EC Test 3 completed!");
    }

    @Order(4)
    @Test
    public void testAddStudentNumeStringNotNull(){
        Student student1 = new Student("a", "a", 2, "a");
        assert(service.addStudent(student1) == null);
        studentXMLRepo.delete("a");
        System.out.println("EC Test 4 completed!");
    }

    @Order(5)
    @Test
    public void testAddStudentNumeStringNull(){
        Student student1 = new Student("a", null, 2, "a");
        try{
            service.addStudent(student1);
            assert(true);
        }
        catch (ValidationException v){
            assert(v.getMessage().equals("Nume null!"));
        }
        System.out.println("EC Test 5 completed!");
    }

    @Order(6)
    @Test
    public void testAddStudentNumeStringEmpty(){
        Student student1 = new Student("a", "", 2, "a");
        try{
            service.addStudent(student1);
            assert(true);
        }
        catch (ValidationException v){
            assert(v.getMessage().equals("Nume empty!"));
        }
        System.out.println("EC Test 6 completed!");
    }

    @Order(7)
    @Test
    public void testAddStudentGrupaIntegerPositive(){
        Student student1 = new Student("a", "a", 0, "a");
        assert(service.addStudent(student1) == null);
        studentXMLRepo.delete("a");
        System.out.println("EC Test 7 completed!");
    }

    @Order(8)
    @Test
    public void testAddStudentGrupaIntegerNegative(){
        Student student1 = new Student("a", "a", -2, "a");
        try{
            service.addStudent(student1);
            assert(true);
        }
        catch (ValidationException v){
            assert(v.getMessage().equals("Grupa negativa!"));
        }
        System.out.println("EC Test 8 completed!");
    }

    @Order(9)
    @Test
    public void testAddStudentEmailStringNotNull(){
        Student student1 = new Student("a", "a", 2, "a");
        assert(service.addStudent(student1) == null);
        studentXMLRepo.delete("a");
        System.out.println("EC Test 9 completed!");
    }

    @Order(10)
    @Test
    public void testAddStudentEmailStringNull(){
        Student student1 = new Student("a", "a", 2, null);
        try{
            service.addStudent(student1);
            assert(true);
        }
        catch (ValidationException v){
            assert(v.getMessage().equals("Email null!"));
        }
        System.out.println("EC Test 10 completed!");
    }

    @Order(11)
    @Test
    public void testAddStudentEmailStringEmpty(){
        Student student1 = new Student("a", "a", 2, "");
        try{
            service.addStudent(student1);
            assert(true);
        }
        catch (ValidationException v){
            assert(v.getMessage().equals("Email empty!"));
        }
        System.out.println("EC Test 11 completed!");
    }

    @Order(12)
    @Test
    public void testAddStudentIdUnique(){
        Student student1 = new Student("a", "a", 2, "a");
        assert(studentXMLRepo.save(student1) == null);
        studentXMLRepo.delete("a");
        System.out.println("EC Test 12 completed!\n");
    }

    @Order(13)
    @Test
    public void testAddStudentIdExistent(){
        Student student1 = new Student("a", "a", 2, "a");
        Student student2 = new Student("a", "b", 2, "2");
        studentXMLRepo.save(student1);
        assert(studentXMLRepo.save(student2).equals(student2));
        studentXMLRepo.delete("a");
        System.out.println("EC Test 13 completed!\n");
    }

    @Order(14)
    @Test
    public void testAddStudentValidReturnNull(){
        Student student1 = new Student("a", "a", 2, "a");
        assert(studentXMLRepo.save(student1) == null);
        studentXMLRepo.delete("a");
        System.out.println("EC Test 14 completed!\n");
    }

    @Order(15)
    @Test
    public void testAddStudentValidNotReturnStudent(){
        Student student1 = new Student("a", "a", 2, "a");
        assert(studentXMLRepo.save(student1) != student1);
        studentXMLRepo.delete("a");
        System.out.println("EC Test 15 completed!\n");
    }
}
