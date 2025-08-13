package br.com.cauagilberto.todolist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

//@Controller quando temos paginas, flexibilidade de retornar HTML, JSON, etc.
@RestController //usando api
@RequestMapping("/primeiraRota")
public class MinhaPrimeriaController {

    /*
     * metodos de acesso HTTP
     * get - busca informação dentro da aplicação
     * post - adicionar dado ou informação
     * put - alterar dado ou informação(varios dados de uma vez)
     * delete - remover dado ou informação
     * patch - alterar parte de um dado ou informação
     * 
     */

    //metodo de funcionalidade de uma classe
    @GetMapping("/")
    public String PrimeiraMensagem() {
        return "funcionou!";
    }
}
