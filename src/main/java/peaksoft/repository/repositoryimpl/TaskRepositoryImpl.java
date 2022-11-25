package peaksoft.repository.repositoryimpl;

import peaksoft.model.Lesson;
import peaksoft.model.Task;
import peaksoft.repository.TaskRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TaskRepositoryImpl implements TaskRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Task> getAllTask() {
        return entityManager.createQuery("select t from Task t").getResultList();
    }

    @Override
    public List<Task> getAllTask(Long lessonId) {
        return entityManager.createQuery("select t from Task t where t.lesson.id = : lessonId",
                Task.class).setParameter("lessonId", lessonId).getResultList();
    }

    @Override
    public Task getTaskById(Long id) {
        return entityManager.find(Task.class, id);
    }

    @Override
    public void saveTask(Long lessonId, Task task) {
        Lesson lesson = entityManager.find(Lesson.class, lessonId);
        lesson.addTask(task);
        task.setLesson(lesson);
        entityManager.merge(task);
    }

    @Override
    public void updateTask(Long id, Task task) {
        Task task1 = entityManager.find(Task.class, id);
        task1.setTaskName(task.getTaskName());
        task1.setTaskText(task.getTaskText());
        task1.setDeadLine(task.getDeadLine());
        entityManager.merge(task1);
    }

    @Override
    public void deleteTask(Long id) {
        entityManager.remove(entityManager.find(Task.class, id));
    }
}