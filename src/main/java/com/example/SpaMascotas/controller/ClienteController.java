package com.example.SpaMascotas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.SpaMascotas.model.Cliente;
import com.example.SpaMascotas.repository.ClienteRepository;

/**
 * Controlador de Clientes.
 * GET /clientes → lista todos los clientes
 * GET /clientes/nuevo → formulario de alta
 * POST /clientes/nuevo → guarda cliente
 */
@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepo;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clientes", clienteRepo.findAll());
        return "clientes/lista";
    }

    @GetMapping("/nuevo")
    public String nuevoForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/form";
    }

    @PostMapping("/nuevo")
    public String guardar(@ModelAttribute Cliente cliente) {
        clienteRepo.save(cliente);
        return "redirect:/clientes";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        clienteRepo.deleteById(id);
        return "redirect:/clientes";
    }
}
