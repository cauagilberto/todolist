package br.com.cauagilberto.todolist.task;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaskRepo extends JpaRepository<taskModel, UUID> {

    List<taskModel> findByIdUser(UUID idUser);
    
}
