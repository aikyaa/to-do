package todo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data //getters,setters etc
@AllArgsConstructor
public class QueuedTaskPayload {
    private String taskId;
    private String rawInput;
}
