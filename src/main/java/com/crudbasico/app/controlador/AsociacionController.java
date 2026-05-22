package com.crudbasico.app.controlador;

import com.crudbasico.app.model.Asociacion;
import com.crudbasico.app.repository.AsociacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/asociacion")
public class AsociacionController {

    @Autowired
    private AsociacionRepository asociacionRepository;

    // Página 1: Formulario para crear asociación
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("asociacion", new Asociacion());
        model.addAttribute("titulo", "Nueva Asociación");
        model.addAttribute("contenido", "asociacion/index");
        return "layout";
    }

    // Guardar asociación
    @PostMapping("/guardar")
    public String guardarAsociacion(@ModelAttribute Asociacion asociacion) {
        asociacionRepository.save(asociacion);
        return "redirect:/asociacion/listar";
    }

    // Página 2: Listar todas las asociaciones
    @GetMapping("/listar")
    public String listarAsociaciones(Model model) {
        model.addAttribute("asociaciones", asociacionRepository.findAll());
        model.addAttribute("titulo", "Lista de Asociaciones");
        model.addAttribute("contenido", "asociacion/listar");
        return "layout";
    }

    // Eliminar asociación
    @PostMapping("/eliminar/{id}")
    public String eliminarAsociacion(@PathVariable Long id) {
        asociacionRepository.deleteById(id);
        return "redirect:/asociacion/listar";
    }

    // Editar asociación
    @GetMapping("/editar/{id}")
    public String editarAsociacion(@PathVariable Long id, Model model) {
        Asociacion asociacion = asociacionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Asociación no encontrada: " + id));
        model.addAttribute("asociacion", asociacion);
        model.addAttribute("titulo", "Editar Asociación");
        model.addAttribute("contenido", "asociacion/index");
        return "layout";
    }
}