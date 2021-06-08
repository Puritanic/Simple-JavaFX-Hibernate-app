package zadatak4.interfaces;

import zadatak4.entities.Student;

import java.util.List;

public interface StudentDAO {
    void addStudent(Student student);

    List<Student> listStudentData();

    void removeStudent(Integer id);

    void updateStudent(Student student);
}
