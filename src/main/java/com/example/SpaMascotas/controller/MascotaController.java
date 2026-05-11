package com.example.SpaMascotas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.SpaMascotas.model.Mascota;
import com.example.SpaMascotas.repository.ClienteRepository;
import com.example.SpaMascotas.repository.MascotaRepository;

/**
 * Controlador de Mascotas.
 *
 * GET /mascotas → lista todas las mascotas
 * GET /mascotas/nueva → formulario de alta
 * POST /mascotas/nueva → guarda nueva mascota
 * GET /mascotas/{id}/editar → formulario de edición
 * POST /mascotas/{id}/editar → actualiza mascota
 * POST /mascotas/{id}/eliminar → elimina mascota
 */
@Controller
@RequestMapping("/mascotas")
public class MascotaController {

    @Autowired
    private MascotaRepository mascotaRepo;
    @Autowired
    private ClienteRepository clienteRepo;

    // ── Listar ──────────────────────────────────────────────────────────────
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("mascotas", mascotaRepo.findAll());
        return "mascotas/lista";
    }

    // ── Formulario nueva mascota ─────────────────────────────────────────────
    @GetMapping("/nueva")
    public String nuevaForm(Model model) {
        model.addAttribute("mascota", new Mascota());
        model.addAttribute("clientes", clienteRepo.findAll());
        return "mascotas/form";
    }

    // ── Guardar nueva mascota ────────────────────────────────────────────────
    @PostMapping("/nueva")
    public String guardar(@ModelAttribute Mascota mascota) {
        mascota.setEstado("ACTIVO");
        mascotaRepo.save(mascota);
        return "redirect:/mascotas";
    }

    // ── Formulario edición ───────────────────────────────────────────────────
    @GetMapping("/{id}/editar")
    public String editarForm(@PathVariable Long id, Model model) {
        Mascota mascota = mascotaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada: " + id));
        model.addAttribute("mascota", mascota);
        model.addAttribute("clientes", clienteRepo.findAll());
        return "mascotas/form";
    }

    // ── Actualizar ───────────────────────────────────────────────────────────
    @PostMapping("/{id}/editar")
    public String actualizar(@PathVariable Long id, @ModelAttribute Mascota mascota) {
        mascota.setId(id);
        mascotaRepo.save(mascota);
        return "redirect:/mascotas";
    }

    // ── Eliminar ─────────────────────────────────────────────────────────────
    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        mascotaRepo.deleteById(id);
        return "redirect:/mascotas";
    }
}
