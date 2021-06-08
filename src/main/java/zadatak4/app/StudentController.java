package zadatak4.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import zadatak4.DAO.StudentDAOImpl;
import zadatak4.entities.Student;

public class StudentController {
    private final StudentDAOImpl studentService = new StudentDAOImpl();
    private ObservableList<Student> listaStudenata = FXCollections.observableArrayList();

    public void addStudent(Student student) {
        studentService.addStudent(student);
    }

    public ObservableList<Student> getStudentData() {
        if (!listaStudenata.isEmpty() && studentService.isValidCache()) {
            return listaStudenata;
        } else {
            listaStudenata.clear();
        }

        listaStudenata = FXCollections.observableList(studentService.listStudentData());
        return listaStudenata;
    }

    public void removeStudent(Integer id) {
        studentService.removeStudent(id);
    }

    public void updateStudent(Student student) {
        System.out.println(student);

        studentService.updateStudent(student);
    }
}