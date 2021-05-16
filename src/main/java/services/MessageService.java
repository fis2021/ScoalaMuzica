package services;

import controllers.LoginController;
import controllers.ViewRequestsController;
import exceptions.StudentNotFound;
import model.Instructor;
import model.Message;
import model.Student;
import org.dizitart.no2.objects.ObjectRepository;

import java.util.List;

import static org.dizitart.no2.objects.filters.ObjectFilters.eq;
import static services.DatabaseService.getDatabase;

public class MessageService {
    private static ObjectRepository<Instructor> instructorRepository = getDatabase().getRepository(Instructor.class);

//    public static void loadStudent() throws StudentNotFound {
//
//        String studentToSend = ViewRequestsController.getStudentToSend();
////        student = studentRepository.find(eq("username", studentToSend)).firstOrDefault();
//        if (student == null) {
//            throw new StudentNotFound();
//        }
//    }

    public static void addMessage(String message) {
        Student student = null;
        String instructor_username = LoginController.getCurrectUsername();
        Instructor instructor = instructorRepository.find(eq("username", instructor_username)).firstOrDefault();
        String studentToSend = ViewRequestsController.getStudentToSend();
        for (Student stud : instructor.getStudents()){
            if(studentToSend.equals(stud.getStudentName())){
                student = stud;
            }
        }
        if(student != null){
            List<Message> messages = student.getMessages();
            messages.add(new Message(LoginController.getCurrectUsername(), message));
        }
        instructorRepository.update(instructor);
    }

}
