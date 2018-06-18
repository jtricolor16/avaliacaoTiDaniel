package br.com.AvaliacaoTiDaniel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/")
    public String index(){
        return "views/index.html";
    }
	@RequestMapping("/cadastrar")
    public String cadastrar(){
        return "views/index.html";
    }
	@RequestMapping("/home")
    public String home(){
        return "views/index.html";
    }
}
