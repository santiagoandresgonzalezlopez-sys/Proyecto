package com.crudbasico.app.controlador;

import com.crudbasico.app.model.Entrenador;
import com.crudbasico.app.repository.EntrenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/entrenador")
public class EntrenadorController {

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    // Página 1: Formulario para crear entrenador
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("entrenador", new Entrenador());
        model.addAttribute("titulo", "Nuevo Entrenador");
        model.addAttribute("contenido", "entrenador/index");
        return "layout";
    }

    // Guardar entrenador
    @PostMapping("/guardar")
    public String guardarEntrenador(@ModelAttribute Entrenador entrenador) {
        entrenadorRepository.save(entrenador);
        return "redirect:/entrenador/listar";
    }

    // Página 2: Listar todos los entrenadores
    @GetMapping("/listar")
    public String listarEntrenadores(Model model) {
        model.addAttribute("entrenadores", entrenadorRepository.findAll());
        model.addAttribute("titulo", "Lista de Entrenadores");
        model.addAttribute("contenido", "entrenador/listar");
        return "layout";
    }

    // Eliminar entrenador
    @PostMapping("/eliminar/{id}")
    public String eliminarEntrenador(@PathVariable Long id) {
        entrenadorRepository.deleteById(id);
        return "redirect:/entrenador/listar";
    }

    // Editar entrenador
    @GetMapping("/editar/{id}")
    public String editarEntrenador(@PathVariable Long id, Model model) {
        Entrenador entrenador = entrenadorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Entrenador no encontrado: " + id));
        model.addAttribute("entrenador", entrenador);
        model.addAttribute("titulo", "Editar Entrenador");
        model.addAttribute("contenido", "entrenador/index");
        return "layout";
    }
}