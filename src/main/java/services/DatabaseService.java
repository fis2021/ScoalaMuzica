package services;

import org.dizitart.no2.Nitrite;

import static services.FileSystemService.getPathToFile;

public class DatabaseService {
    private static Nitrite database;

    public DatabaseService() {
        initDatabase();
    }

    public static void initDatabase() {
        database = Nitrite.builder()
                .filePath(getPathToFile("SM.db").toFile())
                .openOrCreate("admin", "admin");
    }

    public static Nitrite getDatabase() {
        return database;
    }
}
