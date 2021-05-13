package services;

import model.Student;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import java.util.List;

import static services.FileSystemService.getPathToFile;

public class InstructorService {

    private static ObjectRepository<Student> studentRepository;
    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("registration-example.db").toFile())
                .openOrCreate("student", "student");

        studentRepository = database.getRepository(Student.class);
    }

    public static List<Student> getStudents(){
        return studentRepository.find().toList();
    }

    public static void displayStudents(){

        for (Student student : studentRepository.find()){
            System.out.println(student.getStudentName()+" "+student.getEntryHour()+" "+student.getExitHour());
        }
    }

}