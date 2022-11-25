package peaksoft.repository.repositoryimpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Course;
import peaksoft.model.Lesson;
import peaksoft.repository.LessonRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class LessonRepositoryImpl implements LessonRepository {


        @PersistenceContext
        private EntityManager entityManager;

        @Override
        public List<Lesson> getAllLesson() {
            return entityManager.createQuery("select l from Lesson l").getResultList();
        }

        @Override
        public List<Lesson> getAllLesson(Long courseId) {
            return entityManager.createQuery("select l from Lesson l where l.course.id = : courseId",
                    Lesson.class).setParameter("courseId", courseId).getResultList();
        }

        @Override
        public Lesson getLessonById(Long id) {
            return entityManager.find(Lesson.class, id);
        }

        @Override
        public void saveLesson(Long courseId, Lesson lesson) {
            Course course = entityManager.find(Course.class, courseId);
            course.addLesson(lesson);
            lesson.setCourse(course);
            entityManager.merge(lesson);
        }

        @Override
        public void updateLesson(Long id, Lesson lesson) {
            Lesson lesson1 = entityManager.find(Lesson.class, id);
            lesson1.setLessonName(lesson.getLessonName());
            entityManager.merge(lesson1);
        }

        @Override
        public void deleteLesson(Long id) {
            entityManager.remove(entityManager.find(Lesson.class, id));
        }
    }

