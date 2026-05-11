package com.example.SpaMascotas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.example.SpaMascotas.repository.ServicioRepository;

/**
 * Sirve la página pública del catálogo (catalogo.html).
 * No requiere sesión — está excluido del interceptor de autenticación.
 *
 * GET /catalogo → src/main/resources/templates/catalogo.html
 */
@Controller
public class CatalogoController {

    @Autowired
    private ServicioRepository servicioRepo;

    @GetMapping("/catalogo")
    public String catalogo(Model model) {
        model.addAttribute("servicios", servicioRepo.findAll());
        return "catalogo";
    }
}
