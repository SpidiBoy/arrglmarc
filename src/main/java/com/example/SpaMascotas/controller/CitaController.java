package com.example.SpaMascotas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.SpaMascotas.model.Cita;
import com.example.SpaMascotas.repository.CitaRepository;
import com.example.SpaMascotas.repository.MascotaRepository;
import com.example.SpaMascotas.repository.ServicioRepository;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controlador de Citas.s
 *
 * GET /citas → lista todas las citas
 * GET /citas/nueva → formulario de alta
 * POST /citas/nueva → guarda nueva cita
 * POST /citas/{id}/cancelar → cancela una cita
 */
@Controller
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaRepository citaRepo;
    @Autowired
    private MascotaRepository mascotaRepo;
    @Autowired
    private ServicioRepository servicioRepo;

    // ── Listar ──────────────────────────────────────────────────────────────
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("citas", citaRepo.findAll());
        return "citas/lista";
    }

    // ── Formulario nueva cita ────────────────────────────────────────────────
    @GetMapping("/nueva")
    public String nuevaForm(Model model) {
        model.addAttribute("cita", new Cita());
        model.addAttribute("mascotas", mascotaRepo.findAll());
        model.addAttribute("servicios", servicioRepo.findAll());
        return "citas/form";
    }

    // ── Guardar nueva cita ───────────────────────────────────────────────────
    @PostMapping("/nueva")
    public String guardar(@ModelAttribute Cita cita) {
        cita.setEstado("PENDIENTE");
        citaRepo.save(cita);
        return "redirect:/citas";
    }

    // ── Cancelar cita ────────────────────────────────────────────────────────
    @PostMapping("/{id}/cancelar")
    public String cancelar(@PathVariable Long id) {
        Cita cita = citaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada: " + id));
        cita.setEstado("CANCELADO");
        citaRepo.save(cita);
        return "redirect:/citas";
    }

    // ── Completar cita ───────────────────────────────────────────────────────
    @PostMapping("/{id}/completar")
    public String completar(@PathVariable Long id) {
        Cita cita = citaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada: " + id));
        cita.setEstado("COMPLETADO");
        citaRepo.save(cita);
        return "redirect:/citas";
    }
}
