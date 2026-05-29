package todo.model;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity //tells hibernate that this class maps to a database table

@NoArgsConstructor //for instantiation
@AllArgsConstructor

//Lombok generates, useful for read/update/write
@Getters
@Setters

@Builder //cleaner

public class Task {

    @Id //sets as primary key
    @GeneratedValue(strategy = GenerationType.UUID) //generates value
    private String task_id;

    @Column(nullable=false)
    private String title;

    @Column(name = "raw_input", columnDefinition = "TEXT") //text has no length limit
    private String rawInput;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private LocalDateTime deadline;

    @Enumerated(EnumType.STRING) //set enum as string name rather than a number
    @Column(nullable=false)
    @Builder.Default
    private Status status = Status.PENDING;

    @Column
    private String category;

    @Enumerated(EnumType.STRING) //set enum as string name rather than a number
    @Column(nullable=false)
    @Builder.Default
    private Priority priority = Priority.MEDIUM;

    @Column(name = "created_at", updatable = false) //using final we need to set the value in constructor itself
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate(){
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate //JPA triggers automatically
    void onUpdate(){
        updatedAt = LocalDateTime.now();
    }
    public enum Status   { PENDING, IN_PROGRESS, COMPLETED }
    public enum Priority { LOW, MEDIUM, HIGH, URGENT }
}
