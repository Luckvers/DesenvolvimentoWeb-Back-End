package com.example.projetoescola.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class ExemploController {
    @Autowired
    @Qualifier("versao")
    private String versaoApi;

    @Autowired
    @Qualifier("nome")
    private String nomeApi;

    @GetMapping("/versao")
    public String getVersaoApi() {
        return versaoApi + " - " + nomeApi;
    }
    

}
