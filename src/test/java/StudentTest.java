import domain.Student;
import org.junit.Test;
import repository.StudentXMLRepo;

import java.util.Objects;

public class StudentTest {
    @Test
    public void testAddStudentGoodId(){
        StudentXMLRepo studentXMLRepo = new StudentXMLRepo("StudentTest.xml");
        Student student = new Student("1", "A", 1, "A");
        assert (studentXMLRepo.save(student) == null);
        studentXMLRepo.delete("1");
    }

    @Test
    public void testAddStudentIdExists(){
        StudentXMLRepo studentXMLRepo = new StudentXMLRepo("StudentTest.xml");
        Student student1 = new Student("1", "A", 1, "A");
        Student student2 = new Student("1", "B", 2, "B");
        studentXMLRepo.save(student1);
        assert (studentXMLRepo.save(student2).equals(student1));
        studentXMLRepo.delete("1");
    }
}
