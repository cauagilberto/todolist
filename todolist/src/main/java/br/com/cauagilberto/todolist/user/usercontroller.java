package br.com.cauagilberto.todolist.user;



import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/users")

/**
 * Modificadores:
 * Public
 * Private
 * Protected
 */

public class usercontroller {
    
    @Autowired
    private IUserRepo userRepo;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel) {
        var user = this.userRepo.findByUsername(userModel.getUsername());
        if (user != null) {
            //precisamos retornar um erro
            //precisamos retornar um status code
            //System.out.println("Usuário já existe");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }

        var passwrodHarshed = BCrypt.withDefaults()
            .hashToString(12, userModel.getPassword().toCharArray());

        userModel.setPassword(passwrodHarshed);
        var userCreated = this.userRepo.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
     }
}
