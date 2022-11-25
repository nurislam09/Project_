package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
@Getter @Setter
@Table(name = "companies")
public class Company {
        @Id
        @SequenceGenerator(name = "company_seq", sequenceName = "company_seq", allocationSize = 1)
        @GeneratedValue(generator = "company_seq", strategy = GenerationType.SEQUENCE)
        private Long id;

        @Column(length = 500)
        private String companyName;

        @Column(length = 500)
        private String locatedCountry;

        @Column(length = 500)
        private int count;

        public void plusStudent(){
                count++;
        }
        public void minusStudent(){
                count--;
        }

        public Company(String companyName, String locatedCountry , Integer count) {
                this.companyName = companyName;
                this.locatedCountry = locatedCountry;
                this.count= count;
        }

        @OneToMany(cascade = {DETACH, MERGE, REFRESH, REMOVE}, fetch = LAZY, mappedBy = "company")
        private List<Course> courses;

        public void addCourse(Course course) {
                if (courses == null) courses = new ArrayList<>();
                courses.add(course);
        }
}