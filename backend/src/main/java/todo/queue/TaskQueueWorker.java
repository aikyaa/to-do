package todo.queue;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import todo.queue.TaskQueueService;
import todo.service.MLService;
import todo.service.TaskService;
import todo.dto.QueuedTaskPayload;
import jakarta.annotation.PostConstruct;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component //spring registers this worker as a bean
public class TaskQueueWorker {
    private final TaskQueueService queue; //calling the instance created by spring, no new
    private final MLService mlService;
    private final TaskService taskService;

    @PostConstruct //daemon(background) thread is started after injecting dependencies required to run
    public void start(){
        Thread worker = new Thread(this::run, "task-queue-worker"); //seperate so frontend doesn't have to wait for MLService
        worker.setDaemon(true);
        worker.start(); //begins os-level thread and executes run() on it
        log.info("Task queue worker started");
    }

    private void run(){
        while(true){ //runs as long as the app is running
            try{
                //blocks until there's a task
                QueuedTaskPayload payload = queue.take();
                log.info("Processing task {}", payload.getTaskId());

                Map<String, Object> extracted = mlService.extract(payload.getRawInput());

                Map<String, Object> categorized = mlService.categorize(
                        (String) extracted.getOrDefault("task",          ""),
                        (String) extracted.getOrDefault("description",   ""),
                        (String) extracted.getOrDefault("deadline",      ""),
                        (String) extracted.getOrDefault("priority_hint", null));
                
                taskService.enrich(payload.getTaskId(), extracted, categorized);

            } catch(InterruptedException e){//makes the interrupt flag false
                //shut down
                Thread.currentThread().interrupt(); //set interrupt flag to true for detection
                break;
            } catch(Exception e){
                log.error("Worker error: {}", e.getMessage());
            }
        }
    }
    
}
