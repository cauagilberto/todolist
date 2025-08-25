package br.com.cauagilberto.todolist.task;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/tasks")
public class taskController {
    
    @Autowired
    private ITaskRepo taskRepo;
    
    
    @PostMapping("/")
    public taskModel create(@RequestBody taskModel taskModel, HttpServletRequest request){
        var idUser = request.getAttribute("idUser");
        taskModel.setIdUser((UUID) idUser);
        var task = this.taskRepo.save(taskModel);
        return task;
    }
}
