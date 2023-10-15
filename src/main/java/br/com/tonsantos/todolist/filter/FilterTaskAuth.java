package br.com.tonsantos.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.tonsantos.todolist.user.IUserRepository;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component

public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var servletPath = request.getServletPath();

        if (servletPath.startsWith("/tasks/")) {

            // Pegar a autenticação (usuario e senha)
            var authorization = request.getHeader("Authorization");

            // cortar o Basic que existe junto a autorizacao
            var authEncoded = authorization.substring("Basic".length()).trim();

            // decodando o base 64
            byte[] authDecode = Base64.getDecoder().decode(authEncoded);

            var authString = new String(authDecode);

            System.out.println(authString);

            String[] credentials = authString.split(":");

            String userName = credentials[0];
            String password = credentials[1];

            // Validar usuario
            var user = this.userRepository.findByUsername(userName);
            if (user == null) {
                response.sendError(401, "Usuario sem autorizacao");
            } else {
                // validar senha
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());

                if (passwordVerify.verified) {
                    request.setAttribute("idUser", user.getId());
                    // request: tudo que ta vindo da nossa requisicao
                    // response: tudo que esta enviando a nosso usuario

                    // segue viagem

                    filterChain.doFilter(request, response);

                } else {
                    response.sendError(401);
                }
            }

        } else {
            filterChain.doFilter(request, response);

        }

        // TODO Auto-generated method stub
    }

    // // public class FilterTaskAuth implements Filter {//No Filter precisamos
    // converter a requisicao

    // @Override
    // public void doFilter(ServletRequest request, ServletResponse response,
    // FilterChain chain)
    // throws IOException, ServletException {

    // //executar uma acao e entao podemos barrar ou seguir com a requisicao
    // System.out.println("Chegou no filtro");//toda a requisicao passa primeiro
    // pelo filtro depois na controller
    // chain.doFilter(request, response);
    // }
}
