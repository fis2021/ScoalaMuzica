package services;

import model.Instructor;
import model.User;
import org.dizitart.no2.Nitrite;

import static services.FileSystemService.getPathToFile;

public class DatabaseService {
    private static Nitrite database;

    public DatabaseService() {
        initDatabase();
    }

    public static void initDatabase() {
        database = Nitrite.builder()
                .filePath(getPathToFile("registration-example.db").toFile())
                .openOrCreate("test", "test");
    }

    public static Nitrite getDatabase(){
        return database;
    }
}
