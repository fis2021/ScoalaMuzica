package services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.LoginController;
import controllers.ViewRequestsController;
import exceptions.InstructorNotFound;
import exceptions.StudentNotFound;
import model.Instructor;
import model.Message;
import model.Student;
import org.dizitart.no2.objects.ObjectRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.dizitart.no2.objects.filters.ObjectFilters.eq;
import static services.DatabaseService.getDatabase;

public class MessageService {
    private static ObjectRepository<Instructor> instructorRepository = getDatabase().getRepository(Instructor.class);
    private static List<Message> messages = new ArrayList<>();

    public static List<Message> getMessages() {
        return messages;
    }

    public static void setMessages(List<Message> messages) {
        MessageService.messages = messages;
    }

    public static void loadMessages(String studentName) {
        for(Instructor instructor : instructorRepository.find().toList()){
            for(Student student: instructor.getStudents()){
                if(studentName.equals(student.getStudentName())){
                    messages.addAll(student.getMessages());
                }
            }
        }
    }

    public static void addMessage(String message) {
        Student student = null;
        String instructor_username = LoginController.getCurrentUsername();
        Instructor instructor = instructorRepository.find(eq("username", instructor_username)).firstOrDefault();
        String studentToSend = ViewRequestsController.getStudentToSend();
        for (Student stud : instructor.getStudents()) {
            if (studentToSend.equals(stud.getStudentName())) {
                student = stud;
            }
        }
        if (student != null) {
            List<Message> messages = student.getMessages();
            messages.add(new Message(LoginController.getCurrentUsername(), message));
        }
        instructorRepository.update(instructor);
    }

    public static void deleteMessage(String m, String name) throws InstructorNotFound {
        List<Message> student_messages;
        Instructor instructor = instructorRepository.find(eq("username", name)).firstOrDefault();
        if (instructor == null) {
            throw new InstructorNotFound();
        }
        for (Student student : instructor.getStudents()) {
            student_messages = student.getMessages();
            for (Message message : student_messages) {
                if (m.equals(message.getMessage())) {
                    student_messages.remove(message);
                    break;
                }
            }
        }
        instructorRepository.update(instructor);
    }

}
