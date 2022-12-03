package peaksoft.service.serviceimpl;

import peaksoft.model.Course;
import peaksoft.repository.CourseRepository;
import peaksoft.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourse() {
        return courseRepository.getAllCourse();
    }

    @Override
    public List<Course> getAllCourse(Long companyId) {
        return courseRepository.getAllCourse(companyId);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.getCourseById(id);
    }

    @Override
    public void saveCourse(Long companyId, Course course) throws IOException {
        validator(course.getCourseName(),  course.getDescription(),course.getDuration());
        courseRepository.saveCourse(companyId, course);
    }

    @Override
    public void updateCourse(Long id, Course course) throws IOException {
        validator(course.getCourseName(), course.getDescription(),course.getDuration());
        courseRepository.updateCourse(id, course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteCourse(id);
    }

    private void validator(String courseName, String description, int duration) throws IOException {
        if (courseName.length()>3 && description.length()>5 && description.length()<15 && duration>0 && duration<24){
            for (Character i: courseName.toCharArray()) {
                if (!Character.isLetter(i)){
                    throw new IOException("В название курса нельзя вставлять цифры");
                }
            }
        }else {
            throw new IOException("Form error course registration");
        }
    }
}