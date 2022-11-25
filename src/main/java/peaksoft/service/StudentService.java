package peaksoft.service;

import peaksoft.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudent();
    List<Student> getAllStudent(Long groupId);
    Student getStudentById(Long id);
    void saveStudent(Long groupId, Student student);
    void updateStudent(Long id, Student student);
    void deleteStudent(Long id);
}
