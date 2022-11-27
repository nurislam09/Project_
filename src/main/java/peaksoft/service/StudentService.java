package peaksoft.service;

import peaksoft.model.Student;

import java.io.IOException;
import java.util.List;

public interface StudentService {
    List<Student> getAllStudent();
    List<Student> getAllStudent(Long groupId);
    Student getStudentById(Long id);
    void saveStudent(Long groupId, Student student);
    void updateStudent(Long id, Student student);
    void deleteStudent(Long id);

    void assignGroup(Long courseId, Long id) throws IOException;
}
