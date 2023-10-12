package br.com.tonsantos.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id; 

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Entity(name ="tb_users")
public class UserModel {

    @Id //jakarta.persistence
    @GeneratedValue(generator = "UUID")
    private UUID id;


    // @Column(name = "usuarios") //aqui posso renomear o nome da entidade no banco
    private String username;
    private String name;
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    //getters e setters
    //Como estou usando o lombok nao preciso mais dos getters e setters

    // @Getter extensao do lombok
    // @Setter
    // public void setUsername(String username){
    //     this.username = username;
    // }

    // public String getUsername(){
    //     return username;
    // }

    // public void setName(String name){
    //     this.name = name;

    // }

    //    public String getName(){
    //     return name;
    // }
    
    // public void setPassword(String password){
    //     this.password = password;

    // }

    //    public String getPassword(){
    //     return password;
    // }
}
