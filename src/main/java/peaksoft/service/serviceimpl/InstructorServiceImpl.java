
    package peaksoft.service.serviceimpl;


import peaksoft.model.Instructor;
import peaksoft.repository.InstructorRepository;
import peaksoft.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

    @Service
    @Transactional
    public class InstructorServiceImpl implements InstructorService {

        private final InstructorRepository instructorRepository;

        @Autowired
        public InstructorServiceImpl(InstructorRepository instructorRepository) {
            this.instructorRepository = instructorRepository;
        }

        @Override
        public List<Instructor> getAllInstructor() {
            return instructorRepository.getAllInstructor();
        }

        @Override
        public List<Instructor> getAllInstructor(Long courseId) {
            return instructorRepository.getAllInstructor(courseId);
        }

        @Override
        public Instructor getInstructorById(Long id) {
            return instructorRepository.getInstructorById(id);
        }

        @Override
        public void saveInstructor(Long courseId, Instructor instructor) throws IOException{
            List<Instructor> instructors = instructorRepository.getAllInstructor(courseId);
            for (Instructor i : instructors) {
                if (i.getEmail().equals(instructor.getEmail())){
                    throw new IOException("Instructor with email already exists!");
                }
            }
            validator(instructor.getPhoneNumber().replace(" ",""),instructor.getLastname().replace(" ",""),instructor.getFirstname().replace(" ",""));
            instructorRepository.saveInstructor(courseId, instructor);

        }

        @Override
        public void updateInstructor(Long id, Instructor instructor) throws IOException {
            validator(instructor.getPhoneNumber().replace(" ",""),instructor.getLastname().replace(" ",""),instructor.getFirstname().replace(" ",""));
            instructorRepository.updateInstructor(id, instructor);
        }

        @Override
        public void deleteInstructor(Long id) {
            instructorRepository.deleteInstructor(id);
        }

        @Override
        public void assignInstructorToCourse(Long courseId, Long id) throws IOException {
            instructorRepository.assignInstructor(courseId, id);
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

