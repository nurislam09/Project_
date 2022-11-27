package peaksoft.repository;

import peaksoft.model.Student;

import java.io.IOException;
import java.util.List;

public interface StudentRepository {
    List<Student> getAllStudent();
    List<Student> getAllStudent(Long groupId);
    Student getStudentById(Long id);
    void saveStudent(Long groupId, Student student);
    void updateStudent(Long id, Student student);
    void deleteStudent(Long id);

    void assignStudent(Long groupId, Long studentId) throws IOException;
}
