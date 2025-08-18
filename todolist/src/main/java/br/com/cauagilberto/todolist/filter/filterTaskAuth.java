package br.com.cauagilberto.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class filterTaskAuth extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                //pegar autenticação
                var authorization = request.getHeader("Authorization");

                var authEncoded = authorization.substring("Basic".length()).trim();

                byte[] authDecoded = Base64.getDecoder().decode(authEncoded);

                var authString = new String(authDecoded);

                //System.out.println("User Password");
                //System.out.println(authString);

                //vai separar em uma lista [username, password]
                String[] credentials = authString.split(":");
                String username = credentials[0];
                String password = credentials[1];
                System.out.println("Usuário: " +username);
                System.out.println("Senha: " +password);
                //validar usuario
                //validar senha

                filterChain.doFilter(request, response);


    }

    
}
