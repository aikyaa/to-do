package todo.queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import todo.dto.QueuedTaskPayload;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
@Slf4j
public class TaskQueueService {
    private final BlockingQueue<QueuedTaskPayload> queue = new LinkedBlockingQueue<>();

    public void enqueue(QueuedTaskPayload payload){
        queue.offer(payload);
        log.info("Queued task {} for processing", payload.getTaskId());
    }

    public QueuedTaskPayload take() throws InterruptedException{ //cant shut down if thread is sleeping
        return queue.take();
    }
}
