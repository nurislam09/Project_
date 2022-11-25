package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


import java.sql.Date;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
@Table(name = "tasks")
@Setter
@Getter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
    @SequenceGenerator(name = "task_seq", sequenceName = "task_seq", allocationSize = 1)
    private Long id;

    @Column(length = 500)
    private String taskName;

    @Column(length = 500)
    private String taskText;

    @Column(length = 500)
    private Date deadLine;

    public Task(String taskName, String taskText, Date deadLine) {
        this.taskName = taskName;
        this.taskText = taskText;
        this.deadLine = deadLine;
    }

    @ManyToOne(cascade = {DETACH, REFRESH, MERGE, PERSIST}, fetch = LAZY)
    private Lesson lesson;
}