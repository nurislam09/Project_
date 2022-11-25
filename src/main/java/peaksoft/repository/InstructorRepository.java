package peaksoft.repository;

import peaksoft.model.Instructor;

import java.util.List;

public interface InstructorRepository {
    List<Instructor> getAllInstructor();

    List<Instructor> getAllInstructor(Long courseId);

    Instructor getInstructorById(Long id);

    void saveInstructor(Long courseId, Instructor instructor);

    void updateInstructor(Long id, Instructor instructor);

    void deleteInstructor(Long id);
}
