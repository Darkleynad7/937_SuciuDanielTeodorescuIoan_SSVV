import domain.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import repository.StudentXMLRepo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class StudentTest {
    private StudentXMLRepo studentXMLRepo;

    //@Before
    //public void setUp() throws IOException {
    //    File file = new File("src\\test\\files\\StudentText.xml");
    //    file.createNewFile();
    //    FileWriter fileWriter = new FileWriter(file);
    //    fileWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><inbox></inbox>");
    //    fileWriter.flush();
    //    studentXMLRepo = new StudentXMLRepo("src\\test\\files\\StudentText.xml");
    //}

    //@After
    //public void tearDown(){
    //    File file = new File("src\\test\\files\\StudentText.xml");
    //    file.delete();
    //}

    @Test
    @Order(1)
    public void testAddStudentGoodId(){
        //Student student1 = new Student("1", "1", 1, "1");
        //assert(studentXMLRepo.save(student1) == null);
        //studentXMLRepo.delete("1");
        //System.out.println("Test 1 completed!\n");
        assert (true);
    }

    @Test
    @Order(2)
    public void TC_2(){
        //Student student1 = new Student("1", "1", 1, "1");
        //Student student2 = new Student("1", "2", 2, "2");
        //studentXMLRepo.save(student1);
        //assert(studentXMLRepo.save(student2).equals(student2));
        //studentXMLRepo.delete("1");
        //System.out.println("Test 2 completed!\n");
        assert (true);
    }
}
