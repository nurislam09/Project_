package peaksoft.repository.repositoryimpl;

import peaksoft.model.Course;
import peaksoft.model.Group;
import peaksoft.model.Instructor;
import peaksoft.model.Student;
import peaksoft.repository.GroupRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Repository
@Transactional
public class GroupRepositoryImpl implements GroupRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Group> getAllGroup() {
        return entityManager.createQuery("select g from Group g ").getResultList();
    }

    @Override
    public List<Group> getAllGroup(Long courseId) {
        List<Group> groups = entityManager.find(Course.class, courseId).getGroups();
        groups.forEach(System.out::println);
        return groups;
    }

    @Override
    public Group getGroupById(Long id) {
        return entityManager.find(Group.class, id);
    }

    @Override
    public void saveGroup(Long courseId, Group group) {
        Course course = entityManager.find(Course.class, courseId);
        course.addGroup(group);
        group.addCourse(course);
        entityManager.merge(group);
    }

    @Override
    public void updateGroup(Long id, Group group) {
        Group group1 = entityManager.find(Group.class, id);
        group1.setGroupName(group.getGroupName());
        group1.setDataOfStart(group.getDataOfStart());
        group1.setImage(group.getImage());
        entityManager.merge(group1);
    }

    @Override
    public void deleteGroup(Long id) {
        entityManager.remove(entityManager.find(Group.class, id));
    }


    @Override
    public void assignGroup(Long courseId, Long id) throws IOException {
        Group group = entityManager.find(Group.class, id);
        Course course = entityManager.find(Course.class, courseId);
        if (course.getGroups() != null) {
            for (Group g : course.getGroups()) {
                if (g.getId() == id) {
                    throw new IOException("This group already exists!");
                }
            }
        }
        group.addCourse(course);
        course.addGroup(group);
        entityManager.merge(group);
        entityManager.merge(course);
    }
}