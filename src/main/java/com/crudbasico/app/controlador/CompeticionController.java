package com.crudbasico.app.controlador;

import com.crudbasico.app.model.Competicion;
import com.crudbasico.app.repository.CompeticionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/competicion")
public class CompeticionController {

    @Autowired
    private CompeticionRepository competicionRepository;

    // Página 1: Formulario para crear competición
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("competicion", new Competicion());
        model.addAttribute("titulo", "Nueva Competición");
        model.addAttribute("contenido", "competicion/index");
        return "layout";
    }

    // Guardar competición
    @PostMapping("/guardar")
    public String guardarCompeticion(@ModelAttribute Competicion competicion) {
        competicionRepository.save(competicion);
        return "redirect:/competicion/listar";
    }

    // Página 2: Listar todas las competiciones
    @GetMapping("/listar")
    public String listarCompeticiones(Model model) {
        model.addAttribute("competiciones", competicionRepository.findAll());
        model.addAttribute("titulo", "Lista de Competiciones");
        model.addAttribute("contenido", "competicion/listar");
        return "layout";
    }

    // Eliminar competición
    @PostMapping("/eliminar/{id}")
    public String eliminarCompeticion(@PathVariable Long id) {
        competicionRepository.deleteById(id);
        return "redirect:/competicion/listar";
    }

    // Editar competición
    @GetMapping("/editar/{id}")
    public String editarCompeticion(@PathVariable Long id, Model model) {
        Competicion competicion = competicionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Competición no encontrada: " + id));
        model.addAttribute("competicion", competicion);
        model.addAttribute("titulo", "Editar Competición");
        model.addAttribute("contenido", "competicion/index");
        return "layout";
    }
}