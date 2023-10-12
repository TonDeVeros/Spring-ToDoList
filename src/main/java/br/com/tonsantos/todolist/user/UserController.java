package br.com.tonsantos.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")

public class UserController {

    @Autowired// faz com que o spring inicialize a interface automaticamente
    private IUserRepository userRepository;

    @PostMapping("/")
    public void Create(@RequestBody UserModel userModel){
        System.out.println(userModel.getName());
    } 
}
