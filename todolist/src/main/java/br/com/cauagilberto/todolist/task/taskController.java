package br.com.cauagilberto.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/tasks")
public class taskController {
    
    @Autowired
    private ITaskRepo taskRepo;
    
    
    @PostMapping("/")
    public ResponseEntity create(@RequestBody taskModel taskModel, HttpServletRequest request){
        var idUser = request.getAttribute("idUser");
        taskModel.setIdUser((UUID) idUser);

        var currentDate = LocalDateTime.now();
        if (currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("A data de início / data de término não pode ser uma data passada.");
        }
        if (taskModel.getStartAt().isAfter(taskModel.getEndAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("A data de término não pode ser anterior a data de início.");
        }

        var task = this.taskRepo.save(taskModel);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @GetMapping("/")
    public List<taskModel> list(HttpServletRequest request) {
        var idUser = request.getAttribute("idUser");
        var task = this.taskRepo.findByIdUser((UUID) idUser);
        return task;
    }

    @PutMapping("/{id}")
    public taskModel update(@RequestBody taskModel taskModel, HttpServletRequest request, @PathVariable UUID id){
        var idUser = request.getAttribute("idUser");
        taskModel.setIdUser((UUID) idUser);
        taskModel.setId(id);
        return this.taskRepo.save(taskModel);
    }
}
