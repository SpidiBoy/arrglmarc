package com.example.SpaMascotas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.SpaMascotas.repository.CitaRepository;
import com.example.SpaMascotas.repository.ClienteRepository;
import com.example.SpaMascotas.repository.MascotaRepository;
import com.example.SpaMascotas.repository.ServicioRepository;

import java.time.LocalDate;

/**
 * Controlador del dashboard principal.
 * Ruta: GET /
 * Vista: templates/dashboard.html
 */
@Controller
public class DashboardController {

    @Autowired
    private MascotaRepository mascotaRepo;
    @Autowired
    private ClienteRepository clienteRepo;
    @Autowired
    private CitaRepository citaRepo;
    @Autowired
    private ServicioRepository servicioRepo;

    @GetMapping("/")
    public String dashboard(Model model) {
        LocalDate hoy = LocalDate.now();

        // Estadísticas para las tarjetas del dashboard
        model.addAttribute("totalMascotas", mascotaRepo.count());
        model.addAttribute("totalClientes", clienteRepo.count());
        model.addAttribute("citasHoy", citaRepo.countByFechaAndEstadoNot(hoy, "CANCELADO"));
        model.addAttribute("citasPendientes", citaRepo.findByEstado("PENDIENTE").size());

        // Próximas citas del día
        model.addAttribute("citasDelDia", citaRepo.findByFecha(hoy));

        return "dashboard";
    }
}
