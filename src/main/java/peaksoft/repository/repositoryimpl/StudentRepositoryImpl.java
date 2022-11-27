package peaksoft.repository.repositoryimpl;

import peaksoft.model.Course;
import peaksoft.model.Group;
import peaksoft.model.Instructor;
import peaksoft.model.Student;
import peaksoft.repository.StudentRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Repository
@Transactional
public class StudentRepositoryImpl implements StudentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Student> getAllStudent() {
        return entityManager.createQuery("select s from Student s").getResultList();
    }

    @Override
    public List<Student> getAllStudent(Long groupId) {
        return entityManager.createQuery("select s from Student s where s.group.id = : groupId",
                Student.class).setParameter("groupId", groupId).getResultList();
    }

    @Override
    public Student getStudentById(Long id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public void saveStudent(Long groupId, Student student) {
        Group group = entityManager.find(Group.class, groupId);
        group.addStudent(student);
        student.setGroup(group);
        entityManager.merge(student);

        for (Course c:student.getGroup().getCourses()) {
            for (Instructor i: c.getInstructors()) {
                i.plus();
            }
        }
    }


    @Override
    public void updateStudent(Long id, Student student) {
        Student student1 = entityManager.find(Student.class, id);
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setPhoneNumber(student.getPhoneNumber());
        student1.setEmail(student.getEmail());
        student1.setStudyFormat(student.getStudyFormat());
        entityManager.merge(student1);
    }

    @Override
    public void deleteStudent(Long id) {
       Student student = entityManager.find(Student.class,id);
       student.getGroup().getCompany().minusStudent();
        for (Course c:student.getGroup().getCourses()) {
            for (Instructor i:c.getInstructors()) {
                i.minus();
            }
        }
        student.setGroup(null);
        entityManager.remove(student);
        //entityManager.remove(entityManager.find(Student.class, id));
    }

    @Override
    public void assignStudent(Long groupId, Long studentId) throws IOException {
        Student student = entityManager.find(Student.class, studentId);
        Group group = entityManager.find(Group.class, groupId);
        if (group.getStudents() != null) {
            for (Student g : group.getStudents()) {
                if (g.getId() == studentId) {
                    throw new IOException("This student already exists!");
                }
            }
        }
//        for (Course c: student.getGroup().getCourses()) {
//            for (Instructor i: c.getInstructors()) {
//                i.minus();
//            }
//        }
//        for (Course c: group.getCourses()) {
//            for (Instructor i: c.getInstructors()) {
//                i.plus();
//            }
//        }
        student.getGroup().getStudents().remove(student);
        group.assignStudent(student);
        student.setGroup(group);
        entityManager.merge(student);
    }
}
