package services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.*;
import model.Student;
import model.Instructor;
import controllers.RequestInstructorController;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import static org.dizitart.no2.objects.filters.ObjectFilters.eq;
import static services.DatabaseService.getDatabase;
import static services.FileSystemService.getPathToFile;

public class StudentService {
    public static String username;
    public static List<Student> requests;

    private static ObjectRepository<Instructor> instructorRepository = getDatabase().getRepository(Instructor.class);

    public static void loadDatabase() {
        Nitrite database = getDatabase();
        instructorRepository = database.getRepository(Instructor.class);
    }

    private static void checkUserIsNotEmpty(String username) throws NoUserName {
        if (Objects.equals(username, ""))
            throw new NoUserName(username);
    }

    public static void addRequest(String username, int enH, int exH) throws NoUserName, NoExitHour, NoEntryHour, IOException, InstructorNotFound {
        String instructor_user = RequestInstructorController.getUser();
        checkUserIsNotEmpty(instructor_user);

        Instructor instructor = instructorRepository.find(eq("username", instructor_user)).firstOrDefault();
        if(instructor == null){
            throw new InstructorNotFound();
        }
        List<Student> instructor_requests = instructor.getRequests();
        checkUserIsNotEmpty(username);
        instructor_requests.add(new Student(username, enH, exH));
    }

    public static void checkEnHIsNotEmpty(String enH) throws NoEntryHour {
        if (Objects.equals(enH, ""))
            throw new NoEntryHour(enH);
    }

    public static void checkExHIsNotEmpty(String exH) throws NoExitHour {
        if (Objects.equals(exH, ""))
            throw new NoExitHour(exH);
    }

}
