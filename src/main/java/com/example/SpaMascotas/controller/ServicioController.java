package com.example.SpaMascotas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.SpaMascotas.model.Servicio;
import com.example.SpaMascotas.repository.ServicioRepository;

/**
 * Controlador de Servicios.
 * GET /servicios → catálogo de servicios
 * GET /servicios/nuevo → formulario de alta
 * POST /servicios/nuevo → guarda servicio
 */
@Controller
@RequestMapping("/servicios")
public class ServicioController {

    @Autowired
    private ServicioRepository servicioRepo;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("servicios", servicioRepo.findAll());
        return "servicios/lista";
    }

    @GetMapping("/nuevo")
    public String nuevoForm(Model model) {
        model.addAttribute("servicio", new Servicio());
        return "servicios/form";
    }

    @PostMapping("/nuevo")
    public String guardar(@ModelAttribute Servicio servicio) {
        servicioRepo.save(servicio);
        return "redirect:/servicios";
    }

    @GetMapping("/{id}/editar")
    public String editarForm(@PathVariable Long id, Model model) {
        Servicio servicio = servicioRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("servicio", servicio);
        return "servicios/form";
    }

    @PostMapping("/{id}/editar")
    public String actualizar(@PathVariable Long id, @ModelAttribute Servicio servicio) {
        servicio.setId(id);
        servicioRepo.save(servicio);
        return "redirect:/servicios";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        servicioRepo.deleteById(id);
        return "redirect:/servicios";
    }
}
