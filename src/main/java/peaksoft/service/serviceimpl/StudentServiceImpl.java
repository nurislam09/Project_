package peaksoft.service.serviceimpl;


import peaksoft.model.Student;
import peaksoft.repository.StudentRepository;
import peaksoft.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
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
        public void saveStudent(Long groupId, Student student) throws IOException {
            List<Student> students = studentRepository.getAllStudent(groupId);
            for (Student i : students) {
                if (i.getEmail().equals(student.getEmail())){
                    throw new IOException("Student with email already exists!");
                }
            }
            validator(student.getPhoneNumber().replace(" ",""),student.getFirstName().replace(" ",""),student.getLastName().replace(" ",""));
            studentRepository.saveStudent(groupId, student);
        }

        @Override
        public void updateStudent(Long id, Student student) throws IOException {
            validator(student.getPhoneNumber().replace(" ",""),student.getFirstName().replace(" ",""),student.getLastName().replace(" ",""));
            studentRepository.updateStudent(id, student);
        }

        @Override
        public void deleteStudent(Long id) {
            studentRepository.deleteStudent(id);
        }

        @Override
        public void assignGroup(Long courseId, Long id) throws IOException {
            studentRepository.assignStudent(courseId, id);
        }

        private void validator(String phone, String firstName, String lastName) throws IOException {
            if (firstName.length()>2 && lastName.length()>2) {
                for (Character i : firstName.toCharArray()) {
                    if (!Character.isAlphabetic(i)) {
                        throw new IOException("В имени инструктора нельзя вставлять цифры");
                    }
                }

                for (Character i : lastName.toCharArray()) {
                    if (!Character.isAlphabetic(i)) {
                        throw new IOException("В фамилию инструктора нельзя вставлять цифры");
                    }
                }
            } else {
                throw new IOException("В имени или фамилии инструктора должно быть как минимум 3 буквы");
            }

            if (phone.length()==13
                    && phone.charAt(0) == '+'
                    && phone.charAt(1) == '9'
                    && phone.charAt(2) == '9'
                    && phone.charAt(3) == '6'){
                int counter = 0;

                for (Character i : phone.toCharArray()) {
                    if (counter!=0){
                        if (!Character.isDigit(i)) {
                            throw new IOException("Формат номера не правильный");
                        }
                    }
                    counter++;
                }
            }else {
                throw new IOException("Формат номера не правильный");
            }
        }
    }

