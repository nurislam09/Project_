package peaksoft.repository;

import peaksoft.model.Course;

import java.util.List;

public interface CourseRepository {
    List<Course> getAllCourse();
    List<Course> getAllCourse(Long companyId);
    Course getCourseById(Long id);
    void saveCourse(Long companyId, Course course);
    void updateCourse(Long id, Course course);
    void deleteCourse(Long id);


}
