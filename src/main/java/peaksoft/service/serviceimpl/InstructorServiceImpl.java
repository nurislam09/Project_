
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
        public void saveInstructor(Long courseId, Instructor instructor) {
            instructorRepository.saveInstructor(courseId, instructor);

        }

        @Override
        public void updateInstructor(Long id, Instructor instructor) {
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

    }

