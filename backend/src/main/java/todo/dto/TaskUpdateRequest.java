package todo.dto;

import lombok.Data;

@Data
public class TaskUpdateRequest {
    private String title;
    private String description;
    private String deadline;
    private String status;
    private String category;
    private String priority;
}
