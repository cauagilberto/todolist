package br.com.cauagilberto.todolist.user;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    /**
     * string(texto)
     * integer (int - numeros inteiros)
     * double (números 0.00000)
     * float (números 0.00)
     * char (letras/caracteres)
     * date (data)
     * void 
     */

    @PostMapping("/")
    public void create(@RequestBody UserModel userModel) {
        System.out.println(userModel.getUsername());

     }
}
