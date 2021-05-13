package services;

import exceptions.*;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.Cursor;
import org.dizitart.no2.objects.ObjectFilter;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.filters.Filters;
import model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static org.dizitart.no2.objects.filters.ObjectFilters.eq;
import static services.FileSystemService.getPathToFile;

public class UserService {
    public static String role;
    private static ObjectRepository<User> userRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("registration-example.db").toFile())
                .openOrCreate("test", "test");

        userRepository = database.getRepository(User.class);
    }

    public static void addUser(String username, String password, String role) throws UsernameAlreadyExistsException, NoPassword, NoUserName {
        checkUserDoesNotAlreadyExist(username);
        checkUserIsNotEmpty(username);
        checkPassIsNotEmpty(password);
        userRepository.insert(new User(username, encodePassword(username, password), role));
    }

    public static void checkUser(String username, String password) throws NoUserName, NoPassword, InvalidPassword, InvalidUsername {
        checkUserIsNotEmpty(username);
        checkPassIsNotEmpty(password);
        checkUsername(username, password);
    }

    public static void deleteInstructor(String username) throws InstructorNotFound {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername())) {
                if (Objects.equals("Instructor", user.getRole())) {
                    user = null;
                    break;
                }
                throw new InstructorNotFound();
            }
        }
    }

    public static void addInstructor(String username, String password) throws UsernameAlreadyExistsException, NoPassword, NoUserName {
        checkUserDoesNotAlreadyExist(username);
        checkUserIsNotEmpty(username);
        checkPassIsNotEmpty(password);
        addUser(username, encodePassword(username, password), "Instructor");
    }

    private static void checkUsername(String username, String password) throws InvalidPassword, InvalidUsername {

        User user = userRepository.find(eq("username", username)).firstOrDefault();
        if (user == null) {
            throw new InvalidUsername();
        }
        if (!Objects.equals(encodePassword(username, password), user.getPassword()))
            throw new InvalidPassword();
        role = user.getRole();
    }


    public static String getRole() {
        return role;
    }


    private static void checkUserIsNotEmpty(String username) throws NoUserName {
        if (Objects.equals(username, ""))
            throw new NoUserName(username);
    }

    private static void checkPassIsNotEmpty(String password) throws NoPassword {
        if (Objects.equals(password, ""))
            throw new NoPassword(password);
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }


}
