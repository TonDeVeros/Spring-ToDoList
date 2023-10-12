package br.com.tonsantos.todolist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

// @Controller//usamos quando queremos retornar paginas e templates, não somente objetos

@RestController//quando queremos realmente uma API
@RequestMapping("/primeiraRota")


public class MinhaPrimeiraController {

    /*
     * GET - Buscar uma informação
     * POST - Adicionar um dado
     * PUT - Alterar um dado/info
     * DELETE - remover um dado
     * PATCH - Alterar somente uma parte do dado
     */

     @GetMapping("/")
    public String primeiraMensagem(){
        return "Hello, World";
    }
}
