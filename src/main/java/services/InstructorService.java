package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Instructor;
import model.User;
import model.Student;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import java.io.IOException;
import java.util.List;

import static services.FileSystemService.getPathToFile;

public class InstructorService {

    private static List<Student> students;
    private static ObjectRepository<Instructor> instructorRepository;
    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("registration-example.db").toFile())
                .openOrCreate("instructor", "instructor");

        instructorRepository = database.getRepository(Instructor.class);
    }

    public static void loadUsersFromFile() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();


    }

}