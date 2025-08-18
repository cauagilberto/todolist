package br.com.cauagilberto.todolist.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/tasks")
public class taskController {
    
    @Autowired
    private ITaskRepo taskRepo;
    
    
    @PostMapping("/")
    public taskModel create(@RequestBody taskModel taskModel) {
        System.out.println("Chegou no controller");
        var task = this.taskRepo.save(taskModel);
        return task;
    }
}
