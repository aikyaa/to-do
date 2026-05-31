package todo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import todo.repository.TaskRepository;
import todo.queue.TaskQueueService;
import todo.dto.QueuedTaskPayload;
import todo.model.Task;
import todo.dto.*;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskQueueService queueService;

    //create and enqueue the skeleton task
    @Transactional //proxy initiates transactional
    public TaskResponse create(String rawInput){
        Task task=Task.builder()
            .title("Processing");
            .rawInput(rawInput)
            .build();
        task=taskRepository.save(task);
        queueService.enqueue(new QueuedTaskPayload(task.getId(), rawInput));
        return TaskResponse.from(task);
    }

    //enrich
    

    //userupdate
    //getall getbystatus delete etc

}
