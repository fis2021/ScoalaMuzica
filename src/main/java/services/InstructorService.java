package services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.LoginController;
import exceptions.CouldNotWriteUsersException;
import exceptions.InstructorNotFound;
import model.Instructor;
import model.Student;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.dizitart.no2.objects.filters.ObjectFilters.eq;
import static services.DatabaseService.getDatabase;
import static services.FileSystemService.getPathToFile;

public class InstructorService {

    private static ObjectRepository<Instructor> instructorRepository = getDatabase().getRepository(Instructor.class);
    public static Instructor instructor;
    private static List<Student> students;
    private static List<Student> requests;

    private static String instructorUsername = LoginController.getCurrectUsername();


    public static List<Student> getStudents() {
        return students;
    }

    public static List<Student> getRequests() {
        return requests;
    }

    public static void loadInstructor() throws InstructorNotFound {
        instructor = instructorRepository.find(eq("username", instructorUsername)).firstOrDefault();
        if (instructor == null) {
            throw new InstructorNotFound();
        }
        requests = instructor.getRequests();
        students = instructor.getStudents();
    }

    public static void addStudent(String name, int h1, int h2) {
        students.add(new Student(name, h1, h2));
        instructorRepository.update(instructor);
    }

    public static void deleteRequest(String username) {
        for (Student student : requests) {
            if (Objects.equals(student.getStudentName(), username)) {
                requests.remove(student);
                break;
            }
        }
        instructorRepository.update(instructor);
    }

}