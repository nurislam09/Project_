package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "courses")
public class Course {

        @Id
        @SequenceGenerator(name = "course_seq", sequenceName = "course_seq", allocationSize = 1)
        @GeneratedValue(generator = "course_seq", strategy = GenerationType.SEQUENCE)
        private Long id;

        @Column(length = 500)
        private String courseName;

        @Column(length = 500)
        private Integer duration;

        @Column(length = 500)
        private String description;

        public Course(String courseName, Integer duration, String description) {
            this.courseName = courseName;
            this.duration = duration;
            this.description = description;
        }

        @ManyToOne(cascade = {DETACH, MERGE, REFRESH, PERSIST}, fetch = EAGER)
        private Company company;

        @ManyToMany(cascade = {DETACH, REFRESH, MERGE, REMOVE}, fetch = LAZY, mappedBy = "courses")
        private List<Group> groups;

        @OneToMany(cascade = {DETACH, MERGE, REFRESH, REMOVE}, fetch = LAZY, mappedBy = "course")
        private List<Instructor> instructors;

        @OneToMany(cascade = ALL, fetch = LAZY, mappedBy = "course")
        private List<Lesson> lessons;

        public void addGroup(Group group) {
            if (groups == null) groups = new ArrayList<>();
            groups.add(group);
        }
        public void addInstructor(Instructor instructor) {
            if (instructors == null) instructors = new ArrayList<>();
            instructors.add(instructor);
        }
        public void addLesson(Lesson lesson) {
            if (lessons == null) lessons = new ArrayList<>();
            lessons.add(lesson);
        }

    }
