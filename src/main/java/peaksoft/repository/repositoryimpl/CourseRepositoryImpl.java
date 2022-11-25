package peaksoft.repository.repositoryimpl;

import peaksoft.model.Company;
import peaksoft.model.Course;
import peaksoft.repository.CourseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CourseRepositoryImpl implements CourseRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Course> getAllCourse() {
        return entityManager.createQuery("select c from Course c").getResultList();
    }

    @Override
    public List<Course> getAllCourse(Long companyId) {
        return entityManager.createQuery("select c from Course c where c.company.id = : companyId",
                Course.class).setParameter("companyId", companyId).getResultList();
    }

    @Override
    public Course getCourseById(Long id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    public void saveCourse(Long companyId, Course course) {
        Company company = entityManager.find(Company.class, companyId);
        company.addCourse(course);
        course.setCompany(company);
        entityManager.merge(course);
    }

    @Override
    public void updateCourse(Long id, Course course) {
        Course course1 = entityManager.find(Course.class, id);
        course1.setCourseName(course.getCourseName());
        course1.setDuration(course.getDuration());
        course1.setDescription(course.getDescription());
        entityManager.merge(course1);
    }

    @Override
    public void deleteCourse(Long id) {
        entityManager.remove(entityManager.find(Course.class, id));
    }
}