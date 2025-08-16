package br.com.cauagilberto.todolist.task;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaskRepo extends JpaRepository<taskModel, UUID> {

    
}
