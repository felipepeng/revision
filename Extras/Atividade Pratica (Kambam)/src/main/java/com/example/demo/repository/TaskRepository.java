package com.example.demo.repository;

import com.example.demo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE t.status = :status ORDER BY " +
            "CASE t.priority " +
            "WHEN 'Alta' THEN 1 " +
            "WHEN 'Média' THEN 2 " +
            "WHEN 'Baixa' THEN 3 END")
    List<Task> findByStatusOrderedByPriority(@Param("status") String status);

    @Query("SELECT t FROM Task t WHERE t.priority = :priority AND t.deadline <= :date")
    List<Task> findByPriorityAndDeadlineBefore(@Param("priority") String priority, @Param("date") LocalDate date);

    @Query("SELECT t FROM Task t WHERE t.deadline < :today AND t.status <> 'Concluído'")
    List<Task> findOverdueTasks(@Param("today") LocalDate today);

}
