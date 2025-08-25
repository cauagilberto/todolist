package br.com.cauagilberto.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.cauagilberto.todolist.user.IUserRepo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class filterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepo userRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                
        var servletPath = request.getServletPath();

        if(servletPath.equals("/tasks/")){

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
            var user = this.userRepo.findByUsername(username);
            if(user==null){
                response.sendError(401);
            }else{
                //validar senha
                var verifiedPassword = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if(verifiedPassword.verified){
                    request.setAttribute("idUser", user.getId());
                    filterChain.doFilter(request, response);
                }else{
                    response.sendError(401);
                }
                
            }
        }else{
            filterChain.doFilter(request, response);
        }

    }

    
}
