package todo.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

//JSON body of a POST request
@Data
public class TaskRequest {
    @NotBlank(message="Input can't be empty"); //validation
    private String rawInput;
}
