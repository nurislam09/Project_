package peaksoft.service;

import peaksoft.model.Instructor;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.List;

public interface InstructorService {
    List<Instructor> getAllInstructor();
    List<Instructor> getAllInstructor(Long courseId);
    Instructor getInstructorById(Long id);
    void saveInstructor(Long courseId, Instructor instructor) throws IOException;
    void updateInstructor(Long id, Instructor instructor) throws IOException;
    void deleteInstructor(Long id);

    void assignInstructorToCourse(Long courseId, Long id) throws IOException;
}
