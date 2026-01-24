package com.parkcar.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String dashboard() {
        return "index"; // templates/index.html
    }

    @GetMapping("/veiculos")
    public String veiculos() {
        return "veiculos"; // templates/veiculos.html
    }

    @GetMapping("/estacionamentos")
    public String estacionamentos() {
        return "estacionamentos";
    }

    @GetMapping("/garagens")
    public String garagens() {
        return "garagens";
    }
}
