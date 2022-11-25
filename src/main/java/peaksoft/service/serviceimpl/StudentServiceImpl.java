package peaksoft.service.serviceimpl;


import peaksoft.model.Student;
import peaksoft.repository.StudentRepository;
import peaksoft.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

    @Service
    @Transactional
    public class StudentServiceImpl implements StudentService {

        private final StudentRepository studentRepository;

        @Autowired
        public StudentServiceImpl(StudentRepository studentRepository) {
            this.studentRepository = studentRepository;
        }


        @Override
        public List<Student> getAllStudent() {
            return studentRepository.getAllStudent();
        }

        @Override
        public List<Student> getAllStudent(Long groupId) {
            return studentRepository.getAllStudent(groupId);
        }

        @Override
        public Student getStudentById(Long id) {
            return studentRepository.getStudentById(id);
        }

        @Override
        public void saveStudent(Long groupId, Student student) {
            studentRepository.saveStudent(groupId, student);
        }

        @Override
        public void updateStudent(Long id, Student student) {
            studentRepository.updateStudent(id, student);
        }

        @Override
        public void deleteStudent(Long id) {
            studentRepository.deleteStudent(id);
        }
    }

