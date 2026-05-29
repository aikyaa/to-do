package todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import todo.model.Task;

@Repository
//create a repo for entity Task with primary key type String
public interface TaskRepository extends JpaRepository<Task, String>{ //declaring not implementing
    List<Task> findAllByOrderByCreatedAtDesc(); //spring data derives sql query from method name
    List<Task> findAllByStatusOrderByCreatedAtDesc(Task.Status status); //we need Status to be public
}

