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

        @OneToMany(cascade = {DETACH, MERGE, REFRESH, REMOVE}, fetch = LAZY, mappedBy = "company")
        private List<Course> courses;

        @Column
        private int student = 0;


        public Company(String companyName, String locatedCountry ) {
                this.companyName = companyName;
                this.locatedCountry = locatedCountry;
        }

        public void addCourse(Course course) {
                if (courses == null) courses = new ArrayList<>();
                courses.add(course);
        }
        public void plus() {
                System.out.println(student);
                student++;
        }

        public void minus() {
                System.out.println(student);
                student--;
        }

        public  void sout(){
                System.out.println(student);
        }

}