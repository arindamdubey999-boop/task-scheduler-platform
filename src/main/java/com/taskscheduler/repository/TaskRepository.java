package com.taskscheduler.repository;

import com.taskscheduler.model.Task;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends CassandraRepository<Task, UUID> {
    
    List<Task> findByStatus(String status);
    
    @Query("SELECT * FROM tasks WHERE nextExecutionTime <= ?0 ALLOW FILTERING")
    List<Task> findTasksDueForExecution(Instant currentTime);
    
    @Query("UPDATE tasks SET status = ?1, updatedAt = ?2 WHERE id = ?0")
    void updateStatus(UUID taskId, String status, Instant updatedAt);
    
    @Query("UPDATE tasks SET nextExecutionTime = ?1, updatedAt = ?2 WHERE id = ?0")
    void updateNextExecutionTime(UUID taskId, Instant nextExecutionTime, Instant updatedAt);
}
